import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.my.sql.MyConnection;

public class BatchTest {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = MyConnection.getConnection();
			String insertSQL = "INSERT INTO product(prod_no, prod_name, prod_price) VALUES (?,?,?)";
			pstmt = con.prepareStatement(insertSQL);
			for(int i=5; i<=10; i++) {
				if(i==10) {
					pstmt.setString(1, "C00" + i); //C0010
				} else {
					pstmt.setString(1,  "C000" + i); //C0005
				}
				pstmt.setString(2,  "커피" + i);
				pstmt.setInt(3, i*100);
				//pstmt.executeUpdate();//바인드변수값을 DB에 전달
				pstmt.addBatch();//바인드변수값들 일괄처리작업에 추가
			}
			pstmt.executeBatch(); //바인드변수값을 DB에 전달하여 일괄처리
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(pstmt, con);
		}
	}

}
