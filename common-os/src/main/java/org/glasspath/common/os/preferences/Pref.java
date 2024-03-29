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

	public final String key;
	public final String defaultValue;

	public Pref(String key, String defaultValue) {
		this.key = key;
		this.defaultValue = defaultValue;
	}

	public String get(Preferences preferences) {
		return preferences == null ? defaultValue : preferences.get(key, defaultValue);
	}

	public void put(Preferences preferences, String value) {
		if (preferences != null) {
			if (value != defaultValue) {
				preferences.put(key, value);
			} else {
				preferences.remove(key);
			}
		}
	}

}
