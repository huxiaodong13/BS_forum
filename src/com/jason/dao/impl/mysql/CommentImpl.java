package com.jason.dao.impl.mysql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jason.dao.BaseDao;
import com.jason.dao.ResultSetProcessor;
import com.jason.entity.Comment;

public class CommentImpl  extends BaseDao  {
	
	public int insert(Comment comment) {
		String sql = "insert comment(postid,username,context) values(?,?,?)";
		String sql2 = "update post set replycount=replycount+1 where postid="+comment.getPostid();
		if(this.executeUpdate(sql2,null)>0){
			System.out.println("更新成功");
			Object[] params = {comment.getPostid(), comment.getUsername(),comment.getContext()};
			return this.executeUpdate(sql, params);
		}
		else{
			return -1; //更新失败
		}
		
	} 
	
	
	public String findLastcomment(int postid) {
		String sql = "select username from comment where postid=? order by commenttime";
		Object[] params = {postid};
		
		ResultSetProcessor countUserByNameProcessor = new ResultSetProcessor(){
	
			public Object process(ResultSet rs) throws SQLException {
				String name="不存在该用户";
				if(rs != null) {
					if(rs.next()) {
						name = rs.getString("username");
					}
				}
				
				return name;
			}
	
		};
		
		return (String)this.executeQuery(countUserByNameProcessor, sql, params);
	}
	
	
	public List<Map<String, Object>>  findallCommentsBypostid(int postid) {
		String sql = "select comment.*,photo from comment,user where comment.username=user.username and postid=?";
		Object[] params = {postid};
		

		ResultSetProcessor getUsersByNameProcessor = new ResultSetProcessor(){
	
			public Object process(ResultSet rs) throws SQLException {
				List<Map<String, Object>> comments = new ArrayList();		
				//结果集（rs）的结构信息，比如字段数和字段名等
				ResultSetMetaData rsmd = rs.getMetaData();
				// 取得查询出来的字段个数
				int columnCount = rsmd.getColumnCount();
				
				while(rs.next()) { 
					//新建一个Map集合，将查询出内容按照字段名：值得键值对形式存储在map集合中
					Map<String, Object> comment = new HashMap<String,Object>();
					//循环所有查询出的字段
					for (int i = 1; i <=columnCount; i++) {
						if (rsmd.getColumnName(i).equals("commenttime")) {
//								 Timestamp timestamp=new Timestamp(new Date().getTime());
						    Timestamp timestamp=rs.getTimestamp(i);
							
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
							String timeStr = df.format(timestamp);   
							System.out.println(timeStr);
							comment.put(rsmd.getColumnName(i), timeStr);
							continue;
							 
						}
						comment.put(rsmd.getColumnName(i), rs.getObject(i));
					}
					comments.add(comment);	
				}
				
				//System.out.println("评论为"+comments);
				return comments;
				
			}
	
		};		
		return (List<Map<String, Object>> )this.executeQuery(getUsersByNameProcessor, sql,params);
	}

	public int countComment() {
		String sql = "select count(commentid) comment_count from comment";
		int comment_count = (Integer)executeQuery((rs)->{
			rs.next();
			int count = rs.getInt("comment_count");
			
			return new Integer(count);
		}, sql, null);
		return comment_count;
	} 

}
