package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.User;
import com.example.demo.entity.UserByMonth;

// interface 사용 이유 : Release하는 회사마다 DB가 다름 ex) Oracle, MySQL
// UserServiceOracleImpl.java를 호출
// DI를 이용하여 @Service를 바꿔서 Oracle이나 MySQL로 바꾸기 용이함
public interface UserService {
	// public static final 생략 가능
	// 로그인시 세가지 경우를 변수로 지정
	public static final int CORRECT_LOGIN = 0;
	public static final int WRONG_PASSWORD = 1;
	public static final int UID_NOT_EXIST = 2;
	public static final int RECORDS_PER_PAGE = 5;	// 한 페이지당 10개 레코드를 보여줌

	int getUserCount();		// pagination을 위해 사용됨 / 전체 사용자 찾음
	
	User getUser(String uid);
	
	List<UserByMonth> getNumberOfUser();
	
	List<User> getUserList(int page);		// 사용자를 페이지로 찾아냄
	
	void insertUser(User user);
	
	void updateUser(User user);
	
	void deleteUser(String uid);
	
	int login(String uid, String pwd);
	
}
