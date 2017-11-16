package patnet.servlet;

import java.io.IOException;
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
import patnet.model.OrganizationReviews;
import patnet.model.Organizations;
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
	
	private void create(HttpServletRequest req,  HttpServletResponse res) throws ServletException, IOException {
		Organizations org = new Organizations();
		org.setName("New Orgnaization");
		req.setAttribute("Organization", org.toMap());
        req.getRequestDispatcher("/OrganizationEdit.jsp").forward(req, res);
	}
	
	private void edit(HttpServletRequest req,  HttpServletResponse res) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("OrganizationId"));
		req.setAttribute("Organization", oDao.getOrganizationById(id).toMap());
        req.getRequestDispatcher("/OrganizationEdit.jsp").forward(req, res);
	}
	
	// for update
	public void doPut(HttpServletRequest req,  HttpServletResponse res) {
		Organizations org = new Organizations();
		String idstr = req.getParameter("organizationid");
		org.setOrganizationid(idstr == null  ? null:Long.parseLong(idstr));
		org.setName(req.getParameter("Name"));
		org.setAddress(req.getParameter("Address"));
		org.setCity(req.getParameter("City"));
		org.setState(req.getParameter("State"));
		org.setZipcode(req.getParameter("ZipCode"));
		org.setPhone(req.getParameter("Phone"));
		org = oDao.create(org);
	}
	
	// for insert
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
		org.setOrganizationid(Long.parseLong(req.getParameter("OrganizationId")));
		oDao.updateOrganizations(org);
		
        res.sendRedirect(String.format("/patnet/OrganizationProfile?OrganizationId=%d", org.getOrganizationid()));	
	}
	
	// for delete
	public void doDelete(HttpServletRequest req,  HttpServletResponse res) {
		String idstr = req.getParameter("organizationid");
		Organizations org = new Organizations();
		org.setOrganizationid(Long.parseLong(idstr));
		org = oDao.deleteOrganization(org);
	}
}
