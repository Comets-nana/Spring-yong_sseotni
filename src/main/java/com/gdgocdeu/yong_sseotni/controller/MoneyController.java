package com.gdgocdeu.yong_sseotni.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gdgocdeu.yong_sseotni.service.MoneyService;
import com.gdgocdeu.yong_sseotni.vo.Money;

@RestController
@CrossOrigin()
@RequestMapping(value="api/money")
public class MoneyController {

	@Autowired
	MoneyService moneyService;
	
	// 수입/지출 등록
	@PostMapping("save")
	public ResponseEntity<?> save (
			@RequestParam(value="user_idx") int user_idx,
			@RequestParam(value="money_when") String money_when,
			@RequestParam(value="money_type") String money_type,
			@RequestParam(value="money_where") String money_where,
			@RequestParam(value="money_in") int money_in,
			@RequestParam(value="money_out") int money_out
			) {
		
		Money m = new Money();
		
		m.setUser_idx(user_idx);
		m.setMoney_when(money_when);
		m.setMoney_type(money_type);
		m.setMoney_where(money_where);
		m.setMoney_in(money_in);
		m.setMoney_out(money_out);
		
		moneyService.save(m);
		
		return new ResponseEntity<Money>(m, HttpStatus.OK);
		
	}
	
}
