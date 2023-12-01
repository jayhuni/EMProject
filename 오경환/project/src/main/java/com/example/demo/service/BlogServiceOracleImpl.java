package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.db.BlogDaoOracle;
import com.example.demo.entity.Blog;

// Oracle version 구현객체
// BlogServiceOracleImpl에 빨간줄 누루면 자동 Override 실시

@Service		// MySQL 서비스 활용시 주석처리함.
public class BlogServiceOracleImpl implements BlogService {
	@Autowired private BlogDaoOracle blogDao;
	
	@Override
	public Blog getBlog(int bid) {
		Blog blog = blogDao.getBlog(bid);
		return blog;
	}

	@Override
	public List<Blog> getBlogList(String field, String query) {
		query = "%" + query + "%";
		List<Blog> list = blogDao.getBlogList(field, query);
		return list;
	}

	@Override
	public void insertBlog(Blog blog) {
		blogDao.insertBlog(blog);
	}

	@Override
	public void updateBlog(Blog blog) {
		blogDao.updateBlog(blog);
	}

	@Override
	public void deleteBlog(int bid) {
		blogDao.deleteBlog(bid);
	}

	@Override
	public void increaseViewCount(int bid) {
		blogDao.increaseViewCount(bid);
	}

}
