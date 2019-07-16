package com.jason.biz.impl;

import java.util.List;

import com.jason.biz.PostBiz;
import com.jason.dao.PostDao;
import com.jason.dao.impl.mysql.PostDaoImpl;
import com.jason.entity.Post;

public class PostBizImpl implements PostBiz{

	private PostDao postdao = new PostDaoImpl();

	@Override
	public List<Post> getAllPosts() {
		// TODO Auto-generated method stub
		List<Post> listPosts = postdao.getAllPostsWithTitleAndDate();
		return listPosts;
	}

	@Override
	public List<Post> getPostByUsername(String name) {
		// TODO Auto-generated method stub
		List<Post> listPosts = postdao.getPostsByUsername(name);
		return listPosts;
	}

	@Override
	public boolean addPost(Post posts, String username) {
		// TODO Auto-generated method stub
		int result = postdao.addPosts(posts, username);
		if(result == 1) { 
			return true; 
			} 
		return false;
	}

	@Override
	public int deletePostById(int id) {
		// TODO Auto-generated method stub
		int result = postdao.delPosts(id);
		if(result == 1) { 
			return 1; 
			} 
		return 0;	
		}

	@Override
	public Post findPostById(int id) {
		// TODO Auto-generated method stub
		Post listPosts = postdao.getPostsById(id);
		return listPosts;
	}

}
