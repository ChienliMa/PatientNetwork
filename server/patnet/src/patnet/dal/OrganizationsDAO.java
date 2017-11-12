package patnet.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import patnet.model.Organizations;



public class OrganizationsDAO extends GeneralDAO{
	private Function<ResultSet, List<Organizations>> retrivalFunction = 
			rs -> {
				List<Organizations> organizations = new ArrayList<Organizations>();
				try {
					while (rs.next()) {
						organizations.add(new Organizations(
								rs.getLong("OrganizationId"),
								rs.getString("Name"),
								rs.getString("Address"),
								rs.getString("City"),
								rs.getString("State"),
								rs.getInt("ZipCode"),
								rs.getInt("Phone"),
								rs.getString("Location")));
					} 
				}catch (SQLException e) {
					e.printStackTrace();
				}
				return organizations;
			};
			
	public Organizations create(Organizations organization) {
		Function<Connection, PreparedStatement> statementBuilder =
				conn -> GeneralDAO.prepareStatement(conn, 
						"insert into Organizations (Name,Address,City,State,ZipCode,Phone,Location) "
						+ "values (?,?,?,?,?,?,?)", 
						organization.getName(),
						organization.getAddress(),
						organization.getCity(),
						organization.getState(),
						organization.getZipcode(),
						organization.getPhone(),
						organization.getLocation());
		organization.setOrganizationid(this.execWriteQuery(statementBuilder));
		return organization;
	}
				
	public List<Organizations> getOrganizationByFiled(String fieldName, Object fieldValue) {
		String queryString = String.format("select * from Organizations where %s = ?", fieldName);
		Function<Connection, PreparedStatement> statementBuilder =
				conn -> GeneralDAO.prepareStatement(conn, 
						queryString, 
						fieldValue);
		return this.<List<Organizations>>execReadQuery(statementBuilder, retrivalFunction);
	}
	
	public Organizations getOrganizationById(Long id) {
		return this.getOrganizationByFiled("OrganizationId", id).get(0);
	}
	
	public List<Organizations> getOrganizationByCity(String City) {
		return this.getOrganizationByFiled("City", City);
	}
	
	public List<Organizations> getOrganizationByState(Long State) {
		return this.getOrganizationByFiled("State", State);
	}
	
	public Organizations deleteOrganization(Organizations organization) {
		Function<Connection, PreparedStatement> statementBuilder =
				conn -> GeneralDAO.prepareStatement(conn, 
						"delete from Organizations where OrganizationId = ?", 
						organization.getOrganizationid());
		
		if (this.execWriteQuery(statementBuilder) != null) {
			return null;
		}
		return organization;
	}
	
	public Organizations updateOrganizationByField(Organizations organization, String fieldName, Object newValue) {
		String updateQuery = String.format("update Organizations set %s = ? where OrganizationId = ?", fieldName);
		Function<Connection, PreparedStatement> statementBuilder =
				conn -> GeneralDAO.prepareStatement(conn, 
						updateQuery, 
						newValue,
						organization.getOrganizationid());
		
		if (this.execWriteQuery(statementBuilder) != null) {
			organization.update(fieldName, newValue);
		}
		return organization;
	}
}
