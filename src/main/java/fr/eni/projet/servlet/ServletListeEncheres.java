package fr.eni.projet.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projet.BusinessException;
import fr.eni.projet.bll.DAOFactory;
import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.dal.ArticleVenduDAO;
import fr.eni.projet.dal.ArticleVenduDAOJdbcImpl;

/**
 * Servlet implementation class ServletListeEncheres
 */
@WebServlet("/ServletListeEncheres")
public class ServletListeEncheres extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleVenduDAO articleVenduDAO;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	LE TEST DOIT ETRE FAIT SUR L'UTILISATEUR POUR SAVOIR SI CONNECTION OU PAS
/*		if(utilisateur.connecterUtilisateur()!=null) {
			HttpSession session = request.getSession(true); 
			session.invalidate();
		}*/
		
		ArticleVenduDAO articleVenduDAO = DAOFactory.getArticleVenduDAO();
		
		try {
			List<ArticleVendu> liste = new ArrayList<ArticleVendu>();
			liste = (List<ArticleVendu>) articleVenduDAO.selectAllCurentAuctions();
			System.out.println(liste);
			request.setAttribute("maListe", liste);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/ListeEncheres.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
