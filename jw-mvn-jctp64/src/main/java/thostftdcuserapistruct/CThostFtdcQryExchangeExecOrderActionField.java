package thostftdcuserapistruct;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Array;
import org.bridj.ann.Field;
/**
 * \u4ea4\u6613\u6240\u6267\u884c\u5ba3\u544a\u64cd\u4f5c\u67e5\u8be2<br>
 * <i>native declaration : F:\jna\ThostFtdcUserApiStruct.h:3003</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */

 
public class CThostFtdcQryExchangeExecOrderActionField extends StructObject {
	public CThostFtdcQryExchangeExecOrderActionField() {
		super();
	}
	/**
	 * \u4f1a\u5458\u4ee3\u7801<br>
	 * C type : TThostFtdcParticipantIDType
	 */
	@Array({11}) 
	@Field(0) 
	public Pointer<Byte > ParticipantID() {
		return this.io.getPointerField(this, 0);
	}
	/**
	 * \u5ba2\u6237\u4ee3\u7801<br>
	 * C type : TThostFtdcClientIDType
	 */
	@Array({11}) 
	@Field(1) 
	public Pointer<Byte > ClientID() {
		return this.io.getPointerField(this, 1);
	}
	/**
	 * \u4ea4\u6613\u6240\u4ee3\u7801<br>
	 * C type : TThostFtdcExchangeIDType
	 */
	@Array({9}) 
	@Field(2) 
	public Pointer<Byte > ExchangeID() {
		return this.io.getPointerField(this, 2);
	}
	/**
	 * \u4ea4\u6613\u6240\u4ea4\u6613\u5458\u4ee3\u7801<br>
	 * C type : TThostFtdcTraderIDType
	 */
	@Array({21}) 
	@Field(3) 
	public Pointer<Byte > TraderID() {
		return this.io.getPointerField(this, 3);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CThostFtdcQryExchangeExecOrderActionField(Pointer pointer) {
		super(pointer);
	}
}
