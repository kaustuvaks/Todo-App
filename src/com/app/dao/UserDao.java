package com.app.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.app.entities.*;
import com.app.helper.ConnectionProvider;
import com.app.rest.model.UserModel;

public class UserDao {
	
	private Connection con;

	public UserDao(Connection con) {
		this.con = con;
	}
	
	public boolean saveUser(User user) {
		
		boolean f = false;
		try {
			String query = "INSERT INTO users(first_name, last_name, email, password, date) VALUES (?,?,?,?,?) ";
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, user.getFirstName());
			pstmt.setString(2, user.getLastName());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getPassword());
//			pstmt.setString(5, user.getTimestamp().toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyyMMdd hh:mm:ss")));
//			System.out.println(user.getTimestamp());
			pstmt.setString(5, user.getTimestamp());
			
			pstmt.executeUpdate();
			f = true;
		}catch(Exception e) {
			System.out.println("DAOUSER");
			e.printStackTrace();
		}
		
		return f;
	}
	
	public User getUserByEmailAndPassword(String email, String password) {
		User user = null;
		
		String query = "SELECT * FROM users WHERE email=? and password=?";
		 
		try (PreparedStatement pstmt = con.prepareStatement(query);){
			pstmt.setString(1,email);
			pstmt.setString(2,password);
			
			ResultSet set = pstmt.executeQuery();
			if(set.next() && set!=null) {
				user = new User();
				
				user.setFirstName(set.getString("first_name"));
				user.setLastName(set.getString("last_name"));
				user.setId(Integer.valueOf(set.getString("uid")));
				user.setEmail(set.getString("email"));
				user.setTimestamp(set.getString("date"));
				
				System.out.println(user);	
			}
					
		}catch(Exception e) {
			System.out.println("LoginDAO");
			e.printStackTrace();
		}
		
		return user;
	}
	
	public List<User> getUsers(){
		List<User> userList = new ArrayList<User>();
		String query = "SELECT * FROM users;";
		
		try{
			Statement statement = ConnectionProvider.getConnection().createStatement();
			ResultSet set = statement.executeQuery(query);
			
			while(set.next() && set!=null) {
				User user = new User();
				
				user.setFirstName(set.getString("first_name"));
				user.setLastName(set.getString("last_name"));
				user.setId(Integer.valueOf(set.getString("uid")));
				user.setEmail(set.getString("email"));
				user.setTimestamp(set.getString("date"));
				
				userList.add(user);
			}
					
		}catch(Exception e) {
			System.out.println("LoginDAO");
			e.printStackTrace();
		}
		return userList;
	}
	
	public UserModel getUserById(long id) {
		UserModel user = new UserModel();
		
		String query = "SELECT * FROM users WHERE uid=?";
		 
		try (PreparedStatement pstmt = con.prepareStatement(query);){
			pstmt.setLong(1,id);
			
			ResultSet set = pstmt.executeQuery();
			if(set.next() && set!=null) {
				
				user.setFirstName(set.getString("first_name"));
				user.setLastName(set.getString("last_name"));
				user.setId(Integer.valueOf(set.getString("uid")));
				user.setEmail(set.getString("email"));
				user.setTimestamp(set.getString("date"));
				
				System.out.println(user);	
			}
			set.close();
			pstmt.close();
					
		}catch(Exception e) {
			System.out.println("LoginDAO");
			e.printStackTrace();
		}
		
		String query2 = "SELECT * FROM tasks WHERE fk_user=?";
		
		try (PreparedStatement pstmt = con.prepareStatement(query2);){
			pstmt.setLong(1,id);
			
			ResultSet set = pstmt.executeQuery();
			ArrayList<Task> tasks = new ArrayList<Task>();
			while(set.next() && set!=null) {
				tasks.add(new Task(set.getString("task_name"),set.getString("task_created"),set.getString("task_id")));
			}
			set.close();
			pstmt.close();
			user.setTaskList(tasks);
			
		}catch(Exception e) {
			System.out.println("LoginDAO");
			e.printStackTrace();
		}
		
		return user;
	}
	
	public boolean deleteUserById(long id) {
		boolean f = false;
		
		String query = "DELETE FROM users WHERE uid=?";
		 
		try (PreparedStatement pstmt = con.prepareStatement(query);){
			pstmt.setLong(1,id);
			f=pstmt.execute();
			pstmt.close();
					
		}catch(Exception e) {
			System.out.println("LoginDAO");
			e.printStackTrace();
		}
		
		String query2 = "DELETE FROM tasks WHERE fk_user=?";
		
		try (PreparedStatement pstmt = con.prepareStatement(query2);){
			pstmt.setLong(1,id);
			f=pstmt.execute();
			pstmt.close();
			
		}catch(Exception e) {
			System.out.println("LoginDAO");
			e.printStackTrace();
		}
		
		return f;
	}
}
