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
package org.glasspath.common.date;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@SuppressWarnings("nls")
public class DateUtils {

	public static final TimeZone TIME_ZONE = TimeZone.getTimeZone("GMT");

	public static final long HOUR_IN_MILLIS = 3600000L;
	public static final long HALF_HOUR_IN_MILLIS = 1800000L;
	public static final long FOURTEEN_DAYS_IN_MILLIS = 1209600000L;
	public static final long THIRTY_DAYS_IN_MILLIS = 2419200000L;
	public static final int DAY_IN_MINUTES = 1440;

	private static final long DAY_IN_MILLIS = 86400000L; // private because a day can be 1 hour shorter or longer

	// TODO: Switch to java.time
	private static final Calendar localCalendar = Calendar.getInstance();
	private static final Calendar calendar1 = Calendar.getInstance(TIME_ZONE);
	private static final Calendar calendar2 = Calendar.getInstance(TIME_ZONE);

	// TODO: Make this configurable in the preferences dialog?
	static {

		localCalendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar1.setFirstDayOfWeek(Calendar.MONDAY);
		calendar2.setFirstDayOfWeek(Calendar.MONDAY);

		// ISO 8601
		localCalendar.setMinimalDaysInFirstWeek(4);
		calendar1.setMinimalDaysInFirstWeek(4);
		calendar1.setMinimalDaysInFirstWeek(4);

	}

	public static long getLocalTimeInGmtTime() {
		localCalendar.setTime(new Date());
		copyLocalCalendarToCalendar1();
		return calendar1.getTimeInMillis();
	}

	public static long getLocalTimeInGmtTime(long millis) {
		localCalendar.setTimeInMillis(millis);
		copyLocalCalendarToCalendar1();
		return calendar1.getTimeInMillis();
	}

	/*
	public static void printLocalCalendar(long millis) {
		System.out.println(getLocalCalendarString(millis));
	}
	
	public static String getLocalCalendarString(long millis) {
		localCalendar.setTimeInMillis(millis);
		return localCalendar.get(Calendar.YEAR) + "-" + localCalendar.get(Calendar.MONTH) + "-" + localCalendar.get(Calendar.DAY_OF_MONTH) + " " + localCalendar.get(Calendar.HOUR_OF_DAY) + ":" + localCalendar.get(Calendar.MINUTE) + ":" + localCalendar.get(Calendar.SECOND);
	}
	
	public static void printGmtCalendar(long millis) {
		System.out.println(getGmtCalendarString(millis));
	}
	
	public static String getGmtCalendarString(long millis) {
		calendar1.setTimeInMillis(millis);
		return calendar1.get(Calendar.YEAR) + "-" + calendar1.get(Calendar.MONTH) + "-" + calendar1.get(Calendar.DAY_OF_MONTH) + " " + calendar1.get(Calendar.HOUR_OF_DAY) + ":" + calendar1.get(Calendar.MINUTE) + ":" + calendar1.get(Calendar.SECOND);
	}
	*/

	private static void copyLocalCalendarToCalendar1() {
		calendar1.set(Calendar.YEAR, localCalendar.get(Calendar.YEAR));
		calendar1.set(Calendar.MONTH, localCalendar.get(Calendar.MONTH));
		calendar1.set(Calendar.DATE, localCalendar.get(Calendar.DATE));
		calendar1.set(Calendar.HOUR_OF_DAY, localCalendar.get(Calendar.HOUR_OF_DAY));
		calendar1.set(Calendar.MINUTE, localCalendar.get(Calendar.MINUTE));
		calendar1.set(Calendar.SECOND, localCalendar.get(Calendar.SECOND));
		calendar1.set(Calendar.MILLISECOND, localCalendar.get(Calendar.MILLISECOND));
	}

