package com.scheduler.backend.service;

import java.util.List;

import com.scheduler.backend.model.User;
import com.scheduler.backend.repository.UserRepository;



public class UserService implements Service<User> {

	private UserRepository userRepository = new UserRepository();

	@Override
	public User save(User entity) {
		return userRepository.save(entity);
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public boolean delete(User entity) {
		return userRepository.delete(entity);
	}

	@Override
	public boolean deleteById(Long id) {
		return userRepository.deleteById(id);
	}

	public User findByCredentials(String username, String password) {
		return userRepository.findByCredentials(username, password);
	}

	public User findByUserName(String username) {
		return userRepository.findByUserName(username);
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public String[] findNamesForComboBox(List<User> list) {
		String[] result = new String[list.size()];
		int j = 0;
		for (User i : list) {
			if (i.getName().compareTo("ADMIN") != 0) {
				result[j] = i.getName();
				j++;
			}
		}

		return result;
	}
}
