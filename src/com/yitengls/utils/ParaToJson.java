package com.yitengls.utils;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class ParaToJson {
	public static String paraToJson(HttpServletRequest req) {
		StringBuilder json = new StringBuilder("{");
		Map<String, String[]> map = req.getParameterMap();
		Enumeration<String> e = req.getParameterNames();
		int i=1;
		while (e.hasMoreElements()) {
			if(i==1){
				i=2;
			}else{
				json.append(",");
			}
			String paramName = (String) e.nextElement();
			String[] values = req.getParameterValues(paramName);
			String value = values[0];
			json.append("\"").append(paramName).append("\":\"")
			.append(value).append("\"");
		}
		json.append("}");
		return json.toString();
	}
}
