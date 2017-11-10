package patnet.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import patnet.model.Users;

/**
 * @author Dilip Makwana
 *
 */
public class UsersDao {
	// Single pattern: instantiation is limited to one object.
	private static UsersDao instance = null;
	protected ConnectionManager connectionManager;

	protected UsersDao() {
		super();
		connectionManager = new ConnectionManager();
	}

	public static UsersDao getInstance() {
		if (instance == null) {
			instance = new UsersDao();
		}
		return instance;
	}

	public Users create(Users user) throws SQLException {
		String insertRestaurants = "INSERT INTO Users(Username,Password,Type,OrganizationId,PhysicianId, FirstName,LastName)"
				+ "VALUES(?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRestaurants);

			insertStmt.setString(1, user.getUsername());
			insertStmt.setString(2, user.getPassword());
			insertStmt.setString(3, user.getType().name());
			insertStmt.setInt(4, user.getOrganizationId());
			insertStmt.setInt(5, user.getPhysicianId());
			insertStmt.setString(6, user.getFirstName());
			insertStmt.setString(7, user.getLastName());

			insertStmt.executeUpdate();

			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	public Users update(Users user) throws SQLException {
		String updateUserStatement = "UPDATE Users"
				+ " SET Password=?, Type=?, OrganizationId=?, PhysicianId=?, FirstName=?, LastName=? "
				+ "WHERE Username=?";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateUserStatement);

			updateStmt.setString(1, user.getPassword());
			updateStmt.setString(2, user.getType().name());
			updateStmt.setInt(3, user.getOrganizationId());
			updateStmt.setInt(4, user.getPhysicianId());
			updateStmt.setString(5, user.getFirstName());
			updateStmt.setString(6, user.getLastName());
			updateStmt.setString(7, user.getUsername());

			updateStmt.executeUpdate();

			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	public Users getUserFromUserName(String userName) throws SQLException {
		// To build an BlogUser object, we need the Persons record, too.
		String selectBlogUser = "SELECT Username,Password,Type,OrganizationId,PhysicianId, FirstName,LastName FROM Users WHERE Username=? ;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBlogUser);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			if (results.next()) {
				System.out.println("here");
				String resultUserName = results.getString("Username");
				String password = results.getString("Password");
				Users.Type type = Users.Type.valueOf(results.getString("Type"));
				int organizationId = results.getInt("OrganizationId");
				int physicianId = results.getInt("PhysicianId");

				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				Users user = new Users(resultUserName, password, type, organizationId, physicianId, firstName,
						lastName);
				return user;
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
		return null;
	}

	public Users delete(Users user) throws SQLException {
		String deletePerson = "DELETE FROM Users WHERE Username=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePerson);
			deleteStmt.setString(1, user.getUsername());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Persons instance.
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

}
