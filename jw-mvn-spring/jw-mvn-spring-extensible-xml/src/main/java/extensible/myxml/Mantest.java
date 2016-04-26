package extensible.myxml;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Mantest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("myBeans.xml");
		SimpleDateFormat format = (SimpleDateFormat) context.getBean("defautDateFormat");
		System.out.println(format.format(new Date()));
	}
}
