package cititradeweb.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.*;

import cititradeweb.dal.DataAccess;
import cititradeweb.dataobjects.StockObject;

@Path("/onestock")
public class AllQuotesForOneStock {
	
	@GET
	@Produces("text/html")
	public String getText(@QueryParam("str") String str) throws SQLException{

		String temp = "";
		Connection cn = null;

		try{
			List<StockObject> quotes = DataAccess.getStockData(str);
			//System.out.println(quotes);

			//temp += "<input type=\"text\" list=\"Contacts\">";
			
			temp += "<table style= 'border: 1px'><tr><th style= 'border: 1px'>ID</th><th style= 'border: 1px'>Symbol</th><th style= 'border: 1px'>Bid Price</th><th style= 'border: 1px'>Ask Price </th></tr>";

			for(StockObject s: quotes){		
				temp += "<tr><td style= 'border: 1px solid black'>" + s.getStockSymbol() + "</td>" + "<td style= 'border: 1px solid black'>" + s.getAskPrice() + "</td>" + "<td style= 'border: 1px solid black'>" + s.getBidPrice() + "</td></tr>" ;
			}
			temp += "</table>";
		}
		catch(SQLException ex){
			System.out.println("Error getting data: " + ex);
		}
		finally{
			if(cn != null){
				cn.close();
			}
		}
		//System.out.println(temp);
		return temp;
	}
		
	public static Connection getConnection(){

		Connection cn = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost/cititrade", "root", DataAccess.pass);
		}
		catch(SQLException ex){
			System.out.println("Database connection error: " + ex);
		}
		catch(ClassNotFoundException ex){
			System.out.println("Class not found: " + ex);
		}
		return cn;
	}

}