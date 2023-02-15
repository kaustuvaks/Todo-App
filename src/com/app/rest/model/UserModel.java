package com.app.rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.app.entities.User;
import com.app.entities.Task;

@XmlRootElement
public class UserModel extends User{
	private long id;
	private List<Task> taskList;
	
	
	public UserModel(java.lang.String firstName, java.lang.String lastName, java.lang.String email,
				java.lang.String password, java.lang.String timestamp, java.lang.String profile, long id,
				List<Task> taskList) {
			super(firstName, lastName, email, password, timestamp, profile);
			this.id = id;
			this.taskList = taskList;
		}
	public UserModel(long id,List<Task> taskList) {
		super(id);
		this.id = id;
		this.taskList = taskList;
	}

	//    private String name;
//    private String email;
    
    public UserModel() {}
    
//    public UserModel(long id, String name, String email) {
//    	super();
//    	this.id = id;
//    	this.name = name;
//    	this.email = email;
//    }
    public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	
    public long getId() {
		return id;
	}
	
    public void setId(long id) {
		this.id = id;
	}
	
//    public String getName() {
//		return name;
//	}
//	
//    public void setName(String name) {
//		this.name = name;
//	}
//	
//    public String getEmail() {
//		return email;
//	}
//	
//    public void setEmail(String email) {
//		this.email = email;
//	}
}
