package org.bridj;
import static org.bridj.util.DefaultParameterizedType.paramType;

import java.lang.reflect.Type;
import java.nio.Buffer;

class CommonPointerIOs {

	static class NativeObjectPointerIO<N extends NativeObject> extends PointerIO<N> {
		protected volatile long targetSize = -1, targetAlignment = -1;
		protected Type nativeObjectType;
		public NativeObjectPointerIO(Type nativeObjectType) {
			super(nativeObjectType, -1, null);
			this.nativeObjectType = nativeObjectType;
		}


		protected long computeTargetSize() {
			return BridJ.sizeOf(nativeObjectType);
		}
		protected long computeTargetAlignment() {
			return getTargetSize();
		}
        @Override
        public long getTargetSize() {
            if (targetSize < 0)
                targetSize = computeTargetSize();
                
            return targetSize;
        }
        @Override
        public long getTargetAlignment() {
            if (targetAlignment < 0)
                targetAlignment = computeTargetAlignment();
                
            return targetAlignment;
        }
		
		@Override
		public N get(Pointer<N> pointer, long index) {
			return (N)pointer.getNativeObjectAtOffset(index * getTargetSize(), nativeObjectType);
		}
		@Override
		public void set(Pointer<N> pointer, long index, N value) {
			Pointer<N> ps = Pointer.pointerTo(value);
			ps.copyTo(pointer.offset(index * getTargetSize()));
		}
	}
	static class StructPointerIO<S extends StructObject> extends NativeObjectPointerIO<S> {
		final StructIO structIO;
		public StructPointerIO(StructIO structIO) {
			super(structIO.getStructType());
			this.structIO = structIO;
		}
		
		@Override
        protected long computeTargetSize() {
			structIO.build();
            return structIO.getStructSize();
		}
		@Override
        protected long computeTargetAlignment() {
			structIO.build();
            return structIO.getStructAlignment();
		}
	}
	
	static class PointerPointerIO<T> extends PointerIO<Pointer<T>> {
		final PointerIO<T> underlyingIO;

		public PointerPointerIO(PointerIO<T> underlyingIO) {
			super(underlyingIO == null ? Pointer.class : paramType(Pointer.class, new Type[] {underlyingIO.getTargetType()}), Pointer.SIZE, null);
			this.underlyingIO = underlyingIO;
		}
		
		@Override
		public Pointer<T> get(Pointer<Pointer<T>> pointer, long index) {
			return pointer.getPointerAtOffset(index * Pointer.SIZE, underlyingIO);
		}

		@Override
		public void set(Pointer<Pointer<T>> pointer, long index, Pointer<T> value) {
			pointer.setPointerAtOffset(index * Pointer.SIZE, value);
		}
	}
	
	static class PointerArrayIO<T> extends PointerIO<Pointer<T>> {
		final PointerIO<T> underlyingIO;
		final long[] dimensions;
		final long totalRemainingDims;
		final int iDimension;

		static Type arrayPtrType(Type elementType, long... dimensions) {
			Type type = elementType;
			for (int i = 0; i < dimensions.length; i++)
				type = paramType(Pointer.class, type);
			return type;
		}
		static long getTotalRemainingDims(long[] dimensions, int iDimension) {
			long d = 1;
			for (int i = iDimension + 1; i < dimensions.length; i++)
				d *= dimensions[i];
			return d;
		}
		
		public PointerArrayIO(PointerIO<T> underlyingIO, long[] dimensions, int iDimension) {
			super(
				//underlyingIO.getTargetType(),//
				underlyingIO == null ? null : arrayPtrType(underlyingIO.getTargetType(), dimensions), 
				-1, 
				null
			);
			//if (iDimension >= dimensions.length) {
				this.underlyingIO = underlyingIO;
			/*} else {
				this.underlyingIO = new PointerArrayIO(underlyingIO, dimensions, iDimension + 1);
			}*/
			this.dimensions = dimensions;
			this.iDimension = iDimension;
			totalRemainingDims = getTotalRemainingDims(dimensions, iDimension);
		}
		
		@Override
		public long getTargetSize() {
			long subSize = underlyingIO.getTargetSize();
			return dimensions[iDimension + 1] * subSize;// * totalRemainingDims;
		}
		
		@Override
		public Pointer<T> get(Pointer<Pointer<T>> pointer, long index) {
			//long offset = getOffset(index);
			long targetSize = getTargetSize();//underlyingIO.getTargetSize();
			return pointer.offset(index * targetSize).as(underlyingIO);
		}

