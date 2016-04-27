package thostftdcuserapistruct;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
/**
 * \u4fe1\u606f\u5206\u53d1<br>
 * <i>native declaration : F:\jna\ThostFtdcUserApiStruct.h:4</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */

 
public class CThostFtdcDisseminationField extends StructObject {
	public CThostFtdcDisseminationField() {
		super();
	}
	/**
	 * \u5e8f\u5217\u53f7<br>
	 * C type : TThostFtdcSequenceNoType
	 */
	@Field(1) 
	public int SequenceNo() {
		return this.io.getIntField(this, 1);
	}
	/**
	 * \u5e8f\u5217\u53f7<br>
	 * C type : TThostFtdcSequenceNoType
	 */
	@Field(1) 
	public CThostFtdcDisseminationField SequenceNo(int SequenceNo) {
		this.io.setIntField(this, 1, SequenceNo);
		return this;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CThostFtdcDisseminationField(Pointer pointer) {
		super(pointer);
	}
}