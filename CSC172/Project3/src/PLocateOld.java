import java.io.FileInputStream;
import java.util.Scanner;

/* Author: Thomas Jeffries
 * Main class for Point Location
 * */
public class PLocate {
	
	static Line[] lines;
	static BSTree tree = new BSTree();
	private static final int COUNTERCLOCKWISE = 0;
	private static final int COLINEAR = 1;
	private static final int CLOCKWISE = 2;
	
	public static void main(String[] args) {
		try{
			Scanner s = new Scanner(new FileInputStream("C:/Users/thomas/workspace/Project3/bin/in.txt"));
			
			if(s.hasNext()){
				int lineCnt = s.nextInt();
				lines = new Line[lineCnt-1];
				for(int i=0; i<lines.length; i++){
					
					lines[i] = new Line(s.nextDouble(), s.nextDouble(), 
										s.nextDouble(), s.nextDouble());
					lines[i].num = i;
					lines[i].print();
					s.nextLine();
				}
				
				s.nextLine();
				System.out.println();
				
				buildTree();
				
				while(s.hasNextLine()){
					for(int j=0; j<4; j++){
						System.out.printf("%.2f ", s.nextDouble());
					}
					System.out.println();
				}
			}
			
			s.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private static void buildTree(){
		for(int i=0; i<lines.length; i++){
			tree.insert(lines[i]);
		}
		
		tree.printInOrder();
		tree.printPreOrder();
	}
}
