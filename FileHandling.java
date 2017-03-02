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

public void run(){
	BufferedReader rd = openFile("Enter the file name :");
	
	try{
		while(true){
			String line = rd .readLine();
			if(line==null)break;
			println("Read line:["+line+"]");
		}
		rd.close();
	}catch(IOException ex){
		throw new ErrorException(ex);
	}
}
