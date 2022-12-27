/*
 * This file is part of Glasspath Common.
 * Copyright (C) 2011 - 2022 Remco Poelstra
 * Authors: Remco Poelstra
 * 
 * This program is offered under a commercial and under the AGPL license.
 * For commercial licensing, contact us at https://glasspath.org. For AGPL licensing, see below.
 * 
 * AGPL licensing:
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package org.glasspath.common.os.preferences;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.prefs.AbstractPreferences;
import java.util.prefs.BackingStoreException;

@SuppressWarnings("nls")
public class BasicFilePreferences extends AbstractPreferences {

	private final File file;
	private final Map<String, String> root;
	private final Map<String, BasicFilePreferences> children;

	private boolean isRemoved = false;

	public BasicFilePreferences(File file) {
		this(file, null, "");
	}

	public BasicFilePreferences(File file, AbstractPreferences parent, String name) {
		super(parent, name);

		this.file = file;

		root = new TreeMap<String, String>();
		children = new TreeMap<String, BasicFilePreferences>();

		try {
			sync();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void putSpi(String key, String value) {

		root.put(key, value);

		try {
			flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected String getSpi(String key) {
		return root.get(key);
	}

	@Override
	protected void removeSpi(String key) {

		root.remove(key);

		try {
			flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void removeNodeSpi() throws BackingStoreException {
		isRemoved = true;
		flush();
	}

	@Override
	protected String[] keysSpi() throws BackingStoreException {
		return root.keySet().toArray(new String[root.keySet().size()]);
	}

	@Override
	protected String[] childrenNamesSpi() throws BackingStoreException {
		return children.keySet().toArray(new String[children.keySet().size()]);
	}

	@Override
	protected BasicFilePreferences childSpi(String name) {
		BasicFilePreferences child = children.get(name);
		if (child == null || child.isRemoved()) {
			child = new BasicFilePreferences(file, this, name);
			children.put(name, child);
		}
		return child;
	}

	@Override
	protected void syncSpi() throws BackingStoreException {

		if (file.exists() && !isRemoved()) {

			synchronized (file) {

				try (FileInputStream inputStream = new FileInputStream(file)) {

					Properties properties = new Properties();
					properties.load(inputStream);

					StringBuilder sb = new StringBuilder();
					getPath(sb);
					String path = sb.toString();

					Enumeration<?> propertyNames = properties.propertyNames();
					while (propertyNames.hasMoreElements()) {

						String propertyName = (String) propertyNames.nextElement();
						if (propertyName.startsWith(path)) {

							String subKey = propertyName.substring(path.length());
							if (subKey.indexOf('.') == -1) {
								root.put(subKey, properties.getProperty(propertyName));
							}

						}
					}

				} catch (Exception e) {
					throw new BackingStoreException(e);
				}

			}

		}

	}

	private void getPath(StringBuilder sb) {
		BasicFilePreferences parent = (BasicFilePreferences) parent();
		if (parent != null) {
			parent.getPath(sb);
			sb.append(name()).append('.');
		}
	}

	@Override
	protected void flushSpi() throws BackingStoreException {

		synchronized (file) {

			Properties properties = new Properties();

			StringBuilder sb = new StringBuilder();
			getPath(sb);
			String path = sb.toString();

			if (file.exists()) {

				try (FileInputStream inputStream = new FileInputStream(file)) {

					properties.load(inputStream);

					List<String> removePropertyNames = new ArrayList<String>();

					Enumeration<?> propertyNames = properties.propertyNames();
					while (propertyNames.hasMoreElements()) {

						String propertyName = (String) propertyNames.nextElement();
						if (propertyName.startsWith(path)) {

							String subKey = propertyName.substring(path.length());
							// Only do immediate descendants
							if (subKey.indexOf('.') == -1) {
								removePropertyNames.add(propertyName);
							}

						}

					}

					for (String propKey : removePropertyNames) {
						properties.remove(propKey);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			if (!isRemoved) {
				for (String s : root.keySet()) {
					properties.setProperty(path + s, root.get(s));
				}
			}

			try (FileOutputStream outputStream = new FileOutputStream(file)) {
				properties.store(outputStream, "");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
