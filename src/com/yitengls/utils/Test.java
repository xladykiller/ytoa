package com.yitengls.utils;



import java.util.ArrayList;

import com.google.gson.Gson;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
//		System.out.println(Power.validate(6, Power.addUser));
		Gson gson = new Gson();
		System.out.println(gson.toJson(new ArrayList())/*gson.toJson(new PowerBean())*/);
	}

}
