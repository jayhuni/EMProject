package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.User;
import com.example.demo.entity.UserByMonth;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired private UserService userService;
	
	@GetMapping("/update/{uid}")
	@ResponseBody
	public String updateForm(@PathVariable String uid) {
		User user = userService.getUser(uid);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("uid", user.getUid());
		jsonObject.put("uname", user.getUname());
		jsonObject.put("email", user.getEmail());		
		return jsonObject.toJSONString();
	}
	
	@PostMapping("/update")
	public String updateProc(String pwd, String pwd2, String uname, String email, 
							HttpSession session, Model model) {
		String uid = (String) session.getAttribute("sessUid");  // Object > String
		User user = userService.getUser(uid);
		// System.out.println("pwd=" + pwd + ", pwd2=" + pwd2);
		if (pwd.length() >= 4 && pwd.equals(pwd2)) {
			String hashedPwd = BCrypt.hashpw(pwd, BCrypt.gensalt());
			user.setPwd(hashedPwd);
		} else if (pwd.equals("") && pwd2.equals("")) {
			;		// 아무것도 넣지 않을 경우 아무일도 하지 않는다
		} else {
			model.addAttribute("msg", "패스워드를 다시 입력하세요.");
			model.addAttribute("url", "/sample/user/list/" + session.getAttribute("currentUserPage"));
			return "admin/common/alertMsg";
		}
		user.setUname(uname);
		user.setEmail(email);
		userService.updateUser(user);
		
		return "redirect:/user/list/" + session.getAttribute("currentUserPage");
	}
	
	@GetMapping("/delete/{uid}")
	public String delete(@PathVariable String uid, HttpSession session) {
		userService.deleteUser(uid);
		return "redirect:/user/list/" + session.getAttribute("currentUserPage");
	}
	
	@GetMapping("/list/{page}")
	public String list(@PathVariable int page, HttpSession session, Model model) {
		List<User> list = userService.getUserList(page);
		model.addAttribute("userList", list);
		
		int totalUsers = userService.getUserCount();
		int totalPages = (int) Math.ceil((double)totalUsers / UserService.RECORDS_PER_PAGE);
		List<String> pageList = new ArrayList<>();
		for (int i=1; i<=totalPages; i++)
			pageList.add(String.valueOf(i));
		model.addAttribute("pageList", pageList);
		session.setAttribute("currentUserPage", page);
		model.addAttribute("menu", "user");
		
		List<UserByMonth> monthList = userService.getNumberOfUser();
		JSONArray jsonArray = new JSONArray();
		
		Iterator<UserByMonth> it = monthList.iterator();
		while(it.hasNext()) {
			UserByMonth userMonth = it.next();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("month", userMonth.getMonth());
			jsonObject.put("numberOfPerson", userMonth.getNumberOfPerson());
			
			jsonArray.add(jsonObject);
		}
		System.out.println(jsonArray);
		model.addAttribute("json", jsonArray.toJSONString());
		
		return "admin/list";
	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "admin/login";
	}
	
	
	@PostMapping("/login")
	public String loginProc(String uid, String pwd, HttpSession session, Model model) {
		int result = userService.login(uid, pwd);
		if (result == UserService.CORRECT_LOGIN) {
			session.setAttribute("sessUid", uid);
			User user = userService.getUser(uid);
			session.setAttribute("sessUname", user.getUname());
			session.setAttribute("sessEmail", user.getEmail());
			
			// 환영 메세지
			model.addAttribute("msg", user.getUname() + "님 환영합니다.");
			model.addAttribute("url", "/project/admin/index");			
			
		} else if (result == UserService.WRONG_PASSWORD) {
			model.addAttribute("msg", "패스워드 입력이 잘못 되었습니다.");
			model.addAttribute("url", "/project/user/login");
			
		} else {		// UID_NOT_EXIST
			model.addAttribute("msg", "ID 입력이 잘못 되었습니다.");
			model.addAttribute("url", "/project/user/login");
		}
		return "admin/common/alertMsg";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/user/login";
	}
	
	@GetMapping("/register")
	public String registerForm() {
		return "admin/register";
	}
	
	@PostMapping("/register")
	public String registerProc(String uid, String pwd, String pwd2, 
								String uname, String email, Model model) {
		// uid 중복 확인
		if (userService.getUser(uid) != null) {
			model.addAttribute("msg", "사용자 ID가 중복되었습니다.");
			model.addAttribute("url", "/project/user/register");
		}
		
		if (pwd.equals(pwd2) && pwd.length() >= 4) {		// pwd와 pwd2가 같고, 길이가 4이상면
			String hashedPwd = BCrypt.hashpw(pwd, BCrypt.gensalt());	//(pwd, salt부분)
			User user = new User(uid, hashedPwd, uname, email);
			userService.insertUser(user);
			model.addAttribute("msg", "등록을 맞췄습니다. 로그인 하세요.");
			model.addAttribute("url", "/project/user/login");
		} else {
			model.addAttribute("msg", "패스워드 입력이 잘못 되었습니다.");
			model.addAttribute("url", "/project/user/register");
		}
		return "admin/common/alertMsg";
	}
}