		long getOffset(long index) {
			assert iDimension < dimensions.length;
			return index * totalRemainingDims;
		}
		@Override
		public void set(Pointer<Pointer<T>> pointer, long index, Pointer<T> value) {
			throw new RuntimeException("Cannot set a multi-dimensional array's sub-arrays pointers !");
		}
	}
	
	static class CallbackPointerIO<T extends CallbackInterface> extends PointerIO<T> {
		final Class<T> callbackClass;

		public CallbackPointerIO(Class<T> callbackClass) {
			super(callbackClass, Pointer.SIZE, null);
			this.callbackClass = callbackClass;
		}
		
		@Override
		public T get(Pointer<T> pointer, long index) {
			if (index != 0)
				throw new UnsupportedOperationException("Cannot get function pointer at index different from 0");
			//return pointer.getPointerAtOffset(index * Pointer.SIZE, (Class<T>)null).getNativeObject(0, callbackClass);
			return (T)pointer.getNativeObjectAtOffset(0, callbackClass);
		}

		@Override
		public void set(Pointer<T> pointer, long index, T value) {
			throw new UnsupportedOperationException("Cannot write to body of function");
			//pointer.setPointer(index * Pointer.SIZE, Pointer.getPointer(value, callbackClass));
		}
	}
	
	static class IntValuedEnumPointerIO<E extends Enum<E>> extends PointerIO<IntValuedEnum<E>> {
		final Class<E> enumClass;

		public IntValuedEnumPointerIO(Class<E> enumClass) {
			super(IntValuedEnum.class, 4, null);
			this.enumClass = enumClass;
		}
		
		@Override
		public IntValuedEnum<E> get(Pointer<IntValuedEnum<E>> pointer, long index) {
			return FlagSet.fromValue(pointer.getIntAtOffset(4 * index), enumClass);
		}

		@Override
		public void set(Pointer<IntValuedEnum<E>> pointer, long index, IntValuedEnum<E> value) {
			pointer.setIntAtOffset(4 * index, (int)value.value());
		}
	}
	
	static class TypedPointerPointerIO<P extends TypedPointer> extends PointerIO<P> {
		final java.lang.reflect.Constructor cons;
		//final java.lang.reflect.Constructor cons2;
		final Class<P> pointerClass;
		public TypedPointerPointerIO(Class<P> pointerClass) {
			super(pointerClass, Pointer.SIZE, null);
			this.pointerClass = pointerClass;
			try {
				cons = pointerClass.getConstructor(long.class);
				//cons2 = pointerClass.getConstructor(Pointer.class);
			} catch (Exception ex) {
				throw new RuntimeException("Cannot find constructor for " + pointerClass.getName(), ex);
			}
		}
		
		@Override
		public P castTarget(long peer) {
			if (peer == 0)
				return null;
			try {
				return (P)cons.newInstance(peer);
			} catch (Exception ex) {
				throw new RuntimeException("Cannot create pointer of type " + pointerClass.getName(), ex);
			}
		}
		
		@Override
		public P get(Pointer<P> pointer, long index) {
			return castTarget(pointer.getSizeTAtOffset(index * Pointer.SIZE));
		}

