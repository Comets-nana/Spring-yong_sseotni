package com.gdgocdeu.yong_sseotni.vo;

import java.time.LocalDateTime;

public class User {
	
	private int user_idx=0;
	private String login_provider=null;
	private String user_email=null;
	private String user_pw=null;
	private String user_nick=null;
	private String user_birth=null;
	private LocalDateTime created_date=null;
	private LocalDateTime modified_date=null;
	private String del_ny=null;
	
	
	
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public String getLogin_provider() {
		return login_provider;
	}
	public void setLogin_provider(String login_provider) {
		this.login_provider = login_provider;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_nick() {
		return user_nick;
	}
	public void setUser_nick(String user_nick) {
		this.user_nick = user_nick;
	}
	public String getUser_birth() {
		return user_birth;
	}
	public void setUser_birth(String user_birth) {
		this.user_birth = user_birth;
	}
	public LocalDateTime getCreated_date() {
		return created_date;
	}
	public void setCreated_date(LocalDateTime created_date) {
		this.created_date = created_date;
	}
	public LocalDateTime getModified_date() {
		return modified_date;
	}
	public void setModified_date(LocalDateTime modified_date) {
		this.modified_date = modified_date;
	}
	public String getDel_ny() {
		return del_ny;
	}
	public void setDel_ny(String del_ny) {
		this.del_ny = del_ny;
	}
	
}
