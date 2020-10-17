package com.scheduler.backend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.scheduler.backend.model.Room;
import com.scheduler.backend.model.Student;
import com.scheduler.backend.repository.RoomRepository;
import com.scheduler.backend.repository.StudentRepository;

public class gestiuneCamin {

	private JFrame frame;
	private JTable table;

	Connection connection = null;
	private JTextField studentFirstName;
	private JTextField studentLastName;
	private JTextField roomNumber;
	public String year = "I";
	public String faculty = "Arh";
	public String sex = "M";
	public String search;
	private JTextField emailTextField;
	private JTextField mediaTextField;

	public gestiuneCamin() {
		initialize(); 
	}

	public static void gestiuneCaminInit() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gestiuneCamin window = new gestiuneCamin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	void refreshTableHb() {
		StudentRepository stdRepo = new StudentRepository();
		table.setModel(stdRepo.studentToTableModel(stdRepo.findAll()));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/scheduler", "root", "test");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		frame = new JFrame("Gestiune Camine Observator");
		frame.setBounds(100, 100, 926, 503);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		Color c = new Color(252, 119, 3);
		Color bg = new Color(255, 150, 46);
		frame.getContentPane().setBackground(c);

		JButton btnAddStudent = new JButton("Add Student");
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String roomNr = roomNumber.getText();
				RoomRepository roomRepo = new RoomRepository();
				Room room = roomRepo.findByRoomNumber(roomNr);
				Student std = new Student();
				StudentRepository stdRepo = new StudentRepository();

				std.setFirstName(studentFirstName.getText());
				std.setLastName(studentLastName.getText());
				std.setSex(sex);
				std.setRoomNr(Integer.parseInt(roomNumber.getText()));
				std.setFaculty(faculty);
				std.setYearStudy(year);
				std.setEmail(emailTextField.getText());
				std.setMedia(Float.parseFloat(mediaTextField.getText()));

				try {
					if (room.getStudentsNr() < 4) {
						room.setStudentsNr(room.getStudentsNr() + 1);
						roomRepo.save(room);
						stdRepo.save(std);
						refreshTableHb();
					} else {
						JOptionPane.showMessageDialog(null,
								"Room " + Integer.parseInt(roomNumber.getText()) + " is Full");
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Can not insert, incomplete or incorrect data.");
				}
			}
		});
		btnAddStudent.setBounds(27, 32, 174, 23);
		frame.getContentPane().add(btnAddStudent);

		frame.getRootPane().setDefaultButton(btnAddStudent);

		JButton btnShowEmptyRooms = new JButton("Show Empty Rooms");
		btnShowEmptyRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RoomRepository roomRepo = new RoomRepository();
				table.setModel(roomRepo.roomToTableModel(roomRepo.findEmpltyRooms()));
			}
		});
		btnShowEmptyRooms.setBounds(223, 32, 146, 23);
		frame.getContentPane().add(btnShowEmptyRooms);

		JButton btnShowNotFullRooms = new JButton("Show Not Full Rooms");
		btnShowNotFullRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RoomRepository roomRepo = new RoomRepository();
				table.setModel(roomRepo.roomToTableModel(roomRepo.findNotFullRooms()));
			}
		});
		btnShowNotFullRooms.setBounds(379, 32, 157, 23);
		frame.getContentPane().add(btnShowNotFullRooms);

		JButton btnDelete = new JButton("Delete Student");
		btnDelete.setBounds(27, 66, 174, 23);
		frame.getContentPane().add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StudentRepository stdRepo = new StudentRepository();
				Long id = Long.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString());
				stdRepo.deleteById(id);
				RoomRepository roomRepo = new RoomRepository();
				String roomNr = table.getValueAt(table.getSelectedRow(), 4).toString();
				Room room = roomRepo.findByRoomNumber(roomNr);
				room.setStudentsNr(room.getStudentsNr() - 1);
				roomRepo.save(room);
				refreshTableHb();
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(223, 66, 675, 387);
		scrollPane.setBackground(bg);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshTableHb();

				Dimension dim = frame.getSize();

				System.out.println("dim.height = " + dim.height);

				scrollPane.setSize(675 + dim.width - 926, 387 + dim.height - 503);

			}
		});
		btnRefresh.setBounds(56, 419, 108, 23);
		frame.getContentPane().add(btnRefresh);

		JLabel lblStudentName = new JLabel("First Name");
		lblStudentName.setBounds(27, 100, 131, 23);
		frame.getContentPane().add(lblStudentName);

		studentFirstName = new JTextField();
		studentFirstName.setBounds(27, 124, 174, 23);
		frame.getContentPane().add(studentFirstName);
		studentFirstName.setColumns(10);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(27, 147, 89, 23);
		frame.getContentPane().add(lblLastName);

		studentLastName = new JTextField();
		studentLastName.setBounds(27, 170, 174, 23);
		frame.getContentPane().add(studentLastName);
		studentLastName.setColumns(10);

		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(27, 207, 46, 14);
		frame.getContentPane().add(lblGender);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "M", "F" }));
		comboBox.setMaximumRowCount(2);
		comboBox.setBounds(70, 204, 38, 20);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sex = comboBox.getSelectedItem().toString();
			}
		});
		frame.getContentPane().add(comboBox);

		JLabel lblRoom = new JLabel("Room");
		lblRoom.setBounds(118, 207, 46, 14);
		frame.getContentPane().add(lblRoom);

		roomNumber = new JTextField();
		roomNumber.setBounds(149, 204, 52, 20);
		frame.getContentPane().add(roomNumber);
		roomNumber.setColumns(10);

