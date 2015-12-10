package com.epf.cloud.ehealth_cloud.client.util;

public class Tools {
	
	public static String byteArrayToString(byte [] array)
	{
		String _str = "";
		for	(byte k : array)
		{
			_str += Byte.toString(k) + " ";
		}
		return _str.trim();
	}
	
	public static String IntArrayToString(int [] array)
	{
		String _str = "";
		for	(int k : array)
		{
			_str += Integer.toString(k) + " ";
		}
		return _str.trim();
	}

}
