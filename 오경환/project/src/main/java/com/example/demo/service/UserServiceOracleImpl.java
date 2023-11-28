package com.example.demo.service;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.db.UserDaoOracle;
import com.example.demo.entity.User;

// DI 이용 annotation, 나중에 Service 바뀌면 바꿔줌
@Service
public class UserServiceOracleImpl implements UserService {
	@Autowired private UserDaoOracle userDao;
	
	@Override
	public int getUserCount() {
		int count = userDao.getUserCount();
		return count;
	}

	@Override
	public User getUser(String uid) {
		User user = userDao.getUser(uid);
		return user;
	}

	@Override
	public List<User> getUserList(int page) {
		int offset = (page - 1) * RECORDS_PER_PAGE;
		int limit = page * RECORDS_PER_PAGE;
		List<User> list = userDao.getUserList(offset, limit);
		return list;
	}

	@Override
	public void insertUser(User user) {
		userDao.insertUser(user);
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);		
	}

	@Override
	public void deleteUser(String uid) {
		userDao.deleteUser(uid);
	}

	@Override
	public int login(String uid, String pwd) {
		User user = userDao.getUser(uid);
		if (user == null) {		// 해당 user가 없음
			return UID_NOT_EXIST;
		}
		if (BCrypt.checkpw(pwd, user.getPwd())) {
			return CORRECT_LOGIN;
		} else {
			return WRONG_PASSWORD;
		}
	}

}
