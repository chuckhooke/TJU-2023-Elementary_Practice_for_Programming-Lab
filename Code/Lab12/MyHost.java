package com.huawei.classroom.student.h62;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class MyHost {
	private String Ip;
	private int Port;
	private String Username;
	private String Password;
	private String Remotefilepath;
	private BufferedReader in;

	public void setIp(String ip) {
		Ip = ip;
	}

	public void setPort(int port) throws Exception {
		Port = port;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public void setPassword(String password) throws Exception {
		Password = password;
		Socket client;
		client = new Socket(Ip, Port);
		new java.io.PrintWriter(client.getOutputStream());
		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		Remotefilepath = in.readLine();
	}
	
	public void setRemotefilepath(String remotefilepath) {
		Remotefilepath = remotefilepath;
	}
	
	public String getRemotefilepath() {
		return Remotefilepath;
	}

}
