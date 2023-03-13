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
package org.glasspath.common.format;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.glasspath.common.date.DateUtils;
import org.glasspath.common.format.resources.Resources;

@SuppressWarnings("nls")
public class FormatUtils extends CoreFormatUtils {

	protected FormatUtils() {
		super();
	}

	public static float parseFloat(String source) throws ParseException {
		return NUMBER_FORMAT.parse(source).floatValue();
	}

	public static String secondsToString(long seconds) {
		if (seconds > 3600) {
			return DECIMAL_FORMAT.format(seconds / 3600.0) + " " + Resources.getString("hour");
		} else if (seconds > 60) {
			return DECIMAL_FORMAT.format(seconds / 60.0) + " " + Resources.getString("minutes");
		} else {
			return DECIMAL_FORMAT.format(seconds) + " " + Resources.getString("seconds");
		}
	}

	public static SimpleDateFormat createSimpleDateFormat(String format) {
		return createSimpleDateFormat(format, null);
	}

	public static SimpleDateFormat createSimpleDateFormat(String format, Locale locale) {
		SimpleDateFormat simpleDateFormat = locale != null ? new SimpleDateFormat(format, locale) : new SimpleDateFormat(format);
		simpleDateFormat.setTimeZone(DateUtils.TIME_ZONE);
		return simpleDateFormat;
	}

	public static void setMileageType(MileageType mileageType) {

		MILEAGE_TYPE = mileageType;

		if (MILEAGE_TYPE == MileageType.KM) {
			MILEAGE_UNIT_LOWER_CASE = Resources.getString("mileageUnitKm");
		} else if (MILEAGE_TYPE == MileageType.MILES) {
			MILEAGE_UNIT_LOWER_CASE = Resources.getString("mileageUnitMiles");
		}

	}

}
