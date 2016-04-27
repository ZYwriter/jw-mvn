package org.bridj;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

import org.bridj.util.Utils;

/**
 * Helper class that knows how to read/write data from/to a {@link Pointer}.<br>
 * End users don't need to use this class directly as ({@link Pointer} lets you work with {@link java.lang.reflect.Type} and {@link Class}).
 * @author Olivier
 */
public abstract class PointerIO<T> {
	final Type targetType;
	final Class<?> typedPointerClass;
	final int targetSize, targetAlignment = -1;
	
	public PointerIO(Type targetType, int targetSize, Class<?> typedPointerClass) {
		this.targetType = targetType;
		this.targetSize = targetSize;
		this.typedPointerClass = typedPointerClass;
	}
	abstract T get(Pointer<T> pointer, long index);
	abstract void set(Pointer<T> pointer, long index, T value);
	public Object getArray(Pointer<T> pointer, long byteOffset, int length) {
		return pointer.offset(byteOffset).toArray(length);
	}
	public <B extends Buffer> B getBuffer(Pointer<T> pointer, long byteOffset, int length) {
		throw new UnsupportedOperationException("Cannot create a Buffer instance of elements of type " + getTargetType());
	}
	public void setArray(Pointer<T> pointer, long byteOffset, Object array) {
		Object[] a = (Object[])array;
		for (int i = 0, n = a.length; i < n; i++)
			set(pointer, i, (T)a[i]);
	}
	
	public T castTarget(long peer) {
		throw new UnsupportedOperationException("Cannot cast pointer to " + targetType);
	}
	
	PointerIO<Pointer<T>> getReferenceIO() {
		return new CommonPointerIOs.PointerPointerIO<T>(this);
	}
	public long getTargetSize() {
		return targetSize;
	}
	public long getTargetAlignment() { 
		return targetAlignment < 0 ? getTargetSize() : targetAlignment;
	}
	public boolean isTypedPointer() {
		return typedPointerClass != null;
	}
	public Class<?> getTypedPointerClass() {
		return typedPointerClass;
	}
	public Type getTargetType() {
		return targetType;
	}
	
	static Class<?> getClass(Type type) {
		if (type instanceof Class<?>)
			return (Class<?>)type;
		if (type instanceof ParameterizedType)
			return getClass(((ParameterizedType)type).getRawType());
		return null;
	}
	
	public static <T> PointerIO<Pointer<T>> getPointerInstance(Type target) {
		return getPointerInstance((PointerIO<T>)getInstance(target));
	}
	public static <T> PointerIO<Pointer<T>> getPointerInstance(PointerIO<T> targetIO) {
		return new CommonPointerIOs.PointerPointerIO<T>(targetIO);
	}
	public static <T> PointerIO<Pointer<T>> getArrayInstance(PointerIO<T> targetIO, long[] dimensions, int iDimension) {
		return new CommonPointerIOs.PointerArrayIO<T>(targetIO, dimensions, iDimension);
	}
	
	static <T> PointerIO<T> getArrayIO(Object array) {
        		if (array instanceof int[])
			return (PointerIO)PointerIO.getIntInstance();
				if (array instanceof long[])
			return (PointerIO)PointerIO.getLongInstance();
				if (array instanceof short[])
			return (PointerIO)PointerIO.getShortInstance();
				if (array instanceof byte[])
			return (PointerIO)PointerIO.getByteInstance();
				if (array instanceof char[])
			return (PointerIO)PointerIO.getCharInstance();
				if (array instanceof float[])
			return (PointerIO)PointerIO.getFloatInstance();
				if (array instanceof double[])
			return (PointerIO)PointerIO.getDoubleInstance();
				if (array instanceof boolean[])
			return (PointerIO)PointerIO.getBooleanInstance();
				return PointerIO.getInstance(array.getClass().getComponentType());
	}   
	
