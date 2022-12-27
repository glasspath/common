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

import java.util.prefs.Preferences;

/* 
 * The main purpose if this class is to avoid the usage of 
 * hard coded keys and default values in multiple places.
 * 
 * We don't use generics because we want to be able to do:
 * 
 * Pref myStringPref = new Pref("myStringPref", "some default value");
 * Pref myIntPref = new Pref("myIntPref", 123);
 * 
 */
public class Pref {

	private final String key;
	private boolean defaultBooleanValue = false;
	private int defaultIntValue = 0;
	private long defaultLongValue = 0L;
	private float defaultFloatValue = 0.0F;
	private double defaultDoubleValue = 0.0;
	private byte[] defaultByteArrayValue = new byte[0];
	private String defaultStringValue = ""; //$NON-NLS-1$

	public Pref(String key) {
		this.key = key;
	}

	public Pref(String key, boolean defaultBooleanValue) {
		this.key = key;
		this.defaultBooleanValue = defaultBooleanValue;
	}

	public Pref(String key, int defaultIntValue) {
		this.key = key;
		this.defaultIntValue = defaultIntValue;
	}

	public Pref(String key, long defaultLongValue) {
		this.key = key;
		this.defaultLongValue = defaultLongValue;
	}

	public Pref(String key, float defaultFloatValue) {
		this.key = key;
		this.defaultFloatValue = defaultFloatValue;
	}

	public Pref(String key, double defaultDoubleValue) {
		this.key = key;
		this.defaultDoubleValue = defaultDoubleValue;
	}

	public Pref(String key, byte[] defaultByteArrayValue) {
		this.key = key;
		this.defaultByteArrayValue = defaultByteArrayValue;
	}

	public Pref(String key, String defaultStringValue) {
		this.key = key;
		this.defaultStringValue = defaultStringValue;
	}

	public String getKey() {
		return key;
	}

	public boolean getDefaultBooleanValue() {
		return defaultBooleanValue;
	}

	public void setDefaultBooleanValue(boolean defaultBooleanValue) {
		this.defaultBooleanValue = defaultBooleanValue;
	}

	public int getDefaultIntValue() {
		return defaultIntValue;
	}

	public void setDefaultIntValue(int defaultIntValue) {
		this.defaultIntValue = defaultIntValue;
	}

	public long getDefaultLongValue() {
		return defaultLongValue;
	}

	public void setDefaultLongValue(long defaultLongValue) {
		this.defaultLongValue = defaultLongValue;
	}

	public float getDefaultFloatValue() {
		return defaultFloatValue;
	}

	public void setDefaultFloatValue(float defaultFloatValue) {
		this.defaultFloatValue = defaultFloatValue;
	}

	public double getDefaultDoubleValue() {
		return defaultDoubleValue;
	}

	public void setDefaultDoubleValue(double defaultDoubleValue) {
		this.defaultDoubleValue = defaultDoubleValue;
	}

	public byte[] getDefaultByteArrayValue() {
		return defaultByteArrayValue;
	}

	public void setDefaultByteArrayValue(byte[] defaultByteArrayValue) {
		this.defaultByteArrayValue = defaultByteArrayValue;
	}

	public String getDefaultStringValue() {
		return defaultStringValue;
	}

	public void setDefaultStringValue(String defaultStringValue) {
		this.defaultStringValue = defaultStringValue;
	}

	public boolean getBoolean(Preferences preferences) {
		return preferences == null ? defaultBooleanValue : preferences.getBoolean(key, getDefaultBooleanValue());
	}

	public void putBoolean(Preferences preferences, boolean value) {
		if (preferences != null) {
			if (value != defaultBooleanValue) {
				preferences.putBoolean(key, value);
			} else {
				preferences.remove(key);
			}
		}
	}

	public int getInt(Preferences preferences) {
		return preferences == null ? defaultIntValue : preferences.getInt(key, getDefaultIntValue());
	}

	public void putInt(Preferences preferences, int value) {
		if (preferences != null) {
			if (value != defaultIntValue) {
				preferences.putInt(key, value);
			} else {
				preferences.remove(key);
			}
		}
	}

	public long getLong(Preferences preferences) {
		return preferences == null ? defaultLongValue : preferences.getLong(key, getDefaultLongValue());
	}

	public void putLong(Preferences preferences, long value) {
		if (preferences != null) {
			if (value != defaultLongValue) {
				preferences.putLong(key, value);
			} else {
				preferences.remove(key);
			}
		}
	}

	public float getFloat(Preferences preferences) {
		return preferences == null ? defaultFloatValue : preferences.getFloat(key, getDefaultFloatValue());
	}

	public void putFloat(Preferences preferences, float value) {
		if (preferences != null) {
			if (value != defaultFloatValue) {
				preferences.putFloat(key, value);
			} else {
				preferences.remove(key);
			}
		}
	}

	public double getDouble(Preferences preferences) {
		return preferences == null ? defaultDoubleValue : preferences.getDouble(key, getDefaultDoubleValue());
	}

	public void putDouble(Preferences preferences, double value) {
		if (preferences != null) {
			if (value != defaultDoubleValue) {
				preferences.putDouble(key, value);
			} else {
				preferences.remove(key);
			}
		}
	}

	public byte[] getByteArray(Preferences preferences) {
		return preferences == null ? defaultByteArrayValue : preferences.getByteArray(key, getDefaultByteArrayValue());
	}

	public void putByteArray(Preferences preferences, byte[] value) {
		if (preferences != null) {
			if (value != defaultByteArrayValue) {
				preferences.putByteArray(key, value);
			} else {
				preferences.remove(key);
			}
		}
	}

	public String get(Preferences preferences) {
		return preferences == null ? defaultStringValue : preferences.get(key, getDefaultStringValue());
	}

	public void putDouble(Preferences preferences, String value) {
		if (preferences != null) {
			if (value != defaultStringValue) {
				preferences.put(key, value);
			} else {
				preferences.remove(key);
			}
		}
	}

}