		@Override
		public void set(Pointer<P> pointer, long index, P value) {
			pointer.setPointerAtOffset(index * Pointer.SIZE, value);
		}
	}
	
 
	public static final PointerIO<Integer> intIO = new PointerIO<Integer>(Integer.class, 4, null) {
		@Override
		public Integer get(Pointer<Integer> pointer, long index) {
			return pointer.getIntAtOffset(index * 4);
		}

		@Override
		public void set(Pointer<Integer> pointer, long index, Integer value) {
			pointer.setIntAtOffset(index * 4, value);
		}
		
		@Override
		public <B extends Buffer> B getBuffer(Pointer<Integer> pointer, long byteOffset, int length) {
						return (B)pointer.getIntBufferAtOffset(byteOffset, length);
					}
		
		@Override
		public Object getArray(Pointer<Integer> pointer, long byteOffset, int length) {
			return pointer.getIntsAtOffset(byteOffset, length);
		}
		
		@Override
		public void setArray(Pointer<Integer> pointer, long byteOffset, Object array) {
			if (array instanceof int[])
				pointer.setIntsAtOffset(byteOffset, (int[])array);
			else
				super.setArray(pointer, byteOffset, array);
		}
	
	};

 
	public static final PointerIO<Long> longIO = new PointerIO<Long>(Long.class, 8, null) {
		@Override
		public Long get(Pointer<Long> pointer, long index) {
			return pointer.getLongAtOffset(index * 8);
		}

		@Override
		public void set(Pointer<Long> pointer, long index, Long value) {
			pointer.setLongAtOffset(index * 8, value);
		}
		
		@Override
		public <B extends Buffer> B getBuffer(Pointer<Long> pointer, long byteOffset, int length) {
						return (B)pointer.getLongBufferAtOffset(byteOffset, length);
					}
		
		@Override
		public Object getArray(Pointer<Long> pointer, long byteOffset, int length) {
			return pointer.getLongsAtOffset(byteOffset, length);
		}
		
		@Override
		public void setArray(Pointer<Long> pointer, long byteOffset, Object array) {
			if (array instanceof long[])
				pointer.setLongsAtOffset(byteOffset, (long[])array);
			else
				super.setArray(pointer, byteOffset, array);
		}
	
	};

 
	public static final PointerIO<Short> shortIO = new PointerIO<Short>(Short.class, 2, null) {
		@Override
		public Short get(Pointer<Short> pointer, long index) {
			return pointer.getShortAtOffset(index * 2);
		}

		@Override
		public void set(Pointer<Short> pointer, long index, Short value) {
			pointer.setShortAtOffset(index * 2, value);
		}
		
		@Override
		public <B extends Buffer> B getBuffer(Pointer<Short> pointer, long byteOffset, int length) {
						return (B)pointer.getShortBufferAtOffset(byteOffset, length);
					}
		
		@Override
		public Object getArray(Pointer<Short> pointer, long byteOffset, int length) {
			return pointer.getShortsAtOffset(byteOffset, length);
		}
		
		@Override
		public void setArray(Pointer<Short> pointer, long byteOffset, Object array) {
			if (array instanceof short[])
				pointer.setShortsAtOffset(byteOffset, (short[])array);
			else
				super.setArray(pointer, byteOffset, array);
		}
	
	};

 
	public static final PointerIO<Byte> byteIO = new PointerIO<Byte>(Byte.class, 1, null) {
		@Override
		public Byte get(Pointer<Byte> pointer, long index) {
			return pointer.getByteAtOffset(index * 1);
		}

		@Override
		public void set(Pointer<Byte> pointer, long index, Byte value) {
			pointer.setByteAtOffset(index * 1, value);
		}
		
		@Override
		public <B extends Buffer> B getBuffer(Pointer<Byte> pointer, long byteOffset, int length) {
						return (B)pointer.getByteBufferAtOffset(byteOffset, length);
					}
		
		@Override
		public Object getArray(Pointer<Byte> pointer, long byteOffset, int length) {
			return pointer.getBytesAtOffset(byteOffset, length);
		}
		
		@Override
		public void setArray(Pointer<Byte> pointer, long byteOffset, Object array) {
			if (array instanceof byte[])
				pointer.setBytesAtOffset(byteOffset, (byte[])array);
			else
				super.setArray(pointer, byteOffset, array);
		}
	
	};

 
	public static final PointerIO<Character> charIO = new PointerIO<Character>(Character.class, Platform.WCHAR_T_SIZE, null) {
		@Override
		public Character get(Pointer<Character> pointer, long index) {
			return pointer.getCharAtOffset(index * Platform.WCHAR_T_SIZE);
		}

		@Override
		public void set(Pointer<Character> pointer, long index, Character value) {
			pointer.setCharAtOffset(index * Platform.WCHAR_T_SIZE, value);
		}
		
		@Override
		public <B extends Buffer> B getBuffer(Pointer<Character> pointer, long byteOffset, int length) {
						throw new UnsupportedOperationException("Creating direct char buffers in a cross-platform way is tricky, so it's currently disabled");
					}
		
		@Override
		public Object getArray(Pointer<Character> pointer, long byteOffset, int length) {
			return pointer.getCharsAtOffset(byteOffset, length);
		}
		
		@Override
		public void setArray(Pointer<Character> pointer, long byteOffset, Object array) {
			if (array instanceof char[])
				pointer.setCharsAtOffset(byteOffset, (char[])array);
			else
				super.setArray(pointer, byteOffset, array);
		}
	
	};

 
	public static final PointerIO<Float> floatIO = new PointerIO<Float>(Float.class, 4, null) {
		@Override
		public Float get(Pointer<Float> pointer, long index) {
			return pointer.getFloatAtOffset(index * 4);
		}

		@Override
		public void set(Pointer<Float> pointer, long index, Float value) {
			pointer.setFloatAtOffset(index * 4, value);
		}
		
		@Override
		public <B extends Buffer> B getBuffer(Pointer<Float> pointer, long byteOffset, int length) {
						return (B)pointer.getFloatBufferAtOffset(byteOffset, length);
					}
		
		@Override
		public Object getArray(Pointer<Float> pointer, long byteOffset, int length) {
			return pointer.getFloatsAtOffset(byteOffset, length);
		}
		
		@Override
		public void setArray(Pointer<Float> pointer, long byteOffset, Object array) {
			if (array instanceof float[])
				pointer.setFloatsAtOffset(byteOffset, (float[])array);
			else
				super.setArray(pointer, byteOffset, array);
		}
	
	};

 
	public static final PointerIO<Double> doubleIO = new PointerIO<Double>(Double.class, 8, null) {
		@Override
		public Double get(Pointer<Double> pointer, long index) {
			return pointer.getDoubleAtOffset(index * 8);
		}

		@Override
		public void set(Pointer<Double> pointer, long index, Double value) {
			pointer.setDoubleAtOffset(index * 8, value);
		}
		
		@Override
		public <B extends Buffer> B getBuffer(Pointer<Double> pointer, long byteOffset, int length) {
						return (B)pointer.getDoubleBufferAtOffset(byteOffset, length);
					}
		
		@Override
		public Object getArray(Pointer<Double> pointer, long byteOffset, int length) {
			return pointer.getDoublesAtOffset(byteOffset, length);
		}
		
		@Override
		public void setArray(Pointer<Double> pointer, long byteOffset, Object array) {
			if (array instanceof double[])
				pointer.setDoublesAtOffset(byteOffset, (double[])array);
			else
				super.setArray(pointer, byteOffset, array);
		}
	
	};

 
	public static final PointerIO<Boolean> booleanIO = new PointerIO<Boolean>(Boolean.class, 1, null) {
		@Override
		public Boolean get(Pointer<Boolean> pointer, long index) {
			return pointer.getBooleanAtOffset(index * 1);
		}

		@Override
		public void set(Pointer<Boolean> pointer, long index, Boolean value) {
			pointer.setBooleanAtOffset(index * 1, value);
		}
		
		@Override
		public <B extends Buffer> B getBuffer(Pointer<Boolean> pointer, long byteOffset, int length) {
						return (B)pointer.getByteBufferAtOffset(byteOffset, length);
					}
		
		@Override
		public Object getArray(Pointer<Boolean> pointer, long byteOffset, int length) {
			return pointer.getBooleansAtOffset(byteOffset, length);
		}
		
		@Override
		public void setArray(Pointer<Boolean> pointer, long byteOffset, Object array) {
			if (array instanceof boolean[])
				pointer.setBooleansAtOffset(byteOffset, (boolean[])array);
			else
				super.setArray(pointer, byteOffset, array);
		}
	
	};


