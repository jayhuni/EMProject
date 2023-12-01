package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Blog;
import com.example.demo.service.BlogService;

@Controller
@RequestMapping("/blog")
public class BlogController {
//	private BlogDao bDao = new BlogDao();
//	@Autowired private BlogDao bDao;		// Spring DI 사용법 - BlogDao() 객체를 생성해서 injection
	@Autowired private BlogService blogService;	// BlogServiceOracleImpl가 하나여서 자동으로 생성, MySQL이 있으면 @만 바꿈.
	
	@GetMapping("/list")
	public String list(@RequestParam(name="f", defaultValue="title") String field, 
			@RequestParam(name="q", defaultValue="") String query, Model model) {
		List<Blog> list = blogService.getBlogList(field, query);
		model.addAttribute("blogList", list);
		model.addAttribute("menu", "blog");
		model.addAttribute("field", field);
		model.addAttribute("query", query);
		return "blog/list";  
	}
	
	@GetMapping("/write")
	public String writeForm(Model model) {
		model.addAttribute("menu", "blog");
		return "blog/write";
	}
	
	@PostMapping("/write")
	public String writeProc(Blog blog) {		// 자동 객체 생성 blog.getPenName(), blog.getTitle(), blog.getContent()
//	public String writeProc(String penName, String title, String content) {
//		Blog blog = new Blog(penName, title, content);
		blogService.insertBlog(blog);
		return "redirect:/blog/list";
	}
	
	@GetMapping("/detail/{bid}")
	public String detail(@PathVariable int bid, String option, Model model) {
		if(option == null || option.equals(""))		// DNI 옵션이 있으면 증가시키지 않음.
			blogService.increaseViewCount(bid);		// 조회수 증가
		Blog blog = blogService.getBlog(bid);		// blog 내용 가져옴
		model.addAttribute("blog", blog);	
		model.addAttribute("menu", "blog"); // blog link 활성화
		return "blog/detail";
	}
	
	@GetMapping("/update/{bid}")	// list에서 update로 들어갈때
	public String updateForm(@PathVariable int bid, Model model) {
		Blog blog = blogService.getBlog(bid);
		model.addAttribute("blog", blog);
		model.addAttribute("menu", "blog");
		return "blog/update";
	}
	
	@PostMapping("/update")		// update하여 detail로 넘어갈때
	public String updateProc(Blog blog) {
//	public String updateProc(int bid, String penName, String title, String content) {
//	public String updateProc(HttpServletRequest req) {
//		int bid = Integer.parseInt(req.getParameter("bid")); 
//		String penName = req.getParameter("penName"); 
//		String title = req.getParameter("title"); 
//		String content = req.getParameter("content");
//		Blog blog = new Blog(bid, penName, title, content)
 		blogService.updateBlog(blog);
		return "redirect:/blog/detail/" + blog.getBid() + "?option=DNI";	// detail로 redirect할 때 bid도 같이 넘김.
	}																		// DNI : Do not Increase
	
	@GetMapping("/delete/{bid}")
	public String delete(@PathVariable int bid, Model model) {
		model.addAttribute("bid", bid);
		model.addAttribute("menu", "blog");
		return "blog/delete";
	}
	
	@GetMapping("/deleteConfirm/{bid}")
	public String deleteConfirm(@PathVariable int bid) {
		blogService.deleteBlog(bid);
		return "redirect:/blog/list";
	}
}
