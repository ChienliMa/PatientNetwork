package patnet.servlet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import patnet.dal.OrganizationsDAO;
import patnet.model.OrganizationReviews;
import patnet.model.Organizations;

@WebServlet("/OrganizationProfile")
public class OrganizationProfile extends HttpServlet{
	private OrganizationsDAO dao = new OrganizationsDAO();
	public void doGet(HttpServletRequest req,  HttpServletResponse res) 
			throws ServletException, IOException{
		
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.getAttribute("OrganizationId");
        Organizations organizations = dao.getOrganizationById(new Long(3));
        Organizations testOrg = new Organizations(2L, "FFORg", "good", "sea", "wa", 323232, 43434, "FF");
        req.setAttribute("Organization", testOrg.toMap());
       
//        List<OrganizationReviews> reviews = new ArrayList<OrganizationReviews>();
//        reviews.add(new OrganizationReviews(1L,  "LOL", 2L,"very good", new Date(), 5));
//        reviews.add(new OrganizationReviews(2L, "DOTA",2L,  "very bad", new Date(), 0));
//        req.setAttribute("Reviews", reviews);

        req.getRequestDispatcher("/OrganizationProfile.jsp").forward(req, res);
	}
	
}
