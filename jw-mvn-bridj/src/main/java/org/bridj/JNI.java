package org.bridj;

import static org.bridj.Pointer.pointerToAddress;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * Low-level calls to JNI and to BridJ's native library.
 * @author ochafik
 * @deprecated These methods can cause serious issues (segmentation fault, system crashes) if used without care : there are little to no checks performed on the arguments.
 */
@Deprecated
public class JNI {
	static {
		Platform.initLibrary();
	}
    @Deprecated
    public static native long getEnv();
    @Deprecated
    public static native long getJVM();
    @Deprecated
    public static native Object refToObject(long refPeer);
    
    static native long loadLibrary(String path);
    static native void freeLibrary(long libHandle);
    static native long loadLibrarySymbols(String libPath);
    static native void freeLibrarySymbols(long symbolsHandle);
    static native long findSymbolInLibrary(long libHandle, String name);
    static native String[] getLibrarySymbols(long libHandle, long symbolsHandle);
    static native String findSymbolName(long libHandle, long symbolsHandle, long address);

    /**
     * Create a JNI global reference to a Java object : long value that can be safely passed to C programs and stored, which prevent the object from being garbage-collected and which validity runs until {@link JNI#deleteGlobalRef(long)} is called
     */
	public static native long newGlobalRef(Object object);
	/**
     * Delete a global reference created by {@link JNI#newGlobalRef(java.lang.Object)}
     */
	public static native void deleteGlobalRef(long reference);
    
	public static Pointer<?> getGlobalPointer(Object object) {
		return pointerToAddress(newGlobalRef(object), new Pointer.Releaser() {
			public void release(Pointer<?> p) {
				deleteGlobalRef(p.getPeer());
			}
		});
	}
	
	/**
     * Create a JNI weak global reference to a Java object : long value that can be safely passed to C programs and stored, which validity runs until {@link JNI#deleteWeakGlobalRef(long)} is called.<br>
     * Unlike global references, weak global references don't prevent objects from being garbage-collected.
     */
	public static native long newWeakGlobalRef(Object object);
	/**
     * Delete a weak global reference created by {@link JNI#newWeakGlobalRef(java.lang.Object)}
     */
	public static native void deleteWeakGlobalRef(long reference);

    /**
     * Wrap a native address as a direct byte buffer of the specified byte capacity.<br>
     * Memory is not reclaimed when the buffer is garbage-collected.
     */
    public static native ByteBuffer newDirectByteBuffer(long address, long capacity);
    /**
     * Get the native address pointed to by a direct buffer.
     */
    public static native long getDirectBufferAddress(Buffer b);
    /**
     * Get the capacity in bytes of a direct buffer.
     */
    public static native long getDirectBufferCapacity(Buffer b);


    @Deprecated
    static native long getIntArrayElements(int[] array, boolean[] pIsCopy);
    @Deprecated
    static native void releaseIntArrayElements(int[] array, long pointer, int mode);

    @Deprecated
    static native int get_int(long peer);
    @Deprecated
    static native void set_int(long peer, int value);
    @Deprecated
    static native int[] get_int_array(long peer, int length);
    @Deprecated
    static native void set_int_array(long peer, int[] values, int valuesOffset, int length);

		@Deprecated
    static native int get_int_disordered(long peer);
	@Deprecated
    static native void set_int_disordered(long peer, int value);
    @Deprecated
    static native int[] get_int_array_disordered(long peer, int length);
	@Deprecated
    static native void set_int_array_disordered(long peer, int[] values, int valuesOffset, int length);
	
    @Deprecated
    static native long getLongArrayElements(long[] array, boolean[] pIsCopy);
    @Deprecated
    static native void releaseLongArrayElements(long[] array, long pointer, int mode);

    @Deprecated
    static native long get_long(long peer);
    @Deprecated
    static native void set_long(long peer, long value);
    @Deprecated
    static native long[] get_long_array(long peer, int length);
    @Deprecated
    static native void set_long_array(long peer, long[] values, int valuesOffset, int length);

		@Deprecated
    static native long get_long_disordered(long peer);
	@Deprecated
    static native void set_long_disordered(long peer, long value);
    @Deprecated
    static native long[] get_long_array_disordered(long peer, int length);
	@Deprecated
    static native void set_long_array_disordered(long peer, long[] values, int valuesOffset, int length);
	
    @Deprecated
    static native long getShortArrayElements(short[] array, boolean[] pIsCopy);
    @Deprecated
    static native void releaseShortArrayElements(short[] array, long pointer, int mode);

    @Deprecated
    static native short get_short(long peer);
    @Deprecated
    static native void set_short(long peer, short value);
    @Deprecated
    static native short[] get_short_array(long peer, int length);
    @Deprecated
    static native void set_short_array(long peer, short[] values, int valuesOffset, int length);

		@Deprecated
    static native short get_short_disordered(long peer);
	@Deprecated
    static native void set_short_disordered(long peer, short value);
    @Deprecated
    static native short[] get_short_array_disordered(long peer, int length);
	@Deprecated
    static native void set_short_array_disordered(long peer, short[] values, int valuesOffset, int length);
	
