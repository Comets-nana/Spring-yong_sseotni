package com.gdgocdeu.yong_sseotni.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gdgocdeu.yong_sseotni.vo.Board;

@Repository
public class BoardDao {

	@Autowired
	SqlSession s;
	
	public int save(Board b) {
		return s.insert("BoardMapper.save", b);
	}
	
}
