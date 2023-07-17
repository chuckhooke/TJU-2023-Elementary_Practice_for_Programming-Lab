package com.huawei.classroom.student.h60;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;


public class ServerThread extends Thread {
	private final Socket socket;
	private final Map<String, String> users;

	public ServerThread(Socket socket, Map<String, String> passwd) {
		super();
		this.socket = socket;
		this.users = passwd;
		
	}

	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream()); 
			String line = in.readLine();
			String passwdStr = line;
			String flag = pass(passwdStr);
//			System.out.println(flag);
			out.write(flag);
			out.flush();

			if ("wrong".equals(flag)) {
				return;
			}
			while (true) {
				line = in.readLine();
				for (Socket client : ChatServer.clients) {
					PrintWriter clientOut = new PrintWriter(client.getOutputStream());
					clientOut.write(line + "\r\n");
					clientOut.flush();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String pass(String passwdStr) {
		String[] userPasswd = passwdStr.split(" ");
		if (userPasswd.length != 3) {
			return ("wrong" + "\r\n");
		}
		String username = userPasswd[1];
		String password = userPasswd[2];
		String correctpassword = users.get(username);
		if (correctpassword.equals(password))
			return ("right" + "\r\n");
		return ("wrong" + "\r\n");
	}
}
