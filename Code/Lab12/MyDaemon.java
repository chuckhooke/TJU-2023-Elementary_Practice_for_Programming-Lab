package com.huawei.classroom.student.h62;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyDaemon extends Thread{
	private String rootpath;
	private int port;
	private final Map<String, String> data = new HashMap<>();
	private ServerSocket server;
	public static List<Socket> clients = new ArrayList<>();

	public MyDaemon(MyDaemonConfigVo config) throws IOException {
		this.rootpath = config.getRootpath();
		this.port = config.getPort();
		server = new ServerSocket(port);
	}

	@Override
	public void run() {
		while (true) {
			Socket socket = null;
			try {
				socket = server.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			clients.add(socket);
			new ServerThread(socket, data, rootpath).start();
		}
	}
}
