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
package org.glasspath.common.os.net;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("nls")
public class NetworkInterface {

	private String alias = "";
	private String displayName = "";
	private List<String> ipAddresses = new ArrayList<>();
	private long sent = 0;
	private long received = 0;
	private boolean up = false;

	public NetworkInterface() {

	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public List<String> getIpAddresses() {
		return ipAddresses;
	}

	public void setIpAddresses(List<String> ipAddresses) {
		this.ipAddresses = ipAddresses;
	}

	public void addIpAddress(String ipAddress) {
		if (isIpv4Address(ipAddress)) {
			this.ipAddresses.add(ipAddress);
		}
	}

	public void addIpAddresses(String[] ipAddresses) {
		for (String s : ipAddresses) {
			if (isIpv4Address(s)) {
				this.ipAddresses.add(s);
			}
		}
	}

	public String getIpAddressesAsString() {

		String s = "";

		if (ipAddresses.size() > 0) {

			s = ipAddresses.get(0);

			if (ipAddresses.size() > 1) {

				for (int i = 1; i < ipAddresses.size() - 1; i++) {
					s += "," + ipAddresses.get(i);
				}

				s += "," + ipAddresses.get(ipAddresses.size() - 1);

			}

		}

		return s;

	}

	public long getSent() {
		return sent;
	}

	public void setSent(long sent) {
		this.sent = sent;
	}

	public long getReceived() {
		return received;
	}

	public void setReceived(long received) {
		this.received = received;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isAvailable() {
		return up && ipAddresses.size() > 0;
	}

	public static boolean isIpv4Address(String s) {

		if (s != null) {

			String[] split = s.split("\\.");

			if (split.length == 4) {

				try {

					Integer.parseInt(split[0]);
					Integer.parseInt(split[1]);
					Integer.parseInt(split[2]);
					Integer.parseInt(split[3]);

					return true;

				} catch (Exception e) {
					return false;
				}

			}

		}

		return false;

	}

}
