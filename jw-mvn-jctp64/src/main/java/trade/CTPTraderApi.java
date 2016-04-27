package trade;

import org.bridj.BridJ;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;

import ctputil.CTPLibraryUtil;
import mdapi.ThostFtdcMdApiLibrary.THOST_TE_RESUME_TYPE;
import thostftdcuserapistruct.CThostFtdcInputOrderActionField;
import thostftdcuserapistruct.CThostFtdcInputOrderField;
import thostftdcuserapistruct.CThostFtdcParkedOrderActionField;
import thostftdcuserapistruct.CThostFtdcParkedOrderField;
import thostftdcuserapistruct.CThostFtdcQryAccountregisterField;
import thostftdcuserapistruct.CThostFtdcQryBrokerTradingAlgosField;
import thostftdcuserapistruct.CThostFtdcQryBrokerTradingParamsField;
import thostftdcuserapistruct.CThostFtdcQryCFMMCTradingAccountKeyField;
import thostftdcuserapistruct.CThostFtdcQryContractBankField;
import thostftdcuserapistruct.CThostFtdcQryDepthMarketDataField;
import thostftdcuserapistruct.CThostFtdcQryEWarrantOffsetField;
import thostftdcuserapistruct.CThostFtdcQryExchangeField;
import thostftdcuserapistruct.CThostFtdcQryInstrumentCommissionRateField;
import thostftdcuserapistruct.CThostFtdcQryInstrumentField;
import thostftdcuserapistruct.CThostFtdcQryInstrumentMarginRateField;
import thostftdcuserapistruct.CThostFtdcQryInvestorField;
import thostftdcuserapistruct.CThostFtdcQryInvestorPositionCombineDetailField;
import thostftdcuserapistruct.CThostFtdcQryInvestorPositionDetailField;
import thostftdcuserapistruct.CThostFtdcQryInvestorPositionField;
import thostftdcuserapistruct.CThostFtdcQryNoticeField;
import thostftdcuserapistruct.CThostFtdcQryOrderField;
import thostftdcuserapistruct.CThostFtdcQryParkedOrderActionField;
import thostftdcuserapistruct.CThostFtdcQryParkedOrderField;
import thostftdcuserapistruct.CThostFtdcQrySettlementInfoConfirmField;
import thostftdcuserapistruct.CThostFtdcQrySettlementInfoField;
import thostftdcuserapistruct.CThostFtdcQryTradeField;
import thostftdcuserapistruct.CThostFtdcQryTradingAccountField;
import thostftdcuserapistruct.CThostFtdcQryTradingCodeField;
import thostftdcuserapistruct.CThostFtdcQryTradingNoticeField;
import thostftdcuserapistruct.CThostFtdcQryTransferBankField;
import thostftdcuserapistruct.CThostFtdcQryTransferSerialField;
import thostftdcuserapistruct.CThostFtdcQueryMaxOrderVolumeField;
import thostftdcuserapistruct.CThostFtdcRemoveParkedOrderActionField;
import thostftdcuserapistruct.CThostFtdcRemoveParkedOrderField;
import thostftdcuserapistruct.CThostFtdcReqAuthenticateField;
import thostftdcuserapistruct.CThostFtdcReqQueryAccountField;
import thostftdcuserapistruct.CThostFtdcReqTransferField;
import thostftdcuserapistruct.CThostFtdcReqUserLoginField;
import thostftdcuserapistruct.CThostFtdcSettlementInfoConfirmField;
import thostftdcuserapistruct.CThostFtdcTradingAccountPasswordUpdateField;
import thostftdcuserapistruct.CThostFtdcUserLogoutField;
import thostftdcuserapistruct.CThostFtdcUserPasswordUpdateField;
import tradeapi.CThostFtdcTraderApi;
import tradeapi.CThostFtdcTraderSpi;

public class CTPTraderApi {
    static {
        CTPLibraryUtil.initLibrary();
        BridJ.register(CThostFtdcTraderApi.class);
    }
    
    CThostFtdcTraderApi traderApi;//用了组合的方式
    public CTPTraderApi(CThostFtdcTraderApi traderApi) {
        this.traderApi = traderApi;
    }
    public static CTPTraderApi createFtdcTraderApi() {
        return createFtdcTraderApi("");
    }

