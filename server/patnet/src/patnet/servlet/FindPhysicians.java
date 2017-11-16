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

import patnet.model.*;
import patnet.dal.*;

@WebServlet("/findphysicians")
public class FindPhysicians extends HttpServlet {
	protected PhysiciansDao physiciansDao;
	
	@Override
	public void init() throws ServletException {
		physiciansDao = PhysiciansDao.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        Physicians physician = null;
        
        // Retrieve and validate name.
        // providerid is retrieved from the URL query string.
        int providerId = Integer.parseInt(req.getParameter("providerid"));
        if (providerId < 0) {
            messages.put("success", "Please enter a valid name.");
        } else {
        		// Retrieve physicians, and store as a message.
	        	try {
	            	physician = physiciansDao.getPhysicianById(providerId);
	        } catch (SQLException e) {
    				e.printStackTrace();
    				throw new IOException(e);
            }
	        	messages.put("success", "Displaying results for " + providerId);
	        	// Save the previous search term, so it can be used as the default
	        	// in the input box when rendering FindUsers.jsp.
	        	messages.put("previousFirstName", providerId + "");
        }
        req.setAttribute("physician", physician);
        
        req.getRequestDispatcher("/FindPhysicians.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        Physicians physician = null;
        
        // Retrieve and validate name.
        // providerid is retrieved from the URL query string.
        int providerId = Integer.parseInt(req.getParameter("providerid"));
        if (providerId < 0) {
            messages.put("success", "Please enter a valid name.");
        } else {
        		// Retrieve physicians, and store as a message.
	        	try {
	            	physician = physiciansDao.getPhysicianById(providerId);
	        } catch (SQLException e) {
    				e.printStackTrace();
    				throw new IOException(e);
            }
	        	messages.put("success", "Displaying results for " + providerId);
	        	// Save the previous search term, so it can be used as the default
	        	// in the input box when rendering FindUsers.jsp.
	        	messages.put("previousFirstName", providerId + "");
        }
        req.setAttribute("physician", physician);
        
        req.getRequestDispatcher("/FindPhysicians.jsp").forward(req, resp);
	}
	
}
