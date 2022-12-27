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
package org.glasspath.common.list;

import java.util.List;

public class ListUtils {

	private ListUtils() {

	}

	public static <T> void limit(int size, List<T> src, List<T> dest) {

		if (src != null && dest != null) {

			if (size > src.size()) {
				dest.addAll(src);
			} else if (size == 1 && src.size() > 0) {
				dest.add(src.get(0));
			} else if (size > 0) {

				double step = (double) src.size() / (double) size;

				for (double i = 0.0; (int) i < src.size() - 1 && dest.size() < size - 1; i += step) {
					dest.add(src.get((int) i));
				}

				if (dest.size() < size) {
					dest.add(src.get(src.size() - 1));
				}

			}

		}

	}

}
