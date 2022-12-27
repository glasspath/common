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
package org.glasspath.common.os;

import java.awt.event.InputEvent;
import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.glasspath.common.Common;

import oshi.PlatformEnum;
import oshi.SystemInfo;

public class OsUtils {

	public static final boolean PLATFORM_WINDOWS;
	public static final boolean PLATFORM_MACOS;
	public static final boolean PLATFORM_LINUX;
	static {
		PLATFORM_WINDOWS = SystemInfo.getCurrentPlatform() == PlatformEnum.WINDOWS;
		PLATFORM_MACOS = SystemInfo.getCurrentPlatform() == PlatformEnum.MACOS;
		PLATFORM_LINUX = SystemInfo.getCurrentPlatform() == PlatformEnum.LINUX;
	}

	public static final int CTRL_OR_CMD_MASK;
	public static final int SHIFT_MASK;
	static {

		// TODO: Using AWT too early causes problems with setting theme..
		// CTRL_OR_CMD_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();
		if (PLATFORM_MACOS) {
			CTRL_OR_CMD_MASK = InputEvent.META_DOWN_MASK;
		} else {
			CTRL_OR_CMD_MASK = InputEvent.CTRL_DOWN_MASK;
		}

		SHIFT_MASK = InputEvent.SHIFT_DOWN_MASK;

	}

	public static final int MAX_NEW_FILE_NAME_COUNT = 1000;

	private OsUtils() {

	}

	public static void configureJna(String libraryPath) {

		String libraryName = "jnidispatch.dll"; //$NON-NLS-1$
		if (PLATFORM_MACOS) {
			libraryName = "libjnidispatch.jnilib"; //$NON-NLS-1$
		} else if (PLATFORM_LINUX) {
			libraryName = "libjnidispatch.so"; //$NON-NLS-1$
		}

		File libraryFile = new File(libraryPath, libraryName);
		if (libraryFile.exists()) {
			configureJna(true, true, libraryPath);
		} else {
			Common.LOGGER.error("Skipping JNA configuration, library not found: " + libraryPath); //$NON-NLS-1$
		}

	}

	public static void configureJna(boolean noSys, boolean noUnpack, String libraryPath) {

		System.setProperty("jna.nosys", "" + noSys); //$NON-NLS-1$ //$NON-NLS-2$

		System.setProperty("jna.nounpack", "" + noUnpack); //$NON-NLS-1$ //$NON-NLS-2$

		System.setProperty("jna.boot.library.path", libraryPath); //$NON-NLS-1$
		Common.LOGGER.info("JNA library path set to: " + libraryPath); //$NON-NLS-1$

	}

	public static File getApplicationJarFile(Class<?> applicationClass) {

		try {

			URL applicationJarURL = applicationClass.getProtectionDomain().getCodeSource().getLocation();
			if (applicationJarURL != null) {

				File applicationJarFile = Paths.get(applicationJarURL.toURI()).toFile();
				if (applicationJarFile.exists()) {
					return applicationJarFile;
				}

			}

		} catch (Exception e) {
			Common.LOGGER.error("Exception while getting application jar file: ", e); //$NON-NLS-1$
		}

		return null;

	}

	public static File getBundledFile(Class<?> applicationClass, String fileName) {

		File bundledFile = null;

		File applicationJarFile = getApplicationJarFile(applicationClass);
		if (applicationJarFile != null) {

			File applicationDir = applicationJarFile.getParentFile();
			if (applicationDir != null && applicationDir.exists() && applicationDir.isDirectory()) {

				try {
					bundledFile = new File(applicationDir, fileName);
				} catch (Exception e) {
					Common.LOGGER.error("Exception while getting bundled file in application dir: ", e); //$NON-NLS-1$
				}

			}

		}

		if (bundledFile == null) {
			try {
				bundledFile = new File(fileName);
			} catch (Exception e) {
				Common.LOGGER.error("Exception while getting bundled file in working dir: ", e); //$NON-NLS-1$
			}
		}

		return bundledFile;

	}

	public static FileFilter createExtensionsFileFilter(List<String> extensions) {

		return new FileFilter() {

			@Override
			public boolean accept(File file) {

				if (file != null) {

					String fileName = file.getName();
					if (fileName != null) {

						fileName = fileName.toLowerCase();
						for (String extension : extensions) {

							// If extension is an empty string we will accept files without extension
							if (extension.length() == 0) {
								if (!fileName.contains(".")) { //$NON-NLS-1$
									return true;
								}
							} else if (fileName.endsWith(extension.toLowerCase())) { // Else check the extension
								return true;
							}

						}

					}

				}

				return false;

			}
		};

	}

	public static boolean deleteFile(File file) {

		try {
			Files.delete(file.toPath());
			return !file.exists();
		} catch (Exception e) {
			Common.LOGGER.error("Exception while deleting file: ", e); //$NON-NLS-1$
		}

		return false;

	}

