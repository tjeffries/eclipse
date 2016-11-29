package project4;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.LinkedList;

public class Nav {
	
	

	public static void main(String[] args) {
		
		System.out.println("program start");
		if(args.length != 1){
			System.out.println("error, usage: Nav inFile");
			System.exit(1);
		}
		
		try{
			
			Scanner s = new Scanner(new FileInputStream(args[0]));
			//Hardcode: "C:/Users/thomas/workspace/project4/bin/project4/ur.txt"
			
			LinkedList<String> input = new LinkedList<String>();
			
			while(s.hasNextLine()){
				System.out.println(s.next());
			}
			
			s.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}

}
