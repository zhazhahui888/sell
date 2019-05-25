package com.huiwan.utils;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ZhangQian logeed@163.com 2010-3-9
 */
public class MyUtils extends org.apache.commons.lang3.StringUtils {

	protected static final Log log = LogFactory.getLog(MyUtils.class);

	public static boolean isBlank(final String str) {
		return (str == null) || (str.trim().length() <= 0);
	}

	public static boolean isBlank(final Object[] objs) {
		return (objs == null) || (objs.length <= 0);
	}

	public static boolean isBlank(final Collection obj) {
		return (obj == null) || (obj.size() <= 0);
	}

	public static boolean isBlank(final Set obj) {
		return (obj == null) || (obj.size() <= 0);
	}

	public static boolean isBlank(final Serializable obj) {
		return obj == null;
	}

	public static boolean isBlank(final Map obj) {
		return (obj == null) || (obj.size() <= 0);
	}

	public static void delAllFile(boolean deleteDir, String filePath) {
		File file = new File(filePath);
		File[] fileList = file.listFiles();
		String dirPath = null;
		if (fileList != null) {
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].isFile()) {
					fileList[i].delete();
				}
				if (fileList[i].isDirectory()) {
					dirPath = fileList[i].getPath();
					delAllFile(true, dirPath);
				}
			}
			if(deleteDir)
				file.delete();
		}
	}
	
	
	public static String filterUnNumber(String str) {   
        // 只允数字   
        String regEx = "[^0-9\\.]";   
        Pattern p = Pattern.compile(regEx);   
        Matcher m = p.matcher(str.trim());   
    //替换与模式匹配的所有字符（即非数字的字符将被""替换）   
        return m.replaceAll("").trim();   
  
    }  
	/**去掉文本域中的回车*/
	public static final String rePltextRN(String txt){
		return txt.replace("\r\n"," " );
	}
	
	public static String getMD5Signature(String secret) {
		StringBuilder sign = new StringBuilder();
		try {
			// 使用MD5加密
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] bytes = md5.digest(secret.getBytes("UTF-8"));
			// 把二进制转化为大写的十六进制
			for (int i = 0; i < bytes.length; i++) {
				String hex = Integer.toHexString(bytes[i] & 0xFF);
				if (hex.length() == 1) {
					sign.append("0");
				}
				sign.append(hex.toLowerCase());
			}
		} catch (Exception e) {
			throw new RuntimeException("Signature Generate Error!");
		}
		return sign.toString();
	}
	/**/
	public static void main(String[] args){
		System.out.println(isFloat("122"));
	}
	/**验证正整数*/
	public static boolean isNum(String str){ 
		   Pattern pattern = Pattern.compile("^\\d*$"); 
		   Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true; 
		}
	/**验证正浮点数*/
	public static boolean isFloat(String str){
		  String macStr="^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$";
		  Pattern pattern = Pattern.compile(macStr); 
		  Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true; 

	}
}