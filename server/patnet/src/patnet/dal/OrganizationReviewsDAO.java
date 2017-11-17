package patnet.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import patnet.model.OrganizationReviews;
import patnet.model.Reviews;

public class OrganizationReviewsDAO  extends GeneralDAO{
	private Function<ResultSet, List<OrganizationReviews>> retrivalFunction = 
			rs -> {
				List<OrganizationReviews> reviews = new ArrayList();
				try {
					while(rs.next()) {
						reviews.add(new OrganizationReviews(
								rs.getLong("ReviewId"),
								rs.getString("UserName"),
								rs.getLong("OrganizationId"),
								rs.getString("Content"),
								rs.getDate("Created"),
								rs.getInt("Rating")));
					}
				} catch (SQLException e) {
					// TODO: handle exception
				}
				return reviews;
			};
	public List<OrganizationReviews> getOrganizationReviewsByOrgId(Long id) {
		List<OrganizationReviews> reviews = new ArrayList();
		Function<Connection, PreparedStatement> provider = 
				conn -> GeneralDAO.prepareStatement(conn,
						"select * from Reviews where Type = 'OrganizationReview' and Organizationid = ?", 
						id);
		return this.<List<OrganizationReviews>>execReadQuery(provider, retrivalFunction);
	}
	
	public void deleteById(Long id) {
		Function<Connection, PreparedStatement> provider = 
				conn -> GeneralDAO.prepareStatement(conn,
						"delete from Reviews where ReviewId = ?", 
						id);
		this.execWriteQuery(provider);
	}

	
	
	public void create(OrganizationReviews review) {
		Function<Connection, PreparedStatement> provider = 
				conn -> GeneralDAO.prepareStatement(conn,
						"insert into Reviews (Content, Rating, UserName, Type, OrganizationId) values "
						+ "(?,?,?,'OrganizationReview',?)", 
						review.getContent(),
						review.getRating(),
						review.getUsername(),
						review.getOrganizationId());	
		this.execWriteQuery(provider);
	}

	public OrganizationReviews getReviewById(Long id) {
		List<OrganizationReviews> reviews = new ArrayList();
		Function<Connection, PreparedStatement> provider = 
				conn -> GeneralDAO.prepareStatement(conn,
						"select * from Reviews where Type = 'OrganizationReview' and ReviewId = ?", 
						id);
		return this.<List<OrganizationReviews>>execReadQuery(provider, retrivalFunction).get(0);
	}
	
	public OrganizationReviews update(OrganizationReviews review) {
		Function<Connection, PreparedStatement> provider = 
				conn -> GeneralDAO.prepareStatement(conn,
						"update Reviews set"
						+ " Content = ?, "
						+ "Rating = ? "
						+ "where ReviewId = ?",
						review.getContent(),
						review.getRating(),
						review.getReviewId());	
		this.execWriteQuery(provider);
		return null;
	}
}
