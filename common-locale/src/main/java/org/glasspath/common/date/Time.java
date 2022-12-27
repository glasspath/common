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

import java.util.ArrayList;
import java.util.Calendar;

public class Time {

	private int hour = 12;
	private int minute = 0;

	public Time() {

	}

	public Time(int hour, int minute) {
		this.hour = hour;
		this.minute = minute;
	}

	public Time(int minutes) {
		setTime(minutes);
	}

	public Time(Time time) {
		if (time != null) {
			this.hour = time.hour;
			this.minute = time.minute;
		}
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		if (hour >= 24 || hour < 0) {
			hour = 0;
		}
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		if (minute >= 60 || hour < 0) {
			minute = 0;
		}
		this.minute = minute;
	}

	public void setTime(Time time) {
		setHour(time.getHour());
		setMinute(time.getMinute());
	}

	public void setTime(int minutes) {

		if (minutes > DateUtils.DAY_IN_MINUTES) {
			minutes -= DateUtils.DAY_IN_MINUTES;
		} else if (minutes < 0) {
			minutes += DateUtils.DAY_IN_MINUTES;
		}

		setTime(minutesToTime(minutes));

	}

	public static Time getNow() {
		Time time = new Time();
		time.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
		time.setMinute(Calendar.getInstance().get(Calendar.MINUTE));
		return time;
	}

	public void setNow() {
		this.hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		this.minute = Calendar.getInstance().get(Calendar.MINUTE);
	}

	public void addMinutes(int minutes) {
		setTime(toMinutes() + minutes);
	}

	public int toMinutes() {
		return (hour * 60) + minute;
	}

	public static Time parseTime(String s) {

		Time time = new Time();
		String[] hourAndMinutes = s.split(":"); //$NON-NLS-1$

		if (hourAndMinutes.length == 1) {

			// TODO: Parse strings like 1230 / 915 / etc.

			try {
				time.setHour(Integer.parseInt(hourAndMinutes[0]));
				return time;
			} catch (Exception e) {
				// Simply return null if parsing fails
				return null;
			}

		} else if (hourAndMinutes.length >= 2) {

			try {
				time.setHour(Integer.parseInt(hourAndMinutes[0]));
				time.setMinute(Integer.parseInt(hourAndMinutes[1]));
				return time;
			} catch (Exception e) {
				// Simply return null if parsing fails
				return null;
			}

		} else {
			return null;
		}

	}

	public static float intervalInHours(Time from, Time to) {

		int deltaHours = to.hour - from.hour;
		int deltaMinutes = to.minute - from.minute;

		float returnVal = deltaHours + (deltaMinutes / 60.0F);
		if (returnVal < 0) {
			returnVal += 24;
		}

		return returnVal;

	}

	public static float intervalInHours(ArrayList<? extends Time> times) {
		float total = 0;
		for (int i = 0; i < times.size() - 1; i++) {
			total += Time.intervalInHours(times.get(i), times.get(i + 1));
		}
		return total;
	}

	public static Time hoursToTime(float hours) {
		int deltaHours = (int) hours;
		int deltaMinutes = Math.round((hours - deltaHours) * 60.0F);
		return new Time(deltaHours, deltaMinutes);
	}

	public static Time minutesToTime(int minutes) {
		int hour = (int) ((float) minutes / 60.0F);
		return new Time(hour, minutes - (hour * 60));
	}

	public static Time intervalInTime(Time from, Time to) {
		return hoursToTime(intervalInHours(from, to));
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Time && ((Time) obj).hour == hour && ((Time) obj).minute == minute;
	}

	@Override
	public String toString() {
		return hour + ":" + (minute < 10 ? "0" + minute : "" + minute); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

}
