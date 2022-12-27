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

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;
import oshi.hardware.NetworkIF.IfOperStatus;

@SuppressWarnings("nls")
public class NetUtils {

	private NetUtils() {

	}

	public static List<NetworkInterface> getNetworkInterfaces(boolean jna) {

		List<NetworkInterface> networkInterfaces = new ArrayList<>();

		if (jna) {

			SystemInfo systemInfo = new SystemInfo();
			HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();

			List<NetworkIF> networkIFs = hardwareAbstractionLayer.getNetworkIFs(false);

			for (NetworkIF networkIF : networkIFs) {

				IfOperStatus status = networkIF.getIfOperStatus();

				NetworkInterface networkInterface = new NetworkInterface();

				networkInterface.setAlias(networkIF.getIfAlias());
				networkInterface.setDisplayName(networkIF.getDisplayName());
				networkInterface.addIpAddresses(networkIF.getIPv4addr());
				networkInterface.setSent(networkIF.getBytesSent());
				networkInterface.setReceived(networkIF.getBytesRecv());
				networkInterface.setUp(status == IfOperStatus.UP || status == IfOperStatus.DORMANT);

				networkInterfaces.add(networkInterface);

			}

			Collections.sort(networkInterfaces, new Comparator<NetworkInterface>() {

				@Override
				public int compare(NetworkInterface if0, NetworkInterface if1) {
					return Long.compare(if1.getReceived(), if0.getReceived());
				}
			});

		} else {

			try {

				Enumeration<java.net.NetworkInterface> networkIFs = java.net.NetworkInterface.getNetworkInterfaces();
				while (networkIFs.hasMoreElements()) {

					java.net.NetworkInterface networkIF = (java.net.NetworkInterface) networkIFs.nextElement();
					if (!networkIF.isLoopback()) {

						NetworkInterface networkInterface = new NetworkInterface();

						networkInterface.setAlias(networkIF.getDisplayName());
						networkInterface.setDisplayName(networkIF.getDisplayName());
						networkInterface.setUp(networkIF.isUp());

						Enumeration<InetAddress> inetAddresses = networkIF.getInetAddresses();
						while (inetAddresses.hasMoreElements()) {
							InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
							networkInterface.addIpAddress(inetAddress.getHostAddress());
						}

						networkInterfaces.add(networkInterface);

					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return networkInterfaces;

	}

	public static String getIpAddressesAsString(List<NetworkInterface> networkInterfaces, boolean availableInterfacesOnly, String separator) {

		String s = "";

		List<String> ipAddresses = getIpAddresses(networkInterfaces, availableInterfacesOnly);

		if (ipAddresses.size() > 0) {

			s = ipAddresses.get(0);

			if (ipAddresses.size() > 1) {
				for (int i = 1; i < ipAddresses.size(); i++) {
					s += separator + ipAddresses.get(i);
				}
			}

		}

		return s;

	}

	public static List<String> getIpAddresses(List<NetworkInterface> networkInterfaces, boolean availableInterfacesOnly) {

		List<String> ipAddresses = new ArrayList<>();

		for (NetworkInterface networkInterface : networkInterfaces) {
			if (!availableInterfacesOnly || networkInterface.isAvailable()) {
				ipAddresses.addAll(networkInterface.getIpAddresses());
			}
		}

		return ipAddresses;

	}

	public static InetAddress getInetAddress(SocketAddress socketAddress) {
		if (socketAddress instanceof InetSocketAddress) {
			return ((InetSocketAddress) socketAddress).getAddress();
		} else {
			return null;
		}
	}

	public static String getIpAddress(SocketAddress socketAddress) {

		if (socketAddress instanceof InetSocketAddress) {

			InetAddress inetAddress = ((InetSocketAddress) socketAddress).getAddress();

			/*
			if (inetAddress instanceof Inet4Address) {
				System.out.println("IPv4: " + inetAddress);
			} else if (inetAddress instanceof Inet6Address) {
				System.out.println("IPv6: " + inetAddress);
			}
			*/

			return getIpAddress(inetAddress.getAddress());

		}

		return null;

	}

	public static String getIpAddress(byte[] ipBytes) {
		if (ipBytes.length == 4) {
			return (ipBytes[0] & 0xFF) + "." + (ipBytes[1] & 0xFF) + "." + (ipBytes[2] & 0xFF) + "." + (ipBytes[3] & 0xFF);
		} else {
			return null;
		}
	}

}
