package com.Controller;

public class Helper {

	public static String get10WOrds(String s) {
		if(s.length()!=0) {
		String str[] = s.split(" ");
		if (str.length >= 10) {
			String res = "";
			for (int i = 0; i < 10; i++) {
				res += str[i];
				res += " ";
			}
			return res;
		}
		}
		return s;
	}
}
