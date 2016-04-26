package jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author wengqf
 * ���
 */
public class JsoupTest {
	
	static String url = "http://www.cnblogs.com/zyw-205520/archive/2012/12/20/2826402.html";
	public static void main(String[] args) throws IOException {
		blogBody();//body����
		article();//���±�������
		bolg();//����
	}
	
	/**
	 * ��ȡָ��HTML �ĵ�ָ����body
	 * @throws IOException
	 */
	private static void blogBody() throws IOException{
		// ֱ�Ӵ��ַ��������� HTML �ĵ�
        String html = "<html><head><title> ��Դ�й����� </title></head>"
                + "<body><p> ������ jsoup ��Ŀ��������� </p></body></html>";
        Document doc = Jsoup.parse(html);
        System.out.println(doc.body());
        
        //��URlֱ�Ӽ���html�ĵ�
        Document doc2 = Jsoup.connect(url).get();
        String title = doc2.body().toString();
        System.out.println(title);
	}

	/**
	 *  ��ȡ�����ϵ����±��������
	 */
	public static void article(){
		Document doc;
		try {
			doc = Jsoup.connect("http://www.cnblogs.com/zyw-205520/").get();
			Elements listDiv = doc.getElementsByAttributeValue("class", "postTitle");
			for (Element element : listDiv) {
				Elements links = element.getElementsByTag("a");
				for (Element link : links) {
					String linkHref = link.attr("href");
					String linkText = link.text().trim();
					System.out.println(linkHref);
					System.out.println(linkText);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ȡָ���������µ�����
	 */
	public static void bolg(){
		Document doc;
		try {
			doc = Jsoup.connect("http://www.cnblogs.com/zyw-205520/archive/2012/12/20/2826402.html").get();
			Elements listDiv = doc.getElementsByAttributeValue("class", "postBody");
			for (Element element : listDiv) {
				System.out.println(element.html());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
