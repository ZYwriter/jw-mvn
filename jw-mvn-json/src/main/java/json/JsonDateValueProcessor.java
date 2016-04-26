package json;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * json日期格式格式化
 * @author wengqf
 *
 */
public class JsonDateValueProcessor implements JsonValueProcessor {
	private String datePattern = "yyyy年MM月dd日";//日期格式
	
	
	public JsonDateValueProcessor() {
		super();
	}
	

	public JsonDateValueProcessor(String datePattern) {
		super();
		this.datePattern = datePattern;
	}


	@Override
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		return process(value);
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		return process(value);
	}

	private Object process(Object value){
		try {
			if(value instanceof Date){
				SimpleDateFormat sdf = new SimpleDateFormat(datePattern, Locale.UK);
				return sdf.format((Date)value);
			}
			return value == null? "":value.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public String getDatePattern() {
		return datePattern;
	}


	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}

}
