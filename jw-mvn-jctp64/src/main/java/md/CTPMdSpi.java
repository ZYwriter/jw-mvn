package md;

import thostftdcuserapistruct.CThostFtdcDepthMarketDataField;
import thostftdcuserapistruct.CThostFtdcReqUserLoginField;
import thostftdcuserapistruct.CThostFtdcRspInfoField;
import thostftdcuserapistruct.CThostFtdcRspUserLoginField;
import thostftdcuserapistruct.CThostFtdcSpecificInstrumentField;
import thostftdcuserapistruct.CThostFtdcUserLogoutField;
import trade.CTPTraderSpi;

/**
 * ���ߣ�wengqf
 * ��Ŀ��sims2016derive-quotationtranscode-ctp64
 * ˵������װ��
 * ���ڣ�2016��3��2��
 * ��ע��
 */
public class CTPMdSpi {
    private CTPMdApi mdApi = null;
    private int requestID = 0;

    /**
     * �������鶩������
     * ��1��������Լ��ѯ
     * ��2���������鶩��
     */
    public boolean startMDSpi() {

        CTPTraderSpi traderSpi = new CTPTraderSpi();
        try {
            traderSpi.getInstrumentID(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //���� APIʵ��
        //�� APIʵ��ע�� SPIʵ��
        //ע�ύ��ǰ�õ�ַ
        //��ʼ��
        if (null == mdApi) {
            mdApi = CTPMdApi.createFtdcMdApi();
            if (null == mdApi) {
                return false;
            }
        }
        mdApi.registerSpi(this);
        mdApi.registerFront("tcp://180.166.103.34:41213");
        mdApi.init();

        //������̨�̲߳�ѯ��Լ��δʵ��
        return true;
    }

    /**
     * �ر����鶩��
     */
    public void stopMDSpi() {
        if (null != mdApi) {
            mdApi.release();
        }
    }

    /**
     * �ͻ���������ǰ�û��������ӣ����������¼���������֤
     */
    public void onFrontConnected() {
        System.out.println("�������(onFrontConnected):��������ǰ�û��ɹ�!");

        //�����¼
        CThostFtdcReqUserLoginField userLoginField = new CThostFtdcReqUserLoginField();
        userLoginField.setBrokerID("6666");
        userLoginField.setUserID("66660000");
        userLoginField.setPassword("123456");
        //�ú������᷵��һ������ֵ����־�������Ƿ񱻳ɹ����ͳ�ȥ����������������Ƿ�ᱻ����˴���
        if (requestID >= 2100000000) {
            requestID = 0;
        }
        if (mdApi.reqUserLogin(userLoginField, requestID++) != 0) {
            System.out.println("�������(onFrontConnected):���͵�¼����ʧ��:");
        } else {
            System.out.println("�������(onFrontConnected):���͵�¼����ɹ�:");
        }
    }

    /**
     * ����ͻ��˵�����ǰ�õ��������֤���ӽ���ʧ�ܣ� ������������á����еĲ���˵������ʧ�ܵ�ԭ��
     * @param nReason
     */
    public void onFrontDisconnected(int nReason) {
    }

    /**
     * �������һ��ʱ���ڿͻ��˺�ϵͳ֮��û���κ���Ϣ���������� ��������ᷢ����������˵���ͻ��˵�ϵͳ������֮
     * ��������ǻ�Ծ�ġ�
     * @param nTimeLapse
     */
    public void onHeartBeatWarning(int nTimeLapse) {
    }

    /**
     * ����ϵͳ�Կͻ��˵������¼��Ϣ��������Ӧ�� ��¼������Ӧ
     * @param pOutInfo
     * @param pRspInfo
     * @param nRequestID
     * @param isLast
     */
    public void onRspUserLogin(CThostFtdcRspUserLoginField pOutInfo, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {
        //��¼��־����ֵ
        if (null != pRspInfo) {
            System.out.println("�������(OnRspUserLogin):���׷�������¼�ɹ�.");
        } else {
            System.out.println("�������(OnRspUserLogin):���׷�������¼ʧ��");
        }

        //��¼Ӧ����־
        if (null != pOutInfo) {
            System.out.println("�������(onRspUserLogin):" + pOutInfo.toString());
        }

        //���ݲ�ѯ���ĺ�Լ���ж���
        if (isLast) {
            int subResult = mdApi.subscribeMarketData("IF1604");
            if (subResult == 0) {
            	System.out.println("�������(onRspUserLogin):��������ɹ�.");
            }
        }
    }

    /**
     * �˳���¼
     * @param pOutInfo
     * @param pRspInfo
     * @param nRequestID
     * @param isLast
     */
    public void onRspUserLogout(CThostFtdcUserLogoutField pOutInfo, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {
    }

    /**
     * �������ϵͳ�޷�ʶ��ͻ��˷��͵�������Ϣ�� ��ͨ������������ش�����Ϣ��
     * @param pRspInfo
     * @param nRequestID
     * @param isLast
     */
    public void onRspError(CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {
    }

    /**
     * ����ͻ��˶�������������ǲ��Ϸ��ģ��ú������ط������˸����Ĵ�����Ϣ��pRspInfo������ʹ�ͻ��˷�
     * �͵Ķ��������ǺϷ��ģ��ú���Ҳ�ᱻ���ã������ص���Ϣ���ǡ�CTP��No Error����
     * @param pOutInfo
     * @param pRspInfo
     * @param nRequestID
     * @param isLast
     */
    public void onRspSubMarketData(CThostFtdcSpecificInstrumentField pOutInfo, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {

    }

    /**
     * ȡ����������Ӧ��
     * @param pOutInfo
     * @param pRspInfo
     * @param nRequestID
     * @param bIsLast
     */
    public void onRspUnSubMarketData(CThostFtdcSpecificInstrumentField pOutInfo, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {


    }

    /**
     * ���鶩�������ǺϷ��ģ������ֱ�ӷ���ĳ��Լ���г����顣Ƶ���� ÿ������
     * @param pOutInfo
     */
    public void onRtnDepthMarketData(CThostFtdcDepthMarketDataField pOutInfo) {
        //��κϷ�����֤
        if (null == pOutInfo) {
            System.out.println("�������(onRtnDepthMarketData):������������,������������ַΪ��.");
            return;
        }
        System.out.println(pOutInfo);
    }

}
