package com.example.demo.board;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class BoardDAO {
	@Autowired
	private JdbcTemplate tmp;
	
	public class BoardResultMap implements RowMapper<Board>{
		@Override
		public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new Board( rs.getInt(1), rs.getString(2), rs.getDate(3),
					rs.getString(4), rs.getString(5) );
		}
	}
	
	//작성------------------------------------------------------------------
	public void insert(Board b) {
		String sql = "INSERT INTO board(writer, wdate, title, content ) VALUES(?,sysdate(),?,?)";
		System.out.println( "insert에서 " + b.getWriter());
		tmp.update(sql, new Object[] {b.getWriter(), b.getTitle(), b.getContent()});
	}
	
	//검색---------------------------------------------------------------
	public Board selectOne(int num) {
		String sql = "SELECT * FROM board WHERE num=?";
		Board b = null;
		
		try {
			b = tmp.queryForObject(sql, new BoardResultMap(), num );
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			b = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return b;
	}
	
	public ArrayList<Board> selectAll(){
		String sql = "SELECT * FROM board ORDER BY num";
		
		//System.out.println( tmp.query(sql, new BoardResultMap()) );
		
		return (ArrayList<Board>)tmp.query(sql, new BoardResultMap());
	}
	
	public ArrayList<Board> selectByTitle(String title){
		String sql = "SELECT * FROM board WHERE title LIKE ? ORDER BY num";
		
		return (ArrayList<Board>)tmp.query(sql,  new BoardResultMap());
	}
	
	public ArrayList<Board> selectByWriter(String writer){
		String sql = "SELECT * FROM board WHERE writer LIKE ? ORDER BY num";
		
		return (ArrayList<Board>) tmp.query(sql,  new BoardResultMap());
	}
	
	//수정------------------------------------------------------------------
	public void update(Board b) {
		String sql = "UPDATE board SET title=?, content=? WHERE num=?";
		tmp.update(sql, new Object[] {b.getTitle(), b.getContent(), b.getNum()});
	}
	
	//삭제-----------------------------------------------------------------
	public void delete(int num) {
		String sql = "DELETE FROM board WHERE num = ?";
		tmp.update(sql, num);
	}
}
