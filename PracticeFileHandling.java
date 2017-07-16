import java.io.*;
import acm.program.*;
import acm.util.*;

public class PracticeFileHandling extends ConsoleProgram {
	
	private BufferedReader openFile(String prompt){
		BufferedReader rd = null;
		
		while (rd == null){
			try{
				String name = readLine(prompt);
				rd = new BufferedReader ( new FileReader(name));
			}catch (IOException ex){
				println("bad file");
			}
		}
	return rd ;
	}
	
	public void run(){
		BufferedReader xyz = openFile("please enter the file name");
		
		try{
			while(true){
				String line = xyz.readLine();
				if(line == null) break ; 
				println("Read Line :"+line);
			}
			xyz.close();
		}catch (IOException ex){
			throw new ErrorException(ex);
		}
		
	}
}