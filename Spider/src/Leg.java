import java.io.IOException;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;





public class Leg {
	
	private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
	
	private List<String> links = new LinkedList<String>();
	private Document htmlDocument;

	public void crawl(String url){
		
		//give it a url and this will make an http request for the specific web page
	
		org.jsoup.Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
	
	try {
		htmlDocument = ((org.jsoup.Connection) connection).get();
	} catch (IOException e) {
		System.out.println("error");
		e.printStackTrace();
	}
	
	System.out.println("Received web page at " + url);
	
	Elements linksOnPage = htmlDocument.select("a[href]");
	System.out.println("Found ( " + linksOnPage.size() + "links");
	
	for(Element link : linksOnPage)
    {
        this.links.add(link.absUrl("href"));
    }
		
	}
	
	
	public boolean searchForWord(String searchWord){
		System.out.println("Searching for the word " + searchWord + "...");
		String bodyText = this.htmlDocument.body().text();
		return bodyText.toLowerCase().contains(searchWord.toLowerCase());
		
		
	}
	
	
	public List<String> getLinks(){
		
		return this.links;
		
	}
	
}
