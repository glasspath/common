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
package org.glasspath.common.locale;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@SuppressWarnings("nls")
public class LocaleUtils {

	private static final List<String> supportedTagsJdk8 = new ArrayList<>();
	static {
		supportedTagsJdk8.add("sq-AL");
		supportedTagsJdk8.add("ar-DZ");
		supportedTagsJdk8.add("ar-BH");
		supportedTagsJdk8.add("ar-EG");
		supportedTagsJdk8.add("ar-IQ");
		supportedTagsJdk8.add("ar-JO");
		supportedTagsJdk8.add("ar-KW");
		supportedTagsJdk8.add("ar-LB");
		supportedTagsJdk8.add("ar-LY");
		supportedTagsJdk8.add("ar-MA");
		supportedTagsJdk8.add("ar-OM");
		supportedTagsJdk8.add("ar-QA");
		supportedTagsJdk8.add("ar-SA");
		supportedTagsJdk8.add("ar-SD");
		supportedTagsJdk8.add("ar-SY");
		supportedTagsJdk8.add("ar-TN");
		supportedTagsJdk8.add("ar-AE");
		supportedTagsJdk8.add("ar-YE");
		supportedTagsJdk8.add("be-BY");
		supportedTagsJdk8.add("bg-BG");
		supportedTagsJdk8.add("ca-ES");
		supportedTagsJdk8.add("zh-CN");
		supportedTagsJdk8.add("zh-SG");
		supportedTagsJdk8.add("zh-HK");
		supportedTagsJdk8.add("zh-TW");
		supportedTagsJdk8.add("hr-HR");
		supportedTagsJdk8.add("cs-CZ");
		supportedTagsJdk8.add("da-DK");
		supportedTagsJdk8.add("nl-BE");
		supportedTagsJdk8.add("nl-NL");
		supportedTagsJdk8.add("en-AU");
		supportedTagsJdk8.add("en-CA");
		supportedTagsJdk8.add("en-IN");
		supportedTagsJdk8.add("en-IE");
		supportedTagsJdk8.add("en-MT");
		supportedTagsJdk8.add("en-NZ");
		supportedTagsJdk8.add("en-PH");
		supportedTagsJdk8.add("en-SG");
		supportedTagsJdk8.add("en-ZA");
		supportedTagsJdk8.add("en-GB");
		supportedTagsJdk8.add("en-US");
		supportedTagsJdk8.add("et-EE");
		supportedTagsJdk8.add("fi-FI");
		supportedTagsJdk8.add("fr-BE");
		supportedTagsJdk8.add("fr-CA");
		supportedTagsJdk8.add("fr-FR");
		supportedTagsJdk8.add("fr-LU");
		supportedTagsJdk8.add("fr-CH");
		supportedTagsJdk8.add("de-AT");
		supportedTagsJdk8.add("de-DE");
		supportedTagsJdk8.add("de-LU");
		supportedTagsJdk8.add("de-CH");
		supportedTagsJdk8.add("el-CY");
		supportedTagsJdk8.add("el-GR");
		supportedTagsJdk8.add("iw-IL");
		supportedTagsJdk8.add("hi-IN");
		supportedTagsJdk8.add("hu-HU");
		supportedTagsJdk8.add("is-IS");
		supportedTagsJdk8.add("in-ID");
		supportedTagsJdk8.add("ga-IE");
		supportedTagsJdk8.add("it-IT");
		supportedTagsJdk8.add("it-CH");
		supportedTagsJdk8.add("ja-JP");
		supportedTagsJdk8.add("ja-JP-u-ca-japanese");
		supportedTagsJdk8.add("ja-JP-x-lvariant-JP");
		supportedTagsJdk8.add("ko-KR");
		supportedTagsJdk8.add("lv-LV");
		supportedTagsJdk8.add("lt-LT");
		supportedTagsJdk8.add("mk-MK");
		supportedTagsJdk8.add("ms-MY");
		supportedTagsJdk8.add("mt-MT");
		supportedTagsJdk8.add("no-NO");
		supportedTagsJdk8.add("nb-NO");
		supportedTagsJdk8.add("nn-NO");
		supportedTagsJdk8.add("no-NO-x-lvariant-NY");
		supportedTagsJdk8.add("pl-PL");
		supportedTagsJdk8.add("pt-BR");
		supportedTagsJdk8.add("pt-PT");
		supportedTagsJdk8.add("ro-RO");
		supportedTagsJdk8.add("ru-RU");
		supportedTagsJdk8.add("sr-BA");
		supportedTagsJdk8.add("sr-ME");
		supportedTagsJdk8.add("sr-RS");
		supportedTagsJdk8.add("sr-Latn-BA");
		supportedTagsJdk8.add("sr-Latn-ME");
		supportedTagsJdk8.add("sr-Latn-RS");
		supportedTagsJdk8.add("sk-SK");
		supportedTagsJdk8.add("sl-SI");
		supportedTagsJdk8.add("es-AR");
		supportedTagsJdk8.add("es-BO");
		supportedTagsJdk8.add("es-CL");
		supportedTagsJdk8.add("es-CO");
		supportedTagsJdk8.add("es-CR");
		supportedTagsJdk8.add("es-DO");
		supportedTagsJdk8.add("es-EC");
		supportedTagsJdk8.add("es-SV");
		supportedTagsJdk8.add("es-GT");
		supportedTagsJdk8.add("es-HN");
		supportedTagsJdk8.add("es-MX");
		supportedTagsJdk8.add("es-NI");
		supportedTagsJdk8.add("es-PA");
		supportedTagsJdk8.add("es-PY");
		supportedTagsJdk8.add("es-PE");
		supportedTagsJdk8.add("es-PR");
		supportedTagsJdk8.add("es-ES");
		supportedTagsJdk8.add("es-US");
		supportedTagsJdk8.add("es-UY");
		supportedTagsJdk8.add("es-VE");
		supportedTagsJdk8.add("sv-SE");
		supportedTagsJdk8.add("th-TH");
		supportedTagsJdk8.add("th-TH-u-ca-buddhist");
		supportedTagsJdk8.add("th-TH-u-ca-buddhist-nu-thai");
		supportedTagsJdk8.add("th-TH-x-lvariant-TH");
		supportedTagsJdk8.add("tr-TR");
		supportedTagsJdk8.add("uk-UA");
		supportedTagsJdk8.add("vi-VN");
	}

	private LocaleUtils() {

	}

	public static List<String> getSupportedTags() {
		return supportedTagsJdk8;
	}

	public static Locale getLocaleForTag(String tag) {
		if (tag != null && tag.length() > 0) {
			return Locale.forLanguageTag(tag);
		} else {
			return Locale.getDefault();
		}
	}

	public static List<Locale> getISOLanguageLocales() {

		List<Locale> locales = new ArrayList<>();

		for (String isoLanguage : Locale.getISOLanguages()) {
			try {
				locales.add(new Locale(isoLanguage));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return locales;

	}

}
