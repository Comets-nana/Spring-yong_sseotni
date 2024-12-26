package com.gdgocdeu.yong_sseotni.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
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
import com.gdgocdeu.yong_sseotni.service.UserService;
import com.gdgocdeu.yong_sseotni.vo.DailyMoney;
import com.gdgocdeu.yong_sseotni.vo.Money;
import com.gdgocdeu.yong_sseotni.vo.MonthlyMoney;
import com.gdgocdeu.yong_sseotni.vo.User;

@RestController
@CrossOrigin()
@RequestMapping(value="api/money")
public class MoneyController {

	@Autowired
	MoneyService moneyService;
	
	@Autowired
	UserService userService;
	
	// 수입/지출 데이터 삭제
	@PostMapping("/deleteMoney")
	public ResponseEntity<String> deleteMoney(
			@RequestParam(value="money_idx") int money_idx
			) {
		Money money = moneyService.findByIdx(money_idx);
		
		if (money == null) {
	        return new ResponseEntity<>("데이터가 존재하지 않습니다.", HttpStatus.NOT_FOUND);
	    }
		
		money.setDel_ny("y");
		moneyService.deleteMoney(money);
		
		return new ResponseEntity<String>("데이터가 삭제되었습니다.", HttpStatus.OK);
	}
	
	// 월별 사용처별 지출 비중
	@GetMapping("/compareMoneyDetail")
	public ResponseEntity<Map<String, Object>> compareMoneyDetail (
			@RequestParam int user_idx,
			@RequestParam int year,
			@RequestParam int month
			) {
	    
		List<Map<String, BigDecimal>> typePercentages = moneyService.compareMoneyDetail(user_idx, year, month);

	    Map<String, Object> response = new HashMap<>();
	    if (typePercentages != null && !typePercentages.isEmpty()) {
	        response.put("moneyTypePercentages", typePercentages);
	    } else {
	        response.put("message", "데이터가 없습니다.");
	    }

	    return ResponseEntity.ok(response);
	    
	}
	
	// 지난 달과 이번 달과의 지출 비교
	@GetMapping("/compareMoney")
	public ResponseEntity<Map<String, Object>> compareMoney (
			@RequestParam int user_idx,
			@RequestParam int currentYear,
			@RequestParam int currentMonth
			) {
		
		int lastYear = currentMonth == 1 ? currentYear - 1 : currentYear;
	    int lastMonth = currentMonth == 1 ? 12 : currentMonth - 1;
	    
		Map<String, BigDecimal> compareResult = moneyService.compareMoney(user_idx, currentYear, currentMonth, lastYear, lastMonth);
		
	    BigDecimal currentMonthOut = compareResult.getOrDefault("currentMonthOut", BigDecimal.ZERO);
	    BigDecimal lastMonthOut = compareResult.getOrDefault("lastMonthOut", BigDecimal.ZERO);

	    BigDecimal difference = currentMonthOut.subtract(lastMonthOut).abs();
	    
	    DecimalFormat formatter = new DecimalFormat("#,###");
	    String formattedDifference = formatter.format(difference);
	    String message;
	    
	    if (currentMonthOut.compareTo(lastMonthOut) > 0) {
	        message = "저번 달보다 " + formattedDifference + "원 이상 더 썼어요.";
	    } else if (currentMonthOut.compareTo(lastMonthOut) < 0) {
	        message = "저번 달보다 " + formattedDifference + "원 이상 절약했어요.";
	    } else {
	        message = "저번 달과 차이가 없어요.";
	    }

	    Map<String, Object> response = new HashMap<>();
	    response.put("currentMonthOut", currentMonthOut);
	    response.put("lastMonthOut", lastMonthOut);
	    response.put("message", message);

	    return ResponseEntity.ok(response);
	    
	}
	
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
	
	// (달력) 월별 수입/지출 내역 불러오기
	@GetMapping("/getMonthlyMoneyList")
	public ResponseEntity<?> getMonthlyMoneyList (
			@RequestParam int user_idx,
			@RequestParam int year,
			@RequestParam int month) {
		List<MonthlyMoney> monthlyList = moneyService.getMonthlyMoneyList(user_idx, year, month);
		if (monthlyList.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("불러올 데이터가 없습니다.");
	    }
		return ResponseEntity.ok(monthlyList);
	}
	
	// (달력) 일일 수입/지출 내역 불러오기
	@GetMapping("/getDailyMoneyList")
	public ResponseEntity<?> getDailyMoneyList (
			@RequestParam int user_idx,
			@RequestParam int year,
			@RequestParam int month,
			@RequestParam int day) {
		List<DailyMoney> dailyList = moneyService.getDailyMoneyList(user_idx, year, month, day);
		if (dailyList.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("불러올 데이터가 없습니다.");
	    }
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
		
		User user = userService.findByIdx(user_idx);
		
		if (user == null) {
	        return new ResponseEntity<>("로그인 후 이용 가능합니다.", HttpStatus.NOT_FOUND);
	    }
		
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