    @Deprecated
    static native long getByteArrayElements(byte[] array, boolean[] pIsCopy);
    @Deprecated
    static native void releaseByteArrayElements(byte[] array, long pointer, int mode);

    @Deprecated
    static native byte get_byte(long peer);
    @Deprecated
    static native void set_byte(long peer, byte value);
    @Deprecated
    static native byte[] get_byte_array(long peer, int length);
    @Deprecated
    static native void set_byte_array(long peer, byte[] values, int valuesOffset, int length);

	
    @Deprecated
    static native long getCharArrayElements(char[] array, boolean[] pIsCopy);
    @Deprecated
    static native void releaseCharArrayElements(char[] array, long pointer, int mode);

    @Deprecated
    static native char get_char(long peer);
    @Deprecated
    static native void set_char(long peer, char value);
    @Deprecated
    static native char[] get_char_array(long peer, int length);
    @Deprecated
    static native void set_char_array(long peer, char[] values, int valuesOffset, int length);

		@Deprecated
    static native char get_char_disordered(long peer);
	@Deprecated
    static native void set_char_disordered(long peer, char value);
    @Deprecated
    static native char[] get_char_array_disordered(long peer, int length);
	@Deprecated
    static native void set_char_array_disordered(long peer, char[] values, int valuesOffset, int length);
	
    @Deprecated
    static native long getFloatArrayElements(float[] array, boolean[] pIsCopy);
    @Deprecated
    static native void releaseFloatArrayElements(float[] array, long pointer, int mode);

    @Deprecated
    static native float get_float(long peer);
    @Deprecated
    static native void set_float(long peer, float value);
    @Deprecated
    static native float[] get_float_array(long peer, int length);
    @Deprecated
    static native void set_float_array(long peer, float[] values, int valuesOffset, int length);

		@Deprecated
    static native float get_float_disordered(long peer);
	@Deprecated
    static native void set_float_disordered(long peer, float value);
    @Deprecated
    static native float[] get_float_array_disordered(long peer, int length);
	@Deprecated
    static native void set_float_array_disordered(long peer, float[] values, int valuesOffset, int length);
	
    @Deprecated
    static native long getDoubleArrayElements(double[] array, boolean[] pIsCopy);
    @Deprecated
    static native void releaseDoubleArrayElements(double[] array, long pointer, int mode);

    @Deprecated
    static native double get_double(long peer);
    @Deprecated
    static native void set_double(long peer, double value);
    @Deprecated
    static native double[] get_double_array(long peer, int length);
    @Deprecated
    static native void set_double_array(long peer, double[] values, int valuesOffset, int length);

		@Deprecated
    static native double get_double_disordered(long peer);
	@Deprecated
    static native void set_double_disordered(long peer, double value);
    @Deprecated
    static native double[] get_double_array_disordered(long peer, int length);
	@Deprecated
    static native void set_double_array_disordered(long peer, double[] values, int valuesOffset, int length);
	
    @Deprecated
    static native long getBooleanArrayElements(boolean[] array, boolean[] pIsCopy);
    @Deprecated
    static native void releaseBooleanArrayElements(boolean[] array, long pointer, int mode);

    @Deprecated
    static native boolean get_boolean(long peer);
    @Deprecated
    static native void set_boolean(long peer, boolean value);
    @Deprecated
    static native boolean[] get_boolean_array(long peer, int length);
    @Deprecated
    static native void set_boolean_array(long peer, boolean[] values, int valuesOffset, int length);

	
	public static native void callSinglePointerArgVoidFunction(long functionPointer, long pointerArg, int callMode);
	
	static native long createCToJavaCallback(MethodCallInfo info);
	static native long getActualCToJavaCallback(long handle);
	
	static native long bindJavaMethodsToObjCMethods(MethodCallInfo... infos);
	static native long bindJavaToCCallbacks(MethodCallInfo... infos);
	static native long bindJavaMethodsToCFunctions(MethodCallInfo... infos);
	static native long bindJavaMethodsToVirtualMethods(MethodCallInfo... infos);
	
	static native void freeCToJavaCallback(long handle);
	static native void freeObjCMethodBindings(long handle, int size);
	static native void freeJavaToCCallbacks(long handle, int size);
	static native void freeCFunctionBindings(long handle, int size);
	static native void freeVirtualMethodBindings(long handle, int size);
	
	static native long createCallTempStruct();
	static native void deleteCallTempStruct(long handle);
	
	static native long mallocNulled(long size);
	static native long mallocNulledAligned(long size, int alignment);
	
	static native long malloc(long size);
    static native void free(long pointer);
    static native long strlen(long pointer);
    static native long wcslen(long pointer);
    static native void memcpy(long dest, long source, long size);
    static native void memmove(long dest, long source, long size);
    static native long memchr(long ptr, byte value, long num);
    static native long memmem(long haystack, long haystackLength, long needle, long needleLength);
    static native long memmem_last(long haystack, long haystackLength, long needle, long needleLength);
    static native int memcmp(long ptr1, long ptr2, long num);
    static native void memset(long ptr, byte value, long num);
}
