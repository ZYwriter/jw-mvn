package org.bridj.objc;
import org.bridj.BridJ;
import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Ptr;

@Library("Foundation")
public class NSMethodSignature extends NSObject {
	static { BridJ.register(); }

	public static native Pointer<NSMethodSignature> signatureWithObjCTypes(Pointer<Byte> types);	
    
    public native Pointer<Byte> methodReturnType();
    public native @Ptr long numberOfArguments();
    public native boolean isOneway();
    public native Pointer<Byte> getArgumentTypeAtIndex(@Ptr long index);
    public native @Ptr long frameLength();
}
