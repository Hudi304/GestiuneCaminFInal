package com.scheduler.backend;

import java.awt.EventQueue;

import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class App {
	public static String username;
   
	public static void main( String[] args ){
    	
   	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
			Login window = new Login();
			window.Login.setVisible(true); 
			} catch (Exception e) {
				e.printStackTrace(); 
			}
		}
		});
   	
    }
}
