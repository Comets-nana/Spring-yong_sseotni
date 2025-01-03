package com.gdgocdeu.yong_sseotni.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gdgocdeu.yong_sseotni.service.BoardService;
import com.gdgocdeu.yong_sseotni.service.UserService;
import com.gdgocdeu.yong_sseotni.vo.Board;
import com.gdgocdeu.yong_sseotni.vo.User;

@RestController
@CrossOrigin()
@RequestMapping(value="api/board")
public class BoardController {

	@Autowired
	BoardService boardService;
	
	@Autowired
	UserService userService;
	
	// 게시글 조회(정렬조건, 페이지네이션 추가)
	@GetMapping("findByBoardPage")
	public List<Board> findByBoardPage(
			@RequestParam(defaultValue = "latest") String sortBy,
			@RequestParam(defaultValue = "0") int start, 
	        @RequestParam(defaultValue = "10") int count
			) {
		return boardService.findByBoardPage(sortBy, start, count, "n");
	}
	
	// 게시글 삭제
	@PostMapping("deleteBoard")
	public ResponseEntity<String> deleteBoard(
			@RequestParam(value="board_idx") int board_idx,
			@RequestParam(value="user_idx") int user_idx
			) {
		
		User user = userService.findByIdx(user_idx);
		if (user == null) {
	        return new ResponseEntity<>("존재하지 않는 회원입니다.", HttpStatus.NOT_FOUND);
	    }
		
		Board board = boardService.findByIdx(board_idx);
		if (board == null) {
	        return new ResponseEntity<>("존재하지 않는 게시글입니다.", HttpStatus.NOT_FOUND);
	    }
		
		// 요청 사용자와 게시글 작성자 비교
	    if (board.getUser_idx() != user_idx) {
	        return new ResponseEntity<>("삭제할 수 있는 권한이 없습니다.", HttpStatus.FORBIDDEN);
	    }
		
		board.setDel_ny("y");
		int result = boardService.deleteBoard(board);
		
		if (result > 0) {
			return new ResponseEntity<>("게시글 삭제가 완료되었습니다.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("게시글 삭제를 실패하였습니다.", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	// 게시글 수정
	@PostMapping("updateBoard")
	public ResponseEntity<?> updateUser(
			@RequestParam(value="board_idx") int board_idx,
			@RequestParam(value="board_title") String board_title,
			@RequestParam(value="board_content") String board_content,
			@RequestParam(value="user_idx") int user_idx
			) {
		
		User user = userService.findByIdx(user_idx);
		if (user == null) {
	        return new ResponseEntity<>("존재하지 않는 회원입니다.", HttpStatus.NOT_FOUND);
	    }
		
		Board board = boardService.findByIdx(board_idx);
		if (board == null) {
	        return new ResponseEntity<>("존재하지 않는 게시글입니다.", HttpStatus.NOT_FOUND);
	    }
		
		// 요청 사용자와 게시글 작성자 비교
	    if (board.getUser_idx() != user_idx) {
	        return new ResponseEntity<>("수정할 수 있는 권한이 없습니다.", HttpStatus.FORBIDDEN);
	    }
		
		board.setBoard_title(board_title);
		board.setBoard_content(board_content);
		
		int result = boardService.updateBoard(board);
		if (result > 0) {
			return new ResponseEntity<>("게시글 수정이 완료되었습니다.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("게시글 수정이 실패하였습니다.", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	// 게시글 작성
	@PostMapping("save")
	public ResponseEntity<?> save (
			@RequestParam(value="user_idx") int user_idx,
			@RequestParam(value="board_title") String board_title,
			@RequestParam(value="board_content") String board_content
			) {
		
		User user = userService.findByIdx(user_idx);
		
		if (user == null) {
	        return new ResponseEntity<>("로그인 후 작성 가능합니다.", HttpStatus.NOT_FOUND);
	    }
		
		Board b = new Board();
		
		b.setUser_idx(user_idx);
		b.setBoard_title(board_title);
		b.setBoard_content(board_content);
		
		boardService.save(b);
		
		return new ResponseEntity<Board>(b, HttpStatus.OK);
		
	}
	
}
