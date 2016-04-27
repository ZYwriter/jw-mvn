package org.bridj.objc;
import static org.bridj.Pointer.pointerTo;

import org.bridj.BridJ;
import org.bridj.Pointer;
import org.bridj.ann.Library;

@Library("Foundation")
public class NSNumber extends NSObject {
    static {
        BridJ.register();
    }

	public static native Pointer<NSNumber> numberWithBool(boolean value);
	public static native Pointer<NSNumber> numberWithInt(int value);
	public static native Pointer<NSNumber> numberWithDouble(double e);
	public static native Pointer<NSNumber> numberWithLong(long value);
	public static native Pointer<NSNumber> numberWithFloat(float value);
	
	public native short shortValue();
	public native int intValue();
	public native long longValue();
	public native float floatValue();
	public native double doubleValue();
	public native int compare(Pointer<NSNumber> another);
	
	public native boolean isEqualToNumber(Pointer<NSNumber> another);
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof NSNumber))
			return false;
		
		NSNumber nn = (NSNumber)o;
		return isEqualToNumber(pointerTo(nn));
	}
}
