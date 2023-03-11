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

public class IntPref {

	public final String key;
	public final int defaultValue;

	public IntPref(String key, int defaultValue) {
		this.key = key;
		this.defaultValue = defaultValue;
	}

	public int get(Preferences preferences) {
		return preferences == null ? defaultValue : preferences.getInt(key, defaultValue);
	}

	public void put(Preferences preferences, int value) {
		if (preferences != null) {
			if (value != defaultValue) {
				preferences.putInt(key, value);
			} else {
				preferences.remove(key);
			}
		}
	}

}
