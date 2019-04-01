package com.bootdo.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;
import java.util.UUID;

public class FileUtil {

	public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
		File targetFile = new File(filePath);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		FileOutputStream out = new FileOutputStream(filePath + fileName);
		out.write(file);
		out.flush();
		out.close();
	}

	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static String renameToUUID(String fileName) {
		return UUID.randomUUID() + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
	}
	
	public static String reUrl(String type) {
		String path = "";
		Calendar cal = Calendar.getInstance();
		String year = cal.get(Calendar.YEAR) + "";
		String month = (cal.get(Calendar.MONTH) + 1) + "";
		String day = cal.get(Calendar.DAY_OF_MONTH) + "";
		path = "images/" + type + "/" + year + "/" + month + "/" + day + "/";
		return path;
	}
	
	/**
	 * 获取Properties实例
	 * @param className
	 * @param propertiesFile
	 * @return
	 */
	public static Properties getProps(Class className,String propertiesFile){
		Properties props = new Properties();
		try {
			props.load(className.getClassLoader().getResourceAsStream(propertiesFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}
}
