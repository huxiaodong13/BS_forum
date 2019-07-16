package com.jason.biz;

import java.util.List;
import com.jason.entity.Post;


public interface PostBiz{
	List<Post> getAllPosts();
	
	List<Post> getPostByUsername(String name);
	
	boolean addPost(Post posts, String username);
	
	Post findPostById(int id);
	
	int deletePostById(int id);
}
