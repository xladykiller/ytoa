package com.yitengls.config;

import java.util.ResourceBundle;

public class SystemConfig {

	private static String configFile = "c3p0";

	public static String getConfigInfo(String item) {
		try {
			ResourceBundle rb = ResourceBundle.getBundle(configFile);
			return rb.getString(item);
		} catch (Exception e) {
			return "";
		}
	}
}
