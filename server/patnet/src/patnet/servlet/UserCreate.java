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

import patnet.dal.UsersDao;
import patnet.model.Users;
import patnet.model.Users.Type;

@WebServlet("/usercreate")
public class UserCreate extends HttpServlet {

	protected UsersDao usersDao;

	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
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

			int organizationId = Integer.valueOf(req.getParameter("organizationId"));
			int physicianId = Integer.valueOf(req.getParameter("physicianId"));

			try {
				// Exercise: parse the input for StatusLevel.
				Users blogUser = new Users(userName, password, Type.valueOf(userType), organizationId, physicianId,
						firstName, lastName);
				System.out.println("doing post " + blogUser.toString());
				blogUser = usersDao.create(blogUser);
				messages.put("success", "Successfully created " + userName);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}

		req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
	}
}
