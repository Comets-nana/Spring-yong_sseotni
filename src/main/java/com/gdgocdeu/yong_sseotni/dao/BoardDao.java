package com.gdgocdeu.yong_sseotni.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gdgocdeu.yong_sseotni.vo.Board;
import com.gdgocdeu.yong_sseotni.vo.User;

@Repository
public class BoardDao {

	@Autowired
	SqlSession s;
	
	public int deleteBoard(Board b) {
		return s.update("BoardMapper.deleteBoard", b);
	}
	
	public Board findByIdx(int board_idx) {
		return s.selectOne("BoardMapper.findByIdx", board_idx);
	}
	
	public int updateBoard(Board b) {
		return s.update("BoardMapper.updateBoard", b);
	}
	
	public int save(Board b) {
		return s.insert("BoardMapper.save", b);
	}
	
}
