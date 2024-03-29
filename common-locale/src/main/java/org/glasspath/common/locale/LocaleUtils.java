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

import java.util.Currency;
import java.util.Locale;
import java.util.Locale.Category;

@SuppressWarnings("nls")
public class LocaleUtils {

	public static final String SYSTEM_FORMAT_LANGUAGE_TAG = "system-format";
	public static final String SYSTEM_DISPLAY_LANGUAGE_TAG = "system-display";

	public static enum LanguageTag {

		SQ_AL("sq-AL", "shqip", "Shqipëri"),
		AR_DZ("ar-DZ", "العربية", "الجزائر"),
		AR_BH("ar-BH", "العربية", "البحرين"),
		AR_EG("ar-EG", "العربية", "مصر"),
		AR_IQ("ar-IQ", "العربية", "العراق"),
		AR_JO("ar-JO", "العربية", "الأردن"),
		AR_KW("ar-KW", "العربية", "الكويت"),
		AR_LB("ar-LB", "العربية", "لبنان"),
		AR_LY("ar-LY", "العربية", "ليبيا"),
		AR_MA("ar-MA", "العربية", "المغرب"),
		AR_OM("ar-OM", "العربية", "عُمان"),
		AR_QA("ar-QA", "العربية", "قطر"),
		AR_SA("ar-SA", "العربية", "المملكة العربية السعودية"),
		AR_SD("ar-SD", "العربية", "السودان"),
		AR_SY("ar-SY", "العربية", "سوريا"),
		AR_TN("ar-TN", "العربية", "تونس"),
		AR_AE("ar-AE", "العربية", "الإمارات العربية المتحدة"),
		AR_YE("ar-YE", "العربية", "اليمن"),
		BE_BY("be-BY", "беларуская", "Беларусь"),
		BG_BG("bg-BG", "български", "България"),
		CA_ES("ca-ES", "català", "Espanya"),
		ZH_CN("zh-CN", "中文", "中国"),
		ZH_SG("zh-SG", "中文", "新加坡"),
		ZH_HK("zh-HK", "中文", "中國香港特別行政區"),
		ZH_TW("zh-TW", "中文", "台灣"),
		HR_HR("hr-HR", "hrvatski", "Hrvatska"),
		CS_CZ("cs-CZ", "čeština", "Česko"),
		DA_DK("da-DK", "dansk", "Danmark"),
		NL_BE("nl-BE", "Nederlands", "België"),
		NL_NL("nl-NL", "Nederlands", "Nederland"),
		EN_AU("en-AU", "English", "Australia"),
		EN_CA("en-CA", "English", "Canada"),
		EN_IN("en-IN", "English", "India"),
		EN_IE("en-IE", "English", "Ireland"),
		EN_MT("en-MT", "English", "Malta"),
		EN_NZ("en-NZ", "English", "New Zealand"),
		EN_PH("en-PH", "English", "Philippines"),
		EN_SG("en-SG", "English", "Singapore"),
		EN_ZA("en-ZA", "English", "South Africa"),
		EN_GB("en-GB", "English", "United Kingdom"),
		EN_US("en-US", "English", "United States"),
		ET_EE("et-EE", "eesti", "Eesti"),
		FI_FI("fi-FI", "suomi", "Suomi"),
		FR_BE("fr-BE", "français", "Belgique"),
		FR_CA("fr-CA", "français", "Canada"),
		FR_FR("fr-FR", "français", "France"),
		FR_LU("fr-LU", "français", "Luxembourg"),
		FR_CH("fr-CH", "français", "Suisse"),
		DE_AT("de-AT", "Deutsch", "Österreich"),
		DE_DE("de-DE", "Deutsch", "Deutschland"),
		DE_LU("de-LU", "Deutsch", "Luxemburg"),
		DE_CH("de-CH", "Deutsch", "Schweiz"),
		EL_CY("el-CY", "Ελληνικά", "Κύπρος"),
		EL_GR("el-GR", "Ελληνικά", "Ελλάδα"),
		IW_IL("iw-IL", "עברית", "ישראל"),
		HI_IN("hi-IN", "हिन्दी", "भारत"),
		HU_HU("hu-HU", "magyar", "Magyarország"),
		IS_IS("is-IS", "íslenska", "Ísland"),
		IN_ID("in-ID", "Indonesia", "Indonesia"),
		GA_IE("ga-IE", "Gaeilge", "Éire"),
		IT_IT("it-IT", "italiano", "Italia"),
		IT_CH("it-CH", "italiano", "Svizzera"),
		JA_JP("ja-JP", "日本語", "日本"),
		JA_JP_U_CA_JAPANESE("ja-JP-u-ca-japanese", "日本語", "日本"),
		JA_JP_X_LVARIANT_JP("ja-JP-x-lvariant-JP", "日本語", "日本"),
		KO_KR("ko-KR", "한국어", "대한민국"),
		LV_LV("lv-LV", "latviešu", "Latvija"),
		LT_LT("lt-LT", "lietuvių", "Lietuva"),
		MK_MK("mk-MK", "македонски", "Северна Македонија"),
		MS_MY("ms-MY", "Melayu", "Malaysia"),
		MT_MT("mt-MT", "Malti", "Malta"),
		NO_NO("no-NO", "norsk", "Norge"),
		NB_NO("nb-NO", "norsk bokmål", "Norge"),
		NN_NO("nn-NO", "norsk nynorsk", "Noreg"),
		NO_NO_X_LVARIANT_NY("no-NO-x-lvariant-NY", "norsk", "Noreg"),
		PL_PL("pl-PL", "polski", "Polska"),
		PT_BR("pt-BR", "português", "Brasil"),
		PT_PT("pt-PT", "português", "Portugal"),
		RO_RO("ro-RO", "română", "România"),
		RU_RU("ru-RU", "русский", "Россия"),
		SR_BA("sr-BA", "српски", "Босна и Херцеговина"),
		SR_ME("sr-ME", "српски", "Црна Гора"),
		SR_RS("sr-RS", "српски", "Србија"),
		SR_LATN_BA("sr-Latn-BA", "srpski", "Bosna i Hercegovina"),
		SR_LATN_ME("sr-Latn-ME", "srpski", "Crna Gora"),
		SR_LATN_RS("sr-Latn-RS", "srpski", "Srbija"),
		SK_SK("sk-SK", "slovenčina", "Slovensko"),
		SL_SI("sl-SI", "slovenščina", "Slovenija"),
		ES_AR("es-AR", "español", "Argentina"),
		ES_BO("es-BO", "español", "Bolivia"),
		ES_CL("es-CL", "español", "Chile"),
		ES_CO("es-CO", "español", "Colombia"),
		ES_CR("es-CR", "español", "Costa Rica"),
		ES_DO("es-DO", "español", "República Dominicana"),
		ES_EC("es-EC", "español", "Ecuador"),
		ES_SV("es-SV", "español", "El Salvador"),
		ES_GT("es-GT", "español", "Guatemala"),
		ES_HN("es-HN", "español", "Honduras"),
		ES_MX("es-MX", "español", "México"),
		ES_NI("es-NI", "español", "Nicaragua"),
		ES_PA("es-PA", "español", "Panamá"),
		ES_PY("es-PY", "español", "Paraguay"),
		ES_PE("es-PE", "español", "Perú"),
		ES_PR("es-PR", "español", "Puerto Rico"),
		ES_ES("es-ES", "español", "España"),
		ES_US("es-US", "español", "Estados Unidos"),
		ES_UY("es-UY", "español", "Uruguay"),
		ES_VE("es-VE", "español", "Venezuela"),
		SV_SE("sv-SE", "svenska", "Sverige"),
		TH_TH("th-TH", "ไทย", "ไทย"),
		TH_TH_U_CA_BUDDHIST("th-TH-u-ca-buddhist", "ไทย", "ไทย"),
		TH_TH_U_CA_BUDDHIST_NU_THAI("th-TH-u-ca-buddhist-nu-thai", "ไทย", "ไทย"),
		TH_TH_X_LVARIANT_TH("th-TH-x-lvariant-TH", "ไทย", "ประเทศไทย"),
		TR_TR("tr-TR", "Türkçe", "Türkiye"),
		UK_UA("uk-UA", "українська", "Україна"),
		VI_VN("vi-VN", "Tiếng Việt", "Việt Nam");

