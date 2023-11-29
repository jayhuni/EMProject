package com.example.demo.db;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.entity.User;
import com.example.demo.entity.UserByMonth;

@Mapper
public interface UserDaoOracle {
	
	@Select("select count(uname) from users where isDeleted=0")
	public int getUserCount();
	
	@Select("select to_number(to_char(regdate, 'mm')) as month, count(*) as cnt from users "
			+ "group by to_char(regdate, 'mm') order by month")
	public List<UserByMonth> getNumberOfUser();
	
	// \" escape 문자로 oracle에서는 uid 쓰고 있어 "uid"로
	// MyBatis에서만 사용 가능 / JPA 요즘
	@Select("select * from users where \"uid\"=#{uid}")
	public User getUser(String uid);	// user object 만들어서 넘겨줌

	// #{uid} --> user.getUid() 호출
	@Insert("insert into users values (#{uid}, #{pwd}, #{uname}, #{email}, default, default)")
	public void insertUser(User user);
	
	@Select("select * from (select rownum rnum, a.* from"
			+ "    (select * from users where isDeleted=0) a"
			+ "    where rownum <= #{limit}) where rnum > #{offset}")
	public List<User> getUserList(int offset, int limit);
	
	@Update("update users set pwd=#{pwd}, uname=#{uname}, email=#{email} where \"uid\"=#{uid}")
	void updateUser(User user);				// 인터페이스이기 때문에 public 생략 가능
	
	@Update("update users set isDeleted=1 where \"uid\" = #{uid}")
	void deleteUser(String uid);			// 인터페이스이기 때문에 public 생략 가능
	
	
}
