package patnet.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	public Users createOrdinary(Users user) throws SQLException {
		String insertRestaurants = "INSERT INTO Users(Username,Password,Type, FirstName,LastName)"
				+ "VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRestaurants);

			insertStmt.setString(1, user.getUsername());
			insertStmt.setString(2, user.getPassword());
			insertStmt.setString(3, user.getType().name());
			insertStmt.setString(4, user.getFirstName());
			insertStmt.setString(5, user.getLastName());

			System.out.println(insertStmt.toString());

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

	public Users createOrganization(Users user) throws SQLException {
		String insertRestaurants = "INSERT INTO Users(Username,Password,Type,OrganizationId, FirstName,LastName)"
				+ "VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRestaurants);

			insertStmt.setString(1, user.getUsername());
			insertStmt.setString(2, user.getPassword());
			insertStmt.setString(3, user.getType().name());
			insertStmt.setInt(4, user.getOrganizationId());
			insertStmt.setString(5, user.getFirstName());
			insertStmt.setString(6, user.getLastName());

			System.out.println(insertStmt.toString());

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

	public Users createPhysician(Users user) throws SQLException {
		String insertRestaurants = "INSERT INTO Users(Username,Password,Type,PhysicianId, FirstName,LastName)"
				+ "VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRestaurants);

			insertStmt.setString(1, user.getUsername());
			insertStmt.setString(2, user.getPassword());
			insertStmt.setString(3, user.getType().name());
			insertStmt.setInt(4, user.getPhysicianId());
			insertStmt.setString(5, user.getFirstName());
			insertStmt.setString(6, user.getLastName());

			System.out.println(insertStmt.toString());

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
				+ " SET Password=?, Type=?, FirstName=?, LastName=? "
				+ "WHERE Username=?";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateUserStatement);

			updateStmt.setString(1, user.getPassword());
			updateStmt.setString(2, user.getType().name());
//			updateStmt.setInt(3, user.getOrganizationId());
//			updateStmt.setInt(4, user.getPhysicianId());
			updateStmt.setString(3, user.getFirstName());
			updateStmt.setString(4, user.getLastName());
			updateStmt.setString(5, user.getUsername());

			System.out.println(updateStmt.toString());

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

	public List<Users> getUsersFromFirstName(String firstName) throws SQLException {
		List<Users> blogUsers = new ArrayList<>();
		String selectBlogUser = "SELECT Username,Password,Type,OrganizationId,PhysicianId, FirstName,LastName FROM Users WHERE FirstName=? ;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBlogUser);
			selectStmt.setString(1, firstName);
			results = selectStmt.executeQuery();
			while (results.next()) {
				System.out.println("here");
				String resultUserName = results.getString("Username");
				String password = results.getString("Password");
				Users.Type type = Users.Type.valueOf(results.getString("Type"));
				int organizationId = results.getInt("OrganizationId");
				int physicianId = results.getInt("PhysicianId");

				String resultFirstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				Users user = new Users(resultUserName, password, type, organizationId, physicianId, resultFirstName,
						lastName);
				blogUsers.add(user);
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
		return blogUsers;

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
