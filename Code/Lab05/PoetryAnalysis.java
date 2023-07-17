package com.huawei.classroom.student.h55;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PoetryAnalysis {

	/**
	 * 
	 * @param pathFilename 包含诗歌内容的源文件
	 * @param chars 需要统计的字 以半角分号分割 
	 * 统计  
	 * @throws Exception 
	 * 
	 */
	public void analysis(String pathFilename,String chars)  {
		Map<String, Integer> map = new HashMap<String, Integer>();
		List<String> liststr = new ArrayList<String>();
		List<Integer> listnum = new ArrayList<Integer>();
		String totaltxt = readFromTxt(pathFilename);
		String[] strarr = chars.split(";");
		char[] charr = new char [strarr.length];
		for(int i = 0; i < strarr.length; i++) {
			charr[i] = strarr[i].charAt(0);
		}
		char[] txtcharr = totaltxt.toCharArray();
		for(int i = 1; i < txtcharr.length-1; i++) {
			String s1 = "";
			String s2 = "";
			for(int j = 0; j < charr.length; j++) {
				if(charr[j] == txtcharr[i]) {
					if(txtcharr[i-1] == '，' || txtcharr[i-1] == '。') {}
					else {
						s1 = "" + txtcharr[i-1] + txtcharr[i];
					}
					if(txtcharr[i+1] == '，' || txtcharr[i+1] == '。') {}
					else {
						s2 = "" + txtcharr[i] + txtcharr[i+1];
					}
				}
			}
			if(!(s1.equals(""))) {
				int count = 1;
				if (map.containsKey(s1)){
					count =  (Integer) map.get(s1) + 1;
				}
				map.put(s1, count);
			}
			if(!(s2.equals(""))) {
				int count = 1;
				if (map.containsKey(s2)){
					count =  (Integer) map.get(s2) + 1;
				}
				map.put(s2, count);
			}
			
			
		}
		
		
		for(int i = 0; i < 10; i++) {
			int maxcnt = 0;
			String frestr = "";
			Iterator<String> it = map.keySet().iterator();
			while(it.hasNext()) {
				String key = (String)it.next();
				if ((Integer)map.get(key) > maxcnt) {
					maxcnt = (Integer)map.get(key);
					frestr = key;
				}
			}
			map.remove(frestr);
			liststr.add(frestr);
			listnum.add(maxcnt);
		}
		
		for(int i = 0; i < 10; i++) {
			System.out.println(liststr.get(i) + ":" + listnum.get(i));
		}
		
	}
	
	public String readFromTxt(String filename) {
		String totaltxt = "";
		try {
			totaltxt = readFromTxtthrow(filename);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return totaltxt;
	}
	
	public String readFromTxtthrow(String filename) throws Exception {
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
	
	public void close(Closeable inout) {
		if (inout != null) {
			try {
				inout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
