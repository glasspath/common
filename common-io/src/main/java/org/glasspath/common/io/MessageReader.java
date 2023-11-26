/*
 * This file is part of Glasspath Common.
 * Copyright (C) 2011 - 2023 Remco Poelstra
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
package org.glasspath.common.io;

import java.io.InputStream;

public abstract class MessageReader {

	public static final int DEFAULT_BUFFER_SIZE = 16384;

	private final ByteArrayMatcher startMatcher;
	private final ByteArrayMatcher endMatcher;
	private final int bufferSize;

	protected byte[] bytes = new byte[0];
	private int index = 0;
	private int messageStart = -1;
	private boolean exit = false;

	public MessageReader(byte[] messageStartsWith, byte[] messageEndsWith) {
		this(messageStartsWith, messageEndsWith, DEFAULT_BUFFER_SIZE);
	}

	public MessageReader(byte[] messageStartsWith, byte[] messageEndsWith, int bufferSize) {
		this.startMatcher = new ByteArrayMatcher(messageStartsWith);
		this.endMatcher = new ByteArrayMatcher(messageEndsWith);
		this.bufferSize = bufferSize;
	}

	public void readAllBytes(InputStream in) throws Exception { // TODO: Which exceptions?

		while (!exit) {

			byte[] received = new byte[bufferSize];
			int receivedLength = in.read(received);

			if (receivedLength > 0) {

				byte[] newBytes = new byte[bytes.length + receivedLength];
				System.arraycopy(bytes, 0, newBytes, 0, bytes.length);
				System.arraycopy(received, 0, newBytes, bytes.length, receivedLength);
				bytes = newBytes;

				processBytes();

			} else if (receivedLength == 0) {
				Thread.sleep(1);
			} else {
				break; // End of stream reached
			}

		}

	}

	protected void processBytes() {

		while (index < bytes.length) {

			if (startMatcher.match(bytes[index])) {
				messageStart = (index + 1) - startMatcher.bytes.length;
			}

			if (endMatcher.match(bytes[index]) && messageStart >= 0) {

				messageReceived(bytes, messageStart, index);

				messageStart = -1;

				int newLength = bytes.length - (index + 1);
				if (newLength > 0) {

					byte[] newBytes = new byte[newLength];
					System.arraycopy(bytes, index + 1, newBytes, 0, newLength);
					bytes = newBytes;

				} else {
					bytes = new byte[0];
				}

				index = -1;

			}

			index++;

		}

	}

	protected abstract void messageReceived(byte[] bytes, int messageStart, int messageEnd);

	public void exit() {
		exit = true;
	}

	public static class ByteArrayMatcher {

		private final byte[] bytes;
		private int i = 0;

		public ByteArrayMatcher(byte[] bytes) {
			this.bytes = bytes;
		}

		public boolean match(byte b) {

			if (bytes[i] == b) {
				i++;
				if (i >= bytes.length) {
					i = 0;
					return true;
				}
			} else {
				i = 0;
			}

			return false;

		}

	}

}
