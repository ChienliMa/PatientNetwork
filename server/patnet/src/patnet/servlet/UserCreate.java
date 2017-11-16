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

import patnet.dal.OrganizationsDAO;
import patnet.dal.UsersDao;
import patnet.model.Organizations;
import patnet.model.Users;
import patnet.model.Users.Type;

@WebServlet("/usercreate")
public class UserCreate extends HttpServlet {

	protected UsersDao usersDao;
	protected OrganizationsDAO organizationsDAO;

	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
		organizationsDAO = new OrganizationsDAO();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		// Just render the JSP.
		System.out.println("doget /usercreate");

		req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		// Retrieve and validate name.
		String userName = req.getParameter("username");
		if (userName == null || userName.trim().isEmpty()) {
			messages.put("success", "Invalid UserName");
		} else {
			// Create the BlogUser.
			String firstName = req.getParameter("firstname");
			String lastName = req.getParameter("lastname");
			String password = req.getParameter("password");
			String userType = req.getParameter("hiddenField");

			System.out.println("usertype is " + userType);

			try {
				Users blogUser = null;
				if (userType.equals(Type.PHYSICIAN.name())) {
					System.out.println("creatng physc user");
					int physicianId = Integer.valueOf(req.getParameter("physicianId"));
					blogUser = new Users(userName, password, Type.PHYSICIAN, firstName, lastName);
					blogUser.setPhysicianId(physicianId);
					blogUser = usersDao.createPhysician(blogUser);
					messages.put("success", "Successfully created " + userName);
				} else if (userType.equals(Type.ORGANIZATION.name())) {
					System.out.println("creting org user");
					String orgName = req.getParameter("organizationName");
					Organizations org = organizationsDAO.getOrganizationByName(orgName);
					int organizationId = org.getOrganizationid().intValue();
					blogUser = new Users(userName, password, Type.ORGANIZATION, firstName, lastName);
					blogUser.setOrganizationId(organizationId);
					blogUser = usersDao.createOrganization(blogUser);
					messages.put("success", "Successfully created " + userName);
				} else {
					System.out.println("creating ordinary user");
					blogUser = new Users(userName, password, Type.ORDINARY, firstName, lastName);
					blogUser = usersDao.createOrdinary(blogUser);
					messages.put("success", "Successfully created " + userName);

				}
				System.out.println("doing post " + blogUser.toString());
				messages.put("success", "Successfully created " + userName);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}

		req.getRequestDispatcher("/userprofile?username="+userName).forward(req, resp);
	}
}
