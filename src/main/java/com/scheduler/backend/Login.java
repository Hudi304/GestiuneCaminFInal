package com.scheduler.backend;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.scheduler.backend.model.User;
import com.scheduler.backend.repository.UserRepository;

public class Login {

	public JFrame Login;
	private JTextField usernameField;
	private JPasswordField passwordField;

	public Login() {
		initializeLogin();
	}
	
	@SuppressWarnings("deprecation")
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ENTER) {
			UserRepository repo = new UserRepository();
            User nou= new User();
            nou=repo.findByCredentials(usernameField.getText(), passwordField.getText());
            if (nou!=null)
            {
            	JOptionPane.showMessageDialog(null,"Login Sucessfully...");
    			gestiuneCamin.gestiuneCaminInit();
    			Login.dispose();
            }
            else {
            	JOptionPane.showMessageDialog(null,"Incorrect info...");

            }
        
		}
	}

	private void initializeLogin() {
		Login = new JFrame("Login");
		Login.setBounds(100, 100, 443, 225);
		Login.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Login.getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLogin.setBounds(194, 11, 84, 36);
		Login.getContentPane().add(lblLogin);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsername.setBounds(36, 55, 120, 30);
		Login.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(36, 97, 95, 22); 
		Login.getContentPane().add(lblPassword);
		
		usernameField = new JTextField();
		usernameField.setBounds(141, 58, 250, 30);
		Login.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(141, 96, 250, 30);
		Login.getContentPane().add(passwordField);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				UserRepository repo= new UserRepository();
                User nou= new User();
                nou=repo.findByCredentials(usernameField.getText(), passwordField.getText());
                if (nou!=null)
                {
                	JOptionPane.showMessageDialog(null,"Login Sucessfully...");
        			gestiuneCamin.gestiuneCaminInit();
        			Login.dispose();
                }
                else {
                	JOptionPane.showMessageDialog(null,"Incorrect info...");

                }
            }
			
		});
		btnLogin.setBounds(25, 136, 89, 23);
		Login.getContentPane().add(btnLogin);
		Login.getRootPane().setDefaultButton(btnLogin);
		
		JButton btnResetinfo = new JButton("ResetInfo");
		btnResetinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				usernameField.setText(null);
				passwordField.setText(null);
			}
		});
		btnResetinfo.setBounds(125, 136, 89, 23);
		Login.getContentPane().add(btnResetinfo);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(317, 137, 89, 23);
		Login.getContentPane().add(btnExit);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				Register reg =new Register();
//				reg.RegisterInit();
				RegisterWindow regWindow = new RegisterWindow();
				regWindow.launch();
			}
		});
		btnRegister.setBounds(224, 137, 84, 22);
		Login.getContentPane().add(btnRegister);
		
		JButton addDormitoryBtn = new JButton("Add Dormitory");
		addDormitoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCaminWindow CaminWindow = new AddCaminWindow();
				CaminWindow.launch();
			}
		});
		addDormitoryBtn.setBounds(288, 21, 118, 23);
		Login.getContentPane().add(addDormitoryBtn);
	}
}

	

