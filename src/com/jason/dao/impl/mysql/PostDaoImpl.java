package com.jason.dao.impl.mysql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jason.dao.BaseDao;
import com.jason.dao.PostDao;
import com.jason.dao.ResultSetProcessor;
import com.jason.entity.Post;

public class PostDaoImpl extends BaseDao implements PostDao{
	
	//根据回复数量排序获取所有帖子
	public List<Map<String, Object>>  findallPosts() {
		String sql = "select post.*,photo from post,user where post.username=user.username ORDER BY post.replycount DESC";

		ResultSetProcessor getUsersByNameProcessor = new ResultSetProcessor(){
	
			public Object process(ResultSet rs) throws SQLException {
				List<Map<String, Object>> posts = new ArrayList();		
				//结果集（rs）的结构信息，比如字段数和字段名等
				ResultSetMetaData rsmd = rs.getMetaData();
				// 取得查询出来的字段个数
				int columnCount = rsmd.getColumnCount();
				
				while(rs.next()) { 
					//新建一个Map集合，将查询出内容按照字段名：值得键值对形式存储在map集合中
					Map<String, Object> post = new HashMap<String,Object>();
					//循环所有查询出的字段
					for (int i = 1; i <=columnCount; i++) {
						if (rsmd.getColumnName(i).equals("postingtime")) {
						    Timestamp timestamp=rs.getTimestamp(i);
							
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
							String timeStr = df.format(timestamp);   
							System.out.println(timeStr);
							post.put(rsmd.getColumnName(i), timeStr);
							continue;
							 
						}
						post.put(rsmd.getColumnName(i), rs.getObject(i));
					}
					
					posts.add(post);
	
				}
				
				System.out.println(posts);
				return posts;
				
			}
	
		};
		
	
		
		return (List<Map<String, Object>> )this.executeQuery(getUsersByNameProcessor, sql);
	}
	
	//根据帖子序号排序获取所有帖子
	public List<Post> getAllPosts() {
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//获取所有tiezi
		String sql = "select post.postid as postid, tilte, context, user.username as username, postingtime from post, user where post.username = user.username order by post.postid";
		
		ResultSetProcessor postsProcessor = new ResultSetProcessor() {

			public Object process(ResultSet rs) throws SQLException {
				List<Post> listPosts = new ArrayList<Post>();
				
				while(rs != null && rs.next()) {
					int id = rs.getInt("postid");
					String title = rs.getString("title");
					String context = rs.getString("context");
					String author = rs.getString("username");
					Date date = rs.getDate("postingtime");
					String strDate = sdf.format(date);
					int count = 0;
					
					Post posts = new Post(id, title, author,context,count,strDate);
					listPosts.add(posts);
				}
				return listPosts;
			}
			
		};
		
		Object result = executeQuery(postsProcessor, sql, null);
		
		return (List<Post>)result;
	}

	public List<Post> getAllPostsOnlyTitle() {
		String sql = "select postid, title from post order by postid";
		
		ResultSetProcessor postsProcessor = new ResultSetProcessor() {

			public Object process(ResultSet rs) throws SQLException {
				List<Post> listPosts = new ArrayList<Post>();
				while(rs != null && rs.next()) {
					int id = rs.getInt("postid");
					String title = rs.getString("title");
					
					Post posts = new Post(id, title);
					listPosts.add(posts);
				}
				return listPosts;
			}
			
		};
		
		Object result = executeQuery(postsProcessor, sql, null);
		
		return (List<Post>)result;
	}
	
	public List<Post> getAllPostsWithTitleAndDate() {
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		String sql = "select postid, title, postingtime from post order by postid";
		
		ResultSetProcessor postsProcessor = new ResultSetProcessor() {

			public Object process(ResultSet rs) throws SQLException {
				List<Post> listPosts = new ArrayList<Post>();
				while(rs != null && rs.next()) {
					int id = rs.getInt("postid");
					String title = rs.getString("title");
					Date date = rs.getDate("postingtime");
					String strDate = sdf.format(date);
					
					Post news = new Post(id, title, strDate);
					listPosts.add(news);
				}
				return listPosts;
			}
			
		};
		
		Object result = executeQuery(postsProcessor, sql, null);
		
		return (List<Post>)result;
	}

