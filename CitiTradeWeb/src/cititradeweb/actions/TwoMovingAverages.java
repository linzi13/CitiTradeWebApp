package cititradeweb.actions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import cititradeweb.dal.DataAccess;

public class TwoMovingAverages {

	public static void getMovingAverages() throws Exception{
		// TODO Auto-generated method stub
		Scanner kb = new Scanner(System.in);
		String sym = "";
		int movAvgShort = 0, movAvgLong = 0, capture = 0;
		double bidTotal = 0, askTotal = 0, bidAvgLong = 0,  bidAvgShort = 0, askAvgLong = 0, askAvgShort = 0, bidTempShort = 0, bidTempLong = 0, askTempShort = 0, askTempLong = 0;
		int ind = 0;

		System.out.print("\nPlease enter the stock symbol you wish you query: ");
		sym = kb.next().toUpperCase();
		System.out.print("\nPlease enter the short moving average number of trades: ");
		movAvgShort = kb.nextInt();
		System.out.print("\nPlease enter the long moving average number of trades: ");
		movAvgLong = kb.nextInt();
		System.out.print("\nPlease enter capture number of trades: ");
		capture = kb.nextInt();		

		double[] bidMoving = new double[capture];
		double[] askMoving = new double[capture];

		System.out.println("\nSymbol, Ask Price, Bid Price");
		//DataAccess.initTable();

		for(int index = 0; index < capture; index++){
			StringBuilder url = new StringBuilder("http://finance.yahoo.com/d/quotes.csv?s=");

			url.append(sym);
			url.append("&f=sabc1cop&e=.csv");

			String theUrl = url.toString();
			URL obj = new URL(theUrl);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;

			while((inputLine = in.readLine()) != null)	//(essentially while(true))
			{
				//System.out.println(inputLine);
				String[] fields = inputLine.split(",");

				for(int i = 0; i < fields.length; i++){
					System.out.print(fields[i].replaceAll("\"", "") + " | ");
				}

				bidMoving[ind] = Double.parseDouble(fields[1]);	     
				askMoving[ind] = Double.parseDouble(fields[2]);	  
				bidTempShort = 0;
				bidTempLong = 0;
				askTempShort = 0;
				askTempLong = 0;

				for(int loop = ind; loop > ind - movAvgShort && loop >= 0; loop--){
					bidTempShort += bidMoving[loop];
					askTempShort += askMoving[loop];
				} 
				for(int loop = ind; loop > ind - movAvgLong && loop >= 0; loop--){
					bidTempLong += bidMoving[loop];
					askTempLong += askMoving[loop];
				}
				
				ind++;

				if(ind >= movAvgShort){
					bidAvgShort = bidTempShort/movAvgShort;
					askAvgShort = askTempShort/movAvgShort;
				}else{
					bidAvgShort = 0;
					askAvgShort = 0;
				}
				
				if(ind >= movAvgLong){
					bidAvgLong = bidTempLong/movAvgLong;
					askAvgLong = askTempLong/movAvgLong;
				}else{
					bidAvgLong = 0;
					askAvgLong = 0;
				}
				
				DataAccess.addStockQuote(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6], bidAvgShort, bidAvgLong, askAvgShort, askAvgLong);
				//askavg, bidavg, capture, movavg
				//symbol, askprice, bidprice, change, changepercent, open, close
				bidTotal += Double.parseDouble(fields[1]);
				askTotal += Double.parseDouble(fields[2]);
				System.out.println();
				System.out.println("-----------------------------------");
			}			
		}
		System.out.printf("Bid Price Average: %.2f", bidTotal/capture);	       
		System.out.printf("\nAsk Price Average: %.2f", askTotal/capture);
		System.out.printf("\nBid Price Moving Average SHORT (Last %s)/LONG (Last %s): %.2f/%.2f", movAvgShort, movAvgLong, bidAvgShort, bidAvgLong);
		System.out.printf("\nAsk Price Moving Average SHORT (Last %s)/LONG (Last %s): %.2f/%.2f", movAvgShort, movAvgLong, askAvgShort, askAvgLong);
		kb.close();
	}
	
}