	public static File copyFile(File file) {
		return copyFile(file, getNextAvailableFile(file, null));
	}

	public static File copyFile(File file, File targetFile) {

		if (targetFile != null && !targetFile.exists()) {

			try {

				Files.copy(file.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

				if (targetFile.exists()) {
					return targetFile;
				} else {
					return null;
				}

			} catch (Exception e) {
				Common.LOGGER.error("Exception while copying file: ", e); //$NON-NLS-1$
			}

		}

		return null;

	}

	public static File getNextAvailableFile(File file, File newParentDir) {

		File nextAvailableFile = getFileWithNewName(file, getNextAvailableFileName(file, newParentDir), newParentDir, true);
		if (nextAvailableFile != null && !nextAvailableFile.exists()) {
			return nextAvailableFile;
		}

		return null;

	}

	public static String getNextAvailableFileName(File file, File newParentDir) {

		String fileName = file.getName();
		String extension = ""; //$NON-NLS-1$

		int lastIndexOfDot = fileName.lastIndexOf("."); //$NON-NLS-1$
		if (lastIndexOfDot > 0) {
			extension = fileName.substring(lastIndexOfDot);
			fileName = fileName.substring(0, lastIndexOfDot);
		}

		try {

			File parent = file.getParentFile();
			if (newParentDir != null) {
				parent = newParentDir;
			}

			int count = 1;
			while (count < MAX_NEW_FILE_NAME_COUNT) {

				String newFileName = fileName + " (copy " + count + ")";
				File nextAvailableFile = new File(parent, newFileName + extension);
				if (!nextAvailableFile.exists()) {
					return newFileName;
				}

				count++;

			}

		} catch (Exception e) {
			Common.LOGGER.error("Exception while searching next available file name: ", e); //$NON-NLS-1$
		}

		return null;

	}

	public static String getFileNameWithoutExtension(File file) {

		String fileName = file.getName();

		int lastIndexOfDot = fileName.lastIndexOf("."); //$NON-NLS-1$
		if (lastIndexOfDot > 0) {
			fileName = fileName.substring(0, lastIndexOfDot);
		}

		return fileName;

	}

	public static File getFileWithOtherExtension(File file, String extension) {

		if (file != null && extension != null) {

			String fileName = file.getName();

			int lastIndexOfDot = fileName.lastIndexOf("."); //$NON-NLS-1$
			if (lastIndexOfDot > 0) {
				fileName = fileName.substring(0, lastIndexOfDot);
			}

			extension = extension.trim();

			try {
				return new File(file.getParent(), fileName + (extension.length() == 0 ? "" : "." + extension)); //$NON-NLS-1$ //$NON-NLS-2$
			} catch (Exception e) {
				Common.LOGGER.error("Exception while getting file with other extension: ", e); //$NON-NLS-1$
			}

		}

		return null;

	}

	public static String getValidFileName(String fileName) {

		if (fileName != null && fileName.length() > 0) {

			String validFileName = fileName.replaceAll("[\\\\/:*?\"<>|]", ""); //$NON-NLS-1$ //$NON-NLS-2$

			if (validFileName.trim().length() > 0) {
				return validFileName;
			}

		}

		return null;

	}

	public static File getFileWithNewName(File file, String newFileName, File newParentDir, boolean keepOriginalExtension) {

		if (newFileName != null && newFileName.length() > 0) {

			String validFileName = getValidFileName(newFileName);
			if (validFileName != null && newFileName.equals(validFileName)) {

				String fileName = file.getName();
				String extension = ""; //$NON-NLS-1$

				if (keepOriginalExtension) {
					int lastIndexOfDot = fileName.lastIndexOf("."); //$NON-NLS-1$
					if (lastIndexOfDot > 0) {
						extension = fileName.substring(lastIndexOfDot);
						fileName = fileName.substring(0, lastIndexOfDot);
					}
				}

				try {

					File parent = file.getParentFile();
					if (newParentDir != null) {
						parent = newParentDir;
					}

					return new File(parent, newFileName + extension);

				} catch (Exception e) {
					Common.LOGGER.error("Exception while constructing file with new name: ", e); //$NON-NLS-1$
				}

			}

		}

		return null;

	}

	public static boolean isNewFileNameAllowed(File file, String newFileName, File newParentDir, boolean keepOriginalExtension) {
		File fileWithNewName = getFileWithNewName(file, newFileName, newParentDir, keepOriginalExtension);
		return fileWithNewName != null && !fileWithNewName.exists();
	}

	public static File renameFile(File file, File targetFile) {

		if (targetFile != null && !targetFile.exists()) {

			try {

				boolean result = file.renameTo(targetFile);
				if (result && targetFile.exists()) {
					return targetFile;
				}

			} catch (Exception e) {
				Common.LOGGER.error("Exception while renaming file: ", e); //$NON-NLS-1$
			}

		}

		return null;

	}

}
