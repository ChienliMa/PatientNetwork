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

@WebServlet("/physiciancreate")
public class PhysicianCreate extends HttpServlet {
	protected PhysiciansDao physiciansDao;
	
	@Override
	public void init() throws ServletException {
		physiciansDao = PhysiciansDao.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/PhysicianCreate.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        int providerId = Integer.parseInt(req.getParameter("providerid"));
        if (providerId <= 0) {
			messages.put("success", "Invalid ProviderId");
		}else {
			String firstName = req.getParameter("firstname");
        		String lastName = req.getParameter("lastname");
        		String middleName = req.getParameter("middlename");
        		String credential = req.getParameter("credential");
        		String gender = req.getParameter("gender");
        		String streetAddress1 = req.getParameter("streetaddress1");
        		String streetAddress2 = req.getParameter("streetaddress2");
        		String city = req.getParameter("city");
        		String zipCode = req.getParameter("zipcode");
        		String state = req.getParameter("state");
        		String primarySpecialty = req.getParameter("primaryspecialty");
        		String secondarySpecialties = req.getParameter("secondaryspecialties");
        		try {
        			Physicians physician = new Physicians(providerId, lastName, firstName, middleName, credential, gender, streetAddress1, streetAddress2, city,
        					Integer.parseInt(zipCode), state, primarySpecialty, secondarySpecialties);
        			physician = physiciansDao.create(physician);
        			messages.put("success", "Successfully created " + providerId);
        		} catch (SQLException e) {
        			e.printStackTrace();
        			throw new IOException(e);
        		}
		}
        
        req.getRequestDispatcher("/PhysicianCreate.jsp").forward(req, resp);
	}
}