		public final String tag;
		public final String language;
		public final String country;

		LanguageTag(String tag, String language, String country) {
			this.tag = tag;
			this.language = language;
			this.country = country;
		}

	}

	public static LanguageTag[] BASIC_LANGUAGE_TAGS = new LanguageTag[] {
			// LanguageTag.SQ_AL,
			// LanguageTag.AR_DZ,
			// LanguageTag.AR_BH,
			// LanguageTag.AR_EG,
			// LanguageTag.AR_IQ,
			// LanguageTag.AR_JO,
			// LanguageTag.AR_KW,
			// LanguageTag.AR_LB,
			// LanguageTag.AR_LY,
			// LanguageTag.AR_MA,
			// LanguageTag.AR_OM,
			// LanguageTag.AR_QA,
			// LanguageTag.AR_SA,
			// LanguageTag.AR_SD,
			// LanguageTag.AR_SY,
			// LanguageTag.AR_TN,
			// LanguageTag.AR_AE,
			// LanguageTag.AR_YE,
			// LanguageTag.BE_BY,
			// LanguageTag.BG_BG,
			LanguageTag.CA_ES,
			// LanguageTag.ZH_CN,
			// LanguageTag.ZH_SG,
			// LanguageTag.ZH_HK,
			// LanguageTag.ZH_TW,
			LanguageTag.HR_HR,
			LanguageTag.CS_CZ,
			LanguageTag.DA_DK,
			LanguageTag.NL_BE,
			LanguageTag.NL_NL,
			LanguageTag.EN_AU,
			LanguageTag.EN_CA,
			LanguageTag.EN_IN,
			LanguageTag.EN_IE,
			LanguageTag.EN_MT,
			LanguageTag.EN_NZ,
			LanguageTag.EN_PH,
			LanguageTag.EN_SG,
			LanguageTag.EN_ZA,
			LanguageTag.EN_GB,
			LanguageTag.EN_US,
			LanguageTag.ET_EE,
			LanguageTag.FI_FI,
			LanguageTag.FR_BE,
			LanguageTag.FR_CA,
			LanguageTag.FR_FR,
			LanguageTag.FR_LU,
			LanguageTag.FR_CH,
			LanguageTag.DE_AT,
			LanguageTag.DE_DE,
			LanguageTag.DE_LU,
			LanguageTag.DE_CH,
			// LanguageTag.EL_CY,
			// LanguageTag.EL_GR,
			// LanguageTag.IW_IL,
			// LanguageTag.HI_IN,
			LanguageTag.HU_HU,
			LanguageTag.IS_IS,
			LanguageTag.IN_ID,
			LanguageTag.GA_IE,
			LanguageTag.IT_IT,
			LanguageTag.IT_CH,
			LanguageTag.JA_JP,
			LanguageTag.JA_JP_U_CA_JAPANESE,
			LanguageTag.JA_JP_X_LVARIANT_JP,
			// LanguageTag.KO_KR,
			LanguageTag.LV_LV,
			LanguageTag.LT_LT,
			LanguageTag.MK_MK,
			LanguageTag.MS_MY,
			LanguageTag.MT_MT,
			LanguageTag.NO_NO,
			LanguageTag.NB_NO,
			LanguageTag.NN_NO,
			LanguageTag.NO_NO_X_LVARIANT_NY,
			LanguageTag.PL_PL,
			LanguageTag.PT_BR,
			LanguageTag.PT_PT,
			LanguageTag.RO_RO,
			// LanguageTag.RU_RU,
			LanguageTag.SR_BA,
			LanguageTag.SR_ME,
			LanguageTag.SR_RS,
			LanguageTag.SR_LATN_BA,
			LanguageTag.SR_LATN_ME,
			LanguageTag.SR_LATN_RS,
			LanguageTag.SK_SK,
			LanguageTag.SL_SI,
			LanguageTag.ES_AR,
			LanguageTag.ES_BO,
			LanguageTag.ES_CL,
			LanguageTag.ES_CO,
			LanguageTag.ES_CR,
			LanguageTag.ES_DO,
			LanguageTag.ES_EC,
			LanguageTag.ES_SV,
			LanguageTag.ES_GT,
			LanguageTag.ES_HN,
			LanguageTag.ES_MX,
			LanguageTag.ES_NI,
			LanguageTag.ES_PA,
			LanguageTag.ES_PY,
			LanguageTag.ES_PE,
			LanguageTag.ES_PR,
			LanguageTag.ES_ES,
			LanguageTag.ES_US,
			LanguageTag.ES_UY,
			LanguageTag.ES_VE,
			LanguageTag.SV_SE,
			// LanguageTag.TH_TH,
			// LanguageTag.TH_TH_U_CA_BUDDHIST,
			// LanguageTag.TH_TH_U_CA_BUDDHIST_NU_THAI,
			// LanguageTag.TH_TH_X_LVARIANT_TH,
			LanguageTag.TR_TR,
			LanguageTag.UK_UA,
			LanguageTag.VI_VN
	};

