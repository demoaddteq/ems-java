package com.mm.resource;

import java.io.InputStream;
import java.util.Properties;

import com.mm.resource.APPConstant;

public class LocaleConfig {

	private Properties props;

	public LocaleConfig(String localeName) throws Exception {

		props = new Properties();
		String completeLocaleName = APPConstant.LOCALE_COMMON + "Locale_"
				+ localeName + ".properties";
		InputStream dbStream = LocaleConfig.class.getClassLoader()
				.getResourceAsStream(completeLocaleName);
		props.load(dbStream);

	}

	public String getString(String propertyName) {
		return props.getProperty(propertyName).trim();
	}
}