	private static final ConcurrentHashMap<StructIO, PointerIO<?>> structIOs = new ConcurrentHashMap<StructIO, PointerIO<?>>();
	public static <S extends StructObject> PointerIO<S> getInstance(StructIO s) {
        PointerIO io = structIOs.get(s);
        if (io == null) {
            io = new CommonPointerIOs.StructPointerIO(s);
            PointerIO previousIO = structIOs.putIfAbsent(s, io);
            if (previousIO != null)
                io = previousIO;
        }
        return io;
    }
    private static final ConcurrentHashMap<Type, PointerIO<?>> ios = new ConcurrentHashMap<Type, PointerIO<?>>();
	public static <P> PointerIO<P> getInstance(Type type) {
        if (type == null)
            return null;
        
		PointerIO io = ios.get(type);
        if (io == null) {
            final Class<?> cl = Utils.getClass(type);
    	
                                    if (type == Integer.TYPE || type == Integer.class)
                io = CommonPointerIOs.intIO;
                         else             if (type == Long.TYPE || type == Long.class)
                io = CommonPointerIOs.longIO;
                         else             if (type == Short.TYPE || type == Short.class)
                io = CommonPointerIOs.shortIO;
                         else             if (type == Byte.TYPE || type == Byte.class)
                io = CommonPointerIOs.byteIO;
                         else             if (type == Character.TYPE || type == Character.class)
                io = CommonPointerIOs.charIO;
                         else             if (type == Float.TYPE || type == Float.class)
                io = CommonPointerIOs.floatIO;
                         else             if (type == Double.TYPE || type == Double.class)
                io = CommonPointerIOs.doubleIO;
                         else             if (type == Boolean.TYPE || type == Boolean.class)
                io = CommonPointerIOs.booleanIO;
                        else if (cl != null) {
            	    if (TypedPointer.class.isAssignableFrom(cl))
					io = new CommonPointerIOs.TypedPointerPointerIO((Class<? extends TypedPointer>)cl);
				else if (Pointer.class.isAssignableFrom(cl)) {
					if (Pointer.class.equals(type) || !(type instanceof ParameterizedType))
						io = getPointerInstance((PointerIO<?>)null);
					else
						io = getPointerInstance(((ParameterizedType)type).getActualTypeArguments()[0]);
				}
				else if (SizeT.class.isAssignableFrom(cl))
					io = CommonPointerIOs.sizeTIO;
				else if (TimeT.class.isAssignableFrom(cl))
					io = CommonPointerIOs.timeTIO;
				else if (CLong.class.isAssignableFrom(cl))
					io = CommonPointerIOs.clongIO;
				else if (StructObject.class.isAssignableFrom(cl))
					io = getInstance(StructIO.getInstance((Class)cl, type));
				else if (Callback.class.isAssignableFrom(cl))
					io = new CommonPointerIOs.CallbackPointerIO(cl);
				else if (NativeObject.class.isAssignableFrom(cl))
					io = new CommonPointerIOs.NativeObjectPointerIO(type);
				else if (IntValuedEnum.class.isAssignableFrom(cl)) {
					if (type instanceof ParameterizedType) {
						Type enumType = ((ParameterizedType)type).getActualTypeArguments()[0];
						if (enumType instanceof Class)
							io = new CommonPointerIOs.IntValuedEnumPointerIO((Class)enumType);
					}
				}
			}
            //else
            //throw new UnsupportedOperationException("Cannot create pointer io to type " + type + ((type instanceof Class) && ((Class)type).getSuperclass() != null ? " (parent type : " + ((Class)type).getSuperclass().getName() + ")" : ""));
            	//return null; // TODO throw here ?

            //if (io == null)
            //	throw new RuntimeException("Failed to create pointer io to type " + type);
            if (io != null) {
                PointerIO previousIO = ios.putIfAbsent(type, io);
                if (previousIO != null)
                    io = previousIO; // created io twice : not important in general (expecially not compared to cost of contention on non-concurrent map)
            }
        }
        return io;
    }

    private static PointerIO atomicInstance(AtomicReference ref, Type type) {
        PointerIO io = (PointerIO)ref.get();
        if (io != null)
            return io;

        if (ref.compareAndSet(null, io = getInstance(type)))
            return io;
               
        return (PointerIO)ref.get();
    }

