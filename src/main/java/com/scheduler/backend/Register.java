package com.scheduler.backend;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.scheduler.backend.model.User;
import com.scheduler.backend.repository.UserRepository;

public class Register {

	public static String registerName;
	public static String registerPassword;
	private JFrame frame2;
	private JTextField newUsername;
	private JPasswordField newPassword;
	private JLabel lblNewPassword;

	public void RegisterInit() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
					window.frame2.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Register() {
		initialize();
	}
	
	public String getRegisterName()
	{
		return registerName;
	}
	
	public String getRegisterPassword()
	{
		return registerPassword;
	}
	
	private void initialize() {
		frame2 = new JFrame("Register");
		frame2.setBounds(100, 100, 374, 232);
		frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame2.getContentPane().setLayout(null);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				
				User us = new User();
		        
		        us.setUserName(newUsername.getText());
		        us.setPassword(newPassword.getText());
		        
		        UserRepository usRepo = new UserRepository();
		        usRepo.save(us);
				}
				
		});
		btnRegister.setBounds(126, 114, 89, 23);
		frame2.getContentPane().add(btnRegister);
		
		newUsername = new JTextField();
		newUsername.setBounds(145, 47, 179, 20);
		frame2.getContentPane().add(newUsername);
		newUsername.setColumns(10);
		
		newPassword = new JPasswordField();
		newPassword.setBounds(145, 78, 179, 20);
		frame2.getContentPane().add(newPassword);
		
		JLabel lblNewUsername = new JLabel("New Username");
		lblNewUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewUsername.setBounds(10, 42, 139, 31);
		frame2.getContentPane().add(lblNewUsername);
		
		lblNewPassword = new JLabel("New Password");
		lblNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewPassword.setBounds(10, 72, 139, 31);
		frame2.getContentPane().add(lblNewPassword);
		
		JLabel lblRegister = new JLabel("Register");
		lblRegister.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRegister.setBounds(145, 11, 83, 31);
		frame2.getContentPane().add(lblRegister);
	}
}
