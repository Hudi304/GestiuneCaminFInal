package com.scheduler.backend.service;

import java.util.List;

import com.scheduler.backend.model.Student;
import com.scheduler.backend.repository.StudentRepository;


public class StudentService implements Service<Student> {
	
	private StudentRepository studentRepository;
	
	@Override
	public Student save(Student entity) {
		// TODO Auto-generated method stub
		return studentRepository.save(entity);
	}

	@Override
	public Student findById(Long id) {
		// TODO Auto-generated method stub
		return studentRepository.findById(id);
	}

	@Override
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
	}

	@Override
	public boolean delete(Student entity) {
		// TODO Auto-generated method stub
		return studentRepository.delete(entity);
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return studentRepository.deleteById(id);
	}

}
