package patnet.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import patnet.*;
import patnet.dal.PhysicianReviewsDao;
import patnet.model.PhysicianReviews;

@WebServlet("/physicianreviewcreate")
public class PhysicianReviewCreate extends HttpServlet {
	protected PhysicianReviewsDao physicianReviewsDao;
	
	@Override
	public void init() throws ServletException {
		physicianReviewsDao = PhysicianReviewsDao.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/PhysicianReviewCreate.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        Long reviewId = Long.parseLong(req.getParameter("reviewid"));
        if (reviewId == null || reviewId < 0) {
			messages.put("success", "Invalid ReviewId");
		}else {
			String userName = req.getParameter("username");
        		Date created = new Date();
        		int rating = Integer.parseInt(req.getParameter("rating"));
        		String content = req.getParameter("content");
        		Long providerId = Long.parseLong(req.getParameter("providerid"));
        		try {
        			PhysicianReviews physicianReview = new PhysicianReviews(reviewId, userName, providerId, rating, content, created);
        			physicianReview = physicianReviewsDao.create(physicianReview);
        			messages.put("success", "Successfully created " + reviewId);
        		} catch (SQLException e) {
        			e.printStackTrace();
        			throw new IOException(e);
        		}
		}
        
        req.getRequestDispatcher("/PhysicianReviewCreate.jsp").forward(req, resp);
	}
}
