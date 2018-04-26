<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import= "java.sql.Connection, cititradeweb.dataobjects.*, java.sql.DriverManager, java.sql.Statement, java.sql.ResultSet, cititradeweb.dal.DataAccess, java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Live Market Data</title>
</head>
<body>
	
	<h1>Citi Trade</h1>
	<br></br>
	
	
	<input type="submit" value="My Trades"/>
	<br></br>
	
	<form action = "getSingleStock" method= "post">
	<input type="text" name="txtStock"/>
	<input type="submit" value="Search"/>
	</form>
	
	
	<br></br>
	<label>Active Strategies / Favourites</label>
	<br></br>
	
	<form action="getTopQuotesServlet" method="post">
	<table style='border: 3px solid red'>
			<tr><th style='border: 1px solid black'>Symbol</th>
			<th style='border: 1px solid black'>Bid Price</th>
			<th style='border: 1px solid black'>Ask Price</th>
			<th style='border: 1px solid black'>Bid Size</th>
			<th style='border: 1px solid black'>Ask Size</th></tr>
			
		<%
 		List<StockObject> stocks = DataAccess.returnMostRecentQuote();
		
 		for(StockObject s : stocks){
 			out.println("<tr><td style='border: 1px solid black'>"+ s.getStockSymbol() + "</td>");
 			out.println("<td style='border: 1px solid black'>"+ s.getBidPrice() + "</td>");
 			out.println("<td style='border: 1px solid black'>"+ s.getAskPrice() + "</td>");
 			out.println("<td style='border: 1px solid black'>"+ s.getBidSize() + "</td>");
 			out.println("<td style='border: 1px solid black'>"+ s.getAskSize()+ "</td></tr>");
 		}
		%>
		
	
	</table>
	<br></br>
	<label>All Market Data</label>
	<br></br>
	<table style='border: 3px solid red'>
			<tr><th style='border: 1px solid black'>Symbol</th>
			<th style='border: 1px solid black'>Bid Price</th>
			<th style='border: 1px solid black'>Ask Price</th>
			<th style='border: 1px solid black'>Bid Size</th>
			<th style='border: 1px solid black'>Ask Size</th></tr>
			
			<%
 		List<StockObject> stocks1 = DataAccess.returnMostRecentQuote();
		
 		for(StockObject s : stocks1){
 			out.println("<tr><td style='border: 1px solid black'>"+ s.getStockSymbol() + "</td>");
 			out.println("<td style='border: 1px solid black'>"+ s.getBidPrice() + "</td>");
 			out.println("<td style='border: 1px solid black'>"+ s.getAskPrice() + "</td>");
 			out.println("<td style='border: 1px solid black'>"+ s.getBidSize() + "</td>");
 			out.println("<td style='border: 1px solid black'>"+ s.getAskSize() + "</td></tr>");
 		}
		%>
	</table>
	</form>

</body>
</html>