package com.jason.entity;

public class Post {
	private int postid;
	private String title;
	private String username;
	private String context;
	private int replycount;
	private String postingtime;
	public Post() {
	}
	public  Post(int id, String title, String author, String content,int count,String strDate) {
		this.postid = id;
		this.title = title;
		this.username = author;
		this.context = content;
		this.replycount = count;
		this.postingtime = strDate;
	}
	public  Post(int id, String title, String author, String content,String strDate) {
		this.postid = id;
		this.title = title;
		this.username = author;
		this.context = content;
		this.postingtime = strDate;
	}
	public  Post(int id, String title) {
		this.postid = id;
		this.title = title;
	}
	public  Post(int id, String title,String strDate) {
		this.postid = id;
		this.title = title;
		this.postingtime = strDate;
	}
	public  Post(String title,String context) {
		this.title = title;
		this.context = context;
	}
	public  Post(int postid, String title,String context,String strDate) {
		this.postid = postid;
		this.title = title;
		this.context = context;
		this.postingtime = strDate;
	}
	public int getPostid() {
		return postid;
	}
	public void setPostid(int postid) {
		this.postid = postid;
	}
	public String getTitle() {
		return title; 
	}
	public void setTitle(String title) {
		this.title = title;
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
	public int getReplycount() {
		return replycount;
	}
	public void setReplycount(int replycount) {
		this.replycount = replycount;
	}
	public String getPostingtime() {
		return postingtime;
	}
	public void setPostingtime(String postingtime) {
		this.postingtime = postingtime;
	}

	

}
