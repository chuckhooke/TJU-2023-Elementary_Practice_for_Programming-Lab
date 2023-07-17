package com.huawei.classroom.student.h62;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

public class ServerThread extends Thread {
	private final Socket socket;
	private final Map<String, String> data;
	private String path;

	public ServerThread(Socket socket, Map<String, String> data, String rootpath) {
		super();
		this.socket = socket;
		this.path = rootpath;
		this.data = data;
	}

	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			for (Socket client : MyDaemon.clients) {
				PrintWriter out = new PrintWriter(client.getOutputStream());
				out.write(this.path + "\r\n");
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
