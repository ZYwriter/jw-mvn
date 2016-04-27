package org.bridj.objc;
import org.bridj.Pointer;
import org.bridj.TypedPointer;

public class IMP extends TypedPointer {
		public IMP(long peer) { super(peer); }
		public IMP(Pointer<?> ptr) { super(ptr); }
}

