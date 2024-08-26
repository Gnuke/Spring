package com.example.demo.member;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface MemDAO {
	//insert----------------------------------------------
	@Insert("INSERT INTO member(id, pwd, name, email, type) VALUES (#{id}, #{pwd}, "
			+ "#{name}, #{email}, #{type})")
	public void insert(Member m);
		
	//select---------------------------------------------
	@Select("SELECT * FROM member WHERE id=#{id}")
	public Member select(@Param("id") String id);
			
	//update---------------------------------------------
	@Update("UPDATE member SET name=#{name}, email=#{email}, type=#{type} WHERE id = #{id}")
	public void update(Member m);
		
	//delete-----------------------------------------------
	@Delete("DELETE FROM member WHERE id=#{id}")
	public void delete(@Param("id") String id);
}
