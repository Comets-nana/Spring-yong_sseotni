package com.gdgocdeu.yong_sseotni.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gdgocdeu.yong_sseotni.vo.User;

@Repository
public class UserDao {
	
	@Autowired
	SqlSession s;
	
	public User findByEmail(String user_email) {
		return s.selectOne("UserMapper.findByEmail", user_email);
	}
	
	public int join(User u) {
		return s.insert("UserMapper.join", u);
	}
	
}