	public static enum CurrencyCode {

		AED("AED", "د.إ.‏"),
		AFN("AFN", "؋"),
		ALL("ALL", "Lekë"),
		AMD("AMD", "֏"),
		ANG("ANG", "NAf."),
		AOA("AOA", "Kz"),
		ARS("ARS", "$"),
		AUD("AUD", "$"),
		AWG("AWG", "Afl."),
		AZN("AZN", "₼"),
		BAM("BAM", "KM"),
		BBD("BBD", "$"),
		BDT("BDT", "৳"),
		BGN("BGN", "лв."),
		BHD("BHD", "د.ب.‏"),
		BIF("BIF", "FBu"),
		BMD("BMD", "$"),
		BND("BND", "$"),
		BOB("BOB", "Bs"),
		BRL("BRL", "R$"),
		BSD("BSD", "$"),
		BTN("BTN", "Nu."),
		BWP("BWP", "P"),
		BYN("BYN", "Br"),
		BZD("BZD", "$"),
		CAD("CAD", "$"),
		CDF("CDF", "FC"),
		CHF("CHF", "CHF"),
		CLP("CLP", "$"),
		CNY("CNY", "￥"),
		COP("COP", "$"),
		CRC("CRC", "₡"),
		CSD("CSD", "CSD"),
		CUP("CUP", "$"),
		CVE("CVE", "​"),
		CZK("CZK", "Kč"),
		DJF("DJF", "Fdj"),
		DKK("DKK", "kr."),
		DOP("DOP", "RD$"),
		DZD("DZD", "د.ج.‏"),
		EGP("EGP", "ج.م.‏"),
		ERN("ERN", "Nfk"),
		ETB("ETB", "Br"),
		EUR("EUR", "€"),
		FJD("FJD", "$"),
		FKP("FKP", "£"),
		GBP("GBP", "£"),
		GEL("GEL", "₾"),
		GHS("GHS", "GH₵"),
		GIP("GIP", "£"),
		GMD("GMD", "D"),
		GNF("GNF", "GNF"),
		GTQ("GTQ", "Q"),
		GYD("GYD", "$"),
		HKD("HKD", "HK$"),
		HNL("HNL", "L"),
		HRK("HRK", "kn"),
		HTG("HTG", "G"),
		HUF("HUF", "Ft"),
		IDR("IDR", "Rp"),
		ILS("ILS", "₪"),
		INR("INR", "₹"),
		IQD("IQD", "د.ع.‏"),
		IRR("IRR", "IRR"),
		ISK("ISK", "ISK"),
		JMD("JMD", "$"),
		JOD("JOD", "د.أ.‏"),
		JPY("JPY", "￥"),
		KES("KES", "Ksh"),
		KGS("KGS", "сом"),
		KHR("KHR", "៛"),
		KMF("KMF", "CF"),
		KPW("KPW", "KPW"),
		KRW("KRW", "₩"),
		KWD("KWD", "د.ك.‏"),
		KYD("KYD", "$"),
		KZT("KZT", "₸"),
		LAK("LAK", "₭"),
		LBP("LBP", "ل.ل.‏"),
		LKR("LKR", "Rs."),
		LRD("LRD", "$"),
		LSL("LSL", "LSL"),
		LYD("LYD", "د.ل.‏"),
		MAD("MAD", "د.م.‏"),
		MDL("MDL", "L"),
		MGA("MGA", "Ar"),
		MKD("MKD", "den"),
		MMK("MMK", "K"),
		MNT("MNT", "₮"),
		MOP("MOP", "MOP$"),
		MRU("MRU", "UM"),
		MUR("MUR", "Rs"),
		MVR("MVR", "Rf"),
		MWK("MWK", "MK"),
		MXN("MXN", "$"),
		MYR("MYR", "RM"),
		MZN("MZN", "MTn"),
		NAD("NAD", "$"),
		NGN("NGN", "₦"),
		NIO("NIO", "C$"),
		NOK("NOK", "kr"),
		NPR("NPR", "नेरू"),
		NZD("NZD", "$"),
		OMR("OMR", "ر.ع.‏"),
		PAB("PAB", "B/."),
		PEN("PEN", "S/"),
		PGK("PGK", "K"),
		PHP("PHP", "₱"),
		PKR("PKR", "Rs"),
		PLN("PLN", "zł"),
		PYG("PYG", "Gs."),
		QAR("QAR", "ر.ق.‏"),
		RON("RON", "RON"),
		RSD("RSD", "RSD"),
		RUB("RUB", "₽"),
		RWF("RWF", "RF"),
		SAR("SAR", "ر.س.‏"),
		SBD("SBD", "$"),
		SCR("SCR", "SR"),
		SDG("SDG", "SDG"),
		SEK("SEK", "kr"),
		SGD("SGD", "$"),
		SHP("SHP", "£"),
		SLE("SLE", "SLE"),
		SOS("SOS", "S"),
		SRD("SRD", "$"),
		SSP("SSP", "£"),
		STN("STN", "Db"),
		SVC("SVC", "C"),
		SYP("SYP", "ل.س.‏"),
		SZL("SZL", "E"),
		THB("THB", "฿"),
		TJS("TJS", "сом."),
		TMT("TMT", "TMT"),
		TND("TND", "DT"),
		TOP("TOP", "T$"),
		TRY("TRY", "₺"),
		TTD("TTD", "$"),
		TWD("TWD", "$"),
		TZS("TZS", "TSh"),
		UAH("UAH", "₴"),
		UGX("UGX", "USh"),
		USD("USD", "US$"),
		UYU("UYU", "$"),
		UZS("UZS", "soʻm"),
		VES("VES", "Bs.S"),
		VND("VND", "₫"),
		VUV("VUV", "VT"),
		WST("WST", "WS$"),
		XAF("XAF", "FCFA"),
		XCD("XCD", "$"),
		XOF("XOF", "F CFA"),
		XPF("XPF", "FCFP"),
		YER("YER", "ر.ي.‏"),
		ZAR("ZAR", "R"),
		ZMW("ZMW", "K"),
		ZWL("ZWL", "ZWL");

