package md;

import org.bridj.BridJ;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Virtual;

import mdapi.CThostFtdcMdSpi;
import thostftdcuserapistruct.CThostFtdcDepthMarketDataField;
import thostftdcuserapistruct.CThostFtdcRspInfoField;
import thostftdcuserapistruct.CThostFtdcRspUserLoginField;
import thostftdcuserapistruct.CThostFtdcSpecificInstrumentField;
import thostftdcuserapistruct.CThostFtdcUserLogoutField;

/**
 * 作者：wengqf
 * 项目：sims2016derive-quotationtranscode-ctp64
 * 说明：适配器
 * 日期：2016年3月2日
 * 备注：
 */
public class CTPMdSpiAdapter extends CThostFtdcMdSpi {

    CTPMdSpi mdSpi;

    public CTPMdSpiAdapter(CTPMdSpi mdSpi) {
        BridJ.protectFromGC(this);
        this.mdSpi = mdSpi;
    }

    @Override
    @Virtual(0)
    public void OnFrontConnected() {
        mdSpi.onFrontConnected();
    }

    @Override
    @Virtual(1)
    public void OnFrontDisconnected(int nReason) {
        mdSpi.onFrontDisconnected(nReason);
    }

    @Override
    @Virtual(2)
    public void OnHeartBeatWarning(int nTimeLapse) {
        mdSpi.onHeartBeatWarning(nTimeLapse);
    }

    @Override
    @Virtual(3)
    public void OnRspUserLogin(Pointer<CThostFtdcRspUserLoginField> pRspUserLogin, Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID, boolean bIsLast) {
        mdSpi.onRspUserLogin(getStructObject(pRspUserLogin), getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(4)
    public void OnRspUserLogout(Pointer<CThostFtdcUserLogoutField> pUserLogout, Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID, boolean bIsLast) {
        mdSpi.onRspUserLogout(getStructObject(pUserLogout), getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(5)
    public void OnRspError(Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID, boolean bIsLast) {
        mdSpi.onRspError(getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(6)
    public void OnRspSubMarketData(Pointer<CThostFtdcSpecificInstrumentField> pSpecificInstrument, Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        mdSpi.onRspSubMarketData(getStructObject(pSpecificInstrument), getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(7)
    public void OnRspUnSubMarketData(Pointer<CThostFtdcSpecificInstrumentField> pSpecificInstrument, Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        mdSpi.onRspUnSubMarketData(getStructObject(pSpecificInstrument), getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(8)
    public void OnRtnDepthMarketData(Pointer<CThostFtdcDepthMarketDataField> pDepthMarketData) {
        mdSpi.onRtnDepthMarketData(getStructObject(pDepthMarketData));
    }

    private <T extends StructObject> T getStructObject(Pointer<T> field) {
        return field == null ? null : field.get();
    }

}
