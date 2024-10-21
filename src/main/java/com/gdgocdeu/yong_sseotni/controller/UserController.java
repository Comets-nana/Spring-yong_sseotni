package com.gdgocdeu.yong_sseotni.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gdgocdeu.yong_sseotni.service.UserService;
import com.gdgocdeu.yong_sseotni.vo.User;

@RestController
@CrossOrigin()
@RequestMapping(value="api/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	// 회원가입
	@PostMapping("join")
	public ResponseEntity<?> join (
			@RequestParam(value="login_provider") String login_provider,
			@RequestParam(value="user_email") String user_email,
			@RequestParam(value="user_pw") String user_pw,
			@RequestParam(value="user_nick") String user_nick,
			@RequestParam(value="user_birth") String user_birth,
			@RequestParam(value="target_amount") int target_amount
			) {
		
		// 이메일 유효성 검사
		User ue = userService.findByEmail(user_email);
		
		if (ue != null) {
			return new ResponseEntity<String>("이미 가입된 회원입니다.", HttpStatus.FORBIDDEN);
		}
		
		if (login_provider.isEmpty()) {
			login_provider= "basic";
		}
		
		
		// 회원가입
		User u = new User();
		
		u.setLogin_provider(login_provider);
		u.setUser_email(user_email);
		u.setUser_pw(user_pw);
		u.setUser_nick(user_nick);
		u.setUser_birth(user_birth);
		u.setTarget_amount(target_amount);
		
		
		userService.join(u);
		
		User result = userService.findByEmail(user_email);
		return new ResponseEntity<User>(result, HttpStatus.OK);
		
	}
	
}
