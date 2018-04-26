<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*, java.io.PrintWriter, cititradeweb.dal.*, cititradeweb.dataobjects.*, java.sql.SQLException"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CitiTrade Portal</title>
<script type="text/javascript">
	var request = myCreateXMLHttpRequest();

	function myCreateXMLHttpRequest() {
		try {
			return new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
		}
		try {
			return new ActiveXObject("Microsoft.XMLHTTP");
		} catch (e) {
		}
		try {
			return new XMLHttpRequest();
		} catch (e) {
		}
		return null;
	}

	function myOnKeyUp() {

		if (request != null) {
			var textField = document.getElementById("myInputField");
			var url = "rest/symbols?str=" + textField.value;
			request.open("GET", url, true);
			request.onreadystatechange = myHandleCallback;
			request.send(null);
		}
	}

	function myHandleCallback() {

		if (request.readyState == 4 && request.status == 200) {
			var outputField = document.getElementById("myInputField");
			outputField.innerHTML = request.responseText;
		}
	}

	function myHandleCallback2() {

		if (request.readyState == 4 && request.status == 200) {
			var outputField = document.getElementById("tableField");
			outputField.innerHTML = request.responseText;
		}
	}

	function getQuotes(){
		setInterval(function(){
			if (request != null) {
			var url = "rest/quotes"
			request.open("GET", url, true);
			request.onreadystatechange = myHandleCallback2;
			request.send(null);
		}
			}, 1000)		
	}
</script>
</head>
<body onload="getQuotes()">
	<h1>CitiTrade</h1>
	Search:
	<input type="text" id="myInputField" list="Symbols" onkeyup="myOnKeyUp()" />
	<p id="tableField"></p>
	<%-- <%		
			out.println("<h2>Top Quote List: </h2>");
			long timeNow = System.currentTimeMillis();
			long timeElapsed;

			for(int i=1; i<= 5;i++){
				timeElapsed = System.currentTimeMillis() - timeNow;
				if (true) {
					timeNow = System.currentTimeMillis();
					out.println("<table style= 'border: 1px'>");
					out.println("<tr><th style= 'border: 1px'>ID</th>");
					out.println("<th style= 'border: 1px'>Symbol</th>");
					out.println("<th style= 'border: 1px'>Bid Price</th>");
					out.println("<th style= 'border: 1px'>Ask Price </th></tr>");
					try {
						List<StockObject> stocks = DataAccess.returnMostRecentQuote();
						for (StockObject so : stocks) {
							out.println("<tr><td style= 'border: 1px solid black'>"	+ so.getId() + "</td>");
							//out.println("<td style= 'border: 1px solid black'><a href= 'EditShipper.jsp?id=" + so.getId() +"'>" + so.getShipName() + "</a></td>");
							out.println("<td style= 'border: 1px solid black'>"	+ so.getStockSymbol() + "</td>");
							out.println("<td style= 'border: 1px solid black'>"	+ so.getBidPrice() + "</td>");
							out.println("<td style= 'border: 1px solid black'>"	+ so.getAskPrice() + "</td></tr>");
							//out.println("<td style='border: 1px solid black'><a href= 'DeleteShipper.jsp?id=" + p.getId() + "'>Delete</a></td></tr>");			
						}
						out.println("</table>");
					}
					catch (SQLException e) {
						out.print("error" + e);
					}
				}
			}
	%> --%>

</body>
</html>