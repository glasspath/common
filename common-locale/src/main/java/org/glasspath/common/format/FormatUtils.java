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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import org.glasspath.common.date.DateUtils;
import org.glasspath.common.format.resources.Resources;
import org.glasspath.common.locale.LocaleUtils.SystemOfUnits;

@SuppressWarnings("nls")
public class FormatUtils {

	public static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance(Locale.getDefault());
	public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");
	public static final DecimalFormat CURRENCY_FORMAT = new DecimalFormat("0.00");
	public static final DateFormat DATE_FORMAT = new SimpleDateFormat("E d-MMM-yyyy");
	public static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("E d-MMM-yyyy HH:mm");
	public static final DateFormat DATE_FORMAT_YEAR = new SimpleDateFormat("yyyy");
	public static final DateFormat DATE_FORMAT_MONTH = new SimpleDateFormat("MMM-yyyy");
	public static final DateFormat DATE_FORMAT_DAY = new SimpleDateFormat("E");
	public static final DateFormat DATE_FORMAT_DAY_MONTH = new SimpleDateFormat("E d MMM");
	public static final DateFormat DATE_FORMAT_DAY_MONTH_YEAR = new SimpleDateFormat("d-MMM-yyyy");
	public static final DateFormat DATE_FORMAT_YEAR_MONTH_DAY_COMPACT = new SimpleDateFormat("yyyyMMdd");
	public static final DateFormat DATE_FORMAT_HOURS_MINUTES = new SimpleDateFormat("HH:mm");
	public static final DateFormat ANDROID_DATE_FORMAT = new SimpleDateFormat("EEEE d MMMM yyyy");
	static {
		setTimeZone(DateUtils.GMT_TIME_ZONE);
	}

	// TODO: This is probably not the best approach..
	protected static String defaultCurrencySymbol = "€";
	protected static SystemOfUnits defaultSystemOfUnits = SystemOfUnits.METRIC;
	protected static String defaultMileageUnit = "km"; // TODO: Replace by SystemOfUnits.distanceSymbol?

	private FormatUtils() {

	}

	public static void setTimeZone(TimeZone timeZone) {
		DATE_FORMAT.setTimeZone(timeZone);
		DATE_TIME_FORMAT.setTimeZone(timeZone);
		DATE_FORMAT_YEAR.setTimeZone(timeZone);
		DATE_FORMAT_MONTH.setTimeZone(timeZone);
		DATE_FORMAT_DAY.setTimeZone(timeZone);
		DATE_FORMAT_DAY_MONTH.setTimeZone(timeZone);
		DATE_FORMAT_DAY_MONTH_YEAR.setTimeZone(timeZone);
		DATE_FORMAT_YEAR_MONTH_DAY_COMPACT.setTimeZone(timeZone);
		DATE_FORMAT_HOURS_MINUTES.setTimeZone(timeZone);
		ANDROID_DATE_FORMAT.setTimeZone(timeZone);
	}

	public static String getDefaultCurrencySymbol() {
		return defaultCurrencySymbol;
	}

	public static void setDefaultCurrencySymbol(String defaultCurrencySymbol) {
		FormatUtils.defaultCurrencySymbol = defaultCurrencySymbol;
	}

	public static SystemOfUnits getDefaultSystemOfUnits() {
		return defaultSystemOfUnits;
	}

	public static void setDefaultSystemOfUnits(SystemOfUnits defaultSystemOfUnits) {
		FormatUtils.defaultSystemOfUnits = defaultSystemOfUnits;
		if (defaultSystemOfUnits == SystemOfUnits.IMPERIAL) {
			defaultMileageUnit = Resources.getString("mileageUnitMiles");
		} else {
			defaultMileageUnit = Resources.getString("mileageUnitKm");
		}
	}

	public static String getDefaultMileageUnit() {
		return defaultMileageUnit;
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

	public static String millisToMinutesString(long millis) {

		long t = Math.abs(millis);

		if (t < 60000) {
			long s = millis / 1000;
			long ms = millis % 1000;
			if (ms > 500) {
				s++;
			}
			return s + "s";
		} else if (t < 3601000) {
			long m = millis / 60000;
			long s = (millis - (m * 60000)) / 1000;
			long ms = millis % 1000;
			if (ms > 500) {
				s++;
				if (s >= 60) {
					m++;
					s = 0;
				}
			}
			return m + "m " + s + "s";
		} else {
			long h = millis / 3600000;
			long m = (millis - (h * 3600000)) / 60000;
			long s = (millis - ((h * 3600000) + (m * 60000))) / 1000;
			long ms = millis % 1000;
			if (ms > 500) {
				s++;
				if (s >= 60) {
					m++;
					s = 0;
					if (m >= 60) {
						h++;
						m = 0;
					}
				}
			}
			return h + "h " + m + "m " + s + "s";
		}

	}

	public static SimpleDateFormat createSimpleDateFormat(String format) {
		return createSimpleDateFormat(format, null);
	}

	public static SimpleDateFormat createSimpleDateFormat(String format, Locale locale) {
		SimpleDateFormat simpleDateFormat = locale != null ? new SimpleDateFormat(format, locale) : new SimpleDateFormat(format);
		simpleDateFormat.setTimeZone(DateUtils.GMT_TIME_ZONE);
		return simpleDateFormat;
	}

}
