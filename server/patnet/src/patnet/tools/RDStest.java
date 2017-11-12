package patnet.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import patnet.dal.ConnectionManager;
import patnet.dal.UsersDao;
import patnet.model.Users;

public class RDStest {

	public static void main(String[] args) throws SQLException {
		testRDS();

		testUsersDao();

	}

	static void testUsersDao() throws SQLException {

		System.out.println("testing dao");

		UsersDao usersDao = UsersDao.getInstance();

		Users someUser = usersDao.getUserFromUserName("Organization");
		System.out.println(someUser.toString());

		Users newUser = new Users("dilip", "dilipPasswordNew", Users.Type.ORDINARY, 8, 1003037037, "dilipFname",
				"dilipLname");

		 usersDao.create(newUser);
		// usersDao.delete(newUser);
//		usersDao.update(newUser);

		System.out.println(newUser.toString());

	}

	static void testRDS() throws SQLException {

		System.out.println("testing RDS ");

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

		System.out.println("testing RDS end");
	}

}
