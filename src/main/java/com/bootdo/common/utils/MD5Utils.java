package com.bootdo.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import sun.misc.BASE64Encoder;

public class MD5Utils {
	private static final String SALT = "1qazxsw2";

	private static final String ALGORITH_NAME = "md5";

	private static final int HASH_ITERATIONS = 2;

	public static String encrypt(String pswd) {
		String newPassword = new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(SALT), HASH_ITERATIONS).toHex();
		return newPassword;
	}

	public static String encrypt(String username, String pswd) {
		String newPassword = new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(username + SALT),
				HASH_ITERATIONS).toHex();
		return newPassword;
	}
	
	public static String getAppEnc(String uname)
    {
		String str = "",key = "@x#&q*",md5Str = "";
		String time = "";
		time = TimeUtils.getCurDateTime();
		str = uname+"_"+time+"_"+key;
		System.out.println(str);
//		try {
//			MessageDigest md5 = MessageDigest.getInstance("MD5");
//			BASE64Encoder baseEncoder = new BASE64Encoder();
//			try {
//				md5Str = baseEncoder.encode(md5.digest(str.getBytes("utf-8")));
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		  try {

		     // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）

		     MessageDigest messageDigest =MessageDigest.getInstance("MD5");



		     // 输入的字符串转换成字节数组

		     byte[] inputByteArray = str.getBytes();



		     // inputByteArray是输入字符串转换得到的字节数组

		     messageDigest.update(inputByteArray);



		     // 转换并返回结果，也是字节数组，包含16个元素

		     byte[] resultByteArray = messageDigest.digest();



		     // 字符数组转换成字符串返回

		     md5Str = byteArrayToHex(resultByteArray);



		  } catch (NoSuchAlgorithmException e) {

		     return null;

		  }
		
		
//        byte[] buffer = Encoding.UTF8.GetBytes(str);
//        MD5CryptoServiceProvider md5 = new MD5CryptoServiceProvider();
//        byte[] cryptBuffer = md5.ComputeHash(buffer);
//        //把每一个字节 0-255，转换成两位16进制数
//        for (int i = 0; i < cryptBuffer.length; i++)
//        {
//            //大写X转换的是大写字母，小写X转换的是小写字母
//            md5Str += cryptBuffer[i].ToString("X2");
//        }
        return md5Str;
    }

	public static void main(String[] args) {
		System.out.println(MD5Utils.getAppEnc("admin"));
		//System.out.println(MD5Utils.encrypt("admin", "1"));
	}
	//下面这个函数用于将字节数组换成成16进制的字符串

	public static String byteArrayToHex(byte[] byteArray) {

	  // 首先初始化一个字符数组，用来存放每个16进制字符

	  char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' };



	  // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））

	  char[] resultCharArray =new char[byteArray.length * 2];



	  // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去

	  int index = 0;

	  for (byte b : byteArray) {

	     resultCharArray[index++] = hexDigits[b>>> 4 & 0xf];

	     resultCharArray[index++] = hexDigits[b& 0xf];

	  }



	  // 字符数组组合成字符串返回

	  return new String(resultCharArray);
	}

}
