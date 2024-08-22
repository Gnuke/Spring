package com.example.demo.test;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jakarta.servlet.http.HttpSession;

@Repository
public class MemDAO {
	@Autowired
	private JdbcTemplate temp;
	
	public class MemResultMap implements RowMapper<Member>{
		@Override
		public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new Member( rs.getString(1), rs.getString(2), 
					rs.getString(3), rs.getString(4), rs.getString(5) );
		}
	}
	
	//insert----------------------------------------------
	public void insert(Member m) {
		String sql = "INSERT INTO member(id, pwd, name, email, type) ";
		sql += "VALUES (?,?,?,?,?)";
		temp.update(sql, new Object[] {m.getId(),m.getPwd(),m.getName(),
				m.getEmail(),m.getType()});
	}
	
	
	//select---------------------------------------------
	public Member select(String id) {
		String sql = "SELECT * FROM member WHERE id=?";
		Member m = null;
		
		try {
			m = temp.queryForObject(sql, new MemResultMap(), id);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			m = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return m;
	}
	
	//login 시------------------------------------------------
	public void selectMy(HttpSession session) {
		String sql = "SELECT * FROM member WHERE id=?";
		
		temp.queryForObject(sql, new MemResultMap(), session.getAttribute("loginId").toString());
	}
	
	
	//update---------------------------------------------
	public void update(Member m) {
		String sql = "UPDATE member SET name=?, email=?, type=? ";
		sql += "WHERE id = ?";
		temp.update(sql, new Object[]
				{m.getName(), m.getEmail(), m.getType(), m.getId()});
	}
	
	
	//delete-----------------------------------------------
	public void delete(String id) {
		String sql = "DELETE FROM member WHERE id=?";
		temp.update(sql, id);
	}

}
