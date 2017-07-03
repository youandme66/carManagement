package com.groupfour.models;
/**
 * 
 * @author xsk
 *
 */
import java.io.Serializable;

public class UsersModel implements Serializable{
	private int user_id;
	private int role_id;
	private String user_name;
	private String user_password;
	private String user_rename;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_rename() {
		return user_rename;
	}
	public void setUser_rename(String user_rename) {
		this.user_rename = user_rename;
	}

}
