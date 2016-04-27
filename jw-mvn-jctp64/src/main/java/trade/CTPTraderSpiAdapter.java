package trade;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Virtual;

import thostftdcuserapistruct.CThostFtdcAccountregisterField;
import thostftdcuserapistruct.CThostFtdcBrokerTradingAlgosField;
import thostftdcuserapistruct.CThostFtdcBrokerTradingParamsField;
import thostftdcuserapistruct.CThostFtdcCFMMCTradingAccountKeyField;
import thostftdcuserapistruct.CThostFtdcCancelAccountField;
import thostftdcuserapistruct.CThostFtdcChangeAccountField;
import thostftdcuserapistruct.CThostFtdcContractBankField;
import thostftdcuserapistruct.CThostFtdcDepthMarketDataField;
import thostftdcuserapistruct.CThostFtdcEWarrantOffsetField;
import thostftdcuserapistruct.CThostFtdcErrorConditionalOrderField;
import thostftdcuserapistruct.CThostFtdcExchangeField;
import thostftdcuserapistruct.CThostFtdcInputOrderActionField;
import thostftdcuserapistruct.CThostFtdcInputOrderField;
import thostftdcuserapistruct.CThostFtdcInstrumentCommissionRateField;
import thostftdcuserapistruct.CThostFtdcInstrumentField;
import thostftdcuserapistruct.CThostFtdcInstrumentMarginRateField;
import thostftdcuserapistruct.CThostFtdcInstrumentStatusField;
import thostftdcuserapistruct.CThostFtdcInvestorField;
import thostftdcuserapistruct.CThostFtdcInvestorPositionCombineDetailField;
import thostftdcuserapistruct.CThostFtdcInvestorPositionDetailField;
import thostftdcuserapistruct.CThostFtdcInvestorPositionField;
import thostftdcuserapistruct.CThostFtdcNoticeField;
import thostftdcuserapistruct.CThostFtdcNotifyQueryAccountField;
import thostftdcuserapistruct.CThostFtdcOpenAccountField;
import thostftdcuserapistruct.CThostFtdcOrderActionField;
import thostftdcuserapistruct.CThostFtdcOrderField;
import thostftdcuserapistruct.CThostFtdcParkedOrderActionField;
import thostftdcuserapistruct.CThostFtdcParkedOrderField;
import thostftdcuserapistruct.CThostFtdcQueryMaxOrderVolumeField;
import thostftdcuserapistruct.CThostFtdcRemoveParkedOrderActionField;
import thostftdcuserapistruct.CThostFtdcRemoveParkedOrderField;
import thostftdcuserapistruct.CThostFtdcReqQueryAccountField;
import thostftdcuserapistruct.CThostFtdcReqRepealField;
import thostftdcuserapistruct.CThostFtdcReqTransferField;
import thostftdcuserapistruct.CThostFtdcRspAuthenticateField;
import thostftdcuserapistruct.CThostFtdcRspInfoField;
import thostftdcuserapistruct.CThostFtdcRspRepealField;
import thostftdcuserapistruct.CThostFtdcRspTransferField;
import thostftdcuserapistruct.CThostFtdcRspUserLoginField;
import thostftdcuserapistruct.CThostFtdcSettlementInfoConfirmField;
import thostftdcuserapistruct.CThostFtdcSettlementInfoField;
import thostftdcuserapistruct.CThostFtdcTradeField;
import thostftdcuserapistruct.CThostFtdcTradingAccountField;
import thostftdcuserapistruct.CThostFtdcTradingAccountPasswordUpdateField;
import thostftdcuserapistruct.CThostFtdcTradingCodeField;
import thostftdcuserapistruct.CThostFtdcTradingNoticeField;
import thostftdcuserapistruct.CThostFtdcTradingNoticeInfoField;
import thostftdcuserapistruct.CThostFtdcTransferBankField;
import thostftdcuserapistruct.CThostFtdcTransferSerialField;
import thostftdcuserapistruct.CThostFtdcUserLogoutField;
import thostftdcuserapistruct.CThostFtdcUserPasswordUpdateField;
import tradeapi.CThostFtdcTraderSpi;

