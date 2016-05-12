package util;

public class ProtocolDescription {
    public final static int _pack_ob = 30; //�����ư�
    public final static int _pack_req = 31; //�����
    public final static int _pack_reqhasknow = 32;
    public final static int _pack_result = 33; //�����
    public final static int _pack_head = 34; //ͷ��
    public final static int _pack_record = 35; //��¼��
    public final static int _pack_endRecord = 36; //���һ����¼��
    
    public final static int _pack_ = 0x80;
    
    public final static int _pack_req_result = 0x9f;
    public final static int _pack_result_result = 0xa1;
    public final static int _pack_head_result = 0xa2;
    public final static int _pack_record_result = 0xa3;
    public final static int _pack_endRecord_result = 0xa4;
    
    //��ʶ
    public final static byte[] packMark = new byte[] { (byte) 0xA4, (byte) 0xE6 };
    //key �ĸ�ʽΪ 9λ��ǰ5λΪmecrt,����λΪ�������
    public final static String keyHeader = "mecrt"; 
    
    public final static byte packStatus = 0b00; //�����ܲ�ѹ��
//    public final static byte packStatus = 0b11; //����ѹ��
//    public final static byte packStatus = 0b01; //ֻѹ��
//    public final static byte packStatus = 0b10; //ֻ����
    public final static byte version = 0x01; //�汾
    
    public final static int head_length=23;
    public final static int head_marketLength = packMark.length;
    public final static int head_exclueMarketLength = head_length - head_marketLength;
    
    //���������ܺ�
    public final static int heatbit_funnum = 8888;
    public final static int message_order_funnum = 7777;
    public final static int message_cancel_funnum = 7776;
    
    //grid head ����
    public final static int type_i = 0x49; //vint
    public final static int type_s = 0x53; //stringutf �ַ���
    public final static int type_l = 0x4C; //vlong
    public final static int type_f = 0x46; //vfload
    public final static int type_d = 0x44; //vdouble
    public final static int type_c = 0x43; //char
    public final static int type_b= 0x42; //byte
    public final static int type_t= 0x54; //short
    public final static int type_a= 0x41; //����
    
    public final static int type_date = 0x88; //����ֻ���ڲ�ʹ�ã������⴫����Ϊtype_s����
}
