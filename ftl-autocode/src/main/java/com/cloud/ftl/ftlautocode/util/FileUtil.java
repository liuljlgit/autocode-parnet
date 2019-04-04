package com.cloud.ftl.ftlautocode.util;

import java.io.*;

public class FileUtil {

	public static void createFile(String path, String fileName, String content) {
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		File file = new File(path + "/" + fileName);
		FileOutputStream fos = null;
		try {
			file.createNewFile();
			fos = new FileOutputStream(file);
			fos.write(content.getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeOutputStream(fos);
		}
	}
	
	public static String readFile(File file) {
		Long filelength = file.length();
		byte[] filecontent = new byte[filelength.intValue()];
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			in.read(filecontent);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeInputStream(in);
		}
		try {
			return new String(filecontent, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.err.println("The OS does not support " + "UTF-8");
			e.printStackTrace();
			return null;
		}
	}
	
	public static void closeInputStream(InputStream is) {
		if(is != null) {
			try {
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeOutputStream(OutputStream os) {
		if(os != null) {
			try {
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
