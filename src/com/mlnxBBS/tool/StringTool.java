package com.mlnxBBS.tool;

public class StringTool {
	
	public static String mySubString(String str,String start,String end)
	{
		int s = str.indexOf(start);
		int e = str.indexOf(end);
		str = str.substring(s, e);
		return str;
	}
	
	public static String mySubString2(String str,String start)
	{
		int s = str.indexOf(start);
		str = str.substring(s);
		return str;
	}
	
	public static void main(String[] args) {
		
		String sql = "select a from user";
		String str = StringTool.mySubString2(sql, "from");
		System.out.println(str);
	}

}
