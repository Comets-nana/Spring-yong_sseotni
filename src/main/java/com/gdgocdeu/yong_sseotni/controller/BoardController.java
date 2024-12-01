package com.gdgocdeu.yong_sseotni.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gdgocdeu.yong_sseotni.service.BoardService;
import com.gdgocdeu.yong_sseotni.vo.Board;

@RestController
@CrossOrigin()
@RequestMapping(value="api/board")
public class BoardController {

	@Autowired
	BoardService boardService;
	
	// 수입/지출 등록
	@PostMapping("save")
	public ResponseEntity<?> save (
			@RequestParam(value="user_idx") int user_idx,
			@RequestParam(value="board_title") String board_title,
			@RequestParam(value="board_content") String board_content
			) {
		
		Board b = new Board();
		
		b.setUser_idx(user_idx);
		b.setBoard_title(board_title);
		b.setBoard_content(board_content);
		
		boardService.save(b);
		
		return new ResponseEntity<Board>(b, HttpStatus.OK);
		
	}
	
}
