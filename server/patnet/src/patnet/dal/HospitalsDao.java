package patnet.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import patnet.model.Organizations;

public class HospitalsDao extends OrganizationsDAO {
	private Function<ResultSet, List<Organizations>> retrivalFunction = 
			rs -> {
		List<Organizations> hospitals = new ArrayList<Organizations>();
		try {
			while (rs.next()) {
				hospitals.add(new Hospitals(
						rs.getLong("OrganizationId"),
						rs.getString("Name"),
						rs.getString("Address"),
						rs.getString("City"),
						rs.getString("State"),
						rs.getInt("ZipCode"),
						rs.getInt("Phone"),
						rs.getString("Location"),
						rs.getInt("NPI"),
						rs.getString("Type"),
						rs.getString("Ownership"), //TODO Currently TEXT type, maybe enum?
						rs.getString("EmergencyServices"), //TODO: Currently TEXT type, maybe boolean?
						rs.getFloat("OverallRating"), 
						Hospitals.Comparison.valueOf(rs.getString("MortalityNationalComparison")), 
						Hospitals.Comparison.valueOf(rs.getString("SafetyNationalComparison")),
						Hospitals.Comparison.valueOf(rs.getString("ReadmissionNationalComparison")),
						Hospitals.Comparison.valueOf(rs.getString("PatientExperienceNationalComparison,")),
						Hospitals.Comparison.valueOf(rs.getString("CareEffectivenessNationalComparison,")),  
						Hospitals.Comparison.valueOf(rs.getString("TimelinessNationalComparison")),
						Hospitals.Comparison.valueOf(rs.getString("MedicalImageUsageEfficientNationalComparison"))));
			} 
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return hospitals;
	};
	
	public HospitalsDao() {
		// TODO Auto-generated constructor stub
	}

	public List<Hospitals> getHospitalsByField(String fieldName, Object fieldValue) {
		String queryString = String.format(
				"SELECT " +
				"  Hospitals.OrganizationId AS OrganizationId, " + 
				"  Name, " + 
				"  Address, " +
				"  City, " +
				"  State, " +
				"  ZipCode, " +
				"  Phone, " + 
				"  Location, " + 
				"  NPI, " +
				"  Type, " +
				"  Ownership, " + 
				"  EmergencyServices, " + 
				"  OverallRating, " +
				"  MortalityNationalComparison, " + 
				"  SafetyNationalComparison, " +
				"  ReadmissionNationalComparison, " + 
				"  PatientExperienceNationalComparison," +  
				"  CareEffectivenessNationalComparison," +  
				"  TimelinessNationalComparison, " +
				"  MedicalImageUsageEfficientNationalComparison " + 
				"FROM Organizations INNER JOIN Hospitals " +
				"  ON Hospitals.OrganizationId = Organizations.OrganizationId " +
				"WHERE %s = ?;", fieldName);
		Function<Connection, PreparedStatement> statementBuilder =
				conn -> GeneralDAO.prepareStatement(conn, 
						queryString, 
						fieldValue);
		return this.<List<Organizations>>execReadQuery(statementBuilder, retrivalFunction);
	}
	
	public List<Hospitals> getHospitalsById(long id) {
		return this.getHospitalsByField("OrganizationId", id);
	}
	
	public List<Hospitals> getHospitalsByNPI(int npi) {
		return this.getHospitalsByField("NPI", npi);
	}
	
	public List<Hospitals> getHospitalsByName(String name) {
		return this.getHospitalsByField("Name", name);
	}
	
	public List<Hospitals> getHospitalsByCity(String city) {
		return this.getHospitalsByField("City", city);
	}
	
	public List<Hospitals> getHospitalsByState(String state) {
		return this.getHospitalsByField("State", state);
	}
	
	public List<Hospitals> getHospitalsByZipCode(int zipCode) {
		return this.getHospitalsByField("ZipCode", zipCode);
	}
	
	public List<Hospitals> getHospitalsByType(String type) {
		return this.getHospitalsByField("Type", type);
	}
	
	public List<Hospitals> getHospitalsByOwnership(String ownership) {
		return this.getHospitalsByField("Ownership", ownership);
	}
}
