package com.jason.dao;

import java.util.List;

import com.jason.entity.Post;

public interface PostDao{
	//获取所有tiezi
	public List<Post> getAllPosts();	
	
	public List<Post> getAllPostsOnlyTitle();
	
	public List<Post> getAllPostsWithTitleAndDate();
	
	public List<Post> getPostsByUsername(String name);
	
	public int addPosts(Post posts, String username);
	
	public Post getPostsById(int id);
	
	public int delPosts(int id);
	
	public int countPosts();

	public Post getPostByTitle(String key);
}