package patnet.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import patnet.dal.*;
import patnet.model.*;


@WebServlet("/Organizations")
public class OrganizationsServlet extends HttpServlet{
	private OrganizationsDAO oDao = OrganizationsDAO.getInstance();

	// for searching
	public void doGet(HttpServletRequest req,  HttpServletResponse res) throws ServletException, IOException {
		switch (req.getParameter("for").toString()) {
		case "search":
			search(req, res);
			break;
		case "create":
			create(req, res);
			break;
		case "edit":
			edit(req, res);
			break;
		}
	}
	
	private void search(HttpServletRequest req,  HttpServletResponse res) {
		System.out.println("search organization");
		
	}

	// get create page
	private void create(HttpServletRequest req,  HttpServletResponse res) throws ServletException, IOException {
		Organizations org = new Organizations();
		org.setName("New Orgnaization");
		req.setAttribute("Organization", org);
        req.getRequestDispatcher("/OrganizationEdit.jsp").forward(req, res);
	}
	
	// get edit page
	private void edit(HttpServletRequest req,  HttpServletResponse res) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("OrganizationId"));
		Organizations organization = oDao.getOrganizationById(id);
		req.setAttribute("Organization", oDao.getOrganizationById(id));
		
        req.getRequestDispatcher("/OrganizationEdit.jsp").forward(req, res);
	}
	
	// for create
	public void doPut(HttpServletRequest req,  HttpServletResponse res) {
		Organizations org = new Organizations();
		String idstr = req.getParameter("organizationid");
		
		
		org.setOrganizationId(Long.parseLong(idstr));
		org.setName(req.getParameter("Name"));
		org.setAddress(req.getParameter("Address"));
		org.setCity(req.getParameter("City"));
		org.setState(req.getParameter("State"));
		org.setZipcode(req.getParameter("ZipCode"));
		org.setPhone(req.getParameter("Phone"));
		org = oDao.create(org);
	}
	
	// for update
	public void doPost(HttpServletRequest req,  HttpServletResponse res) throws ServletException, IOException {
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
	public void doDelete(HttpServletRequest req,  HttpServletResponse res) {
		String idstr = req.getParameter("organizationid");
		Organizations org = new Organizations();
		org.setOrganizationId(Long.parseLong(idstr));
		org = oDao.deleteOrganization(org);
	}
}
