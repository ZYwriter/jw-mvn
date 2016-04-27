package org.bridj.objc;
import org.bridj.BridJ;
import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Ptr;

@Library("Foundation")
public class NSInvocation extends NSObject {
	static { BridJ.register(); }

    public native SEL selector();
    public native void setSelector(SEL selector);
    
    public native Pointer<? extends ObjCObject> target();
    public native void setTarget(Pointer<? extends ObjCObject> target);
    
    public native void setArgument_atIndex(Pointer<?> buffer, @Ptr long index);
    public native void getArgument_atIndex(Pointer<?> buffer, @Ptr long index);
    
    public native void setReturnValue(Pointer<?> buffer);
    public native void getReturnValue(Pointer<?> buffer);
    
}
