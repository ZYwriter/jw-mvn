package breakrule;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * ȫ������Υ�µ���ʾ��-�ۺ�����
 * @author wengqf ���߽ӿ��ĵ���http://www.juhe.cn/docs/36
 */
public class JuheDemo {
	public static final String DEF_CHATSET = "UTF-8";
	public static final int DEF_CONN_TIMEOUT = 30000;
	public static final int DEF_READ_TIMEOUT = 30000;
	public static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

	// ���������KEY
	public static final String APPKEY = "*************************";

	public static void main(String[] args) {
		getRequest1();
	}

	// 1����ȡ֧�ֳ��в����ӿ�
	public static void getRequest1() {
		String result = null;
		String url = "http://v.juhe.cn/wz/citys";// ����ӿڵ�ַ
		Map<String, String> params = new HashMap<String, String>();// �������
		params.put("province", "");// Ĭ��ȫ����ʡ�ݼ�д���磺ZJ��JS
		params.put("dtype", "");// �������ݸ�ʽ��json��xml��jsonp,Ĭ��json
		params.put("format", "");// ��ʽѡ��1��2��Ĭ��1
		params.put("callback", "");// ���ظ�ʽѡ��jsonpʱ�����봫��
		params.put("key", APPKEY);// �������key
		try {
			result = net(url, params, "GET");
			JSONObject object = JSONObject.fromObject(result);
			if (object.getInt("error_code") == 0) {
				System.out.println(object.get("result"));
			} else {
				System.out.println(object.get("error_code") + ":" + object.get("reason"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ����Υ�²�ѯ�ӿ�
	public static void getRequest2() {
		String result = null;
		String url = "http://v.juhe.cn/wz/query";// ����ӿڵ�ַ
		Map<String, String> params = new HashMap<String, String>();// �������
		params.put("dtype", "");// �������ݸ�ʽ��json��xml��jsonp,Ĭ��json
		params.put("callback", "");// ���ظ�ʽѡ��jsonpʱ�����봫��
		params.put("key", APPKEY);// �������key
		params.put("city", "");// ���д��� *
		params.put("hphm", "");// ���ƺ��� ����7λ ,��Ҫutf8 urlencode*
		params.put("hpzl", "");// �������ͣ�Ĭ��02
		params.put("engineno", "");// �������� (���ݳ��нӿ��еĲ�����д)
		params.put("classno", "");// ���ܺ� (���ݳ��нӿ��еĲ�����д)

		try {
			result = net(url, params, "GET");
			JSONObject object = JSONObject.fromObject(result);
			if (object.getInt("error_code") == 0) {
				System.out.println(object.get("result"));
			} else {
				System.out.println(object.get("error_code") + ":" + object.get("reason"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 3.�ӿ�ʣ�����������ѯ
	public static void getRequest3() {
		String result = null;
		String url = "http://v.juhe.cn/wz/status";// ����ӿڵ�ַ
		Map<String, String> params = new HashMap<String, String>();// �������
		params.put("key", APPKEY);// Ӧ��APPKEY(Ӧ����ϸҳ��ѯ)
		params.put("dtype", "");// �������ݵĸ�ʽ,xml��json��Ĭ��json

		try {
			result = net(url, params, "GET");
			JSONObject object = JSONObject.fromObject(result);
			if (object.getInt("error_code") == 0) {
				System.out.println(object.get("result"));
			} else {
				System.out.println(object.get("error_code") + ":" + object.get("reason"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String net(String strUrl, Map<String, String> params, String method) {
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		String rs = null;
		try {
			StringBuffer sb = new StringBuffer();
			if (method == null || method.equals("GET")) {
				strUrl = strUrl + "?" + urlencode(params);
			}
			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			if (method == null || method.equals("GET")) {
				conn.setRequestMethod("GET");
			} else {
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
			}
			conn.setRequestProperty("User-agent", userAgent);
			conn.setUseCaches(false);
			conn.setConnectTimeout(DEF_CONN_TIMEOUT);
			conn.setReadTimeout(DEF_READ_TIMEOUT);
			conn.setInstanceFollowRedirects(false);
			try {
				if (params != null && method.equals("POST")) {
					DataOutputStream out = new DataOutputStream(conn.getOutputStream());
					out.writeBytes(urlencode(params));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			InputStream is = conn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sb.append(strRead);
			}
			rs = sb.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (conn != null) {
					conn.disconnect();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return rs;
	}

	// ��Map����ת��Ϊ���������
	private static String urlencode(Map<String, String> params) {
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			try {
				sb.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue() + "", DEF_CHATSET))
						.append("&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

}
