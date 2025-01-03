package com.gdgocdeu.yong_sseotni.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdgocdeu.yong_sseotni.dao.BoardDao;
import com.gdgocdeu.yong_sseotni.vo.Board;

@Service
public class BoardService {

	@Autowired
	BoardDao boardDao;
	
	public List<Board> findByBoardPage(String sortBy, int start, int count, String delNy) {
		String orderBy = "created_date DESC";
        
		if ("oldest".equals(sortBy)) {
	        orderBy = "created_date ASC";
	    }
		
        return boardDao.findByBoardPage(orderBy, start, count, delNy);
    }
	
	public int deleteBoard(Board b) {
		return boardDao.deleteBoard(b);
	}
	
	public Board findByIdx(int board_idx) {
		return boardDao.findByIdx(board_idx);
	}
	
	public int updateBoard(Board b) {
		return boardDao.updateBoard(b);
	}
	
	public int save(Board b) {
		return boardDao.save(b);
	}
	
}
