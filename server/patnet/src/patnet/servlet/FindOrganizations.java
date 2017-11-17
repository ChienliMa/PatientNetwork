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

import patnet.model.*;
import patnet.dal.*;

@WebServlet("/findorganizations")
public class FindOrganizations extends HttpServlet {
	
	protected OrganizationsDAO organizationsDao;
	
	@Override
	public void init() {
		organizationsDao = OrganizationsDAO.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		
		List<Organizations> organizations = null;
		
		String city = req.getParameter("city");
		if (city == null || city.trim().isEmpty()) {
			messages.put("success", "Invalid City");
		} else {
			city = city.toUpperCase();
			//try {
				organizations = organizationsDao.getOrganizationByCity(city);
			//} catch (SQLException e) {
			//	e.printStackTrace();
			//	throw new IOException(e);
			//}
			messages.put("success", "Displaying results for " + city);
			// Save the previous search term, so it can be used as the default
			// in the input box when rendering FindUsers.jsp.
			messages.put("previousCity", city + "");
		}
        
		req.setAttribute("organizations", organizations);
		req.getRequestDispatcher("/FindOrganizations.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		
		List<Organizations> organizations = null;
		
		String city = req.getParameter("city");
		if (city == null) {
			messages.put("success", "Invalid City");
		} else {
			//try {
				organizations = organizationsDao.getOrganizationByCity(city);
			//} catch (SQLException e) {
			//	e.printStackTrace();
			//	throw new IOException(e);
			//}
			messages.put("success", "Displaying results for " + city);
			// Save the previous search term, so it can be used as the default
			// in the input box when rendering FindUsers.jsp.
			messages.put("previousCity", city);
		}
        
		req.setAttribute("organizations", organizations);
		req.getRequestDispatcher("/FindOrganizations.jsp").forward(req, resp);
	}
}


