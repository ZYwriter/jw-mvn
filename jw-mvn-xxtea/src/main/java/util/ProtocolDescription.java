package util;

public class ProtocolDescription {
    public final static int _pack_ob = 30; //二进制包
    public final static int _pack_req = 31; //请求包
    public final static int _pack_reqhasknow = 32;
    public final static int _pack_result = 33; //结果包
    public final static int _pack_head = 34; //头包
    public final static int _pack_record = 35; //记录包
    public final static int _pack_endRecord = 36; //最后一条记录包
    
    public final static int _pack_ = 0x80;
    
    public final static int _pack_req_result = 0x9f;
    public final static int _pack_result_result = 0xa1;
    public final static int _pack_head_result = 0xa2;
    public final static int _pack_record_result = 0xa3;
    public final static int _pack_endRecord_result = 0xa4;
    
    //标识
    public final static byte[] packMark = new byte[] { (byte) 0xA4, (byte) 0xE6 };
    //key 的格式为 9位，前5位为mecrt,后四位为请求序号
    public final static String keyHeader = "mecrt"; 
    
    public final static byte packStatus = 0b00; //不加密不压缩
//    public final static byte packStatus = 0b11; //加密压缩
//    public final static byte packStatus = 0b01; //只压缩
//    public final static byte packStatus = 0b10; //只加密
    public final static byte version = 0x01; //版本
    
    public final static int head_length=23;
    public final static int head_marketLength = packMark.length;
    public final static int head_exclueMarketLength = head_length - head_marketLength;
    
    //心跳包功能号
    public final static int heatbit_funnum = 8888;
    public final static int message_order_funnum = 7777;
    public final static int message_cancel_funnum = 7776;
    
    //grid head 类型
    public final static int type_i = 0x49; //vint
    public final static int type_s = 0x53; //stringutf 字符串
    public final static int type_l = 0x4C; //vlong
    public final static int type_f = 0x46; //vfload
    public final static int type_d = 0x44; //vdouble
    public final static int type_c = 0x43; //char
    public final static int type_b= 0x42; //byte
    public final static int type_t= 0x54; //short
    public final static int type_a= 0x41; //数组
    
    public final static int type_date = 0x88; //日期只做内部使用，不向外传，作为type_s存在
}
