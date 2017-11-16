package patnet.servlet;

import patnet.dal.*;
import patnet.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/physiciandelete")
public class PhysicianDelete extends HttpServlet {
	protected PhysiciansDao physiciansDao;
	
	@Override
	public void init() throws ServletException {
		physiciansDao = PhysiciansDao.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Physicians");        
        req.getRequestDispatcher("/PhysicianDelete.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        int providerId = Integer.parseInt(req.getParameter("providerid"));
        if (providerId < 0) {
            messages.put("title", "Invalid ProviderId");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the BlogUser.
	        Physicians physician = new Physicians(providerId);
	        try {
	        		physician = physiciansDao.delete(physician);
		        	// Update the message.
		        if (physician == null) {
		            messages.put("title", "Successfully deleted " + providerId);
		            messages.put("disableSubmit", "true");
		        } else {
			        	messages.put("title", "Failed to delete " + providerId);
			        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/PhysicianDelete.jsp").forward(req, resp);
	}
}
