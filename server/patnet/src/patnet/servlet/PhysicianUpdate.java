package patnet.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import patnet.dal.*;
import patnet.model.*;

@WebServlet("/physicianupdate")
public class PhysicianUpdate extends HttpServlet {
	protected PhysiciansDao physiciansDao;
	
	@Override
	public void init() throws ServletException {
		physiciansDao = PhysiciansDao.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        int providerId = Integer.parseInt(req.getParameter("providerid"));
        if (providerId < 0) {
            messages.put("success", "Please enter a valid ProviderId");
        }else {
	        	try {
	        		Physicians physicians = physiciansDao.getPhysicianById(providerId);
	        		if(physicians == null) {
	        			messages.put("success", "ProviderId does not exist.");
	        		}
        		req.setAttribute("providerId", providerId);
	        	} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/PhysicianUpdate.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        int providerId = Integer.parseInt(req.getParameter("providerid"));
        if (providerId < 0) {
            messages.put("success", "Please enter a valid ProviderId");
        } else {
        	try {
        		Physicians physician = physiciansDao.getPhysicianById(providerId);
        		if(physician == null) {
        			messages.put("success", "ProviderId does not exist. No update to perform.");
        		} else {
        			String newCity = req.getParameter("city");
        			if (newCity == null || newCity.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid ProviderId.");
        	        } else {
        	        	physician = physiciansDao.updateCity(physician, newCity);
        	        	messages.put("success", "Successfully updated " + providerId);
        	        }
        		}
        		req.setAttribute("physician", physician);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/PhysicianUpdate.jsp").forward(req, resp);
	}
}
