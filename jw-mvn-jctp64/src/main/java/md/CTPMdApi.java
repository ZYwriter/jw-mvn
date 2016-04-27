package md;

import org.bridj.BridJ;
import org.bridj.Pointer;

import ctputil.CTPLibraryUtil;
import mdapi.CThostFtdcMdApi;
import mdapi.CThostFtdcMdSpi;
import thostftdcuserapistruct.CThostFtdcReqUserLoginField;
import thostftdcuserapistruct.CThostFtdcUserLogoutField;

/**
 * ���ߣ�wengqf
 * ��Ŀ��sims2016derive-quotationtranscode-ctp64
 * ˵������װ��
 * ���ڣ�2016��3��2��
 * ��ע��
 */
public class CTPMdApi {
    static {
        CTPLibraryUtil.initLibrary();
        BridJ.register(CThostFtdcMdApi.class);
    }

    //CTP md API
    CThostFtdcMdApi mdApi;//������ϵķ�ʽ

    public CTPMdApi(CThostFtdcMdApi mdApi) {
        this.mdApi = mdApi;
    }

    public static CTPMdApi createFtdcMdApi() {
        return createFtdcMdApi("", false);
    }

    public static CTPMdApi createFtdcMdApi(String pszFlowPath, boolean bIsUsingUdp) {
        Pointer<CThostFtdcMdApi> mdApiPtr = CThostFtdcMdApi.CreateFtdcMdApi(Pointer.pointerToCString(pszFlowPath), false, false);
        CThostFtdcMdApi cThostFtdcMdApi = mdApiPtr.get();
        return new CTPMdApi(cThostFtdcMdApi);
    }

    public void release() {
        mdApi.Release();
        CTPLibraryUtil.release();
    }

    public void init() {
        mdApi.Init();
    }

    public int join() {
        return mdApi.Join();
    }

    public String getTradingDay() {
        Pointer<Byte> tradingDay = mdApi.GetTradingDay();
        return tradingDay.getCString();
    }

    public void registerFront(String pszFrontAddress) {
        mdApi.RegisterFront(Pointer.pointerToCString(pszFrontAddress));
    }

    public void registerNameServer(String pszNsAddress) {
        mdApi.RegisterNameServer(Pointer.pointerToCString(pszNsAddress));
    }

    public void registerSpi(CTPMdSpi pSpi) {
        CThostFtdcMdSpi mdSpi = new CTPMdSpiAdapter(pSpi);
        mdApi.RegisterSpi(Pointer.pointerTo(mdSpi));
    }

    /**
     * ��¼�ɹ��󣬲ſ��Խ�������Ķ��ġ�
     * �ͻ���ʹ�ú��� SubscribeMarketData �������鶩�ġ���һ��������һ����������Ҫ���ĵĺ�Լ�����飬 �ڶ��������Ǹ�����ĳ��ȡ�
     * @param ppInstrumentID
     * @return
     */
    public int subscribeMarketData(String... ppInstrumentID) {
        return mdApi.SubscribeMarketData(Pointer.pointerToCStrings(ppInstrumentID), ppInstrumentID.length);
    }

    public int unSubscribeMarketData(String... ppInstrumentID) {
        return mdApi.UnSubscribeMarketData(Pointer.pointerToCStrings(ppInstrumentID), ppInstrumentID.length);
    }

    public int reqUserLogin(CThostFtdcReqUserLoginField pReqUserLoginField, int nRequestID) {
        return mdApi.ReqUserLogin(Pointer.pointerTo(pReqUserLoginField), nRequestID);
    }

    public int reqUserLogout(CThostFtdcUserLogoutField pUserLogout, int nRequestID) {
        return mdApi.ReqUserLogout(Pointer.pointerTo(pUserLogout), nRequestID);
    }
}
