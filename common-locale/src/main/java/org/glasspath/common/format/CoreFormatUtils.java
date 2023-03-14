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

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.glasspath.common.date.DateUtils;

@SuppressWarnings("nls")
public class CoreFormatUtils {

	@Deprecated // TODO: Replace by LocaleUtils.SystemOfUnits
	public static enum MileageType {
		KM, MILES
	}

	// TODO: This is probably not the best approach..
	public static String CURRENCY_SYMBOL = "€";
	@Deprecated // TODO: Replace by LocaleUtils.SystemOfUnits
	public static MileageType MILEAGE_TYPE = MileageType.KM;
	@Deprecated // TODO: Replace by LocaleUtils.SystemOfUnits
	public static String MILEAGE_UNIT_LOWER_CASE = "km";

	public static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance(Locale.getDefault());

	public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

	public static final DecimalFormat CURRENCY_FORMAT = new DecimalFormat("0.00");

	public static final DateFormat DATE_FORMAT = new SimpleDateFormat("E d-MMM-yyyy");
	public static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("E d-MMM-yyyy HH:mm");
	public static final DateFormat DATE_FORMAT_YEAR = new SimpleDateFormat("yyyy");
	public static final DateFormat DATE_FORMAT_MONTH = new SimpleDateFormat("MMM-yyyy");
	public static final DateFormat DATE_FORMAT_DAY = new SimpleDateFormat("E");
	public static final DateFormat ANDROID_DATE_FORMAT = new SimpleDateFormat("EEEE d MMMM yyyy");
	static {
		DATE_FORMAT.setTimeZone(DateUtils.TIME_ZONE);
		DATE_TIME_FORMAT.setTimeZone(DateUtils.TIME_ZONE);
		DATE_FORMAT_YEAR.setTimeZone(DateUtils.TIME_ZONE);
		DATE_FORMAT_MONTH.setTimeZone(DateUtils.TIME_ZONE);
		DATE_FORMAT_DAY.setTimeZone(DateUtils.TIME_ZONE);
		ANDROID_DATE_FORMAT.setTimeZone(DateUtils.TIME_ZONE);
	}

	protected CoreFormatUtils() {

	}

}