	public static final PointerIO<SizeT> sizeTIO = new PointerIO<SizeT>(SizeT.class, SizeT.SIZE, null) {
		@Override
		public SizeT get(Pointer<SizeT> pointer, long index) {
			return new SizeT(pointer.getSizeTAtOffset(index * SizeT.SIZE));
		}
		@Override
		public void set(Pointer<SizeT> pointer, long index, SizeT value) {
			pointer.setSizeTAtOffset(index * SizeT.SIZE, value == null ? 0 : value.longValue());
		}		
	};
	
	public static final PointerIO<TimeT> timeTIO = new PointerIO<TimeT>(TimeT.class, TimeT.SIZE, null) {
		@Override
		public TimeT get(Pointer<TimeT> pointer, long index) {
			long offset = index * TimeT.SIZE;
			return new TimeT(TimeT.SIZE == 4 ? pointer.getIntAtOffset(offset) : pointer.getLongAtOffset(offset));
		}
		@Override
		public void set(Pointer<TimeT> pointer, long index, TimeT value) {
			long offset = index * TimeT.SIZE;
			if (TimeT.SIZE == 4)
				pointer.setIntAtOffset(offset, value == null ? 0 : value.intValue());
			else
				pointer.setLongAtOffset(offset, value == null ? 0 : value.longValue());
		}		
	};
	
	public static final PointerIO<CLong> clongIO = new PointerIO<CLong>(CLong.class, CLong.SIZE, null) {
		@Override
		public CLong get(Pointer<CLong> pointer, long index) {
			return new CLong(pointer.getCLongAtOffset(index * CLong.SIZE));
		}
		@Override
		public void set(Pointer<CLong> pointer, long index, CLong value) {
			pointer.setCLongAtOffset(index * CLong.SIZE, value == null ? 0 : value.longValue());
		}		
	};
	
	

	/*public static final PointerIO<Integer> intIO = new PointerIO<Integer>(Integer.class, 4, null) {
		@Override
		public Integer get(Pointer<Integer> pointer, long index) {
			return pointer.getIntAtOffset(index * 4);
		}

		@Override
		public void set(Pointer<Integer> pointer, long index, Integer value) {
			pointer.setIntAtOffset(index * 4, value);
		}		
	};*/

}

