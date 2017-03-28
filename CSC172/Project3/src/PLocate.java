import java.io.FileInputStream;
import java.util.Scanner;

/* Author: Thomas Jeffries
 * Main class for array based, O(n) Point Location
 * */
public class PLocate {
	
	static Line[] lines;
	static double[] points;
	//private static final int COUNTERCLOCKWISE = 0;
	//private static final int COLINEAR = 1;
	//private static final int CLOCKWISE = 2;
	
	public static void main(String[] args) {
		try{
			if(args.length != 1){
				System.out.println("error, usage: PLocate inFile");
				System.exit(1);
			}
			Scanner s = new Scanner(new FileInputStream(args[0]));
			//Hardcode: "C:/Users/thomas/workspace/Project3/bin/in.txt"
			
			if(s.hasNext()){
				int lineCnt = s.nextInt();
				lines = new Line[lineCnt];
				for(int i=0; i<lines.length; i++){
					
					lines[i] = new Line(s.nextDouble(), s.nextDouble(), 
										s.nextDouble(), s.nextDouble());
					lines[i].num = i;
					lines[i].print();
					s.nextLine();
				}
				
				s.nextLine();
				System.out.println("\ntest points:");
				
				points = new double[4];
				while(s.hasNextLine()){
					for(int i=0; i<4; i++){
						points[i] = s.nextDouble();
						System.out.printf("%.2f ", points[i]);
					}
					if(testPoints(points[0], points[1], points[2], points[3]))
						System.out.println("	points in same region!");
					else
						System.out.println("	points not in same region!");
				}
			}
			
			s.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	private static boolean testPoints(double p1x, double p1y, double p2x, double p2y){
		for(int i=0; i<lines.length; i++){
			//p1 and p2 fall on opposite sides of ith line
			if(lines[i].rel(p1x, p1y) != lines[i].rel(p2x, p2y) )
				return false;
		}
		return true;
	}
	/*
	private static void buildTree(){
		for(int i=0; i<lines.length; i++){
			tree.insert(lines[i]);
		}
		
		tree.printInOrder();
		tree.printPreOrder();
	}*/
}
