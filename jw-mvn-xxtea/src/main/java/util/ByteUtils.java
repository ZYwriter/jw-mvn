package util;

import java.io.DataInput;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;


public class ByteUtils {
	public static void putByte(byte b[], byte bb, int index){
		b[index] = bb;
	}
	
	public static void putByte(byte source[], byte[] bb, int index){
        for (byte c : bb) {
            source[index] = c;
            index ++;
        }
    }
	
	public static byte getByte(byte b[], int index){
		return b[index];
	}
	
	public static byte[] getByte(byte b[], int index,int count){
		byte[] data = new byte[count];
		int breakIndex = count - 1;
		for (int j = 0; j < data.length; j++) {
			data[j] = b[index];
			if(j == breakIndex){
				break;
			}
			index++;
		}
		return data;
	}
	///////////////////////////////////////////////////////////
	public static void putShort(byte b[], short s, int index) {
        b[index + 1] = (byte) (s >> 8);
        b[index] = (byte) (s >> 0);
    }

    public static short getShort(byte[] b, int index) {
        return (short) (((b[index+1] << 8) | b[index] & 0xff));
    }

    // ///////////////////////////////////////////////////////
    public static void putInt(byte[] bb, int x, int index) {
        bb[index + 3] = (byte) (x >> 24);
        bb[index + 2] = (byte) (x >> 16);
        bb[index + 1] = (byte) (x >> 8);
        bb[index + 0] = (byte) (x >> 0);
    }

    public static int getInt(byte[] bb, int index) {
        return (int) ((((bb[index + 3] & 0xff) << 24)
                | ((bb[index + 2] & 0xff) << 16)
                | ((bb[index + 1] & 0xff) << 8) | ((bb[index + 0] & 0xff) << 0)));
    }

    // /////////////////////////////////////////////////////////
    public static void putLong(byte[] bb, long x, int index) {
        bb[index + 7] = (byte) (x >> 56);
        bb[index + 6] = (byte) (x >> 48);
        bb[index + 5] = (byte) (x >> 40);
        bb[index + 4] = (byte) (x >> 32);
        bb[index + 3] = (byte) (x >> 24);
        bb[index + 2] = (byte) (x >> 16);
        bb[index + 1] = (byte) (x >> 8);
        bb[index + 0] = (byte) (x >> 0);
    }

    public static long getLong(byte[] bb, int index) {
        return ((((long) bb[index + 7] & 0xff) << 56)
                | (((long) bb[index + 6] & 0xff) << 48)
                | (((long) bb[index + 5] & 0xff) << 40)
                | (((long) bb[index + 4] & 0xff) << 32)
                | (((long) bb[index + 3] & 0xff) << 24)
                | (((long) bb[index + 2] & 0xff) << 16)
                | (((long) bb[index + 1] & 0xff) << 8)
                | (((long) bb[index + 0] & 0xff) << 0));
    }

    private static byte[] toLH(int n) {
        byte[] b = new byte[4];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        b[2] = (byte) (n >> 16 & 0xff);
        b[3] = (byte) (n >> 24 & 0xff);
        return b;
    }

    @SuppressWarnings("unused")
    private static byte[] toLH(short n) {
        byte[] b = new byte[2];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        return b;
    }

    @SuppressWarnings("unused")
    private static byte[] toLH(float f) {
        return toLH(Float.floatToRawIntBits(f));
    }

    public static byte intTobyte(int intValue){
        int byteValue;
        int temp = intValue % 256;
        if ( intValue < 0) {
         byteValue =  temp < -128 ? 256 + temp : temp;
        }
        else {
         byteValue =  temp > 127 ? temp - 256 : temp;
        }
        return Byte.parseByte(String.valueOf(byteValue));
    }

    /**
     * ×ª»»unicode±íÊ¾
     * @param i
     * @return
     */
    public static String intToUnicode(int i){
        if (i>250) {
            i = 250;
        }
        String tmp = Integer.toHexString(i);
        StringBuffer sb = new StringBuffer();
        sb.append("\\u");
        for (int j = 0; j < 4- tmp.length(); j++) {
            sb.append("0");
        }
        sb.append(tmp);
        return sb.toString();
    }
    
    public static void putFloat(byte[] bb, float x, int index) {  
        int l = Float.floatToIntBits(x);  
        for (int i = 0; i < 4; i++) {  
            bb[index + i] = new Integer(l).byteValue();  
            l = l >> 8;  
        }  
    }  
    @SuppressWarnings("unused")
	private static byte[] convert(byte[] b){
    	int length = b.length;
    	for(int i = 0; i < length/2; i++){
    		byte temp = b[i];
    		b[i] = b[length-i-1];
    		b[length-i-1] = temp;
    	}
    	return b;
    }
    
