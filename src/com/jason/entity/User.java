package com.jason.entity;

public class User {
	
	private int id;
	private String username;
	private String password;
	private String email;
	private String photo;
	
	public User(int id,String username,String password,String email,String photo) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.photo = photo;
	}
	
	public User(String username) {
		this.username = username;
		this.password = "";
		this.email = "";
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
