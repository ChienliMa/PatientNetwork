package patnet.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import patnet.dal.PhysicianReviewsDao;
import patnet.model.PhysicianReviews;

@WebServlet("/physicianreviewdisplay")
public class PhysicianReviewDisplay extends HttpServlet {
	protected PhysicianReviewsDao physicianReviewsDao;
	
	@Override
	public void init() throws ServletException {
		physicianReviewsDao = PhysicianReviewsDao.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
		// Retrieve and validate UserName.
        Long providerId = Long.parseLong(req.getParameter("providerid"));
        if (providerId == null || providerId < 0) {
            messages.put("title", "Invalid reviewid.");
        } else {
        	messages.put("title", "PhysicianReview for physician: " + providerId);
        }
        
        // Retrieve BlogUsers, and store in the request.
        PhysicianReviews physicianReview = new PhysicianReviews();
        try {
//        	PhysicianReviews physicianReview = new PhysicianReviews(reviewId);
        	physicianReview = physicianReviewsDao.getPhysicianReviewByProviderId(providerId);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.setAttribute("physicianreview", physicianReview);
        req.getRequestDispatcher("/PhysicianReviewDisplay.jsp").forward(req, resp);
	}
}
