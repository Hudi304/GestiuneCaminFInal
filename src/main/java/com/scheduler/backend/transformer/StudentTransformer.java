package com.scheduler.backend.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.scheduler.backend.model.Student;

public class StudentTransformer implements Transformer<Student> {

	@Override
	public Student toModel(ResultSet resultSet) {
		
		try {
			if(resultSet.next()) {
				return buildStudent(resultSet);
			}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		return null;
		}

	@Override
	public List<Student> toModelList(ResultSet resultSet) {
			List<Student> studs = new ArrayList<>();
			try {
				while (resultSet.next()) {
					Student stud = buildStudent(resultSet);
					if (stud != null) {
						studs.add(stud);
					}
				}
			} catch (SQLException e) {
				// something went wrong here..
				e.printStackTrace();
			}
			return studs;
		}
	
	
	private Student buildStudent(ResultSet resultSet) throws SQLException {
		Student stud = new Student();
		stud.setFirstName(resultSet.getString("first_name"));
		stud.setLastName(resultSet.getString("last_name"));
		stud.setSex(resultSet.getString("gender"));
		stud.setRoomNr(resultSet.getInt("roomNr"));
		return stud;
	}

}
