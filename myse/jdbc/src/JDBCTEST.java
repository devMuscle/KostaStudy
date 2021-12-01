

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.my.sql.MyConnection;

public class JDBCTEST {
	public static void selectTest() {
				Connection con = null;
				Statement stmt = null; //송신
				ResultSet rs = null; //수신
				try {
					con = MyConnection.getConnection(); //2. DB와 연결
					String selectSQL = "SELECT employee_id, first_name, salary, hire_date FROm employees";
					stmt = con.createStatement(); 
					//4. 결과 수신
					rs = stmt.executeQuery(selectSQL);
					while(rs.next()) { //while(rs.next()==true){
						int emp_id = rs.getInt("employee_id");
						String f_name = rs.getString("first_name");
						int sal = rs.getInt(3);
						Date hireDt = rs.getDate("hire_date");
						System.out.println(emp_id +":" + f_name +":" + sal + ":"+  hireDt);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					//5. DB연결닫기
					MyConnection.close(rs, stmt, con);
					
					
				}
	}
	public static void dmlTest() {
		Connection con = null;
		Statement stmt = null; //송신
		try {
			con = MyConnection.getConnection();
			
			Scanner sc = new Scanner(System.in);
			System.out.print("고객ID:");
			String id = sc.nextLine();
			System.out.print("고객 비밀번호:");
			String pwd = sc.nextLine();
			
			
			stmt = con.createStatement();
			String insertSQL = "INSERT INTO customer(id, pwd) VALUES('" + id + "', '"+ pwd + "')";
			stmt.executeUpdate(insertSQL);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(stmt, con);
		}
	}
	public static void dmlPreparedStatementTest() {
				Connection con = null;
				PreparedStatement pstmt = null;
				try {
					con = MyConnection.getConnection();//2.DB연결
					
					Scanner sc = new Scanner(System.in);
					System.out.print("고객ID:");
					String id = sc.nextLine();
					System.out.print("고객 비밀번호:");
					String pwd = sc.nextLine();

					
					String insertSQL = "INSERT INTO customer(id, pwd) VALUES(?,?)";
					pstmt = con.prepareStatement(insertSQL); //SQL 구문을 미리준비
					pstmt.setString(1, id); //1번 바인드변수는 id값으로 설정
					pstmt.setString(2, pwd); //2번 바인드변수는 pwd값으로 설정
					pstmt.executeUpdate(); //실행
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					MyConnection.close(pstmt, con);
				}
	}
	public static void main(String[] args) {
		selectTest();
		//dmlTest();
		//dmlPreparedStatementTest();
	}
	
}
