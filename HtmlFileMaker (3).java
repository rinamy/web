package exec1Web;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HtmlFileMaker {
	
	int numOfCols;
	private FileWriter file;
	private BufferedWriter bw; 
	String style;
	String page;
	public HtmlFileMaker(String fileName, int numOfCols) throws IOException{
		this.numOfCols = numOfCols;
		file = new FileWriter(fileName + ".html");
		bw = new BufferedWriter(file);
		buildThePage();
	}
	private void buildThePage(){
		StringBuilder page = new StringBuilder();
		style = "<html>" + 
					"<style>" + 
					".Row" +
					"{" +
					    "display: table;"+
					    "width: 100%;" +
					    "height: 100%;"+
					"}"+
					".Column"+
					"{"+
					    "display: table-cell;" +
					    "height: 100%;"+
					"}"+
					".blue{"+
					    "background-color: blue;"+
					"}"+					    
					".green{"+
					    "background-color: green;"+
					"}"+
					".red{"+
					    "background-color: red;"+
					"}"+
					"</style>";
		
		page.append("<body>" +
						"<div class='row'>");
		page.append(arrangeTheColumns());
		page.append("</div>" + 
				  "</body>" + 
				"</html>");
		
		this.page = page.toString();
		System.out.println(style + "\n" + this.page);
		writeToFile();
		try {
			bw.close();
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void writeToFile(){
			try {
				bw.write(style + '\n');
				bw.write(page);
				
			} catch (IOException e) {
				e.printStackTrace();
			}			
			
			
	}
	private String arrangeTheColumns(){
		StringBuilder cols  = new StringBuilder();
		for(int i = 1; i <= numOfCols;i++){
			switch(i%3){
			case 0:
				cols.append("<div class='Column green'></div>");
				break;
			case 1:
				cols.append("<div class='Column blue'></div>");
				break;
			case 2: 
				cols.append("<div class='Column red'></div>");
				break;
			}
		}
		return cols.toString();
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("enter the number of columns between 1-50");
		int cols = scanner.nextInt();
		try{
		new HtmlFileMaker("htmlFile", cols);
		}
		catch (IOException e){
			System.out.println("there some problem");
		}
		scanner.close();
	}

}
