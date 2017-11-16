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

@WebServlet("/userupdate")
public class UserUpdate extends HttpServlet {

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
		req.setAttribute("username", messages);
		System.out.println("doGet /userupdate");

		String userName = req.getParameter("username");

		if (userName == null || userName.trim().isEmpty()) {
			messages.put("failure", "Invalid UserName");
		} else {
			messages.put("username", userName);
			messages.put("success", " Kindly enter updated information for " + userName);
		}

		// Just render the JSP.
		req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		System.out.println(req.toString());
		// Retrieve and validate name.
		String userName = req.getParameter("username");

		if (userName == null || userName.trim().isEmpty()) {
			messages.put("success", "Invalid UserName");
		} else {
			// Update the User.
			String firstName = req.getParameter("firstname");
			String lastName = req.getParameter("lastname");
			String password = req.getParameter("password");

			try {
				Users oldUser = usersDao.getUserFromUserName(userName);
				oldUser.setFirstName(firstName);
				oldUser.setLastName(lastName);
				oldUser.setPassword(password);
				usersDao.update(oldUser);
				messages.put("success", "Successfully updated " + userName);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}

		req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
	}
}
