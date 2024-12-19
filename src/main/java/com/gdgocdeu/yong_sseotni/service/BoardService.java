package com.gdgocdeu.yong_sseotni.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdgocdeu.yong_sseotni.dao.BoardDao;
import com.gdgocdeu.yong_sseotni.vo.Board;
import com.gdgocdeu.yong_sseotni.vo.User;

@Service
public class BoardService {

	@Autowired
	BoardDao boardDao;
	
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
