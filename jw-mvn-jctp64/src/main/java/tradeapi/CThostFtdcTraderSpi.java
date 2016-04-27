package tradeapi;
import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Virtual;
import org.bridj.cpp.CPPObject;

import thostftdcuserapistruct.CThostFtdcAccountregisterField;
import thostftdcuserapistruct.CThostFtdcBrokerTradingAlgosField;
import thostftdcuserapistruct.CThostFtdcBrokerTradingParamsField;
import thostftdcuserapistruct.CThostFtdcCFMMCTradingAccountKeyField;
import thostftdcuserapistruct.CThostFtdcCFMMCTradingAccountTokenField;
import thostftdcuserapistruct.CThostFtdcCancelAccountField;
import thostftdcuserapistruct.CThostFtdcChangeAccountField;
import thostftdcuserapistruct.CThostFtdcCombActionField;
import thostftdcuserapistruct.CThostFtdcCombInstrumentGuardField;
import thostftdcuserapistruct.CThostFtdcContractBankField;
import thostftdcuserapistruct.CThostFtdcDepthMarketDataField;
import thostftdcuserapistruct.CThostFtdcEWarrantOffsetField;
import thostftdcuserapistruct.CThostFtdcErrorConditionalOrderField;
import thostftdcuserapistruct.CThostFtdcExchangeField;
import thostftdcuserapistruct.CThostFtdcExchangeMarginRateAdjustField;
import thostftdcuserapistruct.CThostFtdcExchangeMarginRateField;
import thostftdcuserapistruct.CThostFtdcExchangeRateField;
import thostftdcuserapistruct.CThostFtdcExecOrderActionField;
import thostftdcuserapistruct.CThostFtdcExecOrderField;
import thostftdcuserapistruct.CThostFtdcForQuoteField;
import thostftdcuserapistruct.CThostFtdcForQuoteRspField;
import thostftdcuserapistruct.CThostFtdcInputCombActionField;
import thostftdcuserapistruct.CThostFtdcInputExecOrderActionField;
import thostftdcuserapistruct.CThostFtdcInputExecOrderField;
import thostftdcuserapistruct.CThostFtdcInputForQuoteField;
import thostftdcuserapistruct.CThostFtdcInputOrderActionField;
import thostftdcuserapistruct.CThostFtdcInputOrderField;
import thostftdcuserapistruct.CThostFtdcInputQuoteActionField;
import thostftdcuserapistruct.CThostFtdcInputQuoteField;
import thostftdcuserapistruct.CThostFtdcInstrumentCommissionRateField;
import thostftdcuserapistruct.CThostFtdcInstrumentField;
import thostftdcuserapistruct.CThostFtdcInstrumentMarginRateField;
import thostftdcuserapistruct.CThostFtdcInstrumentOrderCommRateField;
import thostftdcuserapistruct.CThostFtdcInstrumentStatusField;
import thostftdcuserapistruct.CThostFtdcInvestorField;
import thostftdcuserapistruct.CThostFtdcInvestorPositionCombineDetailField;
import thostftdcuserapistruct.CThostFtdcInvestorPositionDetailField;
import thostftdcuserapistruct.CThostFtdcInvestorPositionField;
import thostftdcuserapistruct.CThostFtdcInvestorProductGroupMarginField;
import thostftdcuserapistruct.CThostFtdcNoticeField;
import thostftdcuserapistruct.CThostFtdcNotifyQueryAccountField;
import thostftdcuserapistruct.CThostFtdcOpenAccountField;
import thostftdcuserapistruct.CThostFtdcOptionInstrCommRateField;
import thostftdcuserapistruct.CThostFtdcOptionInstrTradeCostField;
import thostftdcuserapistruct.CThostFtdcOrderActionField;
import thostftdcuserapistruct.CThostFtdcOrderField;
import thostftdcuserapistruct.CThostFtdcParkedOrderActionField;
import thostftdcuserapistruct.CThostFtdcParkedOrderField;
import thostftdcuserapistruct.CThostFtdcProductField;
import thostftdcuserapistruct.CThostFtdcProductGroupField;
import thostftdcuserapistruct.CThostFtdcQueryCFMMCTradingAccountTokenField;
import thostftdcuserapistruct.CThostFtdcQueryMaxOrderVolumeField;
import thostftdcuserapistruct.CThostFtdcQuoteActionField;
import thostftdcuserapistruct.CThostFtdcQuoteField;
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
import thostftdcuserapistruct.CThostFtdcSecAgentACIDMapField;
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
/**
 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:13</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("thosttraderapi") 
public class CThostFtdcTraderSpi extends CPPObject {
	public CThostFtdcTraderSpi() {
		super();
	}
	/**
	 * \u5f53\u5ba2\u6237\u7aef\u4e0e\u4ea4\u6613\u540e\u53f0\u5efa\u7acb\u8d77\u901a\u4fe1\u8fde\u63a5\u65f6\uff08\u8fd8\u672a\u767b\u5f55\u524d\uff09\uff0c\u8be5\u65b9\u6cd5\u88ab\u8c03\u7528\u3002<br>
	 * Original signature : <code>void OnFrontConnected()</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:17</i>
	 */
	@Virtual(0) 
	public native void OnFrontConnected();
	/**
	 * 0x2003 \u6536\u5230\u9519\u8bef\u62a5\u6587<br>
	 * Original signature : <code>void OnFrontDisconnected(int)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:26</i>
	 */
	@Virtual(1) 
	public native void OnFrontDisconnected(int nReason);
	/**
	 * @param nTimeLapse \u8ddd\u79bb\u4e0a\u6b21\u63a5\u6536\u62a5\u6587\u7684\u65f6\u95f4<br>
	 * Original signature : <code>void OnHeartBeatWarning(int)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:30</i>
	 */
	@Virtual(2) 
	public native void OnHeartBeatWarning(int nTimeLapse);
	/**
	 * \u5ba2\u6237\u7aef\u8ba4\u8bc1\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspAuthenticate(CThostFtdcRspAuthenticateField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:33</i>
	 */
	@Virtual(3) 
	public native void OnRspAuthenticate(Pointer<CThostFtdcRspAuthenticateField > pRspAuthenticateField, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u767b\u5f55\u8bf7\u6c42\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspUserLogin(CThostFtdcRspUserLoginField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:37</i>
	 */
	@Virtual(4) 
	public native void OnRspUserLogin(Pointer<CThostFtdcRspUserLoginField > pRspUserLogin, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u767b\u51fa\u8bf7\u6c42\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspUserLogout(CThostFtdcUserLogoutField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:40</i>
	 */
	@Virtual(5) 
	public native void OnRspUserLogout(Pointer<CThostFtdcUserLogoutField > pUserLogout, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u7528\u6237\u53e3\u4ee4\u66f4\u65b0\u8bf7\u6c42\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspUserPasswordUpdate(CThostFtdcUserPasswordUpdateField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:43</i>
	 */
	@Virtual(6) 
	public native void OnRspUserPasswordUpdate(Pointer<CThostFtdcUserPasswordUpdateField > pUserPasswordUpdate, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8d44\u91d1\u8d26\u6237\u53e3\u4ee4\u66f4\u65b0\u8bf7\u6c42\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspTradingAccountPasswordUpdate(CThostFtdcTradingAccountPasswordUpdateField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:46</i>
	 */
	@Virtual(7) 
	public native void OnRspTradingAccountPasswordUpdate(Pointer<CThostFtdcTradingAccountPasswordUpdateField > pTradingAccountPasswordUpdate, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u62a5\u5355\u5f55\u5165\u8bf7\u6c42\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspOrderInsert(CThostFtdcInputOrderField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:49</i>
	 */
	@Virtual(8) 
	public native void OnRspOrderInsert(Pointer<CThostFtdcInputOrderField > pInputOrder, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u9884\u57cb\u5355\u5f55\u5165\u8bf7\u6c42\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspParkedOrderInsert(CThostFtdcParkedOrderField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:52</i>
	 */
	@Virtual(9) 
	public native void OnRspParkedOrderInsert(Pointer<CThostFtdcParkedOrderField > pParkedOrder, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u9884\u57cb\u64a4\u5355\u5f55\u5165\u8bf7\u6c42\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspParkedOrderAction(CThostFtdcParkedOrderActionField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:55</i>
	 */
	@Virtual(10) 
	public native void OnRspParkedOrderAction(Pointer<CThostFtdcParkedOrderActionField > pParkedOrderAction, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u62a5\u5355\u64cd\u4f5c\u8bf7\u6c42\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspOrderAction(CThostFtdcInputOrderActionField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:58</i>
	 */
	@Virtual(11) 
	public native void OnRspOrderAction(Pointer<CThostFtdcInputOrderActionField > pInputOrderAction, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u67e5\u8be2\u6700\u5927\u62a5\u5355\u6570\u91cf\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQueryMaxOrderVolume(CThostFtdcQueryMaxOrderVolumeField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:61</i>
	 */
	@Virtual(12) 
	public native void OnRspQueryMaxOrderVolume(Pointer<CThostFtdcQueryMaxOrderVolumeField > pQueryMaxOrderVolume, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u6295\u8d44\u8005\u7ed3\u7b97\u7ed3\u679c\u786e\u8ba4\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspSettlementInfoConfirm(CThostFtdcSettlementInfoConfirmField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:64</i>
	 */
	@Virtual(13) 
	public native void OnRspSettlementInfoConfirm(Pointer<CThostFtdcSettlementInfoConfirmField > pSettlementInfoConfirm, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u5220\u9664\u9884\u57cb\u5355\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspRemoveParkedOrder(CThostFtdcRemoveParkedOrderField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:67</i>
	 */
	@Virtual(14) 
	public native void OnRspRemoveParkedOrder(Pointer<CThostFtdcRemoveParkedOrderField > pRemoveParkedOrder, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u5220\u9664\u9884\u57cb\u64a4\u5355\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspRemoveParkedOrderAction(CThostFtdcRemoveParkedOrderActionField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:70</i>
	 */
	@Virtual(15) 
	public native void OnRspRemoveParkedOrderAction(Pointer<CThostFtdcRemoveParkedOrderActionField > pRemoveParkedOrderAction, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u6267\u884c\u5ba3\u544a\u5f55\u5165\u8bf7\u6c42\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspExecOrderInsert(CThostFtdcInputExecOrderField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:73</i>
	 */
	@Virtual(16) 
	public native void OnRspExecOrderInsert(Pointer<CThostFtdcInputExecOrderField > pInputExecOrder, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u6267\u884c\u5ba3\u544a\u64cd\u4f5c\u8bf7\u6c42\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspExecOrderAction(CThostFtdcInputExecOrderActionField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:76</i>
	 */
	@Virtual(17) 
	public native void OnRspExecOrderAction(Pointer<CThostFtdcInputExecOrderActionField > pInputExecOrderAction, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8be2\u4ef7\u5f55\u5165\u8bf7\u6c42\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspForQuoteInsert(CThostFtdcInputForQuoteField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:79</i>
	 */
	@Virtual(18) 
	public native void OnRspForQuoteInsert(Pointer<CThostFtdcInputForQuoteField > pInputForQuote, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u62a5\u4ef7\u5f55\u5165\u8bf7\u6c42\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQuoteInsert(CThostFtdcInputQuoteField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:82</i>
	 */
	@Virtual(19) 
	public native void OnRspQuoteInsert(Pointer<CThostFtdcInputQuoteField > pInputQuote, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u62a5\u4ef7\u64cd\u4f5c\u8bf7\u6c42\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQuoteAction(CThostFtdcInputQuoteActionField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:85</i>
	 */
	@Virtual(20) 
	public native void OnRspQuoteAction(Pointer<CThostFtdcInputQuoteActionField > pInputQuoteAction, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u7533\u8bf7\u7ec4\u5408\u5f55\u5165\u8bf7\u6c42\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspCombActionInsert(CThostFtdcInputCombActionField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:88</i>
	 */
	@Virtual(21) 
	public native void OnRspCombActionInsert(Pointer<CThostFtdcInputCombActionField > pInputCombAction, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u62a5\u5355\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryOrder(CThostFtdcOrderField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:91</i>
	 */
	@Virtual(22) 
	public native void OnRspQryOrder(Pointer<CThostFtdcOrderField > pOrder, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u6210\u4ea4\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryTrade(CThostFtdcTradeField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:94</i>
	 */
	@Virtual(23) 
	public native void OnRspQryTrade(Pointer<CThostFtdcTradeField > pTrade, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u6295\u8d44\u8005\u6301\u4ed3\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryInvestorPosition(CThostFtdcInvestorPositionField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:97</i>
	 */
	@Virtual(24) 
	public native void OnRspQryInvestorPosition(Pointer<CThostFtdcInvestorPositionField > pInvestorPosition, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u8d44\u91d1\u8d26\u6237\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryTradingAccount(CThostFtdcTradingAccountField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:100</i>
	 */
	@Virtual(25) 
	public native void OnRspQryTradingAccount(Pointer<CThostFtdcTradingAccountField > pTradingAccount, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u6295\u8d44\u8005\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryInvestor(CThostFtdcInvestorField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:103</i>
	 */
	@Virtual(26) 
	public native void OnRspQryInvestor(Pointer<CThostFtdcInvestorField > pInvestor, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u4ea4\u6613\u7f16\u7801\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryTradingCode(CThostFtdcTradingCodeField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:106</i>
	 */
	@Virtual(27) 
	public native void OnRspQryTradingCode(Pointer<CThostFtdcTradingCodeField > pTradingCode, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u5408\u7ea6\u4fdd\u8bc1\u91d1\u7387\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryInstrumentMarginRate(CThostFtdcInstrumentMarginRateField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:109</i>
	 */
	@Virtual(28) 
	public native void OnRspQryInstrumentMarginRate(Pointer<CThostFtdcInstrumentMarginRateField > pInstrumentMarginRate, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u5408\u7ea6\u624b\u7eed\u8d39\u7387\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryInstrumentCommissionRate(CThostFtdcInstrumentCommissionRateField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:112</i>
	 */
	@Virtual(29) 
	public native void OnRspQryInstrumentCommissionRate(Pointer<CThostFtdcInstrumentCommissionRateField > pInstrumentCommissionRate, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u4ea4\u6613\u6240\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryExchange(CThostFtdcExchangeField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:115</i>
	 */
	@Virtual(30) 
	public native void OnRspQryExchange(Pointer<CThostFtdcExchangeField > pExchange, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u4ea7\u54c1\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryProduct(CThostFtdcProductField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:118</i>
	 */
	@Virtual(31) 
	public native void OnRspQryProduct(Pointer<CThostFtdcProductField > pProduct, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u5408\u7ea6\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryInstrument(CThostFtdcInstrumentField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:121</i>
	 */
	@Virtual(32) 
	public native void OnRspQryInstrument(Pointer<CThostFtdcInstrumentField > pInstrument, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u884c\u60c5\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryDepthMarketData(CThostFtdcDepthMarketDataField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:124</i>
	 */
	@Virtual(33) 
	public native void OnRspQryDepthMarketData(Pointer<CThostFtdcDepthMarketDataField > pDepthMarketData, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u6295\u8d44\u8005\u7ed3\u7b97\u7ed3\u679c\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQrySettlementInfo(CThostFtdcSettlementInfoField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:127</i>
	 */
	@Virtual(34) 
	public native void OnRspQrySettlementInfo(Pointer<CThostFtdcSettlementInfoField > pSettlementInfo, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u8f6c\u5e10\u94f6\u884c\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryTransferBank(CThostFtdcTransferBankField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:130</i>
	 */
	@Virtual(35) 
	public native void OnRspQryTransferBank(Pointer<CThostFtdcTransferBankField > pTransferBank, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u6295\u8d44\u8005\u6301\u4ed3\u660e\u7ec6\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryInvestorPositionDetail(CThostFtdcInvestorPositionDetailField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:133</i>
	 */
	@Virtual(36) 
	public native void OnRspQryInvestorPositionDetail(Pointer<CThostFtdcInvestorPositionDetailField > pInvestorPositionDetail, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u5ba2\u6237\u901a\u77e5\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryNotice(CThostFtdcNoticeField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:136</i>
	 */
	@Virtual(37) 
	public native void OnRspQryNotice(Pointer<CThostFtdcNoticeField > pNotice, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u7ed3\u7b97\u4fe1\u606f\u786e\u8ba4\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQrySettlementInfoConfirm(CThostFtdcSettlementInfoConfirmField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:139</i>
	 */
	@Virtual(38) 
	public native void OnRspQrySettlementInfoConfirm(Pointer<CThostFtdcSettlementInfoConfirmField > pSettlementInfoConfirm, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u6295\u8d44\u8005\u6301\u4ed3\u660e\u7ec6\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryInvestorPositionCombineDetail(CThostFtdcInvestorPositionCombineDetailField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:142</i>
	 */
	@Virtual(39) 
	public native void OnRspQryInvestorPositionCombineDetail(Pointer<CThostFtdcInvestorPositionCombineDetailField > pInvestorPositionCombineDetail, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u67e5\u8be2\u4fdd\u8bc1\u91d1\u76d1\u7ba1\u7cfb\u7edf\u7ecf\u7eaa\u516c\u53f8\u8d44\u91d1\u8d26\u6237\u5bc6\u94a5\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryCFMMCTradingAccountKey(CThostFtdcCFMMCTradingAccountKeyField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:145</i>
	 */
	@Virtual(40) 
	public native void OnRspQryCFMMCTradingAccountKey(Pointer<CThostFtdcCFMMCTradingAccountKeyField > pCFMMCTradingAccountKey, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u4ed3\u5355\u6298\u62b5\u4fe1\u606f\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryEWarrantOffset(CThostFtdcEWarrantOffsetField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:148</i>
	 */
	@Virtual(41) 
	public native void OnRspQryEWarrantOffset(Pointer<CThostFtdcEWarrantOffsetField > pEWarrantOffset, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u6295\u8d44\u8005\u54c1\u79cd/\u8de8\u54c1\u79cd\u4fdd\u8bc1\u91d1\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryInvestorProductGroupMargin(CThostFtdcInvestorProductGroupMarginField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:151</i>
	 */
	@Virtual(42) 
	public native void OnRspQryInvestorProductGroupMargin(Pointer<CThostFtdcInvestorProductGroupMarginField > pInvestorProductGroupMargin, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u4ea4\u6613\u6240\u4fdd\u8bc1\u91d1\u7387\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryExchangeMarginRate(CThostFtdcExchangeMarginRateField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:154</i>
	 */
	@Virtual(43) 
	public native void OnRspQryExchangeMarginRate(Pointer<CThostFtdcExchangeMarginRateField > pExchangeMarginRate, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u4ea4\u6613\u6240\u8c03\u6574\u4fdd\u8bc1\u91d1\u7387\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryExchangeMarginRateAdjust(CThostFtdcExchangeMarginRateAdjustField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:157</i>
	 */
	@Virtual(44) 
	public native void OnRspQryExchangeMarginRateAdjust(Pointer<CThostFtdcExchangeMarginRateAdjustField > pExchangeMarginRateAdjust, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u6c47\u7387\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryExchangeRate(CThostFtdcExchangeRateField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:160</i>
	 */
	@Virtual(45) 
	public native void OnRspQryExchangeRate(Pointer<CThostFtdcExchangeRateField > pExchangeRate, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u4e8c\u7ea7\u4ee3\u7406\u64cd\u4f5c\u5458\u94f6\u671f\u6743\u9650\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQrySecAgentACIDMap(CThostFtdcSecAgentACIDMapField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:163</i>
	 */
	@Virtual(46) 
	public native void OnRspQrySecAgentACIDMap(Pointer<CThostFtdcSecAgentACIDMapField > pSecAgentACIDMap, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u4ea7\u54c1\u7ec4<br>
	 * Original signature : <code>void OnRspQryProductGroup(CThostFtdcProductGroupField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:166</i>
	 */
	@Virtual(47) 
	public native void OnRspQryProductGroup(Pointer<CThostFtdcProductGroupField > pProductGroup, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u62a5\u5355\u624b\u7eed\u8d39\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryInstrumentOrderCommRate(CThostFtdcInstrumentOrderCommRateField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:169</i>
	 */
	@Virtual(48) 
	public native void OnRspQryInstrumentOrderCommRate(Pointer<CThostFtdcInstrumentOrderCommRateField > pInstrumentOrderCommRate, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u671f\u6743\u4ea4\u6613\u6210\u672c\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryOptionInstrTradeCost(CThostFtdcOptionInstrTradeCostField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:172</i>
	 */
	@Virtual(49) 
	public native void OnRspQryOptionInstrTradeCost(Pointer<CThostFtdcOptionInstrTradeCostField > pOptionInstrTradeCost, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u671f\u6743\u5408\u7ea6\u624b\u7eed\u8d39\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryOptionInstrCommRate(CThostFtdcOptionInstrCommRateField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:175</i>
	 */
	@Virtual(50) 
	public native void OnRspQryOptionInstrCommRate(Pointer<CThostFtdcOptionInstrCommRateField > pOptionInstrCommRate, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u6267\u884c\u5ba3\u544a\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryExecOrder(CThostFtdcExecOrderField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:178</i>
	 */
	@Virtual(51) 
	public native void OnRspQryExecOrder(Pointer<CThostFtdcExecOrderField > pExecOrder, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u8be2\u4ef7\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryForQuote(CThostFtdcForQuoteField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:181</i>
	 */
	@Virtual(52) 
	public native void OnRspQryForQuote(Pointer<CThostFtdcForQuoteField > pForQuote, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u62a5\u4ef7\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryQuote(CThostFtdcQuoteField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:184</i>
	 */
	@Virtual(53) 
	public native void OnRspQryQuote(Pointer<CThostFtdcQuoteField > pQuote, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u7ec4\u5408\u5408\u7ea6\u5b89\u5168\u7cfb\u6570\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryCombInstrumentGuard(CThostFtdcCombInstrumentGuardField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:187</i>
	 */
	@Virtual(54) 
	public native void OnRspQryCombInstrumentGuard(Pointer<CThostFtdcCombInstrumentGuardField > pCombInstrumentGuard, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u7533\u8bf7\u7ec4\u5408\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryCombAction(CThostFtdcCombActionField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:190</i>
	 */
	@Virtual(55) 
	public native void OnRspQryCombAction(Pointer<CThostFtdcCombActionField > pCombAction, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u8f6c\u5e10\u6d41\u6c34\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryTransferSerial(CThostFtdcTransferSerialField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:193</i>
	 */
	@Virtual(56) 
	public native void OnRspQryTransferSerial(Pointer<CThostFtdcTransferSerialField > pTransferSerial, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u94f6\u671f\u7b7e\u7ea6\u5173\u7cfb\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryAccountregister(CThostFtdcAccountregisterField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:196</i>
	 */
	@Virtual(57) 
	public native void OnRspQryAccountregister(Pointer<CThostFtdcAccountregisterField > pAccountregister, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u9519\u8bef\u5e94\u7b54<br>
	 * Original signature : <code>void OnRspError(CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:199</i>
	 */
	@Virtual(58) 
	public native void OnRspError(Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u62a5\u5355\u901a\u77e5<br>
	 * Original signature : <code>void OnRtnOrder(CThostFtdcOrderField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:202</i>
	 */
	@Virtual(59) 
	public native void OnRtnOrder(Pointer<CThostFtdcOrderField > pOrder);
	/**
	 * \u6210\u4ea4\u901a\u77e5<br>
	 * Original signature : <code>void OnRtnTrade(CThostFtdcTradeField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:205</i>
	 */
	@Virtual(60) 
	public native void OnRtnTrade(Pointer<CThostFtdcTradeField > pTrade);
	/**
	 * \u62a5\u5355\u5f55\u5165\u9519\u8bef\u56de\u62a5<br>
	 * Original signature : <code>void OnErrRtnOrderInsert(CThostFtdcInputOrderField*, CThostFtdcRspInfoField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:208</i>
	 */
	@Virtual(61) 
	public native void OnErrRtnOrderInsert(Pointer<CThostFtdcInputOrderField > pInputOrder, Pointer<CThostFtdcRspInfoField > pRspInfo);
	/**
	 * \u62a5\u5355\u64cd\u4f5c\u9519\u8bef\u56de\u62a5<br>
	 * Original signature : <code>void OnErrRtnOrderAction(CThostFtdcOrderActionField*, CThostFtdcRspInfoField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:211</i>
	 */
	@Virtual(62) 
	public native void OnErrRtnOrderAction(Pointer<CThostFtdcOrderActionField > pOrderAction, Pointer<CThostFtdcRspInfoField > pRspInfo);
	/**
	 * \u5408\u7ea6\u4ea4\u6613\u72b6\u6001\u901a\u77e5<br>
	 * Original signature : <code>void OnRtnInstrumentStatus(CThostFtdcInstrumentStatusField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:214</i>
	 */
	@Virtual(63) 
	public native void OnRtnInstrumentStatus(Pointer<CThostFtdcInstrumentStatusField > pInstrumentStatus);
	/**
	 * \u4ea4\u6613\u901a\u77e5<br>
	 * Original signature : <code>void OnRtnTradingNotice(CThostFtdcTradingNoticeInfoField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:217</i>
	 */
	@Virtual(64) 
	public native void OnRtnTradingNotice(Pointer<CThostFtdcTradingNoticeInfoField > pTradingNoticeInfo);
	/**
	 * \u63d0\u793a\u6761\u4ef6\u5355\u6821\u9a8c\u9519\u8bef<br>
	 * Original signature : <code>void OnRtnErrorConditionalOrder(CThostFtdcErrorConditionalOrderField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:220</i>
	 */
	@Virtual(65) 
	public native void OnRtnErrorConditionalOrder(Pointer<CThostFtdcErrorConditionalOrderField > pErrorConditionalOrder);
	/**
	 * \u6267\u884c\u5ba3\u544a\u901a\u77e5<br>
	 * Original signature : <code>void OnRtnExecOrder(CThostFtdcExecOrderField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:223</i>
	 */
	@Virtual(66) 
	public native void OnRtnExecOrder(Pointer<CThostFtdcExecOrderField > pExecOrder);
	/**
	 * \u6267\u884c\u5ba3\u544a\u5f55\u5165\u9519\u8bef\u56de\u62a5<br>
	 * Original signature : <code>void OnErrRtnExecOrderInsert(CThostFtdcInputExecOrderField*, CThostFtdcRspInfoField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:226</i>
	 */
	@Virtual(67) 
	public native void OnErrRtnExecOrderInsert(Pointer<CThostFtdcInputExecOrderField > pInputExecOrder, Pointer<CThostFtdcRspInfoField > pRspInfo);
	/**
	 * \u6267\u884c\u5ba3\u544a\u64cd\u4f5c\u9519\u8bef\u56de\u62a5<br>
	 * Original signature : <code>void OnErrRtnExecOrderAction(CThostFtdcExecOrderActionField*, CThostFtdcRspInfoField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:229</i>
	 */
	@Virtual(68) 
	public native void OnErrRtnExecOrderAction(Pointer<CThostFtdcExecOrderActionField > pExecOrderAction, Pointer<CThostFtdcRspInfoField > pRspInfo);
	/**
	 * \u8be2\u4ef7\u5f55\u5165\u9519\u8bef\u56de\u62a5<br>
	 * Original signature : <code>void OnErrRtnForQuoteInsert(CThostFtdcInputForQuoteField*, CThostFtdcRspInfoField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:232</i>
	 */
	@Virtual(69) 
	public native void OnErrRtnForQuoteInsert(Pointer<CThostFtdcInputForQuoteField > pInputForQuote, Pointer<CThostFtdcRspInfoField > pRspInfo);
	/**
	 * \u62a5\u4ef7\u901a\u77e5<br>
	 * Original signature : <code>void OnRtnQuote(CThostFtdcQuoteField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:235</i>
	 */
	@Virtual(70) 
	public native void OnRtnQuote(Pointer<CThostFtdcQuoteField > pQuote);
	/**
	 * \u62a5\u4ef7\u5f55\u5165\u9519\u8bef\u56de\u62a5<br>
	 * Original signature : <code>void OnErrRtnQuoteInsert(CThostFtdcInputQuoteField*, CThostFtdcRspInfoField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:238</i>
	 */
	@Virtual(71) 
	public native void OnErrRtnQuoteInsert(Pointer<CThostFtdcInputQuoteField > pInputQuote, Pointer<CThostFtdcRspInfoField > pRspInfo);
	/**
	 * \u62a5\u4ef7\u64cd\u4f5c\u9519\u8bef\u56de\u62a5<br>
	 * Original signature : <code>void OnErrRtnQuoteAction(CThostFtdcQuoteActionField*, CThostFtdcRspInfoField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:241</i>
	 */
	@Virtual(72) 
	public native void OnErrRtnQuoteAction(Pointer<CThostFtdcQuoteActionField > pQuoteAction, Pointer<CThostFtdcRspInfoField > pRspInfo);
	/**
	 * \u8be2\u4ef7\u901a\u77e5<br>
	 * Original signature : <code>void OnRtnForQuoteRsp(CThostFtdcForQuoteRspField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:244</i>
	 */
	@Virtual(73) 
	public native void OnRtnForQuoteRsp(Pointer<CThostFtdcForQuoteRspField > pForQuoteRsp);
	/**
	 * \u4fdd\u8bc1\u91d1\u76d1\u63a7\u4e2d\u5fc3\u7528\u6237\u4ee4\u724c<br>
	 * Original signature : <code>void OnRtnCFMMCTradingAccountToken(CThostFtdcCFMMCTradingAccountTokenField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:247</i>
	 */
	@Virtual(74) 
	public native void OnRtnCFMMCTradingAccountToken(Pointer<CThostFtdcCFMMCTradingAccountTokenField > pCFMMCTradingAccountToken);
	/**
	 * \u7533\u8bf7\u7ec4\u5408\u901a\u77e5<br>
	 * Original signature : <code>void OnRtnCombAction(CThostFtdcCombActionField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:250</i>
	 */
	@Virtual(75) 
	public native void OnRtnCombAction(Pointer<CThostFtdcCombActionField > pCombAction);
	/**
	 * \u7533\u8bf7\u7ec4\u5408\u5f55\u5165\u9519\u8bef\u56de\u62a5<br>
	 * Original signature : <code>void OnErrRtnCombActionInsert(CThostFtdcInputCombActionField*, CThostFtdcRspInfoField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:253</i>
	 */
	@Virtual(76) 
	public native void OnErrRtnCombActionInsert(Pointer<CThostFtdcInputCombActionField > pInputCombAction, Pointer<CThostFtdcRspInfoField > pRspInfo);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u7b7e\u7ea6\u94f6\u884c\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryContractBank(CThostFtdcContractBankField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:256</i>
	 */
	@Virtual(77) 
	public native void OnRspQryContractBank(Pointer<CThostFtdcContractBankField > pContractBank, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u9884\u57cb\u5355\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryParkedOrder(CThostFtdcParkedOrderField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:259</i>
	 */
	@Virtual(78) 
	public native void OnRspQryParkedOrder(Pointer<CThostFtdcParkedOrderField > pParkedOrder, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u9884\u57cb\u64a4\u5355\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryParkedOrderAction(CThostFtdcParkedOrderActionField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:262</i>
	 */
	@Virtual(79) 
	public native void OnRspQryParkedOrderAction(Pointer<CThostFtdcParkedOrderActionField > pParkedOrderAction, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u4ea4\u6613\u901a\u77e5\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryTradingNotice(CThostFtdcTradingNoticeField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:265</i>
	 */
	@Virtual(80) 
	public native void OnRspQryTradingNotice(Pointer<CThostFtdcTradingNoticeField > pTradingNotice, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u7ecf\u7eaa\u516c\u53f8\u4ea4\u6613\u53c2\u6570\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryBrokerTradingParams(CThostFtdcBrokerTradingParamsField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:268</i>
	 */
	@Virtual(81) 
	public native void OnRspQryBrokerTradingParams(Pointer<CThostFtdcBrokerTradingParamsField > pBrokerTradingParams, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u7ecf\u7eaa\u516c\u53f8\u4ea4\u6613\u7b97\u6cd5\u54cd\u5e94<br>
	 * Original signature : <code>void OnRspQryBrokerTradingAlgos(CThostFtdcBrokerTradingAlgosField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:271</i>
	 */
	@Virtual(82) 
	public native void OnRspQryBrokerTradingAlgos(Pointer<CThostFtdcBrokerTradingAlgosField > pBrokerTradingAlgos, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u8bf7\u6c42\u67e5\u8be2\u76d1\u63a7\u4e2d\u5fc3\u7528\u6237\u4ee4\u724c<br>
	 * Original signature : <code>void OnRspQueryCFMMCTradingAccountToken(CThostFtdcQueryCFMMCTradingAccountTokenField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:274</i>
	 */
	@Virtual(83) 
	public native void OnRspQueryCFMMCTradingAccountToken(Pointer<CThostFtdcQueryCFMMCTradingAccountTokenField > pQueryCFMMCTradingAccountToken, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u94f6\u884c\u53d1\u8d77\u94f6\u884c\u8d44\u91d1\u8f6c\u671f\u8d27\u901a\u77e5<br>
	 * Original signature : <code>void OnRtnFromBankToFutureByBank(CThostFtdcRspTransferField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:277</i>
	 */
	@Virtual(84) 
	public native void OnRtnFromBankToFutureByBank(Pointer<CThostFtdcRspTransferField > pRspTransfer);
	/**
	 * \u94f6\u884c\u53d1\u8d77\u671f\u8d27\u8d44\u91d1\u8f6c\u94f6\u884c\u901a\u77e5<br>
	 * Original signature : <code>void OnRtnFromFutureToBankByBank(CThostFtdcRspTransferField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:280</i>
	 */
	@Virtual(85) 
	public native void OnRtnFromFutureToBankByBank(Pointer<CThostFtdcRspTransferField > pRspTransfer);
	/**
	 * \u94f6\u884c\u53d1\u8d77\u51b2\u6b63\u94f6\u884c\u8f6c\u671f\u8d27\u901a\u77e5<br>
	 * Original signature : <code>void OnRtnRepealFromBankToFutureByBank(CThostFtdcRspRepealField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:283</i>
	 */
	@Virtual(86) 
	public native void OnRtnRepealFromBankToFutureByBank(Pointer<CThostFtdcRspRepealField > pRspRepeal);
	/**
	 * \u94f6\u884c\u53d1\u8d77\u51b2\u6b63\u671f\u8d27\u8f6c\u94f6\u884c\u901a\u77e5<br>
	 * Original signature : <code>void OnRtnRepealFromFutureToBankByBank(CThostFtdcRspRepealField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:286</i>
	 */
	@Virtual(87) 
	public native void OnRtnRepealFromFutureToBankByBank(Pointer<CThostFtdcRspRepealField > pRspRepeal);
	/**
	 * \u671f\u8d27\u53d1\u8d77\u94f6\u884c\u8d44\u91d1\u8f6c\u671f\u8d27\u901a\u77e5<br>
	 * Original signature : <code>void OnRtnFromBankToFutureByFuture(CThostFtdcRspTransferField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:289</i>
	 */
	@Virtual(88) 
	public native void OnRtnFromBankToFutureByFuture(Pointer<CThostFtdcRspTransferField > pRspTransfer);
	/**
	 * \u671f\u8d27\u53d1\u8d77\u671f\u8d27\u8d44\u91d1\u8f6c\u94f6\u884c\u901a\u77e5<br>
	 * Original signature : <code>void OnRtnFromFutureToBankByFuture(CThostFtdcRspTransferField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:292</i>
	 */
	@Virtual(89) 
	public native void OnRtnFromFutureToBankByFuture(Pointer<CThostFtdcRspTransferField > pRspTransfer);
	/**
	 * \u7cfb\u7edf\u8fd0\u884c\u65f6\u671f\u8d27\u7aef\u624b\u5de5\u53d1\u8d77\u51b2\u6b63\u94f6\u884c\u8f6c\u671f\u8d27\u8bf7\u6c42\uff0c\u94f6\u884c\u5904\u7406\u5b8c\u6bd5\u540e\u62a5\u76d8\u53d1\u56de\u7684\u901a\u77e5<br>
	 * Original signature : <code>void OnRtnRepealFromBankToFutureByFutureManual(CThostFtdcRspRepealField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:295</i>
	 */
	@Virtual(90) 
	public native void OnRtnRepealFromBankToFutureByFutureManual(Pointer<CThostFtdcRspRepealField > pRspRepeal);
	/**
	 * \u7cfb\u7edf\u8fd0\u884c\u65f6\u671f\u8d27\u7aef\u624b\u5de5\u53d1\u8d77\u51b2\u6b63\u671f\u8d27\u8f6c\u94f6\u884c\u8bf7\u6c42\uff0c\u94f6\u884c\u5904\u7406\u5b8c\u6bd5\u540e\u62a5\u76d8\u53d1\u56de\u7684\u901a\u77e5<br>
	 * Original signature : <code>void OnRtnRepealFromFutureToBankByFutureManual(CThostFtdcRspRepealField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:298</i>
	 */
	@Virtual(91) 
	public native void OnRtnRepealFromFutureToBankByFutureManual(Pointer<CThostFtdcRspRepealField > pRspRepeal);
	/**
	 * \u671f\u8d27\u53d1\u8d77\u67e5\u8be2\u94f6\u884c\u4f59\u989d\u901a\u77e5<br>
	 * Original signature : <code>void OnRtnQueryBankBalanceByFuture(CThostFtdcNotifyQueryAccountField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:301</i>
	 */
	@Virtual(92) 
	public native void OnRtnQueryBankBalanceByFuture(Pointer<CThostFtdcNotifyQueryAccountField > pNotifyQueryAccount);
	/**
	 * \u671f\u8d27\u53d1\u8d77\u94f6\u884c\u8d44\u91d1\u8f6c\u671f\u8d27\u9519\u8bef\u56de\u62a5<br>
	 * Original signature : <code>void OnErrRtnBankToFutureByFuture(CThostFtdcReqTransferField*, CThostFtdcRspInfoField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:304</i>
	 */
	@Virtual(93) 
	public native void OnErrRtnBankToFutureByFuture(Pointer<CThostFtdcReqTransferField > pReqTransfer, Pointer<CThostFtdcRspInfoField > pRspInfo);
	/**
	 * \u671f\u8d27\u53d1\u8d77\u671f\u8d27\u8d44\u91d1\u8f6c\u94f6\u884c\u9519\u8bef\u56de\u62a5<br>
	 * Original signature : <code>void OnErrRtnFutureToBankByFuture(CThostFtdcReqTransferField*, CThostFtdcRspInfoField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:307</i>
	 */
	@Virtual(94) 
	public native void OnErrRtnFutureToBankByFuture(Pointer<CThostFtdcReqTransferField > pReqTransfer, Pointer<CThostFtdcRspInfoField > pRspInfo);
	/**
	 * \u7cfb\u7edf\u8fd0\u884c\u65f6\u671f\u8d27\u7aef\u624b\u5de5\u53d1\u8d77\u51b2\u6b63\u94f6\u884c\u8f6c\u671f\u8d27\u9519\u8bef\u56de\u62a5<br>
	 * Original signature : <code>void OnErrRtnRepealBankToFutureByFutureManual(CThostFtdcReqRepealField*, CThostFtdcRspInfoField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:310</i>
	 */
	@Virtual(95) 
	public native void OnErrRtnRepealBankToFutureByFutureManual(Pointer<CThostFtdcReqRepealField > pReqRepeal, Pointer<CThostFtdcRspInfoField > pRspInfo);
	/**
	 * \u7cfb\u7edf\u8fd0\u884c\u65f6\u671f\u8d27\u7aef\u624b\u5de5\u53d1\u8d77\u51b2\u6b63\u671f\u8d27\u8f6c\u94f6\u884c\u9519\u8bef\u56de\u62a5<br>
	 * Original signature : <code>void OnErrRtnRepealFutureToBankByFutureManual(CThostFtdcReqRepealField*, CThostFtdcRspInfoField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:313</i>
	 */
	@Virtual(96) 
	public native void OnErrRtnRepealFutureToBankByFutureManual(Pointer<CThostFtdcReqRepealField > pReqRepeal, Pointer<CThostFtdcRspInfoField > pRspInfo);
	/**
	 * \u671f\u8d27\u53d1\u8d77\u67e5\u8be2\u94f6\u884c\u4f59\u989d\u9519\u8bef\u56de\u62a5<br>
	 * Original signature : <code>void OnErrRtnQueryBankBalanceByFuture(CThostFtdcReqQueryAccountField*, CThostFtdcRspInfoField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:316</i>
	 */
	@Virtual(97) 
	public native void OnErrRtnQueryBankBalanceByFuture(Pointer<CThostFtdcReqQueryAccountField > pReqQueryAccount, Pointer<CThostFtdcRspInfoField > pRspInfo);
	/**
	 * \u671f\u8d27\u53d1\u8d77\u51b2\u6b63\u94f6\u884c\u8f6c\u671f\u8d27\u8bf7\u6c42\uff0c\u94f6\u884c\u5904\u7406\u5b8c\u6bd5\u540e\u62a5\u76d8\u53d1\u56de\u7684\u901a\u77e5<br>
	 * Original signature : <code>void OnRtnRepealFromBankToFutureByFuture(CThostFtdcRspRepealField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:319</i>
	 */
	@Virtual(98) 
	public native void OnRtnRepealFromBankToFutureByFuture(Pointer<CThostFtdcRspRepealField > pRspRepeal);
	/**
	 * \u671f\u8d27\u53d1\u8d77\u51b2\u6b63\u671f\u8d27\u8f6c\u94f6\u884c\u8bf7\u6c42\uff0c\u94f6\u884c\u5904\u7406\u5b8c\u6bd5\u540e\u62a5\u76d8\u53d1\u56de\u7684\u901a\u77e5<br>
	 * Original signature : <code>void OnRtnRepealFromFutureToBankByFuture(CThostFtdcRspRepealField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:322</i>
	 */
	@Virtual(99) 
	public native void OnRtnRepealFromFutureToBankByFuture(Pointer<CThostFtdcRspRepealField > pRspRepeal);
	/**
	 * \u671f\u8d27\u53d1\u8d77\u94f6\u884c\u8d44\u91d1\u8f6c\u671f\u8d27\u5e94\u7b54<br>
	 * Original signature : <code>void OnRspFromBankToFutureByFuture(CThostFtdcReqTransferField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:325</i>
	 */
	@Virtual(100) 
	public native void OnRspFromBankToFutureByFuture(Pointer<CThostFtdcReqTransferField > pReqTransfer, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u671f\u8d27\u53d1\u8d77\u671f\u8d27\u8d44\u91d1\u8f6c\u94f6\u884c\u5e94\u7b54<br>
	 * Original signature : <code>void OnRspFromFutureToBankByFuture(CThostFtdcReqTransferField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:328</i>
	 */
	@Virtual(101) 
	public native void OnRspFromFutureToBankByFuture(Pointer<CThostFtdcReqTransferField > pReqTransfer, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u671f\u8d27\u53d1\u8d77\u67e5\u8be2\u94f6\u884c\u4f59\u989d\u5e94\u7b54<br>
	 * Original signature : <code>void OnRspQueryBankAccountMoneyByFuture(CThostFtdcReqQueryAccountField*, CThostFtdcRspInfoField*, int, bool)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:331</i>
	 */
	@Virtual(102) 
	public native void OnRspQueryBankAccountMoneyByFuture(Pointer<CThostFtdcReqQueryAccountField > pReqQueryAccount, Pointer<CThostFtdcRspInfoField > pRspInfo, int nRequestID, boolean bIsLast);
	/**
	 * \u94f6\u884c\u53d1\u8d77\u94f6\u671f\u5f00\u6237\u901a\u77e5<br>
	 * Original signature : <code>void OnRtnOpenAccountByBank(CThostFtdcOpenAccountField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:334</i>
	 */
	@Virtual(103) 
	public native void OnRtnOpenAccountByBank(Pointer<CThostFtdcOpenAccountField > pOpenAccount);
	/**
	 * \u94f6\u884c\u53d1\u8d77\u94f6\u671f\u9500\u6237\u901a\u77e5<br>
	 * Original signature : <code>void OnRtnCancelAccountByBank(CThostFtdcCancelAccountField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:337</i>
	 */
	@Virtual(104) 
	public native void OnRtnCancelAccountByBank(Pointer<CThostFtdcCancelAccountField > pCancelAccount);
	/**
	 * \u94f6\u884c\u53d1\u8d77\u53d8\u66f4\u94f6\u884c\u8d26\u53f7\u901a\u77e5<br>
	 * Original signature : <code>void OnRtnChangeAccountByBank(CThostFtdcChangeAccountField*)</code><br>
	 * <i>native declaration : F:\jna\ThostFtdcTraderApi.h:340</i>
	 */
	@Virtual(105) 
	public native void OnRtnChangeAccountByBank(Pointer<CThostFtdcChangeAccountField > pChangeAccount);
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CThostFtdcTraderSpi(Pointer pointer) {
		super(pointer);
	}
}