    public static float getFloat(byte[] b, int index) {  
        int l;  
        l = b[index + 0];  
        l &= 0xff;  
        l |= ((long) b[index + 1] << 8);  
        l &= 0xffff;  
        l |= ((long) b[index + 2] << 16);  
        l &= 0xffffff;  
        l |= ((long) b[index + 3] << 24);  
        float  fa = Float.intBitsToFloat(l);
        return fa;
    }  
    public static void putDouble(byte[] bb, double x, int index) {  
        long l = Double.doubleToLongBits(x);  
        for (int i = 0; i < 8; i++) {  
            bb[index + i] = new Long(l).byteValue();  
            l = l >> 8;  
        }  
    }  
    public static double getDouble(byte[] b, int index) {  
        long l;  
        l = b[index+0];  
        l &= 0xff;  
        l |= ((long) b[index+1] << 8);  
        l &= 0xffff;  
        l |= ((long) b[index+2] << 16);  
        l &= 0xffffff;  
        l |= ((long) b[index+3] << 24);  
        l &= 0xffffffffl;  
        l |= ((long) b[index+4] << 32);  
        l &= 0xffffffffffl;  
        l |= ((long) b[index+5] << 40);  
        l &= 0xffffffffffffl;  
        l |= ((long) b[index+6] << 48);  
        l &= 0xffffffffffffffl;  
        l |= ((long) b[index+7] << 56);  
        return Double.longBitsToDouble(l);  
    }  
    public static String convertString(byte[] b){
    	StringBuffer sb =  new StringBuffer();
    	for(int i =0 ; i < b.length; i++){
    		if(b[i]==0)
    			break;
    		sb.append((char)b[i]);
    	}
    	return sb.toString();
    }
    
    public static final int ALIGN_LEFT = 10;
	public static final int ALIGN_RIGHT = 12;

	public static int readLittleEndianInt(DataInput in) throws IOException {
		int bigEndian = 0;
		for (int shiftBy = 0; shiftBy < 32; shiftBy += 8) {
			bigEndian |= (in.readUnsignedByte() & 0xff) << shiftBy;
		}
		return bigEndian;
	}

	public static short readLittleEndianShort(DataInput in) throws IOException {
		int low = in.readUnsignedByte() & 0xff;
		int high = in.readUnsignedByte();
		return (short) (high << 8 | low);
	}

	public static byte[] trimLeftSpaces(byte[] arr) {
		StringBuffer t_sb = new StringBuffer(arr.length);
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != ' ') {
				t_sb.append((char) arr[i]);
			}
		}
		return t_sb.toString().getBytes();
	}

	public static short littleEndian(short value) {
		short num1 = value;
		short mask = (short) 0xff;
		short num2 = (short) (num1 & mask);
		num2 <<= 8;
		mask <<= 8;
		num2 |= (num1 & mask) >> 8;
		return num2;
	}

	public static int littleEndian(int value) {
		int num1 = value;
		int mask = 0xff;
		int num2 = 0x00;
		num2 |= num1 & mask;
		for (int i = 1; i < 4; i++) {
			num2 <<= 8;
			mask <<= 8;
			num2 |= (num1 & mask) >> (8 * i);
		}
		return num2;
	}

	public static byte[] textPadding(String text, String characterSetName,
			int length) throws java.io.UnsupportedEncodingException {
		return textPadding(text, characterSetName, length, ALIGN_LEFT);
	}

	public static byte[] textPadding(String text, String characterSetName,
			int length, int alignment)
			throws java.io.UnsupportedEncodingException {
		return textPadding(text, characterSetName, length, alignment,
				(byte) ' ');
	}

	public static byte[] textPadding(String text, String characterSetName,
			int length, int alignment, byte paddingByte)
			throws java.io.UnsupportedEncodingException {
		if (text.length() >= length) {
			return text.substring(0, length).getBytes(characterSetName);
		}
		byte byte_array[] = new byte[length];
		Arrays.fill(byte_array, paddingByte);
		switch (alignment) {
		case ALIGN_LEFT:
			System.arraycopy(text.getBytes(characterSetName), 0, byte_array, 0,
					text.length());
			break;
		case ALIGN_RIGHT:
			int t_offset = length - text.length();
			System.arraycopy(text.getBytes(characterSetName), 0, byte_array,
					t_offset, text.length());
			break;
		}
		return byte_array;
	}

	public static byte[] doubleFormating(Double doubleNum,
			String characterSetName, int fieldLength, int sizeDecimalPart)
			throws java.io.UnsupportedEncodingException {
		int sizeWholePart = fieldLength
				- (sizeDecimalPart > 0 ? (sizeDecimalPart + 1) : 0);
		StringBuffer format = new StringBuffer(fieldLength);
		for (int i = 0; i < sizeWholePart; i++) {
			format.append("#");
		}
		if (sizeDecimalPart > 0) {
			format.append(".");
			for (int i = 0; i < sizeDecimalPart; i++) {
				format.append("0");
			}
		}
		DecimalFormat df = new DecimalFormat(format.toString());
		return textPadding(df.format(doubleNum.doubleValue()).toString(),
				characterSetName, fieldLength, ALIGN_RIGHT);
	}

	public static boolean contains(byte[] arr, byte value) {
		boolean found = false;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == value) {
				found = true;
				break;
			}
		}
		return found;
	}
	public static byte[] IntToBytes(int n){
		byte[] b = new byte[4];
		for(int i = 0;i < 4;i++)
		{
			b[i]=(byte)(n>>(24-i*8));
		}
		return b;
	}
	public static int BytesToInt(byte[] b){
		int mask=0xff;
        int temp=0;
        int n=0;
        for(int i=0;i<4;i++){
        	n<<=8;
            temp=b[i]&mask;
            n|=temp;
        }
        return n;
	}
}
