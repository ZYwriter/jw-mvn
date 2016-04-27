package org.bridj.cpp.com;

import static org.bridj.cpp.com.COMRuntime.S_OK;
import static org.bridj.cpp.com.COMRuntime.getIID;

import org.bridj.Pointer;
import org.bridj.ann.Virtual;

@IID("00000001-0000-0000-C000-000000000046")
public class IClassFactory extends IUnknown
{
	@Virtual(0) // TODO handle in runtime not as zero but as count of parents' virtual methods + 0
	@Deprecated
	public native int CreateInstance( 
		Pointer<IUnknown> pUnkOuter,
		Pointer<Byte> riid,
		Pointer<Pointer<IUnknown>> ppvObject
	);
	
	public <I extends IUnknown> I CreateInstance(Class<I> type) {
		Pointer<Pointer<IUnknown>> p = Pointer.allocatePointer(IUnknown.class);
		int ret = CreateInstance(null, getIID(type), p);
		if (ret != S_OK)
			return null;
		
		return p.get().getNativeObject(type);
	}
	
	@Virtual(1)
	public native int LockServer(boolean fLock);
}