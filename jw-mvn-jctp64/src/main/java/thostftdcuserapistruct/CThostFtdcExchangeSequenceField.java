package thostftdcuserapistruct;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Array;
import org.bridj.ann.Field;
/**
 * \u4ea4\u6613\u6240\u72b6\u6001<br>
 * <i>native declaration : F:\jna\ThostFtdcUserApiStruct.h:5103</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */

 
public class CThostFtdcExchangeSequenceField extends StructObject {
	public CThostFtdcExchangeSequenceField() {
		super();
	}
	/**
	 * \u4ea4\u6613\u6240\u4ee3\u7801<br>
	 * C type : TThostFtdcExchangeIDType
	 */
	@Array({9}) 
	@Field(0) 
	public Pointer<Byte > ExchangeID() {
		return this.io.getPointerField(this, 0);
	}
	/**
	 * \u5e8f\u53f7<br>
	 * C type : TThostFtdcSequenceNoType
	 */
	@Field(1) 
	public int SequenceNo() {
		return this.io.getIntField(this, 1);
	}
	/**
	 * \u5e8f\u53f7<br>
	 * C type : TThostFtdcSequenceNoType
	 */
	@Field(1) 
	public CThostFtdcExchangeSequenceField SequenceNo(int SequenceNo) {
		this.io.setIntField(this, 1, SequenceNo);
		return this;
	}
	/**
	 * \u5408\u7ea6\u4ea4\u6613\u72b6\u6001<br>
	 * C type : TThostFtdcInstrumentStatusType
	 */
	@Field(2) 
	public byte MarketStatus() {
		return this.io.getByteField(this, 2);
	}
	/**
	 * \u5408\u7ea6\u4ea4\u6613\u72b6\u6001<br>
	 * C type : TThostFtdcInstrumentStatusType
	 */
	@Field(2) 
	public CThostFtdcExchangeSequenceField MarketStatus(byte MarketStatus) {
		this.io.setByteField(this, 2, MarketStatus);
		return this;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CThostFtdcExchangeSequenceField(Pointer pointer) {
		super(pointer);
	}
}