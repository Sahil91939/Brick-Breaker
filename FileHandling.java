import java.io.*;
import java.util.*;
import acm.program.*;
import acm.util.*;

public class FileHandling extends ConsoleProgram {
	
	private BufferedReader openFile(String prompt){
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
