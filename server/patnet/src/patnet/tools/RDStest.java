package patnet.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import patnet.dal.ConnectionManager;

public class RDStest {

	public static void main(String[] args) throws SQLException {

		ConnectionManager connectionManager = new ConnectionManager();

		String selectHospitalSpending = "SELECT COUNT(*) AS cnt FROM HospitalSpending;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHospitalSpending);
			
			results = selectStmt.executeQuery();
			
			if (results.next()) {
				System.out.println("HospitalSpending count from RDS DB -> " + results.getInt("cnt"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}

	}

}
