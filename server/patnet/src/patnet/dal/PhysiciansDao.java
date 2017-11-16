package patnet.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import patnet.model.*;

public class PhysiciansDao {
	protected ConnectionManager connectionManager;
	
	private static PhysiciansDao instance = null;
	protected PhysiciansDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static PhysiciansDao getInstance() {
		if (instance == null) {
			instance = new PhysiciansDao();
		}
		return instance;
	}
	
	public Physicians create(Physicians physician) throws SQLException{
		String insertPhysician = 
			"INSERT INTO Physicians(" +
			"ProviderId,LastName,FirstName,MiddleName,Credential,Gender," +
			"StreetAddress1,StreetAddress2,City,ZipCode,State,PrimarySpecialty,SecondarySpecialties) " +
			"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPhysician);
			insertStmt.setInt(1, physician.getProviderId());
			insertStmt.setString(2, physician.getLastName());
			insertStmt.setString(3, physician.getFirstName());
			insertStmt.setString(4, physician.getMiddleName());
			insertStmt.setString(5, physician.getCredential());
			insertStmt.setString(6, physician.getGender());
			insertStmt.setString(7, physician.getStreetAddress1());
			insertStmt.setString(8, physician.getStreetAddress2());
			insertStmt.setString(9, physician.getCity());
			insertStmt.setInt(10, physician.getZipCode());
			insertStmt.setString(11, physician.getState());
			insertStmt.setString(12, physician.getPrimarySpecialty());
			insertStmt.setString(13, physician.getSecondarySpecialties());
			insertStmt.executeUpdate();
			
			return physician;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}
	
	public Physicians getPhysicianById(int providerId) throws SQLException{
		String selectPhysician = 
			"SELECT ProviderId,LastName,FirstName,MiddleName,Credential,Gender," + 
			"StreetAddress1,StreetAddress2,City,ZipCode,State,PrimarySpecialty,SecondarySpecialties " +
			"FROM Physicians " +
			"WHERE ProviderId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPhysician);
			selectStmt.setInt(1, providerId);
			results = selectStmt.executeQuery();
			
			if (results.next()) {
				int resultProviderId = results.getInt("ProviderId");
				String lastName = results.getString("LastName");
				String firstName = results.getString("FirstName");
				String middleName = results.getString("MiddleName");
				String credential = results.getString("Credential");
				String gender = results.getString("Gender"); 
				String streetAddress1 = results.getString("StreetAddress1");
				String streetAddress2 = results.getString("StreetAddress2");
				String city = results.getString("City");
				int zipCode = results.getInt("ZipCode");
				String state = results.getString("State");
				String primarySpecialty = results.getString("PrimarySpecialty");
				String secondarySpecialties = results.getString("SecondarySpecialties");
				Physicians physician = new Physicians(providerId,lastName,firstName,middleName,credential,gender,
						streetAddress1,streetAddress2,city,zipCode,state,primarySpecialty,secondarySpecialties);
				return physician;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	
	
	public Physicians delete(Physicians physician) throws SQLException{
		String deletePhysician = "DELETE FROM Physicians WHERE ProviderId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePhysician);
			deleteStmt.setInt(1, physician.getProviderId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the BlogPosts instance.
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
	
	public Physicians updateCity(Physicians physician, String newCity) throws SQLException{
		String updateBlogComment = "UPDATE Physicians SET City=? WHERE ProviderId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateBlogComment);
			updateStmt.setString(1, newCity);
			updateStmt.setInt(2, physician.getProviderId());
			updateStmt.executeUpdate();
			
			physician.setCity(newCity);
			return physician;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
}
