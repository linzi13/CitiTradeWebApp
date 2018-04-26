<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

 pageEncoding="ISO-8859-1"
 import = "cititradeweb.actions.*, cititradeweb.dal.*, cititradeweb.dataobjects.StockObject, java.util.List"%>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico"
    >

    <title>Off Canvas Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/offcanvas.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>
    <nav class="navbar navbar-fixed-top navbar-inverse">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Citi Trade Group 4</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li><a href="home.jsp">Live Market Data</a></li>
            <li><a href="IndividualQuote.jsp">Individual Quotes</a></li>
            <li class="active"><a href="Strategies.jsp">Strategies</a></li>
            
          </ul>
        </div><!-- /.nav-collapse -->
      </div><!-- /.container -->
    </nav><!-- /.navbar -->

    <div class="container">

      <div class="row row-offcanvas row-offcanvas-right">

        <div class="col-xs-12 col-sm-9">
          <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
          </p>
          <div class="jumbotron">
            <h1>Live Feed: </h1>
            <table style='border: 3px solid black'>
			<tr><th style='border: 1px solid black'>Symbol</th>
			<th style='border: 1px solid black'>Bid Price</th>
			<th style='border: 1px solid black'>Ask Price</th>
			<th style='border: 1px solid black'>Bid Size</th>
			<th style='border: 1px solid black'>Ask Size</th></tr>
				 <%
					List<StockObject> stocks1 = DataAccess.returnMostRecentQuote();
						for(StockObject s : stocks1){
						out.println("<tr><td style='border: 1px solid black'>"+ s.getStockSymbol()+ "</td>");
						out.println("<td style='border: 1px solid black'>"+ s.getBidPrice() + "</td>");
						out.println("<td style='border: 1px solid black'>"+ s.getAskPrice() + "</td>");
						out.println("<td style='border: 1px solid black'>"+ s.getBidSize() + "</td>");
						out.println("<td style='border: 1px solid black'>"+ s.getAskSize() + "</td></tr>");
						}
						%>
			</table>
          </div>
        
        </div><!--/.col-xs-12.col-sm-9-->
		<div class="jumbotron">
            <h1>Current Stock Prices: </h1>
            <table style='border: 3px solid black'>
			<tr><th style='border: 1px solid black'>Symbol</th>
			<th style='border: 1px solid black'>Bid Price</th>
			<th style='border: 1px solid black'>Ask Price</th>

				 <%
				 List<StockObject> stocks2 = DataAccess.getStockData("PIH");
			
						for(StockObject s : stocks2){
						out.println("<tr><td style='border: 1px solid black'>"+ s.getStockSymbol()+ "</td>");
						out.println("<td style='border: 1px solid black'>"+ s.getBidPrice() + "</td>");
						out.println("<td style='border: 1px solid black'>"+ s.getAskPrice() + "</td>");

						}
						%>
			</table>
          </div>
  
        
          </div>
        </div><!--/.sidebar-offcanvas-->
      </div><!--/row-->

      <hr>

      <footer>
        <p>&copy; CitiTrade 2015</p>
      </footer>

    </div><!--/.container-->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="js/ie10-viewport-bug-workaround.js"></script>

    <script src="css/offcanvas.js"></script>
  </body>
</html>