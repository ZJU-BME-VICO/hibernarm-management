package org.hibernarm.management.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class FileUtil {
	public static String extractContent(File file) throws Exception {
		String fileContent=null;
		BufferedReader reader=null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file),"UTF-8"));
			StringBuffer content = new StringBuffer();
			String singleLine = "";
			while ((singleLine = reader.readLine()) != null) {
				content.append(singleLine+"\n");
			}
			fileContent = content.toString();
		}catch(Exception e){
			throw new Exception("提取文件内容出错");
		}finally{
			reader.close();
		}
		
		return fileContent;
	}
}
