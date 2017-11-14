package patnet.servlet;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import patnet.dal.OrganizationReviewsDAO;
import patnet.dal.OrganizationsDAO;
import patnet.model.OrganizationReviews;
import patnet.model.Organizations;

@WebServlet("/OrganizationProfile")
public class OrganizationProfile extends HttpServlet{
	private OrganizationsDAO oDao = new OrganizationsDAO();
	private OrganizationReviewsDAO rDao = new OrganizationReviewsDAO();
	
	public void doGet(HttpServletRequest req,  HttpServletResponse res) 
			throws ServletException, IOException{
        Organizations org = oDao.getOrganizationById(new Long(req.getParameter("OrganizationId")));
        req.setAttribute("Organization", org.toMap());
       
        List<OrganizationReviews> reviews = rDao.getOrganizationReviewsByOrgId(org.getOrganizationid());
        req.setAttribute("Reviews", reviews);

        req.getRequestDispatcher("/OrganizationProfile.jsp").forward(req, res);
	}
}
