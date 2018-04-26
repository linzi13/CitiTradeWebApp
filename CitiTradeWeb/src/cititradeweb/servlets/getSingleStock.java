package cititradeweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;

import cititradeweb.dal.DataAccess;
import cititradeweb.dataobjects.StockObject;
import cititradeweb.ordermanager.OrderManager;

/**
 * Servlet implementation class getSingleStock
 */
@WebServlet("/getSingleStock")
public class getSingleStock extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<h2>Single Stock List: </h2>");
			
			try {
				out.println ("<style> tr:nth-child(odd) {background-color: yellow; } ");
				out.println ("tr:nth-child(even) { background-color: orange; }</style> ");
			out.println ("<table style= 'border: 1px'>");
			out.println ("<th style= 'border: 1px'>Id</th>");
			out.println ("<th style= 'border: 1px'>Symbol</th>");
			out.println ("<th style= 'border: 1px'>Bid Price</th>");
			out.println ("<th style= 'border: 1px'>Ask Price </th></tr>");
			//hardcoded information needs to be altered to parameter from webpage 
			List <StockObject> stocks = DataAccess.getStockData("AAPL");
				for (StockObject so : stocks) {
					out.println("<tr><td style= 'border: 1px solid black'>" + so.getId() + "</td>");
					//out.println("<td style= 'border: 1px solid black'><a href= 'EditShipper.jsp?id=" + p.getId() +"'>" + p.getShipName() + "</a></td>");
					out.println("<td style= 'border: 1px solid black'>" + so.getStockSymbol() + "</td>");
					out.println("<td style= 'border: 1px solid black'>" + so.getBidPrice() + "</td>");
					out.println("<td style= 'border: 1px solid black'>" + so.getAskPrice() + "</td>");
					//out.println("<td style='border: 1px solid black'><a href= 'DeleteShipper.jsp?id=" + p.getId() + "'>Delete</a></td></tr>");
				
				}
			out.println("</table>");
			}
			catch (SQLException e){
				out.print("error" + e);
				Logger log = Logger.getLogger(getSingleStock.class.getClass());
				log.error("ERROR "+ e.getMessage());
			}
		}

	}
