package cititradeweb.dataobjects;

public class OrderObject {
	
	String stock, action;
	double price;
	int shares;
	
	public OrderObject(){}
	
	public OrderObject(String stk, String act, double prc, int shrs){
		stock = stk;
		action = act;
		price = prc;
		shares = shrs;		
	}

	public String getStock() {
		return stock;
	}

	public String getAction() {
		return action;
	}

	public double getPrice() {
		return price;
	}

	public int getShares() {
		return shares;
	}
}
