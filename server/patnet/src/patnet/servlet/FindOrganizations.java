package patnet.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import patnet.model.*;
import patnet.dal.*;

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
		
		Organizations organization = null;
		
		Long id = Long.parseLong(req.getParameter("OrganizationId"));
		if (id < 0) {
			messages.put("success", "Invalid Organization ID.");
		} else {
			//try {
				organization = organizationsDao.getOrganizationById(id);
			//} catch (SQLException e) {
			//	e.printStackTrace();
			//	throw new IOException(e);
			//}
			messages.put("success", "Displaying results for " + id);
			// Save the previous search term, so it can be used as the default
			// in the input box when rendering FindUsers.jsp.
			messages.put("previousOrganizationId", id + "");
		}
        
		req.setAttribute("organization", organization);
		req.getRequestDispatcher("/FindOrganizations.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		
		Organizations organization = null;
		
		Long id = Long.parseLong(req.getParameter("OrganizationId"));
		if (id < 0) {
			messages.put("success", "Invalid Organization ID.");
		} else {
			//try {
				organization = organizationsDao.getOrganizationById(id);
			//} catch (SQLException e) {
			//	e.printStackTrace();
			//	throw new IOException(e);
			//}
			messages.put("success", "Displaying results for " + id);
			// Save the previous search term, so it can be used as the default
			// in the input box when rendering FindUsers.jsp.
			messages.put("previousOrganizationId", id + "");
		}
		
		req.setAttribute("organization", organization);
		req.getRequestDispatcher("/FindOrganizations.jsp").forward(req, resp);
	}
}


