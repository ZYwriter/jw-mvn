package util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;


public class ZipUtils {
    /**
     * 压缩数据
     * @param object
     * @return
     * @throws IOException
     */
    public static byte[] jzlib(byte[] object) {
        byte[] data = null;
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            DeflaterOutputStream zOut = new DeflaterOutputStream(out);
            DataOutputStream objOut = new DataOutputStream(zOut);
            objOut.write(object);
            objOut.flush();
            zOut.close();
            data = out.toByteArray();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 解压被压缩的数据
     * 
     * @param object
     * @return
     * @throws IOException
     */
    public static byte[] unjzlib(byte[] object) {
        byte[] data = null;
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(object);
            InflaterInputStream zIn = new InflaterInputStream(in);
            byte[] buf = new byte[4096];
            int num = -1;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((num = zIn.read(buf, 0, buf.length)) != -1) {
                baos.write(buf, 0, num);
            }
            data = baos.toByteArray();
            baos.flush();
            baos.close();
            zIn.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
