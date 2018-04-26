package cititradeweb.actions;

import java.util.ArrayList;
import java.util.List;

import cititradeweb.dal.DataAccess;

public class CreateTables {

	public static void main(String[] args) throws Exception{
		
		List<String> symbols = new ArrayList<String>();
		symbols = GetStockSymbolsFromCSV.getSymbols();
		
		for(String s: symbols){
			//DataAccess.dropTablesFromStockSymbols(s);
			DataAccess.createTablesFromStockSymbols(s);
		}
		GetQuotes.getQuotes();
	}
}