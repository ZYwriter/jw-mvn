package json;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

/**
 * @author wengqf
 *
 */
public class JavaToJson {
	private JSONObject jo;

	@Test
	public void mapToJson() {
		Map<String, Object> map = new HashMap<>();
		map.put("name", "MJ");
		map.put("KOBE", new String[] { "5个总冠军", "2个FMVP", "1个MVP" });
		map.put("age", 37);
		jo = JSONObject.fromObject(map);
		System.out.println("map->json: " + jo.toString());
	}

	@Test
	public void beanToJson() {
		JsonBean bean = new JsonBean();
		bean.setName("wengqf");
		bean.setAge(25);
		bean.setBorn(new Date());
		jo = JSONObject.fromObject(bean);
		System.out.println("bean->json:" + jo.toString());
	}

	@Test
	public void beanToJsonFormatDate() {
		JsonBean bean = new JsonBean();
		bean.setName("wengqf");
		bean.setAge(25);
		bean.setBorn(new Date());

		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		jo = JSONObject.fromObject(bean, jsonConfig);
		System.out.println("bean->json:" + jo.toString());
	}
	
	//把json的yyyy-MM-dd转换为Bean中的Date类型
	@Test
	public void jsonToDate(){
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[]{"2016-04-26"}));
	}
	
	@Test
	public void jsonToList(){
		String listStr = "[\"apple\",\"orange\"]";
		Collection<String> strList = JSONArray.toCollection(JSONArray.fromObject(listStr));
		for (String str : strList) {
			System.out.println(str);
		}
	}
	
	@Test
	public void jsonToMap(){
		String mapStr = "{\"age\":25,\"name\":\"MJ\",\"baby\":[\"Lucy\",\"Lily\"]}";
		Map<String,Object> map = (Map<String, Object>) JSONObject.toBean(JSONObject.fromObject(mapStr),Map.class);
		for (Map.Entry<String,Object> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "="  + entry.getValue());
		}
	}
}
