import java.awt.List;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;


public class Spider {
	
	private static final int MAX_PAGES_TO_SEARCH = 10000;
	private Set<String> pagesVisited = new HashSet<String>();
	private LinkedList<String> pagesToVisist = new LinkedList<String>();
	
	
	
	
	private String nextUrl(){
		
		String nextUrl;
		do
		{
			nextUrl = this.pagesToVisist.remove(0);
			
		}while(this.pagesVisited.contains(nextUrl));
		
		this.pagesVisited.add(nextUrl);
		
		return nextUrl;
	}
	
	public void search(String url, String searchWord){
		
		
		
		while(this.pagesVisited.size() < MAX_PAGES_TO_SEARCH){
			
			String currentUrl;
			Leg leg = new Leg();
			
			if(this.pagesToVisist.isEmpty()){
				currentUrl = url;
				this.pagesToVisist.add(url);
				
			}else{
				currentUrl = this.nextUrl();
			}
			
			leg.crawl(currentUrl);
			
			boolean success = leg.searchForWord(searchWord);
			
			if(success){
				
				System.out.println(String.format("**SUCCESS** word %s found at %s", searchWord, currentUrl));
			    writeText(searchWord, currentUrl);
				
				//insert method for writing findings to a file.
				
				
			}
			
			this.pagesToVisist.addAll(leg.getLinks());
		}
		
		System.out.println(String.format("**Done** Visited %s web page(s)", this.pagesVisited.size()));
		
		
	}
	
	public void writeText(String searchWord, String currentUrl){
		
		try {
			System.out.println("writing to file");
			PrintWriter	print = new PrintWriter("/home/alex/Desktop/New Workspace/Spider/lib/log.txt");
			print.println(searchWord + " - " + currentUrl);
			print.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	
	
	

}
