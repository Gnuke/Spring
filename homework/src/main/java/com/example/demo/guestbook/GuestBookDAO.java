package com.example.demo.guestbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import conn.DBConnection;

@Repository
public class GuestBookDAO {
	private DBConnection dbconn;
	
	public GuestBookDAO() {
		// TODO Auto-generated constructor stub
		dbconn = DBConnection.getInstance();
	}
	
	//write-----------------------------------------------------------------
	public void write(GuestBook gb) {
		Connection conn = dbconn.getConn();
		String sql = "INSERT INTO guestbook ( writer, pwd, wdate, content)";
		sql += " VALUES ( ?, ?, sysdate(), ? )";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gb.getWriter() );
			pstmt.setString(2, gb.getPwd() );
			pstmt.setString(3, gb.getContent() );
			
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if( conn != null )
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	//selectAll---------------------------------------------------------------------
	public ArrayList<GuestBook> selectAll(){
		Connection conn = dbconn.getConn();
		ResultSet rs = null;
		
		ArrayList<GuestBook> list = new ArrayList<GuestBook>();
		String sql = "SELECT num, writer, pwd, DATE_FORMAT(wdate, '%Y/%m/%d') AS wdate, content FROM guestbook order by num desc";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				list.add( new GuestBook( rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5) ) );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}finally {
			if(conn != null )
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		System.out.println( "나는 리스트 : " + list );
		return list;
	}
	
	
	
}
