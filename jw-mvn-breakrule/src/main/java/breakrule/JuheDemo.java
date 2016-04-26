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
 * 全国车辆违章调用示例-聚合数据
 * @author wengqf 在线接口文档：http://www.juhe.cn/docs/36
 */
public class JuheDemo {
	public static final String DEF_CHATSET = "UTF-8";
	public static final int DEF_CONN_TIMEOUT = 30000;
	public static final int DEF_READ_TIMEOUT = 30000;
	public static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

	// 配置申请的KEY
	public static final String APPKEY = "*************************";

	public static void main(String[] args) {
		getRequest1();
	}

	// 1、获取支持城市参数接口
	public static void getRequest1() {
		String result = null;
		String url = "http://v.juhe.cn/wz/citys";// 请求接口地址
		Map<String, String> params = new HashMap<String, String>();// 请求参数
		params.put("province", "");// 默认全部，省份简写，如：ZJ、JS
		params.put("dtype", "");// 返回数据格式：json或xml或jsonp,默认json
		params.put("format", "");// 格式选择1或2，默认1
		params.put("callback", "");// 返回格式选择jsonp时，必须传递
		params.put("key", APPKEY);// 你申请的key
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

	// 请求违章查询接口
	public static void getRequest2() {
		String result = null;
		String url = "http://v.juhe.cn/wz/query";// 请求接口地址
		Map<String, String> params = new HashMap<String, String>();// 请求参数
		params.put("dtype", "");// 返回数据格式：json或xml或jsonp,默认json
		params.put("callback", "");// 返回格式选择jsonp时，必须传递
		params.put("key", APPKEY);// 你申请的key
		params.put("city", "");// 城市代码 *
		params.put("hphm", "");// 号牌号码 完整7位 ,需要utf8 urlencode*
		params.put("hpzl", "");// 号牌类型，默认02
		params.put("engineno", "");// 发动机号 (根据城市接口中的参数填写)
		params.put("classno", "");// 车架号 (根据城市接口中的参数填写)

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

	// 3.接口剩余请求次数查询
	public static void getRequest3() {
		String result = null;
		String url = "http://v.juhe.cn/wz/status";// 请求接口地址
		Map<String, String> params = new HashMap<String, String>();// 请求参数
		params.put("key", APPKEY);// 应用APPKEY(应用详细页查询)
		params.put("dtype", "");// 返回数据的格式,xml或json，默认json

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

	// 将Map类型转换为请求参数型
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
