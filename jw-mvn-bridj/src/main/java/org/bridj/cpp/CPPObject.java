/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bridj.cpp;

import java.util.Map;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Runtime;

/**
 * Base class for C++ structs and classes.
 * @author Olivier
 */
@Runtime(CPPRuntime.class)
public abstract class CPPObject extends StructObject {
	Map<Class<?>, Object[]> templateParameters;
	
	protected CPPObject() {}
    protected CPPObject(Pointer<? extends CPPObject> peer) {
        super(peer);
    }
    protected CPPObject(Void voidArg, int constructorId, Object... args) {
        super(voidArg, constructorId, args);
    }
    /*
    @Override
    protected void finalize() throws Throwable {
    		BridJ.deallocate(this);
    }*/
}
