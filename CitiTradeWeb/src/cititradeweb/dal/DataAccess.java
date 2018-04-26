package cititradeweb.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cititradeweb.actions.GetStockSymbolsFromCSV;
import cititradeweb.dataobjects.*;

public class DataAccess {
	
	public static String pass = "";

	public static Connection getConnection(){
		
		if(pass.equals("")){
			Scanner kb = new Scanner(System.in);
			System.out.println("Enter password for cititrade database: ");
			pass = kb.next();
		}

		Connection cn = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost/cititrade", "root", pass);
		}
		catch(SQLException ex){
			System.out.println("Database connection error: " + ex);
		}
		catch(ClassNotFoundException ex){
			System.out.println("Class not found: " + ex);
		}
		return cn;
	}
	
public static void addOrder(OrderObject order) throws SQLException{
		
		Connection cn = null;

		try{
			cn = getConnection();
			PreparedStatement pst = cn.prepareStatement("INSERT INTO orders (symbol, action, price, shares) VALUES (?, ?, ?, ?)");
			pst.setString(1, order.getStock());
			pst.setString(2, order.getAction());
			pst.setDouble(3, order.getPrice());
			pst.setInt(4, order.getShares());
			int rows = pst.executeUpdate();
			
			if(rows == 1){
				System.out.println("Order for " + order.getStock() + " added!");
			}			
		}
		catch(SQLException ex){
			System.out.println("Error getting data: " + ex);
		}
		finally{
			if(cn != null){
				cn.close();
			}
		}		
	}
	
	public static void addFavouriteStock(String stockSymbol) throws SQLException{
		
		Connection cn = null;

		try{
			cn = getConnection();
			PreparedStatement pst = cn.prepareStatement("INSERT INTO favourites (symbol) VALUES (?)");
			pst.setString(1, stockSymbol);
			int rows = pst.executeUpdate();
			
			if(rows == 1){
				System.out.println("Favourite stock " + stockSymbol + " added!");
			}			
		}
		catch(SQLException ex){
			System.out.println("Error getting data: " + ex);
		}
		finally{
			if(cn != null){
				cn.close();
			}
		}		
	}
	
