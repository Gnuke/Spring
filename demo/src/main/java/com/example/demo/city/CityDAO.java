package com.example.demo.city;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import conn.DBConnection;

@Repository
public class CityDAO {
	private DBConnection dbconn;
	//private DBClose dbclose;
	
	public CityDAO() {
		dbconn = DBConnection.getInstance();
		//dbclose = DBClose.dbClose(null, null, null);
	}
	
	// select One ------------------------------------------------------------------------------------
	public City search(int id) {
		Connection conn = dbconn.getConn();
		String sql = "SELECT * FROM city where id = ?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			if( rs.next() ) {
				return new City( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5) );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println( e.getMessage() );
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	// Select All ------------------------------------------------------------
	public ArrayList<City> selectAll() {
		Connection conn = dbconn.getConn();
		ResultSet rs = null;
		
		ArrayList<City> list = new ArrayList<City>();
		String sql = "SELECT * FROM city order by id";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				list.add( new City(rs.getInt(1), rs.getString(2), 
						rs.getString(3), rs.getString(4), rs.getInt(5)) );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println( e.getMessage() );
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// insert One -----------------------------------------------------------------
	public void insert(City city) {
		Connection conn = dbconn.getConn();
		String sql = "INSERT INTO city ( name, countryCode, district, population)";
		sql += " VALUES ( ?, ?, ?, ?)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, city.getName() );
			pstmt.setString(2, city.getCountryCode() );
			pstmt.setString(3, city.getDistrict() );
			pstmt.setInt(4, city.getPopulation() );
			
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if( conn != null ) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//update----------------------------------------------------------------
	public void update(City city) {
		Connection conn = dbconn.getConn();
		String sql = "UPDATE city set population=? where id=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, city.getPopulation());
			pstmt.setInt(2, city.getId());
			pstmt.executeUpdate();
			
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if( conn != null )
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	//delete------------------------------------------------------------
	public void delete(int id) {
		Connection conn = dbconn.getConn();
		String sql = "DELETE from city where id=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(conn != null )
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
}
