package com.jason.entity;

public class Comment {
	private int commentid;
	private int postid;
	private String username;
	private String context;
	private String commenttime;
	
	public Comment(int postid, String username, String context) {
		super();
		this.postid = postid;
		this.username = username;
		this.context = context;
	}
	
	public int getCommentid() {
		return commentid;
	}
	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}
	public int getPostid() {
		return postid;
	}
	public void setPostid(int postid) {
		this.postid = postid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username; 
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getCommenttime() {
		return commenttime;
	}
	public void setCommenttime(String commenttime) {
		this.commenttime = commenttime;
	}

}
