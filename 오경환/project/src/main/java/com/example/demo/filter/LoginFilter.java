package com.example.demo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

/*
 * Servlet Filter
 * 보안상 취약점을 보안
 */

@Component
public class LoginFilter extends HttpFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		session.setMaxInactiveInterval(10 * 3600); 			// 세션 유효시간 : 10시간
		
		String uri = httpRequest.getRequestURI();
		String sessionUid = (String) session.getAttribute("sessUid");
		
		// 로그인해야만 들어올 수 있는 path
		String[] urlPatterns = {"/user/list", "/user/update", "/user/delete", "/schedule"};
		for (String routing: urlPatterns) {
			if (uri.contains(routing)) {
				// 배열안에 들어 있지만 아이디가 없거나 입력하지 않은 경우 로그인 페이지로 보냄
				if (sessionUid == null || sessionUid.equals(""))
					httpResponse.sendRedirect("/sample/user/login");
				break;
			}
		}
		
		chain.doFilter(request, response);		// chain으로 마무리함
	}
	
	
	
}
