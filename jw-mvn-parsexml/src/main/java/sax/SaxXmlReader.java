package sax;

import java.io.File;
import java.util.Stack;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * ������ʽ���¼�����
 * @author wengqf
 *
 */
public class SaxXmlReader extends DefaultHandler {
	private Stack<String> tags = new Stack<String>();

	public static void main(String[] args) {
		long lasting = System.currentTimeMillis();
		try {
			SAXParserFactory sf = SAXParserFactory.newInstance();
			SAXParser sp = sf.newSAXParser();
			sp.parse(new File("demo.xml"),new SaxXmlReader());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("����ʱ�䣺" + (System.currentTimeMillis() - lasting) + "����");
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		tags.push(qName);
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String tag = tags.peek();
		if (tag.equals("NO")) {
			System.out.print("���ƺ��룺" + new String(ch, start, length));
		}
		if (tag.equals("ADDR")) {
			System.out.println("��ַ:" + new String(ch, start, length));
		}
	}

}
