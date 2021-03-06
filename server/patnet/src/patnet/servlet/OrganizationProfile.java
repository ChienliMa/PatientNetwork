package patnet.servlet;
import java.io.IOException;
import java.sql.SQLException;
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
import patnet.dal.UsersDao;
import patnet.model.OrganizationReviews;
import patnet.model.Organizations;
import patnet.model.Users;

@WebServlet("/OrganizationProfile")
public class OrganizationProfile extends HttpServlet{
	private OrganizationsDAO oDao = new OrganizationsDAO();
	private OrganizationReviewsDAO rDao = new OrganizationReviewsDAO();
	private UsersDao uDao = UsersDao.getInstance();
	
	public void doGet(HttpServletRequest req,  HttpServletResponse res) 
			throws ServletException, IOException{
        Organizations org = oDao.getOrganizationById(Long.parseLong(req.getParameter("OrganizationId")));
        req.setAttribute("Organization", org);
       
        List<OrganizationReviews> reviews = rDao.getOrganizationReviewsByOrgId(org.getOrganizationId());
        req.setAttribute("Reviews", reviews);
        
        req.setAttribute("Username", req.getParameter("Username"));
        
        req.getRequestDispatcher("/OrganizationProfile.jsp").forward(req, res);
	}
}
