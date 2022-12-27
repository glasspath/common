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

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class PreferencesProvider {

	private Preferences preferences = null;
	private boolean updatingPreferences = false;
	private boolean enabled = true;
	private final List<PreferencesProviderListener> listeners = new ArrayList<>();

	public PreferencesProvider(Preferences preferences) {
		this.preferences = preferences;
	}

	public Preferences getPreferences() {
		return preferences;
	}

	public void setPreferences(Preferences preferences) {

		updatingPreferences = true;

		this.preferences = preferences;
		firePreferencesChanged(preferences);

		updatingPreferences = false;

	}

	public boolean isUpdatingPreferences() {
		return updatingPreferences;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		firePreferencesStateChanged(enabled);
	}

	public void clearPreferences() {

		if (preferences != null) {

			try {
				for (String key : preferences.keys()) {
					preferences.remove(key);
				}
			} catch (Exception e) {
				e.printStackTrace(); // TODO
			}

			firePreferencesChanged(preferences);

		}

	}

	public void addListener(PreferencesProviderListener listener) {
		listeners.add(listener);
	}

	public void removeListener(PreferencesProviderListener listener) {
		listeners.add(listener);
	}

	protected void firePreferencesChanged(Preferences preferences) {
		for (PreferencesProviderListener listener : listeners) {
			listener.preferencesChanged(preferences);
		}
	}

	protected void firePreferencesStateChanged(boolean enabled) {
		for (PreferencesProviderListener listener : listeners) {
			listener.preferencesStateChanged(enabled);
		}
	}

	public static interface PreferencesProviderListener {

		public void preferencesChanged(Preferences preferences);

		public void preferencesStateChanged(boolean enabled);

	}

}