	public static boolean isTodayLocalTime(long date) {
		getLocalTimeInGmtTime();
		calendar2.setTimeInMillis(date);
		return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) && calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR);
	}

	public static boolean isYesterdayLocalTime(long date) {
		getLocalTimeInGmtTime();
		calendar1.add(Calendar.DATE, -1);
		calendar2.setTimeInMillis(date);
		return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) && calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR);
	}

	public static int getYearNumber() {
		getLocalTimeInGmtTime();
		return calendar1.get(Calendar.YEAR) % 1000;
	}

	private static void resetCalendar1ToMidnight() {
		calendar1.set(Calendar.HOUR_OF_DAY, 0);
		// calendar1.set(Calendar.HOUR, 0);
		calendar1.set(Calendar.MINUTE, 0);
		calendar1.set(Calendar.SECOND, 0);
		calendar1.set(Calendar.MILLISECOND, 0);
	}

	/*
	private static void resetCalendar2ToMidnight() {
		calendar2.set(Calendar.HOUR_OF_DAY, 0);
		calendar2.set(Calendar.MINUTE, 0);
		calendar2.set(Calendar.SECOND, 0);
		calendar2.set(Calendar.MILLISECOND, 0);
	}
	 */

	private static void setCalendersToSameTime() {
		calendar1.set(Calendar.HOUR_OF_DAY, calendar2.get(Calendar.HOUR_OF_DAY));
		// calendar1.set(Calendar.HOUR, calendar2.get(Calendar.HOUR));
		calendar1.set(Calendar.MINUTE, calendar2.get(Calendar.MINUTE));
		calendar1.set(Calendar.SECOND, calendar2.get(Calendar.SECOND));
		calendar1.set(Calendar.MILLISECOND, calendar2.get(Calendar.MILLISECOND));
	}

	public static Time getTimeFromDate(long date) {
		calendar1.setTimeInMillis(date);
		return new Time(calendar1.get(Calendar.HOUR_OF_DAY), calendar1.get(Calendar.MINUTE));
	}

	public static boolean isSameDay(long firstDate, long secondDate) {
		calendar1.setTimeInMillis(firstDate);
		calendar2.setTimeInMillis(secondDate);
		return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) && calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR);
	}

	public static boolean isSameWeek(long firstDate, long secondDate) {
		return getFirstDayOfWeekForDate(firstDate) == getFirstDayOfWeekForDate(secondDate);
	}

	public static boolean isSameMonth(long firstDate, long secondDate) {
		calendar1.setTimeInMillis(firstDate);
		calendar2.setTimeInMillis(secondDate);
		return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH);
	}

	public static boolean isSameQuarter(long firstDate, long secondDate) {
		calendar1.setTimeInMillis(firstDate);
		calendar2.setTimeInMillis(secondDate);
		return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) && getQuarter(calendar1) == getQuarter(calendar2);
	}

	public static int getQuarter(long date) {
		calendar1.setTimeInMillis(date);
		return getQuarter(calendar1);
	}

	private static int getQuarter(Calendar calendar) {

		int month = calendar.get(Calendar.MONTH) + 1;

		switch (month) {

		case 1:
		case 2:
		case 3:
			return 1;

		case 4:
		case 5:
		case 6:
			return 2;

		case 7:
		case 8:
		case 9:
			return 3;

		case 10:
		case 11:
		case 12:
			return 4;

		default:
			return 0;

		}

	}

	public static boolean isSameYear(long firstDate, long secondDate) {
		calendar1.setTimeInMillis(firstDate);
		calendar2.setTimeInMillis(secondDate);
		return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR);
	}

	public static Date getDayAfter(Date date) {
		calendar1.setTime(date);
		calendar1.add(Calendar.DATE, 1);
		return calendar1.getTime();
	}

	public static long getDayAfterInMillis(long date) {
		calendar1.setTimeInMillis(date);
		calendar1.add(Calendar.DATE, 1);
		return calendar1.getTimeInMillis();
	}

	public static Date getDayBefore(Date date) {
		calendar1.setTime(date);
		calendar1.add(Calendar.DATE, -1);
		return calendar1.getTime();
	}

	public static long getDayBeforeInMillis(long date) {
		calendar1.setTimeInMillis(date);
		calendar1.add(Calendar.DATE, -1);
		return calendar1.getTimeInMillis();
	}

	public static long shiftByDays(long date, int days) {
		calendar1.setTimeInMillis(date);
		calendar1.add(Calendar.DATE, days);
		return calendar1.getTimeInMillis();
	}

	public static long getWeekEarlierInMillis(long date) {
		calendar1.setTimeInMillis(date);
		calendar1.add(Calendar.DATE, -7);
		return calendar1.getTimeInMillis();
	}

	public static long getWeekLaterInMillis(long date) {
		calendar1.setTimeInMillis(date);
		calendar1.add(Calendar.DATE, 7);
		return calendar1.getTimeInMillis();
	}

	public static long getMonthBeforeInMillis(long date) {
		calendar1.setTimeInMillis(date);
		resetCalendar1ToMidnight();
		calendar1.add(Calendar.MONTH, -1);
		return calendar1.getTimeInMillis();
	}

	public static long getMonthAfterInMillis(long date) {
		calendar1.setTimeInMillis(date);
		resetCalendar1ToMidnight();
		calendar1.add(Calendar.MONTH, 1);
		return calendar1.getTimeInMillis();
	}

	public static long getQuarterBeforeInMillis(long date) {
		calendar1.setTimeInMillis(date);
		resetCalendar1ToMidnight();
		calendar1.add(Calendar.MONTH, -3);
		return calendar1.getTimeInMillis();
	}

	public static long getQuarterAfterInMillis(long date) {
		calendar1.setTimeInMillis(date);
		resetCalendar1ToMidnight();
		calendar1.add(Calendar.MONTH, 3);
		return calendar1.getTimeInMillis();
	}

	public static long getYearBeforeInMillis(long date) {
		calendar1.setTimeInMillis(date);
		resetCalendar1ToMidnight();
		calendar1.add(Calendar.YEAR, -1);
		return calendar1.getTimeInMillis();
	}

	public static long getYearAfterInMillis(long date) {
		calendar1.setTimeInMillis(date);
		resetCalendar1ToMidnight();
		calendar1.add(Calendar.YEAR, 1);
		return calendar1.getTimeInMillis();
	}

	public static int getPeriodInDays(long from, long to) {
		return Math.abs(getDifferenceInDays(from, to)) + 1;
	}

	// private static long differenceInMillis;
	public static int getDifferenceInDays(long from, long to) {

		calendar1.setTimeInMillis(from);
		calendar2.setTimeInMillis(to);
		setCalendersToSameTime();
		return (int) ((calendar2.getTimeInMillis() - calendar1.getTimeInMillis()) / DAY_IN_MILLIS);

		/*
		calendar1.setTimeInMillis(from);
		resetCalendar1ToMidnight();
		calendar2.setTimeInMillis(to);
		resetCalendar2ToMidnight();
		
		differenceInMillis = calendar2.getTimeInMillis() - calendar1.getTimeInMillis();
		if (differenceInMillis>=0) {
			return (int)((differenceInMillis + HOUR_IN_MILLIS) / DAY_IN_MILLIS);
		} else  {
			return (int)((differenceInMillis - HOUR_IN_MILLIS) / DAY_IN_MILLIS);
		}
		 */

	}

	public static long getFirstDayOfWeekForDate(long date) {
		calendar1.setTimeInMillis(date);
		resetCalendar1ToMidnight();
		calendar1.set(Calendar.DAY_OF_WEEK, calendar1.getFirstDayOfWeek());
		return calendar1.getTimeInMillis();
	}

	public static long getLastDayOfWeekForDate(long date) {
		calendar1.setTimeInMillis(date);
		resetCalendar1ToMidnight();
		calendar1.set(Calendar.DAY_OF_WEEK, calendar1.getFirstDayOfWeek() + 6);
		return calendar1.getTimeInMillis();
	}

	public static long getFirstDayOfMonthForDate(long date) {
		calendar1.setTimeInMillis(date);
		resetCalendar1ToMidnight();
		calendar1.set(Calendar.DAY_OF_MONTH, 1);
		return calendar1.getTimeInMillis();
	}

	public static long getLastDayOfMonthForDate(long date) {
		calendar1.setTimeInMillis(date);
		resetCalendar1ToMidnight();
		calendar1.set(Calendar.DAY_OF_MONTH, 1);
		calendar1.add(Calendar.MONTH, 1);
		calendar1.add(Calendar.DATE, -1);
		return calendar1.getTimeInMillis();
	}

	public static long getFirstDayOfQuarterForDate(long date) {

		calendar1.setTimeInMillis(date);
		resetCalendar1ToMidnight();

		int quarter = getQuarter(calendar1);
		switch (quarter) {

		case 1:
			calendar1.set(Calendar.MONTH, 0);
			break;

		case 2:
			calendar1.set(Calendar.MONTH, 3);
			break;

		case 3:
			calendar1.set(Calendar.MONTH, 6);
			break;

		case 4:
			calendar1.set(Calendar.MONTH, 9);
			break;

		default:
			break;
		}

		calendar1.set(Calendar.DAY_OF_MONTH, 1);

		return calendar1.getTimeInMillis();

	}

	public static long getLastDayOfQuarterForDate(long date) {

		calendar1.setTimeInMillis(date);
		resetCalendar1ToMidnight();

		int quarter = getQuarter(calendar1);
		switch (quarter) {

		case 1:
			calendar1.set(Calendar.MONTH, 0);
			break;

		case 2:
			calendar1.set(Calendar.MONTH, 3);
			break;

		case 3:
			calendar1.set(Calendar.MONTH, 6);
			break;

		case 4:
			calendar1.set(Calendar.MONTH, 9);
			break;

		default:
			break;
		}

		calendar1.set(Calendar.DAY_OF_MONTH, 1);
		calendar1.add(Calendar.MONTH, 3);
		calendar1.add(Calendar.DATE, -1);

		return calendar1.getTimeInMillis();

	}

	public static long getFirstDayOfYearForDate(long date) {
		calendar1.setTimeInMillis(date);
		resetCalendar1ToMidnight();
		calendar1.set(Calendar.MONTH, 0);
		calendar1.set(Calendar.DATE, 1);
		return calendar1.getTimeInMillis();
	}

	public static long getLastDayOfYearForDate(long date) {
		calendar1.setTimeInMillis(date);
		resetCalendar1ToMidnight();
		calendar1.set(Calendar.MONTH, 0);
		calendar1.set(Calendar.DATE, 1);
		calendar1.add(Calendar.YEAR, 1);
		calendar1.add(Calendar.DATE, -1);
		return calendar1.getTimeInMillis();
	}

	public static Date getDateForDayOfWeekForDate(Date date, int dayOfWeek) {
		calendar1.setTime(date);
		resetCalendar1ToMidnight();
		calendar1.set(Calendar.DAY_OF_WEEK, dayOfWeek);
		return calendar1.getTime();
	}

	public static int getWeekNumber(long date) {
		calendar1.setTimeInMillis(date);
		return calendar1.get(Calendar.WEEK_OF_YEAR);
	}

	public static int getYear() {
		localCalendar.setTime(new Date());
		return localCalendar.get(Calendar.YEAR);
	}

	public static int getYear(long date) {
		calendar1.setTimeInMillis(date);
		return calendar1.get(Calendar.YEAR);
	}

	public static long getDateForYear(int year) {
		calendar1.clear();
		calendar1.set(Calendar.YEAR, year);
		resetCalendar1ToMidnight();
		return calendar1.getTimeInMillis();
	}

	public static long getDateForWeekNumber(int weekNumber, int year) {
		calendar1.clear();
		calendar1.set(Calendar.WEEK_OF_YEAR, weekNumber);
		calendar1.set(Calendar.YEAR, year);
		resetCalendar1ToMidnight();
		return calendar1.getTimeInMillis();
	}

	public static long getDateWithTime(long date, Time time) {
		calendar1.setTimeInMillis(date);
		if (time != null) {
			calendar1.set(Calendar.HOUR_OF_DAY, time.getHour());
			calendar1.set(Calendar.MINUTE, time.getMinute());
			calendar1.set(Calendar.SECOND, 0);
		} else {
			calendar1.set(Calendar.HOUR_OF_DAY, 12);
			calendar1.set(Calendar.MINUTE, 0);
			calendar1.set(Calendar.SECOND, 0);
		}
		return calendar1.getTimeInMillis();
	}

	public static long getDateWithTime(long date, int hour, int minute) {
		calendar1.setTimeInMillis(date);
		calendar1.set(Calendar.HOUR_OF_DAY, hour);
		calendar1.set(Calendar.MINUTE, minute);
		calendar1.set(Calendar.SECOND, 0);
		calendar1.set(Calendar.MILLISECOND, 0);
		return calendar1.getTimeInMillis();
	}

	public static long getDateWithAdditionalMinutes(long date, int minutes) {
		calendar1.setTimeInMillis(date);
		calendar1.add(Calendar.MINUTE, minutes);
		return calendar1.getTimeInMillis();
	}

	public static int compareDates(long date1, long date2) {
		return (date1 < date2) ? -1 : ((date1 == date2) ? 0 : 1);
	}

	public static String getTimeString(long date) {
		calendar1.setTimeInMillis(date);
		int minute = calendar1.get(Calendar.MINUTE);
		return calendar1.get(Calendar.HOUR_OF_DAY) + ":" + (minute < 10 ? "0" + minute : "" + minute);
	}

	public static float getIntervalInHours(long from, long to) {
		return (float) (to - from) / HOUR_IN_MILLIS;
	}

	public static long getDatePreservingTime(long oldDate, long newDate) {
		calendar1.setTimeInMillis(oldDate);
		calendar2.setTimeInMillis(newDate);
		calendar1.set(Calendar.YEAR, calendar2.get(Calendar.YEAR));
		calendar1.set(Calendar.MONTH, calendar2.get(Calendar.MONTH));
		calendar1.set(Calendar.DATE, calendar2.get(Calendar.DATE));
		return calendar1.getTimeInMillis();
	}

	public static long getDatePreservingTime(long oldDate, int newYear, int newMonth, int newDay) {
		calendar1.setTimeInMillis(oldDate);
		calendar1.set(Calendar.YEAR, newYear);
		calendar1.set(Calendar.MONTH, newMonth);
		calendar1.set(Calendar.DATE, newDay);
		return calendar1.getTimeInMillis();
	}

}
