package pro05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import pro04.MemberVo;

public class BookShopDao {
	
	public void insert(BookVo vo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		    // 3. SQL문 준비 / 바인딩 / 실행
			String query = "insert into bookshop values (seq_bookshop_id.nextval, ? , ? , ? , ? , ?)";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getPubs());
			pstmt.setString(3, vo.getPub_date());
			pstmt.setString(4, vo.getAuthor_name());
			pstmt.setInt(5, vo.getState_code());
			
			int count = pstmt.executeUpdate();
			
		    // 4.결과처리
			System.out.println(count+"건 저장완료");
			
		} catch(ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch(SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {        
		        if(pstmt != null) {
		            pstmt.close();
		        }
		        if(conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }
		}
	}

	public void selectBook(int BookId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BookVo book = new BookVo();
		
		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		    // 3. SQL문 준비 / 바인딩 / 실행
			String query = " select id, title, pubs, pub_date, author_name, state_code "+
		                   " FROM bookshop "+
					       " WHERE id = ? ";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, BookId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pub_date = rs.getString("pub_date");
				String author_name = rs.getString("author_name");
				int state_code = rs.getInt("state_code");
				book.setId(id);
				book.setTitle(title);
				book.setPubs(pubs);
				book.setPub_date(pub_date);
				book.setAuthor_name(author_name);
				book.setState_code(state_code);

			} else {
				book = null;
			}
			
		} catch(ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch(SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {        
		        if(pstmt != null) {
		            pstmt.close();
		        }
		        if(conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }
		}
	}
}
