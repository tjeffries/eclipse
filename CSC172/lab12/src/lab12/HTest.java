/* Author: Thomas Jeffries
 * Test class for HTable string hash table
 */
package lab12;

import java.io.FileInputStream;
import java.util.Scanner;

public class HTest {
	
	private static HTable hash;
	
	public static void main(String[] args) {
		hash = new HTable();
		
		if(args.length != 1){
			System.out.println("error, usage: HTest inFile");
			System.exit(1);
		}
		
		try{
			
			Scanner s = new Scanner(new FileInputStream(args[0]));
			//Hardcode: "C:/Users/thomas/workspace/lab12/bin/in.txt"
			while(s.hasNextLine()){
				String line = s.nextLine();
				String[] words = line.toLowerCase().replaceAll("[.,]", "").split("\\s+");
				//System.out.println(line);
				for(int i=0; i<words.length; i++)
					hash.insert(words[i]);
			}
			
			hash.print();
			System.out.printf("total read: %d\n", hash.total());
			System.out.printf("final capacity: %d\n", hash.capacity());
			System.out.printf("unique elements: %d\n", hash.uniques());
			System.out.printf("load factor: %f\n", hash.loadFactor());
		
		
			s.close();
		} catch(Exception e){
			e.printStackTrace();
		}
		
		/*hash.insert("this");
		hash.insert("this");
		hash.insert("that");
		hash.insert("that");
		hash.insert("and");
		hash.insert("the");
		hash.insert("other");
		hash.insert("thing");
		hash.insert("lots");
		hash.insert("of");
		hash.insert("stuff");*/
	}

}