    private static final AtomicReference<PointerIO<Integer>> intInstance = new AtomicReference<PointerIO<Integer>>();
	public static PointerIO<Integer> getIntInstance() {
        return atomicInstance(intInstance, Integer.class);
    }
    /*    PointerIO<Integer> io = intInstance.get();
        if (io != null)
            return io;

        if (intInstance.compareAndSet(null, io = getInstance(Integer.class)))
            return io;
               
        return intInstance.get();
	}*/
    private static final AtomicReference<PointerIO<Long>> longInstance = new AtomicReference<PointerIO<Long>>();
	public static PointerIO<Long> getLongInstance() {
        return atomicInstance(longInstance, Long.class);
    }
    /*    PointerIO<Long> io = longInstance.get();
        if (io != null)
            return io;

        if (longInstance.compareAndSet(null, io = getInstance(Long.class)))
            return io;
               
        return longInstance.get();
	}*/
    private static final AtomicReference<PointerIO<Short>> shortInstance = new AtomicReference<PointerIO<Short>>();
	public static PointerIO<Short> getShortInstance() {
        return atomicInstance(shortInstance, Short.class);
    }
    /*    PointerIO<Short> io = shortInstance.get();
        if (io != null)
            return io;

        if (shortInstance.compareAndSet(null, io = getInstance(Short.class)))
            return io;
               
        return shortInstance.get();
	}*/
    private static final AtomicReference<PointerIO<Byte>> byteInstance = new AtomicReference<PointerIO<Byte>>();
	public static PointerIO<Byte> getByteInstance() {
        return atomicInstance(byteInstance, Byte.class);
    }
    /*    PointerIO<Byte> io = byteInstance.get();
        if (io != null)
            return io;

        if (byteInstance.compareAndSet(null, io = getInstance(Byte.class)))
            return io;
               
        return byteInstance.get();
	}*/
    private static final AtomicReference<PointerIO<Character>> charInstance = new AtomicReference<PointerIO<Character>>();
	public static PointerIO<Character> getCharInstance() {
        return atomicInstance(charInstance, Character.class);
    }
    /*    PointerIO<Character> io = charInstance.get();
        if (io != null)
            return io;

        if (charInstance.compareAndSet(null, io = getInstance(Character.class)))
            return io;
               
        return charInstance.get();
	}*/
    private static final AtomicReference<PointerIO<Float>> floatInstance = new AtomicReference<PointerIO<Float>>();
	public static PointerIO<Float> getFloatInstance() {
        return atomicInstance(floatInstance, Float.class);
    }
    /*    PointerIO<Float> io = floatInstance.get();
        if (io != null)
            return io;

        if (floatInstance.compareAndSet(null, io = getInstance(Float.class)))
            return io;
               
        return floatInstance.get();
	}*/
    private static final AtomicReference<PointerIO<Double>> doubleInstance = new AtomicReference<PointerIO<Double>>();
	public static PointerIO<Double> getDoubleInstance() {
        return atomicInstance(doubleInstance, Double.class);
    }
    /*    PointerIO<Double> io = doubleInstance.get();
        if (io != null)
            return io;

        if (doubleInstance.compareAndSet(null, io = getInstance(Double.class)))
            return io;
               
        return doubleInstance.get();
	}*/
    private static final AtomicReference<PointerIO<Boolean>> booleanInstance = new AtomicReference<PointerIO<Boolean>>();
	public static PointerIO<Boolean> getBooleanInstance() {
        return atomicInstance(booleanInstance, Boolean.class);
    }
    /*    PointerIO<Boolean> io = booleanInstance.get();
        if (io != null)
            return io;

        if (booleanInstance.compareAndSet(null, io = getInstance(Boolean.class)))
            return io;
               
        return booleanInstance.get();
	}*/
    private static final AtomicReference<PointerIO<CLong>> CLongInstance = new AtomicReference<PointerIO<CLong>>();
	public static PointerIO<CLong> getCLongInstance() {
        return atomicInstance(CLongInstance, CLong.class);
    }
    /*    PointerIO<CLong> io = CLongInstance.get();
        if (io != null)
            return io;

        if (CLongInstance.compareAndSet(null, io = getInstance(CLong.class)))
            return io;
               
        return CLongInstance.get();
	}*/
    private static final AtomicReference<PointerIO<SizeT>> SizeTInstance = new AtomicReference<PointerIO<SizeT>>();
	public static PointerIO<SizeT> getSizeTInstance() {
        return atomicInstance(SizeTInstance, SizeT.class);
    }
    /*    PointerIO<SizeT> io = SizeTInstance.get();
        if (io != null)
            return io;

        if (SizeTInstance.compareAndSet(null, io = getInstance(SizeT.class)))
            return io;
               
        return SizeTInstance.get();
	}*/
    private static final AtomicReference<PointerIO<Pointer>> PointerInstance = new AtomicReference<PointerIO<Pointer>>();
	public static PointerIO<Pointer> getPointerInstance() {
        return atomicInstance(PointerInstance, Pointer.class);
    }
    /*    PointerIO<Pointer> io = PointerInstance.get();
        if (io != null)
            return io;

        if (PointerInstance.compareAndSet(null, io = getInstance(Pointer.class)))
            return io;
               
        return PointerInstance.get();
	}*/
	
    public static <P> PointerIO<P> getBufferPrimitiveInstance(Buffer buffer) {
        		if (buffer instanceof IntBuffer)
            return (PointerIO<P>)getIntInstance();
				if (buffer instanceof LongBuffer)
            return (PointerIO<P>)getLongInstance();
				if (buffer instanceof ShortBuffer)
            return (PointerIO<P>)getShortInstance();
				if (buffer instanceof ByteBuffer)
            return (PointerIO<P>)getByteInstance();
				if (buffer instanceof CharBuffer)
            return (PointerIO<P>)getCharInstance();
				if (buffer instanceof FloatBuffer)
            return (PointerIO<P>)getFloatInstance();
				if (buffer instanceof DoubleBuffer)
            return (PointerIO<P>)getDoubleInstance();
		        throw new UnsupportedOperationException();
    }

    private static final AtomicReference<PointerIO> stringInstance = new AtomicReference<PointerIO>();
    public static PointerIO<String> getStringInstance() {
        return atomicInstance(stringInstance, String.class);/*
        PointerIO io = stringInstance.get();
        if (io != null)
            return io;

        if (stringInstance.compareAndSet(null, io = getInstance(String.class)))
            return io;
               
        return stringInstance.get();*/
    }

}
