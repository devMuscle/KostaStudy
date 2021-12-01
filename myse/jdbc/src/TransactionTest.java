import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.my.sql.MyConnection;

public class TransactionTest {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = MyConnection.getConnection();
			con.setAutoCommit(false);
//			
//			String sql1 = "UPDATE account SET balance = balance -1000 WHERE no='101'";
//			String sql2 = "UPDATE account SET balance = balance + 1000 WHERE no='102'";
//			
//			
//			pstmt = con.prepareStatement(sql1); //101번의 잔액70원에서 1000원을 출금
//			pstmt.executeUpdate();
//			
//			pstmt = con.prepareStatement(sql2); //102번의 잔액
//			pstmt.executeUpdate();
//			
//			
//			String sql4 = "SELECT balance FROm account where no='101'";
//			pstmt = con.prepareStatement(sql4);
//			rs = pstmt.executeQuery();
//			rs.next();
//			if(rs.getInt("balance")<0) { //101번의 잔액이 음수인가
//				//con.rollback();
//				throw new SQLException("마이너스통장안됨!");
//			}
//			
//			String sql3 = "UPDATE account SET balance = balance + 10 WHERE no='777'"; //없는 계좌번호
//			String sql5 = "UPDATE account SET balance = balance - 10 WHERE no='101'";
//			pstmt = con.prepareStatement(sql5); //101번의 잔액 70원에서 10원을 출금
//			int rowcnt1 = pstmt.executeUpdate();
//			if(rowcnt1 == 0) {
//				//con.rollback();
//				throw new SQLException("101번 계좌없음");
//			}
//			
//			pstmt = con.prepareStatement(sql3);//777번(없는 계좌)의 10원을 입금
//			int rowcnt2 = pstmt.executeUpdate();
//			if(rowcnt2 == 0) {
//				//con.rollback();
//				throw new SQLException("777번 계좌없음");
//			}
			
			String sql6 = "UPDATE account SET balance = balance + 10 WHERE no='101'"; //있는 계좌번호
			String sql7 = "UPDATE account SET balance = balance - 10 WHERE no='102'"; //있는 계좌번호
			pstmt = con.prepareStatement(sql6);//101번의 잔액70원에서 10원을 출금
			int rowcnt3 = pstmt.executeUpdate();
			if(rowcnt3 == 0) {
				//con.rollback();
				throw new SQLException("101번 계좌없음");
			}
			
			pstmt = con.prepareStatement(sql7); //102번의 잔액30원에서 10원을 입금 
			int rowcnt4= pstmt.executeUpdate();
			if(rowcnt4 == 0) {
				throw new SQLException("102번 계좌없음");
			}
			
			String sql8 = "SELECT balance FROM account WHERE no='101'";
			pstmt = con.prepareStatement(sql8);
			rs = pstmt.executeQuery();
			rs.next();
			if(rs.getInt("balance") < 0) { //101번의 잔액이 음수인가
				throw new SQLException("마이너스통장안됨!");
			}
			con.commit();

			
			
		} catch (SQLException e) {
			e.printStackTrace();
			if(con!= null) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			MyConnection.close(pstmt, con);
		}
		
		
		
	}

}
