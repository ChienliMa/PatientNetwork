package patnet.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import patnet.model.Organizations;

public class PhysiciansDao extends GeneralDAO {
	private Function<ResultSet, List<Organizations>> retrivalFunction = 
			rs -> {
		List<Physicians> physicians = new ArrayList<Organizations>();
		try {
			while (rs.next()) {
				physicians.add(new Physicians(
						rs.getLong("ProviderId"),
						rs.getString("LastName"),
						rs.getString("FirstName"),
						rs.getString("MiddleName"),
						rs.getString("Credential"),
						rs.getString("Gender"), // TODO: I think this should be enum type.
						rs.getString("StreetAddress1"),
						rs.getString("StreetAddress2"),
						rs.getString("City"),
						rs.getInt("ZipCode"),
						rs.getString("State"),
						rs.getString("PrimarySpecialty"),
						rs.getString("SecondarySpecialties")));
			} 
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return physicians;
	};
	public PhysiciansDao() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Physicians> getPhysiciansByField(String fieldName, Object fieldValue) {
		String queryString = String.format(
				"SELECT * FROM Physicians WHERE %s = ?;", fieldName);
		Function<Connection, PreparedStatement> statementBuilder =
				conn -> GeneralDAO.prepareStatement(conn, 
						queryString, 
						fieldValue);
		return this.<List<Organizations>>execReadQuery(statementBuilder, retrivalFunction);
	}
	
	public Physicians getPhysiciansById(Long id) {
		return this.getPhysiciansByField("ProviderId", id).get(0);
	}
	
	public List<Physicians> getPhysiciansByLastName(String lastName) {
		return this.getPhysiciansByField("LastName", lastName);
	}
	
	public List<Physicians> getPhysiciansByFirstName(String firstName) {
		return this.getPhysiciansByField("FirstName", firstName);
	}
	
	public List<Physicians> getPhysiciansByGender(String gender) {
		return this.getPhysiciansBy("Gender", gender);
	}

	public List<Physicians> getPhysiciansByCity(String city) {
		return this.getPhysiciansBy("City", city);
	}
	
	public List<Physicians> getPhysiciansByZipCode(String zipCode) {
		return this.getPhysiciansBy("ZipCode", zipCode);
	}
	
	public List<Physicians> getPhysiciansByState(String state) {
		return this.getPhysiciansBy("State", state);
	}
	
	public List<Physicians> getPhysiciansBySpecialty(String specialty) {
		return this.getPhysiciansBy("PrimarySpecialty", specialty);
	}
}
