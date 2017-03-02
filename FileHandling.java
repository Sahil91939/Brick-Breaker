import java.io.*;
import java.util.*;

public class FileHandling {
	
	private BufferedReader(String prompt){
		BufferedReader rd =null;
		while(rd==null){
			try{
				String name = readLine(prompt);
				rd = new BufferedReader(new FileReader(name));
			}catch(IOException ex){
				println("bad File");
			}
		}
		return rd ;
	}
	
}
