package com.groupfour.tools;

import java.io.UnsupportedEncodingException;

public class EncodingTool {
	public static String getCh(String oldname){
		String newname=null;
		try {
			newname = new String(oldname.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newname;
	}
}