	public Post getPostsById(int id) {
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		//获取tiezi
		String sql = "select post.postid as postid, title, context, user.username as username, postingtime from post, user where post.username = user.username and post.postid = ?";
		Object[] params = {id};
		
		ResultSetProcessor postsProcessor = new ResultSetProcessor() {

			public Object process(ResultSet rs) throws SQLException {
				Post posts = null;
				if(rs != null && rs.next()) {
					int id = rs.getInt("postid");
					String title = rs.getString("title");
					String context = rs.getString("context");
					String author = rs.getString("username");
					Date date = rs.getDate("postingtime");
					String strDate = sdf.format(date);
					
					posts = new Post(id, title, context, author, strDate);
				}
				return posts;
			}
			
		};
		
		Object result = executeQuery(postsProcessor, sql, params);
		
		return (Post)result;
	}

	public int addPosts(Post posts, String username) {
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = new Date();
		String strDate = sdf.format(date);
		String sql = "insert into post (title,username,context,replycount,postingtime) values(?, ?, ?, ?, ?)";
		Object[] params = {posts.getTitle(), username, posts.getContext(),0,strDate};
		return this.executeUpdate(sql, params);
	}


	@Override
	public List<Post> getPostsByUsername(String name) {
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		String sql = "select postid, title, postingtime from post where username = ?";
		Object[] params = {name};
		System.out.println(sql);
		ResultSetProcessor postsProcessor = new ResultSetProcessor() {

			public Object process(ResultSet rs) throws SQLException {
				List<Post> listPosts = new ArrayList<Post>();
				while(rs != null && rs.next()) {
					int id = rs.getInt("postid");
					String title = rs.getString("title");
					Date date = rs.getDate("postingtime");
					String strDate = sdf.format(date);
					
					Post news = new Post(id, title, strDate);
					listPosts.add(news);
				}
				return listPosts;
			}
			
		};
		
		Object result = executeQuery(postsProcessor, sql, params);
		
		return (List<Post>)result;
	}


	@Override
	public int delPosts(int id) {
		String sql = "delete from post where id = ?";
		return executeUpdate(sql,id);
	}
	
	public int delPostByTitle(String title) {
		String sql = "delete from post where title = ?";
		return executeUpdate(sql,title);
	}
	
	public Map<String, Object>  findPostbyid(int postid) {
		String sql = "select post.*,photo from post,user where post.username=user.username and postid=?";
		Object[] params = {postid};

		ResultSetProcessor getUsersByNameProcessor = new ResultSetProcessor(){
	
			public Object process(ResultSet rs) throws SQLException {
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();
				Map<String, Object> post = new HashMap<String,Object>();
				if(rs.next()) { 
				
					for (int i = 1; i <=columnCount; i++) {
						if (rsmd.getColumnName(i).equals("postingtime")) {
						    Timestamp timestamp=rs.getTimestamp(i);
							
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
							String timeStr = df.format(timestamp);   
							System.out.println(timeStr);
							post.put(rsmd.getColumnName(i), timeStr);
							continue;
							 
						}
						post.put(rsmd.getColumnName(i), rs.getObject(i));
					}
				}
				
				System.out.println(post);
				return post;				
			}
	
		};
			
		return (Map<String, Object>)this.executeQuery(getUsersByNameProcessor, sql,params);
	}
	//修改帖子
	public int update(Post post) {
		System.out.println(post);
			String sql = "update post set title=?, context=? ,postingtime=? where postid=?";
			Object[] params = {post.getTitle(),post.getContext(),post.getPostingtime(),post.getPostid()};
			return this.executeUpdate(sql, params);
		
		}

//计算帖子数
	@Override
	public int countPosts() {
		String sql = "select count(title) post_count from post";
		int post_count = (Integer)executeQuery((rs)->{
			rs.next();
			int count = rs.getInt("post_count");
			
			return new Integer(count);
		}, sql, null);
		return post_count;
	}
	//通过标题找
	public Post getPostByTitle(String title) {
		String sql = "select * from post where title=?";
		Object[] params = {title};
		ResultSetProcessor postsProcessor = new ResultSetProcessor() {

			public Object process(ResultSet rs) throws SQLException {
				Post listPosts = new Post();
				while(rs != null && rs.next()) {
					int id = rs.getInt("postid");
					String title = rs.getString("title");
					String author = rs.getString("username");
					String content = rs.getString("context");
					String strDate = rs.getString("postingtime");
					Post posts = new Post(id, title, author,content,strDate);
					listPosts = posts;
				}
				return listPosts;
			}
		};
		Object result = executeQuery(postsProcessor, sql, params);
		return (Post)result;
	}


}
