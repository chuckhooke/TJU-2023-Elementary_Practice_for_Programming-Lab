package com.huawei.classroom.student.h60;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;


public class ChatClient {
	private String ip;
    private int port;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private boolean flag;
	 /**
	  * 根据情况适当抛出异常 
	  * @param ip
	  * @param port
	  * @throws IOException 
	  */
	public ChatClient (String ip, int port) throws IOException {
        this.ip = ip;
        this.port = port;
        this.socket = new Socket(ip, port);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream());
	}
	/**
	 * 登录,成功返回true，否则返回false，根据情况适当抛出异常 
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public boolean login(String userName,String password) {
		 try {
	            out.write("login" + " " + userName + " " + password + "\r\n");
	    		out.flush();
	            String getin = in.readLine();
	            flag = getin.equals("right");
	            return flag;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	}
	/**
	 * 退出，根据情况适当抛出异常 
	 * @throws Exception 
	 */
	public void logout() throws Exception {
		if (!flag) throw new Exception("no login"); 
		flag = false;
	}
	/**
	 * 发言, 只有登录以后才能发言， 根据情况适当抛出异常 
	 * 如果没有登录 抛出异常
	 *  
	 * @param str
	 * @throws Exception 
	 */
	public void speak(String str) throws Exception {
		if (!flag) throw new Exception("no login"); 
		out.write(str + "\r\n");
		out.flush();
	}
	/**
	 * 读取聊天室目前的发言，根据情况适当抛出异常 
	 * 只有登录以后才可以读到,否则返回null
	 * 得到聊天室里面所有的发言（包括自己的），如果此时没有发言则立刻返回null，否则每次调用read的时候按队的方式返回一个句话
	 * @throws IOException 
	 * @throws Exception 
	 */
	public String read() throws IOException {
		if (!flag) {
			return null;
		}
		return in.readLine();
		
		
	}
	
}
