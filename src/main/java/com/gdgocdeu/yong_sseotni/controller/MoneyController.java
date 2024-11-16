package com.gdgocdeu.yong_sseotni.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gdgocdeu.yong_sseotni.service.MoneyService;
import com.gdgocdeu.yong_sseotni.vo.DailyMoney;
import com.gdgocdeu.yong_sseotni.vo.Money;

@RestController
@CrossOrigin()
@RequestMapping(value="api/money")
public class MoneyController {

	@Autowired
	MoneyService moneyService;
	
	// (달력) 일일 수입/지출 총액 데이터 계산해서 불러오기
	@GetMapping("/getDailyTotal")
	public ResponseEntity<Map<String, BigDecimal>> getDailyTotal (
			@RequestParam int user_idx,
			@RequestParam int year,
			@RequestParam int month,
			@RequestParam int day
			) {
		Map<String, BigDecimal> dailyTotal = moneyService.getDailyTotal(user_idx, year, month, day);
		return ResponseEntity.ok(dailyTotal);
	}
	
	// (달력) 일일 수입/지출 내역 불러오기
	@GetMapping("/getDailyMoneyList")
	public ResponseEntity<List<DailyMoney>> getDailyMoneyList (
			@RequestParam int user_idx,
			@RequestParam int year,
			@RequestParam int month,
			@RequestParam int day) {
		List<DailyMoney> dailyList = moneyService.getDailyMoneyList(user_idx, year, month, day);
		return ResponseEntity.ok(dailyList);
	}
	
	// (달력) 월 수입/지출 총액 데이터 계산해서 불러오기
	@GetMapping("/getMonthlyTotal")
	public ResponseEntity<Map<String, BigDecimal>> getMonthlyTotal (
			@RequestParam int user_idx,
			@RequestParam int year,
            @RequestParam int month) {
		Map<String, BigDecimal> totals = moneyService.getMonthlyTotal(user_idx, year, month);
        return ResponseEntity.ok(totals);
	}
	
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
