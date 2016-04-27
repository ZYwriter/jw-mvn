package md;

import thostftdcuserapistruct.CThostFtdcDepthMarketDataField;
import thostftdcuserapistruct.CThostFtdcReqUserLoginField;
import thostftdcuserapistruct.CThostFtdcRspInfoField;
import thostftdcuserapistruct.CThostFtdcRspUserLoginField;
import thostftdcuserapistruct.CThostFtdcSpecificInstrumentField;
import thostftdcuserapistruct.CThostFtdcUserLogoutField;
import trade.CTPTraderSpi;

/**
 * 作者：wengqf
 * 项目：sims2016derive-quotationtranscode-ctp64
 * 说明：封装类
 * 日期：2016年3月2日
 * 备注：
 */
public class CTPMdSpi {
    private CTPMdApi mdApi = null;
    private int requestID = 0;

    /**
     * 启动行情订阅流程
     * （1）启动合约查询
     * （2）启动行情订阅
     */
    public boolean startMDSpi() {

        CTPTraderSpi traderSpi = new CTPTraderSpi();
        try {
            traderSpi.getInstrumentID(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //创建 API实例
        //向 API实例注册 SPI实例
        //注册交易前置地址
        //初始化
        if (null == mdApi) {
            mdApi = CTPMdApi.createFtdcMdApi();
            if (null == mdApi) {
                return false;
            }
        }
        mdApi.registerSpi(this);
        mdApi.registerFront("tcp://180.166.103.34:41213");
        mdApi.init();

        //启动后台线程查询合约暂未实现
        return true;
    }

    /**
     * 关闭行情订阅
     */
    public void stopMDSpi() {
        if (null != mdApi) {
            mdApi.release();
        }
    }

    /**
     * 客户端与行情前置机建立连接，调用行情登录进行身份验证
     */
    public void onFrontConnected() {
        System.out.println("行情服务(onFrontConnected):连接行情前置机成功!");

        //行情登录
        CThostFtdcReqUserLoginField userLoginField = new CThostFtdcReqUserLoginField();
        userLoginField.setBrokerID("6666");
        userLoginField.setUserID("66660000");
        userLoginField.setPassword("123456");
        //该函数将会返回一个整数值，标志该请求是否被成功发送出去，而不代表该请求是否会被服务端处理。
        if (requestID >= 2100000000) {
            requestID = 0;
        }
        if (mdApi.reqUserLogin(userLoginField, requestID++) != 0) {
            System.out.println("行情服务(onFrontConnected):发送登录请求失败:");
        } else {
            System.out.println("行情服务(onFrontConnected):发送登录请求成功:");
        }
    }

    /**
     * 如果客户端到行情前置的无身份验证连接建立失败， 这个函数被调用。其中的参数说明连接失败的原因。
     * @param nReason
     */
    public void onFrontDisconnected(int nReason) {
    }

    /**
     * 如果超过一定时间在客户端和系统之间没有任何消息交换发生， 这个函数会发送心跳用来说明客户端到系统服务器之
     * 间的连接是活跃的。
     * @param nTimeLapse
     */
    public void onHeartBeatWarning(int nTimeLapse) {
    }

    /**
     * 交易系统对客户端的请求登录消息作出的响应。 登录请求响应
     * @param pOutInfo
     * @param pRspInfo
     * @param nRequestID
     * @param isLast
     */
    public void onRspUserLogin(CThostFtdcRspUserLoginField pOutInfo, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {
        //记录日志返回值
        if (null != pRspInfo) {
            System.out.println("行情服务(OnRspUserLogin):交易服务器登录成功.");
        } else {
            System.out.println("行情服务(OnRspUserLogin):交易服务器登录失败");
        }

        //记录应答日志
        if (null != pOutInfo) {
            System.out.println("行情服务(onRspUserLogin):" + pOutInfo.toString());
        }

        //根据查询到的合约进行订阅
        if (isLast) {
            int subResult = mdApi.subscribeMarketData("IF1604");
            if (subResult == 0) {
            	System.out.println("行情服务(onRspUserLogin):共订请求成功.");
            }
        }
    }

    /**
     * 退出登录
     * @param pOutInfo
     * @param pRspInfo
     * @param nRequestID
     * @param isLast
     */
    public void onRspUserLogout(CThostFtdcUserLogoutField pOutInfo, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {
    }

    /**
     * 如果交易系统无法识别客户端发送的请求消息， 就通过这个函数返回错误信息。
     * @param pRspInfo
     * @param nRequestID
     * @param isLast
     */
    public void onRspError(CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {
    }

    /**
     * 如果客户端订阅行情的请求是不合法的，该函数返回服务器端给出的错误信息（pRspInfo）。即使客户端发
     * 送的订阅请求是合法的，该函数也会被调用，而返回的信息则是“CTP：No Error”。
     * @param pOutInfo
     * @param pRspInfo
     * @param nRequestID
     * @param isLast
     */
    public void onRspSubMarketData(CThostFtdcSpecificInstrumentField pOutInfo, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {

    }

    /**
     * 取消订阅行情应答
     * @param pOutInfo
     * @param pRspInfo
     * @param nRequestID
     * @param bIsLast
     */
    public void onRspUnSubMarketData(CThostFtdcSpecificInstrumentField pOutInfo, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean isLast) {


    }

    /**
     * 行情订阅请求是合法的，服务端直接返回某合约的市场行情。频率是 每秒两次
     * @param pOutInfo
     */
    public void onRtnDepthMarketData(CThostFtdcDepthMarketDataField pOutInfo) {
        //入参合法性验证
        if (null == pOutInfo) {
            System.out.println("行情服务(onRtnDepthMarketData):接收行情有误,传入参数行情地址为空.");
            return;
        }
        System.out.println(pOutInfo);
    }

}
