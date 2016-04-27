package test.trade;

import org.junit.Test;
import trade.CTPTraderSpi;

public class TestTrader {
    @Test
    public void spiTest() throws InterruptedException {
        CTPTraderSpi traderSpi = new CTPTraderSpi();
        traderSpi.getInstrumentID(false);
    }
}
