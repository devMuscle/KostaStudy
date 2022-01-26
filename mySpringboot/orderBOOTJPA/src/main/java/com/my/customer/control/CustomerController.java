package com.my.customer.control;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.customer.entity.Customer;
import com.my.customer.service.CustomerService;
import com.my.exception.AddException;
import com.my.exception.FindException;

@Controller
public class CustomerController {
	@Autowired
	private CustomerService service;

	@PostMapping("login")
	@ResponseBody
	public Map<String, Object> login(String id, String pwd, HttpSession session) {
		session.removeAttribute("loginInfo");
		String resultMsg = "";
		int status=0;
		try {
			Customer c = service.login(id, pwd);
			session.setAttribute("loginInfo", c);
			resultMsg = "로그인 성공";
			status=1;
		} catch (Exception e) {
			resultMsg = "로그인 실패";
		}
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("status", status);
		returnMap.put("msg", resultMsg);
		return returnMap;
	}

	@RequestMapping("logout")
	public ResponseEntity logout(HttpSession session) {
		session.removeAttribute("loginInfo");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("signup")
	@ResponseBody
	public Map<String, Object> signup(Customer c, Model model) {
		String resultMsg = "";
		int status = 0;
		try {
			service.signup(c);
			status = 1;
			resultMsg = "가입성공";

		} catch (AddException e) {
			e.printStackTrace();
			resultMsg = e.getMessage();

		}
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("status", status);
		returnMap.put("msg", resultMsg);
		return returnMap;
	}

	@GetMapping("iddupchk")
	@ResponseBody
	public Map<String, Object> idDupChk(@RequestParam(name = "id") String idValue, Model model) {
		System.out.println("입력받은 id값:" + idValue);
		
		String resultMsg = "";
		int status = 0;
		try {
			service.addupchk(idValue);
			System.out.println("중복된 아이디입니다");
			resultMsg = "중복된 아이디입니다";
		} catch (NoSuchElementException e) {
			System.out.println("사용가능한 아이디입니다");
			resultMsg = "사용가능한 아이디입니다";
			status = 1;
		}
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("status", status);
		returnMap.put("msg", resultMsg);
		return returnMap;
	}
}
