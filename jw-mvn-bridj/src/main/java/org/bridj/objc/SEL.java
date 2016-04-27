package org.bridj.objc;
import static org.bridj.objc.ObjectiveCRuntime.sel_getName;
import static org.bridj.objc.ObjectiveCRuntime.sel_registerName;

import org.bridj.Pointer;
import org.bridj.TypedPointer;

public class SEL extends TypedPointer {
		public SEL(long peer) { super(peer); }
		public SEL(Pointer<?> ptr) { super(ptr); }
		
		public static SEL valueOf(String name) {
			return sel_registerName(pointerToCString(name));
		}
		
		protected volatile String name;
		public String getName() {
			if (name == null)
				name = sel_getName(this).getCString();
			return name;
		}
		public String toString() {
			return "@selector(" + getName() + ")";
		}
		public boolean equals(Object o) {
			if (!(o instanceof SEL))
				return false;
			return getName().equals(((SEL)o).getName());
		}
		public int hashCode() {
			return getName().hashCode();
		}
}