public static void removeFavouriteStock(String stockSymbol) throws SQLException{
		
		Connection cn = null;

		try{
			cn = getConnection();
			PreparedStatement pst = cn.prepareStatement("REMOVE FROM favourites WHERE symbol LIKE ?");
			pst.setString(1, stockSymbol);
			int rows = pst.executeUpdate();
			
			if(rows == 1){
				System.out.println("Favourite stock " + stockSymbol + " removed!");
			}			
		}
		catch(SQLException ex){
			System.out.println("Error getting data: " + ex);
		}
		finally{
			if(cn != null){
				cn.close();
			}
		}		
	}

	public static void addStockQuote(String symbol, String askPrice, String bidPrice, String change, String changePercent, String open, String close, double bidAvgShort, double bidAvgLong, double askAvgShort, double askAvgLong) throws SQLException{

		Connection cn = null;

		try{
			cn = getConnection();
			if(!symbol.contains("^") && !symbol.contains(".") && !symbol.contains("'") && !symbol.contains("''")){
				/*PreparedStatement pst = cn.prepareStatement("INSERT INTO quotes(symbol, askprice, bidprice, changed, changedpercent, open, close) VALUES (?, ?, ?, ?, ?, ?, ?)");
				pst.setString(1, symbol.replaceAll("\"", ""));
				if(!askPrice.equals("N/A") || !bidPrice.equals("N/A")){
					pst.setDouble(2, Double.parseDouble(askPrice));
					pst.setDouble(3, Double.parseDouble(bidPrice));
					pst.setDouble(4, Double.parseDouble(change));
					pst.setDouble(5, Double.parseDouble(changePercent));
					pst.setDouble(6, Double.parseDouble(open));
					pst.setDouble(7, Double.parseDouble(close));
				}else{
					System.out.println("No ask/bid price data for " + symbol);
				}					
				int rows = pst.executeUpdate();
				if(rows == 1){
					System.out.println(symbol + " added to quotes table.");
				}*/

				String query = "INSERT INTO " + symbol.toLowerCase().replaceAll("\"", "").replaceAll("'", "") + " (symbol, askprice, bidprice, changed, changedPercent, open, close, bidshortavg, bidlongavg, askshortavg, asklongavg) VALUES ('" + symbol.replaceAll("\"", "") + "', '" + Double.parseDouble(askPrice) + "', '" + Double.parseDouble(bidPrice) + "', '" + Double.parseDouble(change) + "', '" + changePercent + "', '" + Double.parseDouble(open) + "', '" + Double.parseDouble(close)  + "', '" + bidAvgShort  + "', '" + bidAvgLong  + "', '" + askAvgShort  + "', '" + askAvgLong + "')";
				//symbol, askprice, bidprice, change, changepercent, open, close
				System.out.println(query);
				Statement st = cn.createStatement();
				st.executeUpdate(query);
			}
		}
		catch(SQLException ex){
			System.out.println("Error getting data: " + ex);
		}
		finally{
			if(cn != null){
				cn.close();
			}
		}
	}
	
	public static void addStockQuote(String symbol, String askPrice, String bidPrice, String change, String changePercent, String open, String close) throws SQLException{

		Connection cn = null;

		try{
			cn = getConnection();
			if(!symbol.contains("^") && !symbol.contains(".") && !symbol.contains("'") && !symbol.contains("''")){
				/*PreparedStatement pst = cn.prepareStatement("INSERT INTO quotes(symbol, askprice, bidprice, changed, changedpercent, open, close) VALUES (?, ?, ?, ?, ?, ?, ?)");
				pst.setString(1, symbol.replaceAll("\"", ""));
				if(!askPrice.equals("N/A") || !bidPrice.equals("N/A")){
					pst.setDouble(2, Double.parseDouble(askPrice));
					pst.setDouble(3, Double.parseDouble(bidPrice));
					pst.setDouble(4, Double.parseDouble(change));
					pst.setDouble(5, Double.parseDouble(changePercent));
					pst.setDouble(6, Double.parseDouble(open));
					pst.setDouble(7, Double.parseDouble(close));
				}else{
					System.out.println("No ask/bid price data for " + symbol);
				}					
				int rows = pst.executeUpdate();
				if(rows == 1){
					System.out.println(symbol + " added to quotes table.");
				}*/

				String query = "INSERT INTO " + symbol.toLowerCase().replaceAll("\"", "").replaceAll("'", "") + " (symbol, askprice, bidprice, changed, changedPercent, open, close, bidshortavg, bidlongavg, askshortavg, asklongavg) VALUES ('" + symbol.replaceAll("\"", "") + "', '" + Double.parseDouble(askPrice) + "', '" + Double.parseDouble(bidPrice) + "', '" + Double.parseDouble(change) + "', '" + changePercent + "', '" + Double.parseDouble(open) + "', '" + Double.parseDouble(close)  + "', '" + 0  + "', '" + 0  + "', '" + 0  + "', '" + 0 + "')";
				//symbol, askprice, bidprice, change, changepercent, open, close
				System.out.println(query);
				Statement st = cn.createStatement();
				st.executeUpdate(query);
			}
		}
		catch(SQLException ex){
			System.out.println("Error getting data: " + ex);
		}
		finally{
			if(cn != null){
				cn.close();
			}
		}
	}

	public static void createTablesFromStockSymbols(String symbol) throws SQLException{

		Connection cn = null;

		try{
			cn = getConnection();
			if(!symbol.contains("^") && !symbol.contains(".") && !symbol.contains("'") && !symbol.contains("''")){	//change to statements
								
				PreparedStatement pst5 = cn.prepareStatement("USE cititrade;");
				pst5.executeUpdate();

				PreparedStatement pst = cn.prepareStatement("CREATE TABLE IF NOT EXISTS symbols (id int auto_increment PRIMARY KEY, symbol nvarchar(100));");
				int rows = pst.executeUpdate();
				if(rows == 1){
					System.out.println("Symbols table created!");
				}
				
				PreparedStatement pst2 = cn.prepareStatement("INSERT INTO symbols (symbol) VALUES(?);");
				pst2.setString(1, symbol);
				int rows2 = pst2.executeUpdate();
				if(rows2 == 1){
					System.out.println("Symbol added!");
				}
				
				PreparedStatement pst3 = cn.prepareStatement("CREATE TABLE IF NOT EXISTS favourites (id int auto_increment PRIMARY KEY, symbol nvarchar(100));");
				int rows3 = pst3.executeUpdate();
				if(rows3 == 1){
					System.out.println("Favourites table completed!");
				}
				
				PreparedStatement pst4 = cn.prepareStatement("CREATE TABLE IF NOT EXISTS orders (id int auto_increment PRIMARY KEY, ordertime TIMESTAMP DEFAULT CURRENT_TIMESTAMP, symbol nvarchar(100), action nvarchar(100), price DECIMAL(10,2) NOT NULL DEFAULT 0, shares int NOT NULL DEFAULT 0);");
				int rows4 = pst4.executeUpdate();
				if(rows4 == 1){
					System.out.println("Orders table completed!");
				}			
				
				String query = "CREATE TABLE IF NOT EXISTS " + symbol.toLowerCase() + " (id int auto_increment PRIMARY KEY, stocktime TIMESTAMP DEFAULT CURRENT_TIMESTAMP, symbol nvarchar(100) NOT NULL, askprice DECIMAL(10,2) NOT NULL, bidprice DECIMAL(10,2) NOT NULL, changed DECIMAL(10,2) NOT NULL DEFAULT 0, changedPercent nvarchar(100) NOT NULL DEFAULT 0, open DECIMAL(10,2) NOT NULL DEFAULT 0, close DECIMAL(10,2) NOT NULL DEFAULT 0, bidshortavg DECIMAL(10,2) NOT NULL DEFAULT 0, bidlongavg DECIMAL(10,2) NOT NULL DEFAULT 0, askshortavg DECIMAL(10,2) NOT NULL DEFAULT 0, asklongavg DECIMAL(10,2) NOT NULL DEFAULT 0);";
				System.out.println(query);
				//symbol, askprice, bidprice, change, changepercent, open, close, bidshort, bidlong, askshort, asklong

				Statement st = cn.createStatement();
				st.executeUpdate(query);
				//System.out.println(symbol + " table created!");				
			}			
		}
		catch(SQLException ex){
			System.out.println("Error getting data: " + ex);
		}
		finally{
			if(cn != null){
				cn.close();
			}
		}
	}
	
	public List<String> getSymbolsFromDataBase() throws SQLException{
		
		List<String> temp = new ArrayList <String>();
		Connection con = null;
		try {
			con = getConnection ();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT symbol FROM symbols");
			while (rs.next()) {
				temp.add(rs.getString(1));
			}//while
		} //try 
		catch (SQLException ex) {
			System.out.println("Error getting data " + ex);
		}
		finally {
			if (con != null) {
				con.close();
			}
		}
		return temp;		
	}	

	public static void dropTablesFromStockSymbols(String symbol) throws SQLException{

		Connection cn = null;

		try{
			cn = getConnection();
			if(!symbol.contains("^") && !symbol.contains(".") && !symbol.contains("'") && !symbol.contains("''")){
				
				String query = "DROP TABLE " + symbol;
				Statement st = cn.createStatement();
				st.executeUpdate(query);
			}
		}
		catch(SQLException ex){
			System.out.println("Error getting data: " + ex);
		}
		finally{
			if(cn != null){
				cn.close();
			}
		}
	}

	public static List<StockObject> getStockData (String stockSymbol) throws SQLException {

		List<StockObject> temp = new ArrayList <StockObject>();
		Connection con = null;
		try {
			con = getConnection ();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM " + stockSymbol + " ORDER BY stocktime DESC");
			while (rs.next()) {
				temp.add(new StockObject (rs.getInt("id"), rs.getDate("stocktime"),rs.getString("symbol"),
						rs.getDouble("askprice"),
						rs.getDouble("bidprice"),
						rs.getDouble("open"),
						rs.getDouble("close")));
			}//while
		} //try 
		catch (SQLException ex) {
			System.out.println("Error getting data " + ex);
		}

		finally {
			if (con != null) {
				con.close();
			}
		}
		return temp;
	}

	public static List<StockObject> returnMostRecentQuote() throws SQLException {

		List<String> listOfSymbols = GetStockSymbolsFromCSV.getSymbols();
		//System.out.println(listOfSymbols);
		List<StockObject> stocks = new ArrayList <>();
		Connection con = null;
		con = getConnection();
		try {
			for (String s : listOfSymbols) {	

				Statement st = con.createStatement();
				if(!s.equals("symbol")){
				ResultSet rs = st.executeQuery("SELECT * FROM " + s + " ORDER BY stocktime DESC LIMIT 1");	
				while (rs.next()) {
					stocks.add (new StockObject (rs.getInt("id"), rs.getDate("stocktime"),rs.getString("symbol"),
							rs.getDouble("askprice"),
							rs.getDouble("bidprice"),
							rs.getDouble("open"),
							rs.getDouble("close")));
				}//while
				}
			}//for
		}//try
		catch (SQLException ex) {
			System.out.println("Error getting data " + ex);
		}

		finally {

			if (con != null) {
				//System.out.println("No files");
				con.close();
			}//if
		}//finally
		return stocks;
	}
	

}