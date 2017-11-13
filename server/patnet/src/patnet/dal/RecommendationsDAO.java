//package patnet.dal;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.function.Function;
//
//import com.neu.dbms.model.Recommendations;
//
//import java.sql.PreparedStatement;
//
//public class RecommendationsDAO extends GeneralDAO{
//	private Function<ResultSet, List<Recommendations>> retrivalFunc = 
//			rs -> {
//				List<Recommendations> recommendations = new ArrayList<Recommendations>();
//				try {
//					Recommendations recommendation = new Recommendations();
//					recommendation.setRestaurant_id(rs.getLong("RestaurantId"));
//					recommendation.setUsername(rs.getString("UserName"));
//					recommendations.add(recommendation);
//				} catch (SQLException e) {
//					// TODO: handle exception
//				}
//				return recommendations;
//			};
//	
//	public Recommendations create(Recommendations recommendation) {
//		Function<Connection, PreparedStatement> provider =  
//				conn -> GeneralDAO.prepareStatement(conn, "insert into Recommendations (RestaurantId,UserName)values (?, ?)", recommendation.getRestaurant_id(), recommendation.getUsername());
//		this.execWriteQuery(provider);
//		return recommendation;
//	}
//	
//	public Recommendations getRecommendationById(int recommendationId) {
//		Function<Connection, PreparedStatement> provider = 
//				conn -> GeneralDAO.prepareStatement(conn, "select * from Recommendations where RecommendationId = ?", new Integer(recommendationId));
//		return this.<List<Recommendations>>execReadQuery(provider, retrivalFunc).get(0);
//	}
//	
//	public List<Recommendations> getRecommendationsByUserName(String userName) {
//		Function<Connection, PreparedStatement> provider =  
//				conn -> GeneralDAO.prepareStatement(conn, "select * from Recommdations where UserName = ?", userName);
//		return this.<List<Recommendations>>execReadQuery(provider, retrivalFunc);
//	}
//	
//	public List<Recommendations> getRecommendationsByRestaurantId(int restaurantId) {
//		Function<Connection, PreparedStatement> provider =  
//				conn -> GeneralDAO.prepareStatement(conn, "select * from Recommdations where RestaurantId = ?", new Integer(restaurantId));
//				return this.<List<Recommendations>>execReadQuery(provider, retrivalFunc);
//	}
//	
//	public Recommendations delete(Recommendations recommendation) {
//		Function<Connection, PreparedStatement> provider =  
//				conn -> GeneralDAO.prepareStatement(conn, "delete from Recommendations where RestaurantId = ?", recommendation.getRestaurant_id());
//		this.execWriteQuery(provider);
//		return recommendation;
//	}
//
//}
