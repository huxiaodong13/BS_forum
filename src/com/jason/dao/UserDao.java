package com.jason.dao;

import com.jason.entity.User;

public interface UserDao {
	User findUserByName(String name);
	int countUserByName(String name);
	
	int insertUser(User user);
	int countUser();
}