public class CTPTraderSpiAdapter extends CThostFtdcTraderSpi{
private CTPTraderSpi traderSpi;
    
    public CTPTraderSpiAdapter(CTPTraderSpi traderSpi) {this.traderSpi = traderSpi;
    }
    @Override
    @Virtual(0)
    public void OnFrontConnected() {
        traderSpi.onFrontConnected();
    }

    @Override
    @Virtual(1)
    public void OnFrontDisconnected(int nReason) {
        traderSpi.onFrontDisconnected(nReason);
    }

    @Override
    @Virtual(2)
    public void OnHeartBeatWarning(int nTimeLapse) {
        traderSpi.onHeartBeatWarning(nTimeLapse);
    }

    @Override
    @Virtual(3)
    public void OnRspAuthenticate(
            Pointer<CThostFtdcRspAuthenticateField> pRspAuthenticateField,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspAuthenticate(getStructObject(pRspAuthenticateField), getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(4)
    public void OnRspUserLogin(
            Pointer<CThostFtdcRspUserLoginField> pRspUserLogin,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspUserLogin(getStructObject(pRspUserLogin), getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(5)
    public void OnRspUserLogout(Pointer<CThostFtdcUserLogoutField> pUserLogout,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspUserLogout(getStructObject(pUserLogout), getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(6)
    public void OnRspUserPasswordUpdate(
            Pointer<CThostFtdcUserPasswordUpdateField> pUserPasswordUpdate,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspUserPasswordUpdate(getStructObject(pUserPasswordUpdate), getStructObject(pRspInfo), nRequestID,
                bIsLast);
    }

    @Override
    @Virtual(7)
    public void OnRspTradingAccountPasswordUpdate(
            Pointer<CThostFtdcTradingAccountPasswordUpdateField> pTradingAccountPasswordUpdate,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspTradingAccountPasswordUpdate(getStructObject(pTradingAccountPasswordUpdate),
                getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(8)
    public void OnRspOrderInsert(
            Pointer<CThostFtdcInputOrderField> pInputOrder,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspOrderInsert(getStructObject(pInputOrder), getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(9)
    public void OnRspParkedOrderInsert(
            Pointer<CThostFtdcParkedOrderField> pParkedOrder,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspParkedOrderInsert(getStructObject(pParkedOrder), getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(10)
    public void OnRspParkedOrderAction(
            Pointer<CThostFtdcParkedOrderActionField> pParkedOrderAction,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspParkedOrderAction(getStructObject(pParkedOrderAction), getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(11)
    public void OnRspOrderAction(
            Pointer<CThostFtdcInputOrderActionField> pInputOrderAction,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspOrderAction(getStructObject(pInputOrderAction), getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(12)
    public void OnRspQueryMaxOrderVolume(
            Pointer<CThostFtdcQueryMaxOrderVolumeField> pQueryMaxOrderVolume,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspQueryMaxOrderVolume(getStructObject(pQueryMaxOrderVolume), getStructObject(pRspInfo), nRequestID,
                bIsLast);
    }

    @Override
    @Virtual(13)
    public void OnRspSettlementInfoConfirm(
            Pointer<CThostFtdcSettlementInfoConfirmField> pSettlementInfoConfirm,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspSettlementInfoConfirm(getStructObject(pSettlementInfoConfirm), getStructObject(pRspInfo), nRequestID,
                bIsLast);
    }

    @Override
    @Virtual(14)
    public void OnRspRemoveParkedOrder(
            Pointer<CThostFtdcRemoveParkedOrderField> pRemoveParkedOrder,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspRemoveParkedOrder(getStructObject(pRemoveParkedOrder), getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(15)
    public void OnRspRemoveParkedOrderAction(
            Pointer<CThostFtdcRemoveParkedOrderActionField> pRemoveParkedOrderAction,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspRemoveParkedOrderAction(getStructObject(pRemoveParkedOrderAction), pRspInfo.get(),
                nRequestID, bIsLast);
    }

    @Override
    @Virtual(16)
    public void OnRspQryOrder(Pointer<CThostFtdcOrderField> pOrder,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspQryOrder(getStructObject(pOrder), getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(17)
    public void OnRspQryTrade(Pointer<CThostFtdcTradeField> pTrade,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspQryTrade(getStructObject(pTrade), getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(18)
    public void OnRspQryInvestorPosition(
            Pointer<CThostFtdcInvestorPositionField> pInvestorPosition,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspQryInvestorPosition(getStructObject(pInvestorPosition), getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(19)
    public void OnRspQryTradingAccount(
            Pointer<CThostFtdcTradingAccountField> pTradingAccount,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspQryTradingAccount(getStructObject(pTradingAccount), getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(20)
    public void OnRspQryInvestor(Pointer<CThostFtdcInvestorField> pInvestor,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspQryInvestor(getStructObject(pInvestor), getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(21)
    public void OnRspQryTradingCode(
            Pointer<CThostFtdcTradingCodeField> pTradingCode,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspQryTradingCode(getStructObject(pTradingCode), getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(22)
    public void OnRspQryInstrumentMarginRate(
            Pointer<CThostFtdcInstrumentMarginRateField> pInstrumentMarginRate,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspQryInstrumentMarginRate(getStructObject(pInstrumentMarginRate), getStructObject(pRspInfo), nRequestID,
                bIsLast);
    }

    @Override
    @Virtual(23)
    public void OnRspQryInstrumentCommissionRate(
            Pointer<CThostFtdcInstrumentCommissionRateField> pInstrumentCommissionRate,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspQryInstrumentCommissionRate(getStructObject(pInstrumentCommissionRate), getStructObject(pRspInfo),
                nRequestID, bIsLast);
    }

    @Override
    @Virtual(24)
    public void OnRspQryExchange(Pointer<CThostFtdcExchangeField> pExchange,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspQryExchange(getStructObject(pExchange), getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(25)
    public void OnRspQryInstrument(
            Pointer<CThostFtdcInstrumentField> pInstrument,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspQryInstrument(getStructObject(pInstrument), getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(26)
    public void OnRspQryDepthMarketData(
            Pointer<CThostFtdcDepthMarketDataField> pDepthMarketData,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspQryDepthMarketData(getStructObject(pDepthMarketData), getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(27)
    public void OnRspQrySettlementInfo(
            Pointer<CThostFtdcSettlementInfoField> pSettlementInfo,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspQrySettlementInfo(getStructObject(pSettlementInfo), getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(28)
    public void OnRspQryTransferBank(
            Pointer<CThostFtdcTransferBankField> pTransferBank,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspQryTransferBank(getStructObject(pTransferBank), getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(29)
    public void OnRspQryInvestorPositionDetail(
            Pointer<CThostFtdcInvestorPositionDetailField> pInvestorPositionDetail,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspQryInvestorPositionDetail(getStructObject(pInvestorPositionDetail), getStructObject(pRspInfo),
                nRequestID, bIsLast);
    }

    @Override
    @Virtual(30)
    public void OnRspQryNotice(Pointer<CThostFtdcNoticeField> pNotice,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspQryNotice(getStructObject(pNotice), getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(31)
    public void OnRspQrySettlementInfoConfirm(
            Pointer<CThostFtdcSettlementInfoConfirmField> pSettlementInfoConfirm,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspQrySettlementInfoConfirm(getStructObject(pSettlementInfoConfirm), getStructObject(pRspInfo),
                nRequestID, bIsLast);
    }

    @Override
    @Virtual(32)
    public void OnRspQryInvestorPositionCombineDetail(
            Pointer<CThostFtdcInvestorPositionCombineDetailField> pInvestorPositionCombineDetail,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspQryInvestorPositionCombineDetail(getStructObject(pInvestorPositionCombineDetail), 
                getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(33)
    public void OnRspQryCFMMCTradingAccountKey(
            Pointer<CThostFtdcCFMMCTradingAccountKeyField> pCFMMCTradingAccountKey,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspQryCFMMCTradingAccountKey(getStructObject(pCFMMCTradingAccountKey), getStructObject(pRspInfo),
                nRequestID, bIsLast);
    }

    @Override
    @Virtual(34)
    public void OnRspQryEWarrantOffset(
            Pointer<CThostFtdcEWarrantOffsetField> pEWarrantOffset,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspQryEWarrantOffset(getStructObject(pEWarrantOffset), getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(35)
    public void OnRspQryTransferSerial(
            Pointer<CThostFtdcTransferSerialField> pTransferSerial,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspQryTransferSerial(getStructObject(pTransferSerial), getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(36)
    public void OnRspQryAccountregister(
            Pointer<CThostFtdcAccountregisterField> pAccountregister,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspQryAccountregister(getStructObject(pAccountregister), getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(37)
    public void OnRspError(Pointer<CThostFtdcRspInfoField> pRspInfo,
            int nRequestID, boolean bIsLast) {
        traderSpi.onRspError(getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(38)
    public void OnRtnOrder(Pointer<CThostFtdcOrderField> pOrder) {
        traderSpi.onRtnOrder(getStructObject(pOrder));
    }

    @Override
    @Virtual(39)
    public void OnRtnTrade(Pointer<CThostFtdcTradeField> pTrade) {
        traderSpi.onRtnTrade(getStructObject(pTrade));
    }

    @Override
    @Virtual(40)
    public void OnErrRtnOrderInsert(
            Pointer<CThostFtdcInputOrderField> pInputOrder,
            Pointer<CThostFtdcRspInfoField> pRspInfo) {
        traderSpi.onErrRtnOrderInsert(getStructObject(pInputOrder), getStructObject(pRspInfo));
    }

    @Override
    @Virtual(41)
    public void OnErrRtnOrderAction(
            Pointer<CThostFtdcOrderActionField> pOrderAction,
            Pointer<CThostFtdcRspInfoField> pRspInfo) {
        traderSpi.onErrRtnOrderAction(getStructObject(pOrderAction), getStructObject(pRspInfo));
    }

    @Override
    @Virtual(42)
    public void OnRtnInstrumentStatus(
            Pointer<CThostFtdcInstrumentStatusField> pInstrumentStatus) {
        traderSpi.onRtnInstrumentStatus(getStructObject(pInstrumentStatus));
    }

    @Override
    @Virtual(43)
    public void OnRtnTradingNotice(
            Pointer<CThostFtdcTradingNoticeInfoField> pTradingNoticeInfo) {
        traderSpi.onRtnTradingNotice(getStructObject(pTradingNoticeInfo));
    }

    @Override
    @Virtual(44)
    public void OnRtnErrorConditionalOrder(
            Pointer<CThostFtdcErrorConditionalOrderField> pErrorConditionalOrder) {
        traderSpi.onRtnErrorConditionalOrder(getStructObject(pErrorConditionalOrder));
    }

    @Override
    @Virtual(45)
    public void OnRspQryContractBank(
            Pointer<CThostFtdcContractBankField> pContractBank,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspQryContractBank(getStructObject(pContractBank), getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(46)
    public void OnRspQryParkedOrder(
            Pointer<CThostFtdcParkedOrderField> pParkedOrder,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspQryParkedOrder(getStructObject(pParkedOrder), getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(47)
    public void OnRspQryParkedOrderAction(
            Pointer<CThostFtdcParkedOrderActionField> pParkedOrderAction,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspQryParkedOrderAction(getStructObject(pParkedOrderAction), getStructObject(pRspInfo), nRequestID,
                bIsLast);
    }

    @Override
    @Virtual(48)
    public void OnRspQryTradingNotice(
            Pointer<CThostFtdcTradingNoticeField> pTradingNotice,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspQryTradingNotice(getStructObject(pTradingNotice), getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(49)
    public void OnRspQryBrokerTradingParams(
            Pointer<CThostFtdcBrokerTradingParamsField> pBrokerTradingParams,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspQryBrokerTradingParams(getStructObject(pBrokerTradingParams), getStructObject(pRspInfo), nRequestID,
                bIsLast);
    }

    @Override
    @Virtual(50)
    public void OnRspQryBrokerTradingAlgos(
            Pointer<CThostFtdcBrokerTradingAlgosField> pBrokerTradingAlgos,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspQryBrokerTradingAlgos(getStructObject(pBrokerTradingAlgos), getStructObject(pRspInfo), nRequestID,
                bIsLast);
    }

    @Override
    @Virtual(51)
    public void OnRtnFromBankToFutureByBank(
            Pointer<CThostFtdcRspTransferField> pRspTransfer) {
        traderSpi.onRtnFromBankToFutureByBank(getStructObject(pRspTransfer));
    }

    @Override
    @Virtual(52)
    public void OnRtnFromFutureToBankByBank(
            Pointer<CThostFtdcRspTransferField> pRspTransfer) {
        traderSpi.onRtnFromFutureToBankByBank(getStructObject(pRspTransfer));
    }

    @Override
    @Virtual(53)
    public void OnRtnRepealFromBankToFutureByBank(
            Pointer<CThostFtdcRspRepealField> pRspRepeal) {
        traderSpi.onRtnRepealFromBankToFutureByBank(getStructObject(pRspRepeal));
    }

    @Override
    @Virtual(54)
    public void OnRtnRepealFromFutureToBankByBank(
            Pointer<CThostFtdcRspRepealField> pRspRepeal) {
        traderSpi.onRtnRepealFromFutureToBankByBank(getStructObject(pRspRepeal));
    }

    @Override
    @Virtual(55)
    public void OnRtnFromBankToFutureByFuture(
            Pointer<CThostFtdcRspTransferField> pRspTransfer) {
        traderSpi.onRtnFromBankToFutureByFuture(getStructObject(pRspTransfer));
    }

    @Override
    @Virtual(56)
    public void OnRtnFromFutureToBankByFuture(
            Pointer<CThostFtdcRspTransferField> pRspTransfer) {
        traderSpi.onRtnFromFutureToBankByFuture(getStructObject(pRspTransfer));
    }

    @Override
    @Virtual(57)
    public void OnRtnRepealFromBankToFutureByFutureManual(
            Pointer<CThostFtdcRspRepealField> pRspRepeal) {
        traderSpi.onRtnRepealFromBankToFutureByFutureManual(getStructObject(pRspRepeal));
    }

    @Override
    @Virtual(58)
    public void OnRtnRepealFromFutureToBankByFutureManual(
            Pointer<CThostFtdcRspRepealField> pRspRepeal) {
        traderSpi.onRtnRepealFromFutureToBankByFutureManual(getStructObject(pRspRepeal));
    }

    @Override
    @Virtual(59)
    public void OnRtnQueryBankBalanceByFuture(
            Pointer<CThostFtdcNotifyQueryAccountField> pNotifyQueryAccount) {
        traderSpi.onRtnQueryBankBalanceByFuture(getStructObject(pNotifyQueryAccount));
    }

    @Override
    @Virtual(60)
    public void OnErrRtnBankToFutureByFuture(
            Pointer<CThostFtdcReqTransferField> pReqTransfer,
            Pointer<CThostFtdcRspInfoField> pRspInfo) {
        traderSpi.onErrRtnBankToFutureByFuture(getStructObject(pReqTransfer), getStructObject(pRspInfo));
    }

    @Override
    @Virtual(61)
    public void OnErrRtnFutureToBankByFuture(
            Pointer<CThostFtdcReqTransferField> pReqTransfer,
            Pointer<CThostFtdcRspInfoField> pRspInfo) {
        traderSpi.onErrRtnFutureToBankByFuture(getStructObject(pReqTransfer), getStructObject(pRspInfo));
    }

    @Override
    @Virtual(62)
    public void OnErrRtnRepealBankToFutureByFutureManual(
            Pointer<CThostFtdcReqRepealField> pReqRepeal,
            Pointer<CThostFtdcRspInfoField> pRspInfo) {
        traderSpi.onErrRtnRepealBankToFutureByFutureManual(getStructObject(pReqRepeal), getStructObject(pRspInfo));
    }

    @Override
    @Virtual(63)
    public void OnErrRtnRepealFutureToBankByFutureManual(
            Pointer<CThostFtdcReqRepealField> pReqRepeal,
            Pointer<CThostFtdcRspInfoField> pRspInfo) {
        traderSpi.onErrRtnRepealFutureToBankByFutureManual(getStructObject(pReqRepeal), getStructObject(pRspInfo));
    }

    @Override
    @Virtual(64)
    public void OnErrRtnQueryBankBalanceByFuture(
            Pointer<CThostFtdcReqQueryAccountField> pReqQueryAccount,
            Pointer<CThostFtdcRspInfoField> pRspInfo) {
        traderSpi.onErrRtnQueryBankBalanceByFuture(getStructObject(pReqQueryAccount), getStructObject(pRspInfo));
    }

    @Override
    @Virtual(65)
    public void OnRtnRepealFromBankToFutureByFuture(
            Pointer<CThostFtdcRspRepealField> pRspRepeal) {
        traderSpi.onRtnRepealFromBankToFutureByFuture(getStructObject(pRspRepeal));
    }

    @Override
    @Virtual(66)
    public void OnRtnRepealFromFutureToBankByFuture(
            Pointer<CThostFtdcRspRepealField> pRspRepeal) {
        traderSpi.onRtnRepealFromFutureToBankByFuture(getStructObject(pRspRepeal));
    }

    @Override
    @Virtual(67)
    public void OnRspFromBankToFutureByFuture(
            Pointer<CThostFtdcReqTransferField> pReqTransfer,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspFromBankToFutureByFuture(getStructObject(pReqTransfer), getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(68)
    public void OnRspFromFutureToBankByFuture(
            Pointer<CThostFtdcReqTransferField> pReqTransfer,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspFromFutureToBankByFuture(getStructObject(pReqTransfer), getStructObject(pRspInfo), nRequestID, bIsLast);
    }

    @Override
    @Virtual(69)
    public void OnRspQueryBankAccountMoneyByFuture(
            Pointer<CThostFtdcReqQueryAccountField> pReqQueryAccount,
            Pointer<CThostFtdcRspInfoField> pRspInfo, int nRequestID,
            boolean bIsLast) {
        traderSpi.onRspQueryBankAccountMoneyByFuture(getStructObject(pReqQueryAccount), getStructObject(pRspInfo),
                nRequestID, bIsLast);
    }

    @Override
    @Virtual(70)
    public void OnRtnOpenAccountByBank(
            Pointer<CThostFtdcOpenAccountField> pOpenAccount) {
        traderSpi.onRtnOpenAccountByBank(getStructObject(pOpenAccount));
    }

    @Override
    @Virtual(71)
    public void OnRtnCancelAccountByBank(
            Pointer<CThostFtdcCancelAccountField> pCancelAccount) {
        traderSpi.onRtnCancelAccountByBank(getStructObject(pCancelAccount));
    }

    @Override
    @Virtual(72)
    public void OnRtnChangeAccountByBank(
            Pointer<CThostFtdcChangeAccountField> pChangeAccount) {
        traderSpi.onRtnChangeAccountByBank(getStructObject(pChangeAccount));
    }
    
    private <T extends StructObject> T getStructObject(Pointer<T> field) {
        return field == null ? null : field.get();
    }
}
