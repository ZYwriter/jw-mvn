package org.bridj;

/**
 * Native error encapsulated as a Java error.
 * @author ochafik
 */
public abstract class NativeError extends Error {
    protected NativeError(String message) {
        super(message);
    }
    static String toHex(long address) {
        return "0x" + Long.toHexString(address);
    }
}
