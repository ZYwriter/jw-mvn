package dom;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class DomXMLReader {

	
	public DomXMLReader() {
		long s = System.currentTimeMillis();
		try {
			File file = new ClassPathResource("demo.xml").getFile();
//			File file = ResourceUtils.getFile("./src/test/resources/testdemo.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			NodeList nl = doc.getElementsByTagName("HOME");
			for (int i = 0; i < nl.getLength(); i++) {
				System.out.print("³µÅÆºÅÂë:" + doc.getElementsByTagName("NO").item(i).getFirstChild().getNodeValue());
				System.out.println("µØÖ·:" + doc.getElementsByTagName("ADDR").item(i).getFirstChild().getNodeValue());
			}
			long l = System.currentTimeMillis();
			System.out.println("ºÄÊ±£º" + (l-s) + "ms");
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new DomXMLReader();
	}
}
