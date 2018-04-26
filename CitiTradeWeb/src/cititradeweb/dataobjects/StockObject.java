package cititradeweb.dataobjects;

import java.util.Date;

public class StockObject {

		private int id;
		private Date stockTime;
		private String stockSymbol;
		private double openPrice;
		private double closePrice;
		public StockObject(int id, Date date, String symbol,
				double askPrice, double bidPrice, double openPrice, double closePrice) {
			this.id = id;
			this.stockTime = date;
			this.stockSymbol = symbol;
			this.askPrice = askPrice;
			this.bidPrice = bidPrice;
			this.openPrice = openPrice;
			this.closePrice = closePrice;
		}
		
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public Date getStockTime() {
			return stockTime;
		}
		public void setStockTime(Date stockTime) {
			this.stockTime = stockTime;
		}
		public double getOpenPrice() {
			return openPrice;
		}
		public void setOpenPrice(double openPrice) {
			this.openPrice = openPrice;
		}
		public double getClosePrice() {
			return closePrice;
		}
		public void setClosePrice(double closePrice) {
			this.closePrice = closePrice;
		}
		private double bidPrice;
		private double askPrice;
		private double bidSize;
		private double askSize;
		private double askMovingAverage;
		private double bidMovingAverage;
		public String getStockSymbol() {
			return stockSymbol;
		}
		public void setStockSymbol(String stockSymbol) {
			this.stockSymbol = stockSymbol;
		}
		public double getBidPrice() {
			return bidPrice;
		}
		public void setBidPrice(double bidPrice) {
			this.bidPrice = bidPrice;
		}
		public double getAskPrice() {
			return askPrice;
		}
		public void setAskPrice(double askPrice) {
			this.askPrice = askPrice;
		}
		public double getBidSize() {
			return bidSize;
		}
		public void setBidSize(double bidSize) {
			this.bidSize = bidSize;
		}
		public double getAskSize() {
			return askSize;
		}
		public void setAskSize(double askSize) {
			this.askSize = askSize;
		}
		public double getAskMovingAverage() {
			return askMovingAverage;
		}
		public void setAskMovingAverage(double askMovingAverage) {
			this.askMovingAverage = askMovingAverage;
		}
		public double getBidMovingAverage() {
			return bidMovingAverage;
		}
		public void setBidMovingAverage(double bidMovingAverage) {
			this.bidMovingAverage = bidMovingAverage;
		}
		
	
}
