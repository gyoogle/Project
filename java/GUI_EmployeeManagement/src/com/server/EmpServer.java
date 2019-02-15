package com.server;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.emp.Employee;

public class EmpServer {

	private int port = 6000;

	public void receive() {
		ServerSocket ss = null;
		
		try {
			ss = new ServerSocket(port);
			System.out.println("ServerSocket ok. port=" + port);
		} catch (Exception e) {
			System.err.println(e);
		}
		
		while(true) {
			Socket s = null;
			ObjectInputStream ois = null;
			
			try {
				System.out.println("server ready...");
				s = ss.accept();
				
				ois = new ObjectInputStream(s.getInputStream());
				
				while(true) {
					Employee e = (Employee) ois.readObject();
					if(e == null) break;
					System.out.println(e);
				}
			} catch (Exception e) {
				//System.err.println(e);
			} finally {
				try {
					System.out.println("receive ok");
					ois.close();
					s.close();
				} catch (Exception e2) {
					System.err.println(e2);
				}
			}
		}
	}
	public static void main(String[] args) {
		new EmpServer().receive();
	}

}