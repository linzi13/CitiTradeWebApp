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
   <style type = "text/css">
   .myTable {
	margin:0px;padding:0px;
	width:100%;
	box-shadow: 10px 10px 5px #888888;
	border: 0px;
	font-size: 10px;
	
	-moz-border-radius-bottomleft:25px;
	-webkit-border-bottom-left-radius:25px;
	border-bottom-left-radius:25px;
	
	-moz-border-radius-bottomright:25px;
	-webkit-border-bottom-right-radius:25px;
	border-bottom-right-radius:25px;
	
	-moz-border-radius-topright:25px;
	-webkit-border-top-right-radius:25px;
	border-top-right-radius:25px;
	
	-moz-border-radius-topleft:25px;
	-webkit-border-top-left-radius:25px;
	border-top-left-radius:25px;
}.myTable table{
    border-collapse: collapse;
        border-spacing: 0;
	width:100%;
	height:100%;
	margin:0px;padding:0px;
}

</style>
  </head>

  <body onload= "getQuotes()">
  <h1>CitiTrade</h1>
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
            <li class="active"><a href="home.jsp">Live Market Data</a></li>
            <li><a href="IndividualQuote.jsp">Individual Quotes</a></li>
            <li><a href="Strategies.jsp">Strategies</a></li>
            
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
            <h3>Live Market Data</h3>
            <p id="tableField"></p>

			
				
          </div>
          <div class="row">
            
          </div><!--/row-->
        </div><!--/.col-xs-12.col-sm-9-->

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
           <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
          
                  <form class="form-search" method="get" id="s" action="/">
   				  <div class="input-append">
   				  <h3>Search Symbol:</h3>
   				   </div>    	
   				  </form>
   				   <form action = "IndividualQuote.jsp" method= "get">
					<input type="text" id= "myInputField" list= "Symbols" onkeyup="myOnKeyUp()" name="str" placeholder="Enter Symbol" value=""/>
				
					<button type="submit" class="btn btn-default">Submit</button>
					</form>
        		     
   				 
   			
   				  
          <div class="list-group">
            <a href="#mytrades" class="list-group-item">My Trades</a>
            <a href="#livestockfeed" class="list-group-item">Market URL</a>
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