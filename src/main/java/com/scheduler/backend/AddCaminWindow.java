package com.scheduler.backend;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.scheduler.backend.model.Camin;
import com.scheduler.backend.service.CaminService;
import com.scheduler.backend.service.UserService;

public class AddCaminWindow {

	private JFrame addCaminFrame;
	private JTextField adressField;
	private JTextField numberField;
	private JTextField roomCountField;
	private UserService userService = new UserService();

	public void launch() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCaminWindow window = new AddCaminWindow();
					window.addCaminFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AddCaminWindow() {
		initialize();
	}

	private void initialize() {
		addCaminFrame = new JFrame();
		addCaminFrame.setBounds(100, 100, 320, 320);
		addCaminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addCaminFrame.getContentPane().setLayout(null);

		adressField = new JTextField();
		adressField.setBounds(140, 60, 130, 20);
		addCaminFrame.getContentPane().add(adressField);
		adressField.setColumns(10);

		numberField = new JTextField();
		numberField.setBounds(140, 140, 130, 20);
		addCaminFrame.getContentPane().add(numberField);
		numberField.setColumns(10);

		roomCountField = new JTextField();
		roomCountField.setBounds(140, 180, 130, 20);
		addCaminFrame.getContentPane().add(roomCountField);
		roomCountField.setColumns(10);

		JLabel adressLabel = new JLabel("Adress");
		adressLabel.setBounds(60, 60, 45, 13);
		addCaminFrame.getContentPane().add(adressLabel);

		JLabel adminLabel = new JLabel("Admin Name");
		adminLabel.setBounds(45, 100, 80, 13);
		addCaminFrame.getContentPane().add(adminLabel);

		JLabel numberLabel = new JLabel("Camin number");
		numberLabel.setBounds(40, 140, 100, 20);
		addCaminFrame.getContentPane().add(numberLabel);

		JLabel roomCountLabel = new JLabel("Room count");
		roomCountLabel.setBounds(45, 180, 100, 20);
		addCaminFrame.getContentPane().add(roomCountLabel);

		JComboBox<String> adminNameComboBox = new JComboBox<String>(
				userService.findNamesForComboBox(userService.findAll()));
		adminNameComboBox.setBounds(140, 100, 130, 21);
		addCaminFrame.getContentPane().add(adminNameComboBox);

		JButton addButton = new JButton("Add");
		addButton.setBounds(165, 220, 70, 30);
		addCaminFrame.getContentPane().add(addButton);

		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CaminService caminService = new CaminService();
				String numberString = numberField.getText();
				if (caminService.isInteger(numberString)) {
					Integer numberInt = Integer.parseInt(numberString);
					Camin existing = caminService.findByNumber(numberInt);
					if (existing != null && existing.getNumber().equals(numberInt))
						JOptionPane.showMessageDialog(null, "Camin number already exists!Choose another number!");
					else {
						String roomsString = roomCountField.getText();
						if (caminService.isInteger(roomsString)) {// daca si numarul caminului e in regula si nr de
																	// camere introdus e valid
							Integer roomInt = Integer.parseInt(roomsString);
							Camin nou = new Camin();
							nou.setAdress(adressField.getText());
							nou.setNumber(numberInt);
							nou.setRoomCount(roomInt);
							nou.setAdmin(adminNameComboBox.getItemAt(adminNameComboBox.getSelectedIndex()));
							caminService.save(nou);
							// TODO: generate the rooms for the Camin you just added!
							JOptionPane.showMessageDialog(null, "New Camin added successfully");
							addCaminFrame.setVisible(false);
							addCaminFrame.dispose();
						} else {
							JOptionPane.showMessageDialog(null,
									"Invalid number in Room count field! Please choose a valid number!");
						}

					}

				} else {
					JOptionPane.showMessageDialog(null,
							"Invalid number in the Camin number field!Please choose a valid Number!");
				}
			}
		});
		
		addCaminFrame.getRootPane().setDefaultButton(addButton);

	}
}
