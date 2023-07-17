/**
 * 
 */
package com.huawei.classroom.student.h56;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;

/**
 * @author Administrator
 *
 */
public class FileTool {
	 
	/**
	 * 将homeDir 目录下（包括子目录）所有的文本文件（扩展名为.txt，扩展名不是.txt的文件不要动，扩展名区分大小写) 文件中，orgStr替换为 targetStr
	 * 所有文本文件均为UTF-8编码
	 * 例如将某个目录中所有文本文件中的 南开大学 替换为 天津大学
	 * @param homeDir
	 * @param orgStr
	 * @param targetStr
	 */
	public void replaceTxtFileContent(String homeDir,String orgStr,String targetStr) {
		File dir = new File (homeDir);
		if (!dir.exists() || !dir.isDirectory()) {
			return;
		}
		String[] files = dir.list();
		for (int i = 0; i < files.length; i++) {
			File file = new File(dir, files[i]);
			if (file.isFile()) {
				if(istxt(file)) {
					String absolutePath = file.getAbsolutePath();
					System.out.println(absolutePath);
					try {
						String content = readFromTxt(String.valueOf(file));
						String contentnew = content.replace(orgStr, targetStr);
						file.delete();
						CreateFile(absolutePath, contentnew);
					}
					catch(Exception e) {};
					
				}
			} else {
				replaceTxtFileContent(String.valueOf(file), orgStr, targetStr); // 对于子目录,进行递归调用
			}
		}
	}
	
	
	public boolean istxt(File file) {
		String filename = file.getName();
		char[] name = filename.toCharArray();
		if(name[name.length-1] == 't' && name[name.length-2] == 'x' && name[name.length-3] == 't') {
			return true;
		}
		else return false;
	}
	
	
	// 从txt中读取后存入String
		private String readFromTxt(String filename) throws Exception {
			Reader reader = null;
			try {
				StringBuffer buf = new StringBuffer();
				char[] chars = new char[1024];

				reader = new InputStreamReader(new FileInputStream(filename), "UTF-8");
				int readed = reader.read(chars);
				while (readed != -1) {
					buf.append(chars, 0, readed);
					readed = reader.read(chars);
				}
				return buf.toString();
			} finally {
				close(reader);
			}
		}
		
		// 关闭输入输出流
		private static void close(Closeable inout) {
			if (inout != null) {
				try {
					inout.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		

		public void CreateFile(String filename, String content) {
		        try {

		             File file = new File(filename);
		             boolean success = file.createNewFile(); 
		             if (success) {
		                 System.out.println("File created successfully!");
		             } else {
		                 System.out.println("File already exists.");
		             }
		            
		            FileOutputStream fos = new FileOutputStream(file);
		            fos.write(content.getBytes());
		            fos.close();
		            System.out.println("File created and content written");
		        } catch (IOException e) {
		            System.out.println("Error occurred");
		            e.printStackTrace();
		        }
		      
		}
	


}