    public static CTPTraderApi createFtdcTraderApi(String pszFlowPath) {
        Pointer<CThostFtdcTraderApi> traderApiPtr = CThostFtdcTraderApi.CreateFtdcTraderApi(Pointer.pointerToCString(pszFlowPath));
        CThostFtdcTraderApi cThostFtdcTraderApi = traderApiPtr.get();
        return new CTPTraderApi(cThostFtdcTraderApi);
    }

    public void release() {
        try {
            traderApi.Release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() {
        traderApi.Init();
    }

    public int join() {
        return traderApi.Join();
    }

    public String getTradingDay() {
        Pointer<Byte> tradingDay = traderApi.GetTradingDay();
        return tradingDay.getCString();
    }

    public void registerFront(String pszFrontAddress) {
        traderApi.RegisterFront(Pointer.pointerToCString(pszFrontAddress));
    }

    public void registerNameServer(String pszNsAddress) {
        traderApi.RegisterNameServer(Pointer.pointerToCString(pszNsAddress));
    }

    public void registerSpi(CTPTraderSpi pSpi) {
        CThostFtdcTraderSpi spiAdapter = new CTPTraderSpiAdapter(pSpi);
        traderApi.RegisterSpi(Pointer.pointerTo(spiAdapter));
    }

    public void subscribePrivateTopic(IntValuedEnum<THOST_TE_RESUME_TYPE> nResumeType) {
        traderApi.SubscribePrivateTopic(nResumeType);
    }

    public void subscribePublicTopic(IntValuedEnum<THOST_TE_RESUME_TYPE> nResumeType) {
        traderApi.SubscribePublicTopic(nResumeType);
    }

    public int reqAuthenticate(CThostFtdcReqAuthenticateField pReqAuthenticateField, int nRequestID) {
        return traderApi.ReqAuthenticate(Pointer.pointerTo(pReqAuthenticateField), nRequestID);
    }

    public int reqUserLogin(CThostFtdcReqUserLoginField pReqUserLoginField, int nRequestID) {
        return traderApi.ReqUserLogin(Pointer.pointerTo(pReqUserLoginField), nRequestID);
    }

    public int reqUserLogout(CThostFtdcUserLogoutField pUserLogout, int nRequestID) {
        return traderApi.ReqUserLogout(Pointer.pointerTo(pUserLogout), nRequestID);
    }

    public int reqUserPasswordUpdate(CThostFtdcUserPasswordUpdateField pUserPasswordUpdate, int nRequestID) {
        return traderApi.ReqUserPasswordUpdate(Pointer.pointerTo(pUserPasswordUpdate), nRequestID);
    }

    public int reqTradingAccountPasswordUpdate(CThostFtdcTradingAccountPasswordUpdateField pTradingAccountPasswordUpdate, int nRequestID) {
        return traderApi.ReqTradingAccountPasswordUpdate(Pointer.pointerTo(pTradingAccountPasswordUpdate), nRequestID);
    }

    public int reqOrderInsert(CThostFtdcInputOrderField pInputOrder, int nRequestID) {
        return traderApi.ReqOrderInsert(Pointer.pointerTo(pInputOrder), nRequestID);
    }

    public int reqParkedOrderInsert(CThostFtdcParkedOrderField pParkedOrder, int nRequestID) {
        return traderApi.ReqParkedOrderInsert(Pointer.pointerTo(pParkedOrder), nRequestID);
    }

    public int reqParkedOrderAction(CThostFtdcParkedOrderActionField pParkedOrderAction, int nRequestID) {
        return traderApi.ReqParkedOrderAction(Pointer.pointerTo(pParkedOrderAction), nRequestID);
    }

    public int reqOrderAction(CThostFtdcInputOrderActionField pInputOrderAction, int nRequestID) {

        return traderApi.ReqOrderAction(Pointer.pointerTo(pInputOrderAction), nRequestID);
    }

    public int reqQueryMaxOrderVolume(CThostFtdcQueryMaxOrderVolumeField pQueryMaxOrderVolume, int nRequestID) {

        return traderApi.ReqQueryMaxOrderVolume(Pointer.pointerTo(pQueryMaxOrderVolume), nRequestID);
    }

    public int reqSettlementInfoConfirm(CThostFtdcSettlementInfoConfirmField pSettlementInfoConfirm, int nRequestID) {

        return traderApi.ReqSettlementInfoConfirm(Pointer.pointerTo(pSettlementInfoConfirm), nRequestID);
    }

    public int reqRemoveParkedOrder(CThostFtdcRemoveParkedOrderField pRemoveParkedOrder, int nRequestID) {

        return traderApi.ReqRemoveParkedOrder(Pointer.pointerTo(pRemoveParkedOrder), nRequestID);
    }

    public int reqRemoveParkedOrderAction(CThostFtdcRemoveParkedOrderActionField pRemoveParkedOrderAction, int nRequestID) {

        return traderApi.ReqRemoveParkedOrderAction(Pointer.pointerTo(pRemoveParkedOrderAction), nRequestID);
    }

    public int reqQryOrder(CThostFtdcQryOrderField pQryOrder, int nRequestID) {

        return traderApi.ReqQryOrder(Pointer.pointerTo(pQryOrder), nRequestID);
    }

    public int reqQryTrade(CThostFtdcQryTradeField pQryTrade, int nRequestID) {

        return traderApi.ReqQryTrade(Pointer.pointerTo(pQryTrade), nRequestID);
    }

    public int reqQryInvestorPosition(CThostFtdcQryInvestorPositionField pQryInvestorPosition, int nRequestID) {

        return traderApi.ReqQryInvestorPosition(Pointer.pointerTo(pQryInvestorPosition), nRequestID);
    }

    public int reqQryTradingAccount(CThostFtdcQryTradingAccountField pQryTradingAccount, int nRequestID) {

        return traderApi.ReqQryTradingAccount(Pointer.pointerTo(pQryTradingAccount), nRequestID);
    }

    public int reqQryInvestor(CThostFtdcQryInvestorField pQryInvestor, int nRequestID) {

        return traderApi.ReqQryInvestor(Pointer.pointerTo(pQryInvestor), nRequestID);
    }

    public int reqQryTradingCode(CThostFtdcQryTradingCodeField pQryTradingCode, int nRequestID) {

        return traderApi.ReqQryTradingCode(Pointer.pointerTo(pQryTradingCode), nRequestID);
    }

    public int reqQryInstrumentMarginRate(CThostFtdcQryInstrumentMarginRateField pQryInstrumentMarginRate, int nRequestID) {

        return traderApi.ReqQryInstrumentMarginRate(Pointer.pointerTo(pQryInstrumentMarginRate), nRequestID);
    }

    public int reqQryInstrumentCommissionRate(CThostFtdcQryInstrumentCommissionRateField pQryInstrumentCommissionRate, int nRequestID) {

        return traderApi.ReqQryInstrumentCommissionRate(Pointer.pointerTo(pQryInstrumentCommissionRate), nRequestID);
    }

    public int reqQryExchange(CThostFtdcQryExchangeField pQryExchange, int nRequestID) {

        return traderApi.ReqQryExchange(Pointer.pointerTo(pQryExchange), nRequestID);
    }

    public int reqQryInstrument(CThostFtdcQryInstrumentField pQryInstrument, int nRequestID) {

        return traderApi.ReqQryInstrument(Pointer.pointerTo(pQryInstrument), nRequestID);
    }

    public int reqQryDepthMarketData(CThostFtdcQryDepthMarketDataField pQryDepthMarketData, int nRequestID) {

        return traderApi.ReqQryDepthMarketData(Pointer.pointerTo(pQryDepthMarketData), nRequestID);
    }

    public int reqQrySettlementInfo(CThostFtdcQrySettlementInfoField pQrySettlementInfo, int nRequestID) {

        return traderApi.ReqQrySettlementInfo(Pointer.pointerTo(pQrySettlementInfo), nRequestID);
    }

    public int reqQryTransferBank(CThostFtdcQryTransferBankField pQryTransferBank, int nRequestID) {

        return traderApi.ReqQryTransferBank(Pointer.pointerTo(pQryTransferBank), nRequestID);
    }

    public int reqQryInvestorPositionDetail(CThostFtdcQryInvestorPositionDetailField pQryInvestorPositionDetail, int nRequestID) {

        return traderApi.ReqQryInvestorPositionDetail(Pointer.pointerTo(pQryInvestorPositionDetail), nRequestID);
    }

    public int reqQryNotice(CThostFtdcQryNoticeField pQryNotice, int nRequestID) {

        return traderApi.ReqQryNotice(Pointer.pointerTo(pQryNotice), nRequestID);
    }

    public int reqQrySettlementInfoConfirm(CThostFtdcQrySettlementInfoConfirmField pQrySettlementInfoConfirm, int nRequestID) {

        return traderApi.ReqQrySettlementInfoConfirm(Pointer.pointerTo(pQrySettlementInfoConfirm), nRequestID);
    }

    public int reqQryInvestorPositionCombineDetail(CThostFtdcQryInvestorPositionCombineDetailField pQryInvestorPositionCombineDetail, int nRequestID) {

        return traderApi.ReqQryInvestorPositionCombineDetail(Pointer.pointerTo(pQryInvestorPositionCombineDetail), nRequestID);
    }

    public int reqQryCFMMCTradingAccountKey(CThostFtdcQryCFMMCTradingAccountKeyField pQryCFMMCTradingAccountKey, int nRequestID) {

        return traderApi.ReqQryCFMMCTradingAccountKey(Pointer.pointerTo(pQryCFMMCTradingAccountKey), nRequestID);
    }

    public int reqQryEWarrantOffset(CThostFtdcQryEWarrantOffsetField pQryEWarrantOffset, int nRequestID) {

        return traderApi.ReqQryEWarrantOffset(Pointer.pointerTo(pQryEWarrantOffset), nRequestID);
    }

    public int reqQryTransferSerial(CThostFtdcQryTransferSerialField pQryTransferSerial, int nRequestID) {

        return traderApi.ReqQryTransferSerial(Pointer.pointerTo(pQryTransferSerial), nRequestID);
    }

    public int reqQryAccountregister(CThostFtdcQryAccountregisterField pQryAccountregister, int nRequestID) {

        return traderApi.ReqQryAccountregister(Pointer.pointerTo(pQryAccountregister), nRequestID);
    }

    public int reqQryContractBank(CThostFtdcQryContractBankField pQryContractBank, int nRequestID) {

        return traderApi.ReqQryContractBank(Pointer.pointerTo(pQryContractBank), nRequestID);
    }

    public int reqQryParkedOrder(CThostFtdcQryParkedOrderField pQryParkedOrder, int nRequestID) {

        return traderApi.ReqQryParkedOrder(Pointer.pointerTo(pQryParkedOrder), nRequestID);
    }

    public int reqQryParkedOrderAction(CThostFtdcQryParkedOrderActionField pQryParkedOrderAction, int nRequestID) {

        return traderApi.ReqQryParkedOrderAction(Pointer.pointerTo(pQryParkedOrderAction), nRequestID);
    }

    public int reqQryTradingNotice(CThostFtdcQryTradingNoticeField pQryTradingNotice, int nRequestID) {
        return traderApi.ReqQryTradingNotice(Pointer.pointerTo(pQryTradingNotice), nRequestID);
    }

    public int reqQryBrokerTradingParams(CThostFtdcQryBrokerTradingParamsField pQryBrokerTradingParams, int nRequestID) {
        return traderApi.ReqQryBrokerTradingParams(Pointer.pointerTo(pQryBrokerTradingParams), nRequestID);
    }

    public int reqQryBrokerTradingAlgos(CThostFtdcQryBrokerTradingAlgosField pQryBrokerTradingAlgos, int nRequestID) {
        return traderApi.ReqQryBrokerTradingAlgos(Pointer.pointerTo(pQryBrokerTradingAlgos), nRequestID);
    }

    public int reqFromBankToFutureByFuture(CThostFtdcReqTransferField pReqTransfer, int nRequestID) {
        return traderApi.ReqFromBankToFutureByFuture(Pointer.pointerTo(pReqTransfer), nRequestID);
    }

    public int reqFromFutureToBankByFuture(CThostFtdcReqTransferField pReqTransfer, int nRequestID) {
        return traderApi.ReqFromFutureToBankByFuture(Pointer.pointerTo(pReqTransfer), nRequestID);
    }

    public int reqQueryBankAccountMoneyByFuture(CThostFtdcReqQueryAccountField pReqQueryAccount, int nRequestID) {
        return traderApi.ReqQueryBankAccountMoneyByFuture(Pointer.pointerTo(pReqQueryAccount), nRequestID);
    }
    
}