		public final String code;
		public final String symbol;

		CurrencyCode(String code, String symbol) {
			this.code = code;
			this.symbol = symbol;
		}

	}

	public static enum SystemOfUnits {

		// TODO: Add Mass, Weight, Volume, Area, Length etc.?
		// For now this is only used for mileage tracking (kilometers, miles)
		METRIC("metric", "Kilometers", "km"),
		IMPERIAL("imperial", "Miles", "mi");

		public final String code;
		public final String distanceDisplayName;
		public final String distanceSymbol;

		SystemOfUnits(String code, String displayName, String symbol) {
			this.code = code;
			this.distanceDisplayName = displayName;
			this.distanceSymbol = symbol;
		}

	}

	protected static Locale systemDisplayLocale = Locale.getDefault(Category.DISPLAY);
	protected static Locale systemFormatLocale = Locale.getDefault(Category.FORMAT);

	private LocaleUtils() {

	}

	public static Locale getSystemDisplayLocale() {
		return systemDisplayLocale;
	}

	public static Locale getSystemFormatLocale() {
		return systemFormatLocale;
	}

	public static Locale getDefaultLocale() {
		return Locale.getDefault();
	}

	public static void setDefaultLocale(Locale defaultLocale) {
		Locale.setDefault(defaultLocale);
	}

