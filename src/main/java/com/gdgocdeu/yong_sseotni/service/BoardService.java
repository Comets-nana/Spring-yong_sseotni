package com.gdgocdeu.yong_sseotni.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdgocdeu.yong_sseotni.dao.BoardDao;
import com.gdgocdeu.yong_sseotni.vo.Board;

@Service
public class BoardService {

	@Autowired
	BoardDao boardDao;
	
	public int save(Board b) {
		return boardDao.save(b);
	}
	
}
