package com.example.demo.guestbook;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class GuestBookDAO {
	@Autowired
	private JdbcTemplate temp; // 사용할 JdbcTemplate 객체 의존성 주입
	
	//resultMap 정의
	public class GuestResultMap implements RowMapper<GuestBook>{
		
		//ResultSet의 한 행을 처리. Param으로 처리해야할 행 번호를 받음 -> int rowNum
		//각 컬럼을 vo 생성자 Param에 Mapping
		@Override
		public GuestBook mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new GuestBook(rs.getInt(1), rs.getString(2), rs.getString(3), 
					rs.getDate(4), rs.getString(5) );
		}
	}
	
	//write-----------------------------------------------------------------
	public void write(GuestBook gb) {
		String sql = "INSERT INTO guestbook(writer, pwd, wdate, content)";
		sql += " VALUES (?,?,sysdate(),?)";
		temp.update(sql, new Object[] {gb.getWriter(), gb.getPwd(), gb.getContent()});
	}
	
	//selectOne--------------------------------------------------------
	public GuestBook select(int num) {
		String sql = "SELECT * FROM guestbook WHERE num=?";
		GuestBook gb = null;
		try {
			gb = temp.queryForObject(sql, new GuestResultMap(), num);
		}catch(Exception e) {
			System.out.println(e);
		}
		return gb;
	}
	
	//selectAll---------------------------------------------------------------------
	public ArrayList<GuestBook> selectAll(){
		String sql = "SELECT * FROM guestbook ORDER BY num";
		
		return (ArrayList<GuestBook>) temp.query(sql, new GuestResultMap());
	}
	
	//update----------------------------------------------------------------------
	public void update(GuestBook gb) {
		String sql = "UPDATE guestbook SET content=? WHERE num=?";
		temp.update(sql, new Object[] {gb.getContent(), gb.getNum()});
	}
	
	//delete-----------------------------------------------------------------------
	public void delete(int num) {
		String sql = "DELETE FROM guestbook WHERE num=?";
		temp.update(sql, num);
	}
	
}
