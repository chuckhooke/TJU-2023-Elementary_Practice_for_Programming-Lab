package com.huawei.classroom.student.h62;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class MyDaemonConfigVo {
	private String rootpath;
	private int port;
	private final Map<String, String> data = new HashMap<>();
	
	public void setRoot(String remoteHome) {
		rootpath = remoteHome;
	}

	public void setPort(int i) {
		port = i;
	}
	
	public int getPort() {
		return port;
	}

	public String getRootpath() {
		return rootpath;
	}
	
	public void setPasswordFile(String string) {
		Reader in;
		try {
			in = new FileReader(string);
			LineNumberReader reader = new LineNumberReader(in);
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("#"))
					continue;
				String[] datas = line.split("\t");
				data.put(datas[0], datas[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
