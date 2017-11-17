package patnet.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import patnet.model.*;

public class PhysicianReviewsDao {
	protected ConnectionManager connectionManager;
	
	private static PhysicianReviewsDao instance = null;
	protected PhysicianReviewsDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static PhysicianReviewsDao getInstance() {
		if (instance == null) {
			instance = new PhysicianReviewsDao();
		}
		return instance;
	}
	
	public PhysicianReviews create(PhysicianReviews physicianReview) throws SQLException{
		String insertPhysicianReview = 
			"INSERT INTO Reviews(" +
			"ReviewId,Created,Content,Rating,UserName,Type,ProviderId) " +
			"VALUES(?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPhysicianReview);
			insertStmt.setLong(1, physicianReview.getReviewId());
			insertStmt.setTimestamp(2, new Timestamp(physicianReview.getCreated().getTime()));
			insertStmt.setString(3, physicianReview.getContent());
			insertStmt.setInt(4, physicianReview.getRating());
			insertStmt.setString(5, physicianReview.getUsername());
			insertStmt.setString(6, "PhysicianReview");
			insertStmt.setLong(7, physicianReview.getProviderId());
			insertStmt.executeUpdate();
			
//			resultKey = insertStmt.getGeneratedKeys();
//			Long reviewId = null;
//			if(resultKey.next()) {
//				reviewId = resultKey.getLong(1);
//			} else {
//				throw new SQLException("Unable to retrieve auto-generated key.");
//			}
//			physicianReview.setReviewId(reviewId);
			
			return physicianReview;
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
	
	public PhysicianReviews getPhysicianReviewById(Long reviewId) throws SQLException{
		String selectPhysicianReview = 
			"SELECT ReviewId,Created,Content,Rating,UserName,Type,OrganizationId,ProviderId " +
			"FROM Reviews " +
			"WHERE ReviewId=? and Type='PhysicianReview';";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPhysicianReview);
			selectStmt.setLong(1, reviewId);
			results = selectStmt.executeQuery();
			
			if (results.next()) {
				Long resultReviewId = results.getLong("ReviewId");
				Date created = new Date(results.getTimestamp("Created").getTime());
				String content = results.getString("Content");
				Integer rating = results.getInt("Rating");
				String userName = results.getString("UserName"); 
				Long providerId = results.getLong("ProviderId");
				PhysicianReviews physicianReview = new PhysicianReviews(resultReviewId, userName, providerId, rating, content, 
						created);
				return physicianReview;
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
	
	public PhysicianReviews getPhysicianReviewByProviderId(Long providerId) throws SQLException{
		String selectPhysicianReview = 
			"SELECT ReviewId,Created,Content,Rating,UserName,Type,OrganizationId,ProviderId " +
			"FROM Reviews " +
			"WHERE ProviderId=? and Type='PhysicianReview';";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPhysicianReview);
			selectStmt.setLong(1, providerId);
			results = selectStmt.executeQuery();
			
			if (results.next()) {
				Long reviewId = results.getLong("ReviewId");
				Date created = new Date(results.getTimestamp("Created").getTime());
				String content = results.getString("Content");
				Integer rating = results.getInt("Rating");
				String userName = results.getString("UserName"); 
				Long resultProviderId = results.getLong("ProviderId");
				PhysicianReviews physicianReview = new PhysicianReviews(reviewId, userName, resultProviderId, rating, content, 
						created);
				return physicianReview;
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
	
	public PhysicianReviews delete(PhysicianReviews physicianReview) throws SQLException{
		String deletePhysicianReview = "DELETE FROM Physicians WHERE ReviewId=? and Type='PhysicianReview';";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePhysicianReview);
			deleteStmt.setLong(1, physicianReview.getReviewId());
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
	
	public PhysicianReviews updateContent(PhysicianReviews physicianReview, String newContent) throws SQLException{
		String updateRevewContent = "UPDATE Physicians SET Content=? WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateRevewContent);
			updateStmt.setString(1, newContent);
			updateStmt.setLong(2, physicianReview.getReviewId());
			updateStmt.executeUpdate();
			
			physicianReview.setContent(newContent);
			return physicianReview;
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
