package com.bit.servlet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PhoneDaoOrclImpl implements PhoneDao {
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			// 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			//드라이버 매니저 -> 접속 얻어오기
			conn = DriverManager.getConnection(dburl, "C##KMS", "7777");
		} catch(ClassNotFoundException e) {
			System.err.println("드라이버 로딩 실패");
		}
		
		return conn;
	}
	
	@Override
	public List<PhoneVo> getList() {
		List<PhoneVo> list = new ArrayList<>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			String sql = "SELECT id, name, hp, tel "
					+ "FROM phone_book ORDER BY id";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Long id = rs.getLong(1);
				String name = rs.getString(2);
				String hp = rs.getString(3);
				String tel = rs.getString(4);
				
				PhoneVo vo = new PhoneVo(id, name, hp, tel);
				
				list.add(vo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e) {
				
			}
		}
		
		return list;
	}

	@Override
	public List<PhoneVo> search(String keyword) {
		List<PhoneVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "SELECT id, name, hp, tel FROM phone_book WHERE name LIKE ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PhoneVo vo = new PhoneVo();
				vo.setId(rs.getLong(1));
				vo.setName(rs.getString(2));
				vo.setHp(rs.getString(3));
				vo.setTel(rs.getString(4));
				list.add(vo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public boolean insert(PhoneVo vo) {
		Connection conn = null;
		String sql = "INSERT INTO phone_book VALUES(seq_phone_book.NEXTVAL, ?, ?, ?)";
		int insertedCount = 0;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,  vo.getName());
			pstmt.setString(2, vo.getHp());
			pstmt.setString(3, vo.getTel());
			
			insertedCount = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return insertedCount == 1;
	}

	@Override
	public boolean delete(Long id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deletedCount = 0;
		
		try {
			conn = getConnection();
			String sql = "DELETE FROM phone_book WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, id);
			deletedCount = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return deletedCount == 1;
	}
}
