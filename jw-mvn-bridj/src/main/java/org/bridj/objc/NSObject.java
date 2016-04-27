package org.bridj.objc;

import org.bridj.Pointer;
import org.bridj.ann.Library;

@Library("Foundation")
public class NSObject extends ObjCObject {
    public NSObject(Pointer<? extends NSObject> peer) {
        super(peer);
    }

    public NSObject() {
        super();
    }

    
}
