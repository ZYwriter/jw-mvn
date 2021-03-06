package thostftdcuserapistruct;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Array;
import org.bridj.ann.Field;
/**
 * \u67e5\u8be2\u4ea7\u54c1<br>
 * <i>native declaration : F:\jna\ThostFtdcUserApiStruct.h:2388</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */

 
public class CThostFtdcQryProductField extends StructObject {
	public CThostFtdcQryProductField() {
		super();
	}
	/**
	 * \u4ea7\u54c1\u4ee3\u7801<br>
	 * C type : TThostFtdcInstrumentIDType
	 */
	@Array({31}) 
	@Field(0) 
	public Pointer<Byte > ProductID() {
		return this.io.getPointerField(this, 0);
	}
	/**
	 * \u4ea7\u54c1\u7c7b\u578b<br>
	 * C type : TThostFtdcProductClassType
	 */
	@Field(1) 
	public byte ProductClass() {
		return this.io.getByteField(this, 1);
	}
	/**
	 * \u4ea7\u54c1\u7c7b\u578b<br>
	 * C type : TThostFtdcProductClassType
	 */
	@Field(1) 
	public CThostFtdcQryProductField ProductClass(byte ProductClass) {
		this.io.setByteField(this, 1, ProductClass);
		return this;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CThostFtdcQryProductField(Pointer pointer) {
		super(pointer);
	}
}
