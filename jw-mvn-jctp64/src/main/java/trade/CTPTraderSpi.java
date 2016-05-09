package trade;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import thostftdcuserapidatatype.ThostFtdcUserApiDataTypeLibrary.THOST_TE_RESUME_TYPE;
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
import thostftdcuserapistruct.CThostFtdcQryInstrumentField;
import thostftdcuserapistruct.CThostFtdcQueryMaxOrderVolumeField;
import thostftdcuserapistruct.CThostFtdcRemoveParkedOrderActionField;
import thostftdcuserapistruct.CThostFtdcRemoveParkedOrderField;
import thostftdcuserapistruct.CThostFtdcReqQueryAccountField;
import thostftdcuserapistruct.CThostFtdcReqRepealField;
import thostftdcuserapistruct.CThostFtdcReqTransferField;
import thostftdcuserapistruct.CThostFtdcReqUserLoginField;
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

public class CTPTraderSpi {
    private BlockingQueue<Boolean> blockingQueue = new ArrayBlockingQueue<Boolean>(10);

    private int requestID = 0;
    private int instrumentCount = 0;
    private CTPTraderApi traderApi = null;
    private long beginTime = System.currentTimeMillis();

    public CTPTraderSpi() {
        super();
    }

    /**
     * 查询柜台合约保存到合约列表
     * 
     * @param backQuery ture表示后台查询，每隔5分查询一次；false表示前台查询，前台查询程序启动时查询一次
     * @return
     */
    public boolean getInstrumentID(boolean backThread) {

        //(1)创建 API实例
        //(2)向 API实例注册 SPI实例
        //(3)注册交易前置地址
        //(4)初始化
        if (null == traderApi) {
            traderApi = CTPTraderApi.createFtdcTraderApi();
            if (null == traderApi) {
                System.out.println("交易服务(createFtdcTraderApi)失败!");
                return false;
            }
        }
        traderApi.registerSpi(this);
        traderApi.registerFront("tcp://180.166.103.34:41205");
        traderApi.subscribePrivateTopic(THOST_TE_RESUME_TYPE.THOST_TERT_RESTART);
        traderApi.subscribePublicTopic(THOST_TE_RESUME_TYPE.THOST_TERT_RESTART);
        traderApi.init();

        //之后的执行流程:
        //(1)在OnFrontConnected中登陆,
        //(2)在OnRspUserLogin中查询合约ID,
        //(3)在OnRspSubMarketData中返回查询合约ID成功与否,若成功把合约ID存储到二维数组中。
        //(4)最长等待60秒唤醒                 
        try {
            blockingQueue.poll(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            blockingQueue = null;
        }

        if (null != traderApi) {
            try {
                traderApi.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    /**
     * 当客户端与交易后台建立起通信连接时（还未登录前）,该方法被调用。
     */
    public void onFrontConnected() {
        //System.out.println("交易服务(onFrontConnected):连接交易前置机成功!");

        //登录交易服务

        CThostFtdcReqUserLoginField userLoginField = new CThostFtdcReqUserLoginField();
        userLoginField.setBrokerID("6666");
        userLoginField.setUserID("66660000");
        userLoginField.setPassword("123456");

        if (traderApi.reqUserLogin(userLoginField, requestID++) != 0) {
            System.out.println("交易服务(onFrontConnected):发送登录请求失败:");
        } else {
            System.out.println("交易服务(onFrontConnected):发送登录请求成功:");
        }
    }

    /**
     * 当客户端与交易后台通信连接断开时,该方法被调用。当发生这个情况后,API会自动重新连接,客户端可不做处理。
     * 0x1001 网络读失败
     * 0x1002 网络写失败
     * 0x2001 接收心跳超时
     * 0x2002 发送心跳失败
     * 0x2003 收到错误报文
     * @param nReason
     */
    public void onFrontDisconnected(int nReason) {
    }

    /**
     * 登录请求响应
     * @param pOutInfo
     * @param pRspInfo
     * @param nRequestID
     * @param isLast
     */
    public void onRspUserLogin(CThostFtdcRspUserLoginField pOutInfo, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {
        System.out.println("=======onRspUserLogin==============");

        //记录日志返回值
        if (null != pRspInfo) {
            System.out.println("交易服务(OnRspUserLogin):交易服务器登录成功.");
        } else {
            System.out.println("交易服务(OnRspUserLogin):交易服务器登录失败");
        }

        //查询交易所合约供行情订阅使用
        if (isLast) {
            CThostFtdcQryInstrumentField stInstrument = new CThostFtdcQryInstrumentField();

            //暂时不过滤，执行全部查询
            stInstrument.setExchangeID("");
            if (0 != traderApi.reqQryInstrument(stInstrument, requestID++)) {
                System.out.println("交易服务(ReqQryInstrument):发送查询合约请求失败");
            } else {
                System.out.println("交易服务(ReqQryInstrument):发送查询合约请求成功");
            }
        }
    }

    /**
    * 请求查询合约响应
    * @param pOutInfo
    * @param pRspInfo
    * @param nRequestID
    * @param isLast
    *	TODO Bridj底层存在bug
    */
    public void onRspQryInstrument(CThostFtdcInstrumentField pOutInfo, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {
    	//记录日志返回值
        if (null != pRspInfo) {
            System.out.println("交易服务(onRspQryInstrument):查询合约响应失败.");
        }
        instrumentCount++;
        System.out.println("查询第" + instrumentCount + "条" + pOutInfo.toString());
        //如果是前台查询记录总数，如果是后台查询记录查询总数和是否有新增
        if (isLast) {
        	double diffTime = (System.currentTimeMillis() - beginTime) / 1000.0;
        	System.out.println("交易服务(onRspQryInstrument):本次共查询" + instrumentCount + "条,订阅列表  条.耗时" + diffTime);
        	
        	//激活信息量
        	try {
        		blockingQueue.put(true);
        	} catch (InterruptedException e) {
        		e.printStackTrace();
        	}
        } 
    }

    /**
     * 永远不会发生，已经对 API 用户屏蔽了该响应
     * @param nTimeLapse
     */
    public void onHeartBeatWarning(int nTimeLapse) {
        System.out.println("交易服务(onHeartBeatWarning):心跳超时" + nTimeLapse + "秒,警告!");
    }

    /**
     * 如果交易系统无法识别客户端发送的请求消息， 就通过这个函数返回错误信息。
     * @param pRspInfo
     * @param nRequestID
     * @param isLast
     */
    public void onRspError(CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {
    }

    ///////////////////////////////////////////////////////////////////////////////////
    //                                 以下暂不需要实现                                                                                   //
    ///////////////////////////////////////////////////////////////////////////////////

    public void onRspAuthenticate(CThostFtdcRspAuthenticateField pRspAuthenticateField, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {

    }

    public void onRspQryInstrumentMarginRate(CThostFtdcInstrumentMarginRateField pInstrumentMarginRate, CThostFtdcRspInfoField pRspInfo, int nRequestID,
            boolean isLast) {

    }

    public void onRspUserLogout(CThostFtdcUserLogoutField pUserLogout, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {

    }

    public void onRspUserPasswordUpdate(CThostFtdcUserPasswordUpdateField pUserPasswordUpdate, CThostFtdcRspInfoField pRspInfo, int nRequestID,
            boolean isLast) {

    }

    public void onRspTradingAccountPasswordUpdate(CThostFtdcTradingAccountPasswordUpdateField pTradingAccountPasswordUpdate, CThostFtdcRspInfoField pRspInfo,
            int nRequestID, boolean isLast) {

    }

    public void onRspOrderInsert(CThostFtdcInputOrderField pInputOrder, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {

    }

    public void onRspParkedOrderInsert(CThostFtdcParkedOrderField pParkedOrder, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {

    }

    public void onRspParkedOrderAction(CThostFtdcParkedOrderActionField pParkedOrderAction, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {

    }

    public void onRspOrderAction(CThostFtdcInputOrderActionField pInputOrderAction, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {

    }

    public void onRspQueryMaxOrderVolume(CThostFtdcQueryMaxOrderVolumeField pQueryMaxOrderVolume, CThostFtdcRspInfoField pRspInfo, int nRequestID,
            boolean isLast) {

    }

    public void onRspSettlementInfoConfirm(CThostFtdcSettlementInfoConfirmField pSettlementInfoConfirm, CThostFtdcRspInfoField pRspInfo, int nRequestID,
            boolean isLast) {

    }

    public void onRspRemoveParkedOrder(CThostFtdcRemoveParkedOrderField pRemoveParkedOrder, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {

    }

    public void onRspRemoveParkedOrderAction(CThostFtdcRemoveParkedOrderActionField pRemoveParkedOrderAction, CThostFtdcRspInfoField pRspInfo, int nRequestID,
            boolean isLast) {

    }

    public void onRspQryOrder(CThostFtdcOrderField pOrder, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {

    }

    public void onRspQryTrade(CThostFtdcTradeField pTrade, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {

    }

    public void onRspQryInvestorPosition(CThostFtdcInvestorPositionField pInvestorPosition, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {

    }

    public void onRspQryTradingAccount(CThostFtdcTradingAccountField pTradingAccount, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {

    }

    public void onRspQryInvestor(CThostFtdcInvestorField pInvestor, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {

    }

    public void onRspQryTradingCode(CThostFtdcTradingCodeField pTradingCode, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {

    }

    public void onRspQryInstrumentCommissionRate(CThostFtdcInstrumentCommissionRateField pInstrumentCommissionRate, CThostFtdcRspInfoField pRspInfo,
            int nRequestID, boolean isLast) {

    }

    public void onRspQryExchange(CThostFtdcExchangeField pExchange, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {

    }

    public void onRspQryDepthMarketData(CThostFtdcDepthMarketDataField pDepthMarketData, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {

    }

    public void onRspQrySettlementInfo(CThostFtdcSettlementInfoField pSettlementInfo, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {

    }

    public void onRspQryTransferBank(CThostFtdcTransferBankField pTransferBank, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {

    }

    public void onRspQryInvestorPositionDetail(CThostFtdcInvestorPositionDetailField pInvestorPositionDetail, CThostFtdcRspInfoField pRspInfo, int nRequestID,
            boolean isLast) {

    }

    public void onRspQryNotice(CThostFtdcNoticeField pNotice, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {

    }

    public void onRspQrySettlementInfoConfirm(CThostFtdcSettlementInfoConfirmField pSettlementInfoConfirm, CThostFtdcRspInfoField pRspInfo, int nRequestID,
            boolean isLast) {

    }

    public void onRspQryInvestorPositionCombineDetail(CThostFtdcInvestorPositionCombineDetailField pInvestorPositionCombineDetail,
            CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {

    }

    public void onRspQryCFMMCTradingAccountKey(CThostFtdcCFMMCTradingAccountKeyField pCFMMCTradingAccountKey, CThostFtdcRspInfoField pRspInfo, int nRequestID,
            boolean isLast) {

    }

    public void onRspQryEWarrantOffset(CThostFtdcEWarrantOffsetField pEWarrantOffset, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {

    }

    public void onRspQryTransferSerial(CThostFtdcTransferSerialField pTransferSerial, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {

    }

    public void onRspQryAccountregister(CThostFtdcAccountregisterField pAccountregister, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {

    }

    public void onRtnOrder(CThostFtdcOrderField pOrder) {

    }

    public void onRtnTrade(CThostFtdcTradeField pTrade) {

    }

    public void onErrRtnOrderInsert(CThostFtdcInputOrderField pInputOrder, CThostFtdcRspInfoField pRspInfo) {

    }

    public void onErrRtnOrderAction(CThostFtdcOrderActionField pOrderAction, CThostFtdcRspInfoField pRspInfo) {

    }

    public void onRtnInstrumentStatus(CThostFtdcInstrumentStatusField pInstrumentStatus) {

    }

    public void onRtnTradingNotice(CThostFtdcTradingNoticeInfoField pTradingNoticeInfo) {

    }

    public void onRtnErrorConditionalOrder(CThostFtdcErrorConditionalOrderField pErrorConditionalOrder) {

    }

    public void onRspQryContractBank(CThostFtdcContractBankField pContractBank, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {

    }

    public void onRspQryParkedOrder(CThostFtdcParkedOrderField pParkedOrder, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {

    }

    public void onRspQryParkedOrderAction(CThostFtdcParkedOrderActionField pParkedOrderAction, CThostFtdcRspInfoField pRspInfo, int nRequestID,
            boolean isLast) {

    }

    public void onRspQryTradingNotice(CThostFtdcTradingNoticeField pTradingNotice, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {

    }

    public void onRspQryBrokerTradingParams(CThostFtdcBrokerTradingParamsField pBrokerTradingParams, CThostFtdcRspInfoField pRspInfo, int nRequestID,
            boolean isLast) {

    }

    public void onRspQryBrokerTradingAlgos(CThostFtdcBrokerTradingAlgosField pBrokerTradingAlgos, CThostFtdcRspInfoField pRspInfo, int nRequestID,
            boolean isLast) {

    }

    public void onRtnFromBankToFutureByBank(CThostFtdcRspTransferField pRspTransfer) {

    }

    public void onRtnFromFutureToBankByBank(CThostFtdcRspTransferField pRspTransfer) {

    }

    public void onRtnRepealFromBankToFutureByBank(CThostFtdcRspRepealField pRspRepeal) {

    }

    public void onRtnRepealFromFutureToBankByBank(CThostFtdcRspRepealField pRspRepeal) {

    }

    public void onRtnFromBankToFutureByFuture(CThostFtdcRspTransferField pRspTransfer) {

    }

    public void onRtnFromFutureToBankByFuture(CThostFtdcRspTransferField pRspTransfer) {

    }

    public void onRtnRepealFromBankToFutureByFutureManual(CThostFtdcRspRepealField pRspRepeal) {

    }

    public void onRtnRepealFromFutureToBankByFutureManual(CThostFtdcRspRepealField pRspRepeal) {

    }

    public void onRtnQueryBankBalanceByFuture(CThostFtdcNotifyQueryAccountField pNotifyQueryAccount) {

    }

    public void onErrRtnBankToFutureByFuture(CThostFtdcReqTransferField pReqTransfer, CThostFtdcRspInfoField pRspInfo) {

    }

    public void onErrRtnFutureToBankByFuture(CThostFtdcReqTransferField pReqTransfer, CThostFtdcRspInfoField pRspInfo) {

    }

    public void onErrRtnRepealBankToFutureByFutureManual(CThostFtdcReqRepealField pReqRepeal, CThostFtdcRspInfoField pRspInfo) {

    }

    public void onErrRtnRepealFutureToBankByFutureManual(CThostFtdcReqRepealField pReqRepeal, CThostFtdcRspInfoField pRspInfo) {

    }

    public void onErrRtnQueryBankBalanceByFuture(CThostFtdcReqQueryAccountField pReqQueryAccount, CThostFtdcRspInfoField pRspInfo) {

    }

    public void onRtnRepealFromBankToFutureByFuture(CThostFtdcRspRepealField pRspRepeal) {

    }

    public void onRtnRepealFromFutureToBankByFuture(CThostFtdcRspRepealField pRspRepeal) {

    }

    public void onRspFromBankToFutureByFuture(CThostFtdcReqTransferField pReqTransfer, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {

    }

    public void onRspFromFutureToBankByFuture(CThostFtdcReqTransferField pReqTransfer, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {

    }

    public void onRspQueryBankAccountMoneyByFuture(CThostFtdcReqQueryAccountField pReqQueryAccount, CThostFtdcRspInfoField pRspInfo, int nRequestID,
            boolean isLast) {

    }

    public void onRtnOpenAccountByBank(CThostFtdcOpenAccountField pOpenAccount) {

    }

    public void onRtnCancelAccountByBank(CThostFtdcCancelAccountField pCancelAccount) {

    }

    public void onRtnChangeAccountByBank(CThostFtdcChangeAccountField pChangeAccount) {

    }
}