//		JSeparator separator = new JSeparator();
//		separator.setBackground(new Color(0, 255, 153));
//		separator.setOrientation(SwingConstants.VERTICAL);
//		separator.setBounds(209, 23, 5, 430);
//		frame.getContentPane().add(separator);

		JLabel lblFaculty = new JLabel("Faculty");
		lblFaculty.setBounds(27, 232, 46, 14);
		frame.getContentPane().add(lblFaculty);

		JComboBox FacultyComboBox = new JComboBox();
		FacultyComboBox.setMaximumRowCount(5);
		FacultyComboBox.setModel(new DefaultComboBoxModel(new String[] { "Arh", "AC", "ETTI", "IE", "Constr" }));
		FacultyComboBox.setBounds(27, 249, 57, 20);
		FacultyComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				faculty = FacultyComboBox.getSelectedItem().toString();
			}
		});
		frame.getContentPane().add(FacultyComboBox);

		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(118, 232, 46, 14);
		frame.getContentPane().add(lblYear);

		JComboBox YearComboBox = new JComboBox();
		YearComboBox.setModel(new DefaultComboBoxModel(new String[] { "I", "II", "III", "IV", "V", "VI" }));
		YearComboBox.setMaximumRowCount(6);
		YearComboBox.setBounds(118, 249, 57, 20);
		YearComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				year = YearComboBox.getSelectedItem().toString();
			}
		});
		frame.getContentPane().add(YearComboBox);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(
				new String[] { "SearchWhat?", "firstName", "lastName", "sex", "roomNr", "faculty", "yearStudy" }));
		comboBox_1.setBounds(223, 7, 123, 20);
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search = comboBox_1.getSelectedItem().toString();
			}
		});
		frame.getContentPane().add(comboBox_1);

		JTextField searchField = new JTextField();
		searchField.setBounds(437, 7, 313, 21);
		frame.getContentPane().add(searchField);
		searchField.setColumns(10);

		JButton SearchButton = new JButton("Search");
		SearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StudentRepository stdRepo = new StudentRepository();
				String comboBoxStr = search;
				String textFieldStr = searchField.getText();
				stdRepo.search(comboBoxStr, textFieldStr);
				table.setModel(stdRepo.studentToTableModel(stdRepo.search(comboBoxStr, textFieldStr)));
			}
		});
		SearchButton.setBounds(358, 7, 79, 20);
		frame.getContentPane().add(SearchButton);

		JButton showSelectedRoomBtn = new JButton("Show Selected Room\r\n");
		showSelectedRoomBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StudentRepository stdRepo = new StudentRepository();
				String roomNr = table.getValueAt(table.getSelectedRow(), 4).toString();
				System.out.println(roomNr);
				table.setModel(stdRepo.studentToTableModel(stdRepo.findByRoomNr(roomNr)));
			}
		});
		showSelectedRoomBtn.setBounds(546, 32, 204, 23);
		frame.getContentPane().add(showSelectedRoomBtn);

		emailTextField = new JTextField();
		emailTextField.setBounds(27, 304, 174, 20);
		frame.getContentPane().add(emailTextField);
		emailTextField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setBounds(27, 279, 46, 14);
		frame.getContentPane().add(lblNewLabel);

		mediaTextField = new JTextField();
		mediaTextField.setBounds(27, 357, 46, 20);
		frame.getContentPane().add(mediaTextField);
		mediaTextField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Media");
		lblNewLabel_1.setBounds(27, 332, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
