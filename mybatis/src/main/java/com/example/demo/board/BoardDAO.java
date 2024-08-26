package com.example.demo.board;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface BoardDAO {
	
	//작성------------------------------------------------------------------
	@Insert("INSERT INTO board(writer, wdate, title, content ) VALUES(#{writer},sysdate(),#{title},#{content})")
	public void insert(Board b);
	
	//검색---------------------------------------------------------------
	@Select("SELECT * FROM board WHERE num=#{num}")
	public Board selectOne(@Param("num") int num);
	
	@Select("SELECT * FROM board ORDER BY num")
	public ArrayList<Board> selectAll();

		//제목으로 검색
		@Select("SELECT * FROM board WHERE title LIKE #{title} ORDER BY num")
		public ArrayList<Board> selectByTitle(@Param("title") String title);
		//작성자 이름으로 검색
		@Select("SELECT * FROM board WHERE writer LIKE #{writer} ORDER BY num")
		public ArrayList<Board> selectByWriter(@Param("writer") String writer);
	
	//수정------------------------------------------------------------------
	@Update("UPDATE board SET title=#{title}, content=#{content} WHERE num=#{num}")
	public void update(Board b);
	
	//삭제-----------------------------------------------------------------
	@Delete("DELETE FROM board WHERE num = #{num}")
	public void delete(@Param("num") int num);
}
