package patnet.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.jrockit.jfr.RequestableEvent;
import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry.Entry;
import com.sun.corba.se.spi.orb.StringPair;
import com.sun.javafx.collections.MappingChange.Map;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.xml.internal.bind.v2.model.core.ID;

import jdk.nashorn.internal.runtime.regexp.joni.SearchAlgorithm;
import patnet.dal.OrganizationsDAO;
import patnet.dal.UsersDao;
import patnet.model.OrganizationReviews;
import patnet.model.Organizations;
import patnet.model.Users;
import sun.security.x509.PrivateKeyUsageExtension;


@WebServlet("/Organizations")
public class OrganizationsServlet extends HttpServlet{
	private OrganizationsDAO oDao = new OrganizationsDAO();

	// for searching
	public void doGet(HttpServletRequest req,  HttpServletResponse res) throws ServletException, IOException {
		switch (req.getParameter("for").toString()) {
		case "search":
			search(req, res);
			break;
		case "create":
			getCreate(req, res);
			break;
		case "edit":
			getEdit(req, res);
			break;
		}
	}
	
	private void search(HttpServletRequest req,  HttpServletResponse res) {
		System.out.println("search organization");
		
	}

	// get create page
	private void getCreate(HttpServletRequest req,  HttpServletResponse res) throws ServletException, IOException {
		Organizations org = new Organizations();
		org.setName("New Orgnaization");
		req.setAttribute("Organization", org);
		req.setAttribute("EditPropose", "create");
        req.getRequestDispatcher("/OrganizationEdit.jsp").forward(req, res);
	}
	
	// get edit page
	private void getEdit(HttpServletRequest req,  HttpServletResponse res) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("OrganizationId"));
		Organizations organization = oDao.getOrganizationById(id);
		req.setAttribute("Organization", oDao.getOrganizationById(id));
		req.setAttribute("EditPropose", "edit");
        req.getRequestDispatcher("/OrganizationEdit.jsp").forward(req, res);
	}
	
	public void doPost(HttpServletRequest req,  HttpServletResponse res) throws IOException {
		String propse = req.getParameter("EditPropose"); 
		switch (req.getParameter("EditPropose").toString()) {
		case "create":
			postCreate(req, res);
			break;
		case "edit":
			postEdit(req, res);
			break;
		}
	}
	
	// for create
	public void postCreate(HttpServletRequest req,  HttpServletResponse res) throws IOException {
		Organizations org = new Organizations();
		org.setName(req.getParameter("Name"));
		org.setAddress(req.getParameter("Address"));
		org.setCity(req.getParameter("City"));
		org.setState(req.getParameter("State"));
		org.setZipcode(req.getParameter("ZipCode"));
		org.setPhone(req.getParameter("Phone"));
		org = oDao.create(org);
		res.sendRedirect(String.format("/patnet/OrganizationProfile?OrganizationId=%d", org.getOrganizationId()));
	}
	
	// for update
	public void postEdit(HttpServletRequest req,  HttpServletResponse res) throws IOException{
		Organizations org = new Organizations();
		java.util.Map<String, String[]> adr = req.getParameterMap();
		
		org.setName(req.getParameter("Location")==null?"":req.getParameter("Location"));
		org.setName(req.getParameter("Name"));
		org.setAddress(req.getParameter("Address"));
		org.setCity(req.getParameter("City"));
		org.setState(req.getParameter("State"));
		org.setZipcode(req.getParameter("ZipCode"));
		org.setPhone(req.getParameter("Phone")); 
		org.setOrganizationId(Long.parseLong(req.getParameter("OrganizationId")));
		oDao.updateOrganizations(org);
        res.sendRedirect(String.format("/patnet/OrganizationProfile?OrganizationId=%d", org.getOrganizationId()));	
	}
	
	// for delete
	public void doDelete(HttpServletRequest req,  HttpServletResponse res) throws IOException {
		String idstr = req.getParameter("organizationid");
		Organizations org = new Organizations();
		org.setOrganizationId(Long.parseLong(idstr));
		org = oDao.deleteOrganization(org);
		Users user = UsersDao.getInstance().getUserFromUserName(req.getParameter("UserName"));
		user.setOrganizationId(0);
		try {
			UsersDao.getInstance().update(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res.sendRedirect(String.format("/patnet/userprofile?username=%s", user.getUsername()));
	}
}
