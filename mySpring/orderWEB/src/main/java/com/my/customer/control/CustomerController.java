package com.my.customer.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

import com.my.customer.service.CustomerService;
import com.my.customer.vo.Customer;
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
		//return resultMsg;
		//return "{\"status\":" + status + ", \"msg\":\"" + resultMsg + "\"}";
	}
//	@PostMapping("login")
//	public String login(String id, String pwd, HttpSession session, Model model) {
//		session.removeAttribute("loginInfo");
//		String resultMsg = "";
//		String viewName = "jsonresult.jsp";
//
//		try {
//			Customer c = service.login(id, pwd);
//			session.setAttribute("loginInfo", c);
//
//			resultMsg = "로그인 성공";
//			model.addAttribute("status", 1);
//		} catch (FindException e) {
//			resultMsg = "로그인 실패";
//			model.addAttribute("status", 0);
//		}
//
//		model.addAttribute("msg", resultMsg);
//		return viewName;
//	}

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
//			path = "success";
//			model.addAttribute("status", 1);
			status = 1;
			resultMsg = "가입성공";

		} catch (AddException e) {
			e.printStackTrace();
//			path = "fail";
//			model.addAttribute("status", 0);
			resultMsg = e.getMessage();

		}
//		model.addAttribute("msg", resultMsg);
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
//			model.addAttribute("status", 0);
//				path = "fail";
		} catch (FindException e) {
			System.out.println("사용가능한 아이디입니다");
			resultMsg = "사용가능한 아이디입니다";
//			model.addAttribute("status", 1);
			status = 1;
//				path = "success";
		}
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("status", status);
		returnMap.put("msg", resultMsg);
		return returnMap;
	}
}
