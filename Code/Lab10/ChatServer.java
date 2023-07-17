package com.huawei.classroom.student.h60;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ChatServer extends Thread{
	/**
	 * 初始化 ， 根据情况适当抛出异常
	 * @param port
	 * @param passwordFilename 所有用户的用户名 口令
	 */

	private int port;
    private String passwordFilename;
    private Map<String, String> users;
    private ServerSocket serverSocket;
    public static List<Socket> clients = new ArrayList<>();
    
	public ChatServer (int port, String passwordFilename) throws IOException {
        this.port = port;
        this.passwordFilename = passwordFilename;
        this.users = new HashMap<>();
        serverSocket = new ServerSocket(port);
        loadUsers(passwordFilename);
        

	}
	
	public void loadUsers(String passwordFilename) {
		try (BufferedReader br = new BufferedReader(new FileReader(passwordFilename))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	        	String part[] = line.split("\t");
	        	users.put(part[0], part[1]);
	        }
	    }
		catch (Exception e) {
	        e.printStackTrace();
	    }
		
	}
	
	/**
	 *  根据情况适当抛出异常
	 * 开始监听
	 */
	public void startListen( ) {
		this.start();
	}
	
	@Override
	public void run() {
		while (true) {
			Socket socket = null;
			try {
				socket = serverSocket.accept();

			} catch (IOException e) {
				e.printStackTrace();
			}
			clients.add(socket);
			new ServerThread(socket, users).start();
		}
	}


	 
}
