package test.trade;

import trade.CTPTraderSpi;

public class TestTrader {
	
	public static void main(String[] args) {
		CTPTraderSpi traderSpi = new CTPTraderSpi();
		traderSpi.getInstrumentID(false);
	}
}
