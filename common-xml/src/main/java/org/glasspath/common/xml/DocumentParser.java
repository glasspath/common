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
package org.glasspath.common.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.glasspath.common.xml.Table.Column;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@SuppressWarnings("nls")
public class DocumentParser {

	private final Table table;
	private final List<Node> nodePath = new ArrayList<>();
	private int currentLevel = -1;
	private Map<String, String> extractedFields = null;

	public DocumentParser(Table table) {
		this.table = table;
	}

	public List<Map<String, String>> parse(Document document) {

		List<Map<String, String>> occurrences = new ArrayList<>();

		nodePath.clear();
		currentLevel = -1;
		extractedFields = null;

		if (document != null) {

			// TODO: Why is #document node added?
			if ("#document".equals(document.getNodeName()) && document.getFirstChild() != null) {
				parseNode(document.getFirstChild(), occurrences);
			} else {
				parseNode(document, occurrences);
			}

			if (extractedFields != null) {
				occurrences.add(extractedFields);
				extractedFields = null;
			}

		}

		return occurrences;

	}

	protected void parseNode(Node node, List<Map<String, String>> occurrences) {

		nodePath.add(node);
		currentLevel++;

		if (table != null) {

			String nodePathAsString = nodePathToString();

			if (nodePathMatches(nodePathAsString, table.getPath())) {

				if (extractedFields != null) {
					occurrences.add(extractedFields);
					extractedFields = null;
				}

			} else if (nodePathStartsWith(nodePathAsString, table.getPath())) {

				for (Column column : table.getColumns()) {

					if (nodePathMatches(nodePathAsString, table.getPath() + "/" + column.getPath())) {

						String value = null;

						String attributeName = getAttributeName(column.getPath());
						if (attributeName != null) {

							if (node.hasAttributes()) {

								Node attributeNode = node.getAttributes().getNamedItem(attributeName);
								if (attributeNode != null) {
									value = attributeNode.getNodeValue();
								}

							}

						} else {
							value = node.getTextContent();
						}

						if (value != null) {

							if (extractedFields == null) {
								extractedFields = new HashMap<>();
							}

							extractedFields.put(column.getName(), value);

						}

					}

				}

			}

		}

		NodeList childNodes = node.getChildNodes();
		if (childNodes != null) {

			for (int i = 0; i < childNodes.getLength(); i++) {
				parseNode(childNodes.item(i), occurrences);
			}

		}

		nodePath.remove(currentLevel);
		currentLevel--;

	}

	protected String nodePathToString() {

		String s = "";

		for (int i = 0; i < nodePath.size(); i++) {

			// s += nodePath.get(i).getNodeName().replaceAll("[^\\w]", "");
			s += nodePath.get(i).getNodeName();

			if (i < nodePath.size() - 1) {
				s += "/";
			}

		}

		return s;

	}

	protected boolean nodePathStartsWith(String nodePath, String path) {
		return Pattern.compile(toRegexPath(path) + "[/\\w]+").matcher(nodePath).matches();
	}

	protected boolean nodePathMatches(String nodePath, String path) {
		return Pattern.compile(toRegexPath(path)).matcher(nodePath).matches();
	}

	protected boolean nodePathEndsWith(String nodePath, String path) {
		return Pattern.compile("[/\\w]+" + toRegexPath(path)).matcher(nodePath).matches();
	}

	protected String toRegexPath(String path) {
		return path.split("\\.")[0].replace("*", "[\\w]+").replace("#", "[/\\w]+");
	}

	protected String getAttributeName(String path) {

		String[] split = path.split("\\.");
		if (split.length == 2) {
			return split[1];
		} else {
			return null;
		}

	}

}
