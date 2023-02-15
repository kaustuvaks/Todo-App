package com.app.rest.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;

import com.app.dao.UserDao;
import com.app.entities.User;
import com.app.helper.ConnectionProvider;
import com.app.rest.model.UserModel;

public class UserService {
	private static List<UserModel> users = new ArrayList<UserModel>();

    public List<User> fetchAll() {
    	
    	UserDao dao = new UserDao(ConnectionProvider.getConnection());
    	List<User> userList = dao.getUsers();
    	
        return userList;
    }

    public User fetchBy(long id) throws NotFoundException {
    	 UserDao dao = new UserDao(ConnectionProvider.getConnection());
         User user = dao.getUserById(id);
         if(user == null) {
        	 return null;
         }else {
        	 return user;
         }
     }

    public List<UserModel> create(UserModel user) {
         users.add(user);
         return users;
    }

    public void update(long id,UserModel user) {
         for (UserModel user2 : users) {
            if (id == user2.getId()) {
                users.remove(user2);
                users.add(user);
            }
         }
    }

    public void delete(long id) throws NotFoundException {
    	
    	UserModel userX = null;
    	boolean flag = true;
    	for (UserModel user : users) {
            if (user.getId() == id) {
                userX = user;
            }
        }
    	if(flag) users.remove(userX);
    }
}
