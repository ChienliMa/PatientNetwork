package patnet.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.coyote.http11.filters.VoidInputFilter;

import com.sun.org.apache.bcel.internal.generic.DALOAD;

import patnet.dal.OrganizationReviewsDAO;
import patnet.model.OrganizationReviews;
import patnet.model.Reviews;

@WebServlet("/OrganizationReviews")
public class OrganizationReviewServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req,  HttpServletResponse res) throws IOException, ServletException {
		switch (req.getParameter("method")) {
		case "delete":
			deleteReview(req, res);
			break;
		case "edit":
			getEdit(req, res);
			break;
		}
	}
	
	private void getEdit(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		OrganizationReviews reviews = new OrganizationReviewsDAO()
				.getReviewById(Long.parseLong(req.getParameter("ReviewId")));
        req.setAttribute("Review", reviews);
		req.getRequestDispatcher("/OrganizationReviewEdit.jsp").forward(req, res);
	}

	public void deleteReview(HttpServletRequest req,  HttpServletResponse res) throws IOException {
		Long reviewId = Long.parseLong(req.getParameter("ReviewId"));
		new OrganizationReviewsDAO().deleteById(reviewId);
		backToProfile(req, res);	
	}
	
	public void doPost(HttpServletRequest req,  HttpServletResponse res) throws IOException {
		switch (req.getParameter("method")) {
		case "create":
			createReview(req, res);
			break;
		case "edit":
			editReview(req, res);
			break;
		}
	}
	
	private void editReview(HttpServletRequest req, HttpServletResponse res)  throws IOException  {
		OrganizationReviewsDAO dao = new OrganizationReviewsDAO();
		OrganizationReviews review = dao.getReviewById(Long.parseLong(req.getParameter("ReviewId")));
		review.setContent(req.getParameter("Content"));
		review.setRating(Integer.parseInt(req.getParameter("Rating")));
		dao.update(review);
		backToProfile(req, res);
	}

	public void createReview(HttpServletRequest req,  HttpServletResponse res) throws IOException {
		OrganizationReviews review = new OrganizationReviews();
		review.setContent(req.getParameter("Content"));
		review.setRating(Integer.parseInt(req.getParameter("Rating")));
		review.setOrganizationId(Long.parseLong(req.getParameter("OrganizationId")));
		review.setUsername(req.getParameter("Username"));
		new OrganizationReviewsDAO().create(review);
		backToProfile(req, res);
	}
	
	public void backToProfile(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.sendRedirect(String.format("/patnet/OrganizationProfile?OrganizationId=%s&Username=%s", 
				req.getParameter("OrganizationId"),
				req.getParameter("Username")));	
	}
}