	public static Locale getLocaleForTag(String tag) {
		if (tag != null && tag.length() > 0) {
			if (SYSTEM_FORMAT_LANGUAGE_TAG.equals(tag)) {
				return systemFormatLocale;
			} else if (SYSTEM_DISPLAY_LANGUAGE_TAG.equals(tag)) {
				return systemDisplayLocale;
			} else {
				return Locale.forLanguageTag(tag);
			}
		} else {
			return Locale.getDefault();
		}
	}

	public static LanguageTag getLanguageTagForLocale(Locale locale) {

		if (locale != null) {

			// TODO: Is there a better way to get the tag for a locale?
			for (LanguageTag languageTag : LanguageTag.values()) {

				try {
					Locale l = Locale.forLanguageTag(languageTag.tag);
					if (locale.equals(l)) {
						return languageTag;
					}
				} catch (Exception e) {
					e.printStackTrace(); // TODO?
				}

			}

		}

		return null;

	}

	public static CurrencyCode getCurrencyCodeForLocale(Locale locale) {

		if (locale != null) {

			Currency currency = Currency.getInstance(locale);
			if (currency != null) {
				return getCurrencyCode(currency.getCurrencyCode());
			}

		}

		return null;

	}

	public static CurrencyCode getCurrencyCode(String code) {

		if (code != null) {

			for (CurrencyCode currencyCode : CurrencyCode.values()) {
				if (code.equals(currencyCode.code)) {
					return currencyCode;
				}
			}

		}

		return null;

	}

	public static SystemOfUnits getSystemOfUnitsForLocale(Locale locale) {

		if (locale != null) {

			String countryCode = locale.getCountry();
			switch (countryCode) {

			case "US":
			case "LR": // Liberia
			case "MM": // Myanmar
				return SystemOfUnits.IMPERIAL;

			default:
				return SystemOfUnits.METRIC;

			}

		} else {
			return SystemOfUnits.METRIC;
		}

	}

	public static SystemOfUnits getSystemOfUnits(String code) {
		if (SystemOfUnits.IMPERIAL.code.equals(code)) {
			return SystemOfUnits.IMPERIAL;
		} else {
			return SystemOfUnits.METRIC;
		}
	}

}
