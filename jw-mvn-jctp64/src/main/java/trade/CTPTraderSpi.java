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
     * ��ѯ��̨��Լ���浽��Լ�б�
     * 
     * @param backQuery ture��ʾ��̨��ѯ��ÿ��5�ֲ�ѯһ�Σ�false��ʾǰ̨��ѯ��ǰ̨��ѯ��������ʱ��ѯһ��
     * @return
     */
    public boolean getInstrumentID(boolean backThread) {

        //(1)���� APIʵ��
        //(2)�� APIʵ��ע�� SPIʵ��
        //(3)ע�ύ��ǰ�õ�ַ
        //(4)��ʼ��
        if (null == traderApi) {
            traderApi = CTPTraderApi.createFtdcTraderApi();
            if (null == traderApi) {
                System.out.println("���׷���(createFtdcTraderApi)ʧ��!");
                return false;
            }
        }
        traderApi.registerSpi(this);
        traderApi.registerFront("tcp://180.166.103.34:41205");
        traderApi.subscribePrivateTopic(THOST_TE_RESUME_TYPE.THOST_TERT_RESTART);
        traderApi.subscribePublicTopic(THOST_TE_RESUME_TYPE.THOST_TERT_RESTART);
        traderApi.init();

        //֮���ִ������:
        //(1)��OnFrontConnected�е�½,
        //(2)��OnRspUserLogin�в�ѯ��ԼID,
        //(3)��OnRspSubMarketData�з��ز�ѯ��ԼID�ɹ����,���ɹ��Ѻ�ԼID�洢����ά�����С�
        //(4)��ȴ�60�뻽��                 
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
     * ���ͻ����뽻�׺�̨������ͨ������ʱ����δ��¼ǰ��,�÷��������á�
     */
    public void onFrontConnected() {
        //System.out.println("���׷���(onFrontConnected):���ӽ���ǰ�û��ɹ�!");

        //��¼���׷���

        CThostFtdcReqUserLoginField userLoginField = new CThostFtdcReqUserLoginField();
        userLoginField.setBrokerID("6666");
        userLoginField.setUserID("66660000");
        userLoginField.setPassword("123456");

        if (traderApi.reqUserLogin(userLoginField, requestID++) != 0) {
            System.out.println("���׷���(onFrontConnected):���͵�¼����ʧ��:");
        } else {
            System.out.println("���׷���(onFrontConnected):���͵�¼����ɹ�:");
        }
    }

    /**
     * ���ͻ����뽻�׺�̨ͨ�����ӶϿ�ʱ,�÷��������á���������������,API���Զ���������,�ͻ��˿ɲ�������
     * 0x1001 �����ʧ��
     * 0x1002 ����дʧ��
     * 0x2001 ����������ʱ
     * 0x2002 ��������ʧ��
     * 0x2003 �յ�������
     * @param nReason
     */
    public void onFrontDisconnected(int nReason) {
    }

    /**
     * ��¼������Ӧ
     * @param pOutInfo
     * @param pRspInfo
     * @param nRequestID
     * @param isLast
     */
    public void onRspUserLogin(CThostFtdcRspUserLoginField pOutInfo, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {
        System.out.println("=======onRspUserLogin==============");

        //��¼��־����ֵ
        if (null != pRspInfo) {
            System.out.println("���׷���(OnRspUserLogin):���׷�������¼�ɹ�.");
        } else {
            System.out.println("���׷���(OnRspUserLogin):���׷�������¼ʧ��");
        }

        //��ѯ��������Լ�����鶩��ʹ��
        if (isLast) {
            CThostFtdcQryInstrumentField stInstrument = new CThostFtdcQryInstrumentField();

            //��ʱ�����ˣ�ִ��ȫ����ѯ
            stInstrument.setExchangeID("");
            if (0 != traderApi.reqQryInstrument(stInstrument, requestID++)) {
                System.out.println("���׷���(ReqQryInstrument):���Ͳ�ѯ��Լ����ʧ��");
            } else {
                System.out.println("���׷���(ReqQryInstrument):���Ͳ�ѯ��Լ����ɹ�");
            }
        }
    }

    /**
    * �����ѯ��Լ��Ӧ
    * @param pOutInfo
    * @param pRspInfo
    * @param nRequestID
    * @param isLast
    *	TODO Bridj�ײ����bug
    */
    public void onRspQryInstrument(CThostFtdcInstrumentField pOutInfo, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {
    	//��¼��־����ֵ
        if (null != pRspInfo) {
            System.out.println("���׷���(onRspQryInstrument):��ѯ��Լ��Ӧʧ��.");
        }
        instrumentCount++;
        System.out.println("��ѯ��" + instrumentCount + "��" + pOutInfo.toString());
        //�����ǰ̨��ѯ��¼����������Ǻ�̨��ѯ��¼��ѯ�������Ƿ�������
        if (isLast) {
        	double diffTime = (System.currentTimeMillis() - beginTime) / 1000.0;
        	System.out.println("���׷���(onRspQryInstrument):���ι���ѯ" + instrumentCount + "��,�����б�  ��.��ʱ" + diffTime);
        	
        	//������Ϣ��
        	try {
        		blockingQueue.put(true);
        	} catch (InterruptedException e) {
        		e.printStackTrace();
        	}
        } 
    }

    /**
     * ��Զ���ᷢ�����Ѿ��� API �û������˸���Ӧ
     * @param nTimeLapse
     */
    public void onHeartBeatWarning(int nTimeLapse) {
        System.out.println("���׷���(onHeartBeatWarning):������ʱ" + nTimeLapse + "��,����!");
    }

    /**
     * �������ϵͳ�޷�ʶ��ͻ��˷��͵�������Ϣ�� ��ͨ������������ش�����Ϣ��
     * @param pRspInfo
     * @param nRequestID
     * @param isLast
     */
    public void onRspError(CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {
    }

    ///////////////////////////////////////////////////////////////////////////////////
    //                                 �����ݲ���Ҫʵ��                                                                                   //
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
