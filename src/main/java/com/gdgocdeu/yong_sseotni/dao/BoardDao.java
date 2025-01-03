package com.gdgocdeu.yong_sseotni.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gdgocdeu.yong_sseotni.vo.Board;

@Repository
public class BoardDao {

	@Autowired
	SqlSession s;
	
	public List<Board> findByBoardPage(String orderBy, int start, int count, String delNy) {
		Map<String, Object> params = new HashMap<>();
		params.put("orderBy", orderBy);
		params.put("start", start);
	    params.put("count", count);
	    params.put("delNy", delNy);
		return s.selectList("BoardMapper.findByBoardPage", params);
	}
	
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
