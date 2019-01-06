package com.nelson.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nelson.bdd.Noms;
import com.nelson.beans.Utilisateur;
import com.nelson.dao.DaoException;
import com.nelson.dao.DaoFactory;
import com.nelson.dao.UtilisateurDao;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UtilisateurDao utilisateurDao;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
    }
    
    public void init() {
    	DaoFactory daofactory = DaoFactory.getInstance();
    	this.utilisateurDao = daofactory.getUtilisateurDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
		 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			 try {
		            request.setAttribute("utilisateurs", utilisateurDao.lister());
		        }
		        catch (DaoException e) {
		            request.setAttribute("erreur", e.getMessage());
		        }
			 this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
		    }

		    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		    	 try {
		             Utilisateur utilisateur = new Utilisateur();
		             utilisateur.setNom(request.getParameter("nom"));
		             utilisateur.setPrenom(request.getParameter("prenom"));
		             
		             utilisateurDao.ajouter(utilisateur);
		             request.setAttribute("utilisateurs", utilisateurDao.lister());
		         }
		         catch (Exception e) {
		             request.setAttribute("erreur", e.getMessage());
		         }
		        this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
		    }
		    
		    

		}