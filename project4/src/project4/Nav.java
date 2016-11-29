/* Author: Thomas Jeffries
 * This is the main class for project 4, and performs io and pathfinding algorithms.
 */
package project4;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.LinkedList;
import javax.swing.*;

public class Nav {
	
	private static Graph g;
	
	public static void main(String[] args) {
		
		if(args.length < 1){
			System.out.println("error, usage: Nav inFile");
			System.exit(1);
		}
		
		try{
			
			Scanner s = new Scanner(new FileInputStream(args[0]));
			//Hardcode: "C:/Users/thomas/workspace/project4/bin/project4/ur.txt"
			
			LinkedList<String> input = new LinkedList<String>();
			
			int numIntersects = 0;
			while(s.hasNextLine()){
				input.add(s.next());
				//System.out.println(input.peekLast());
				/* The following is faster but assumes all intersections declared before any roads:
				if(numIntersects == 0 && input.peekLast().compareTo("r")==0)
					numIntersects = input.size()/4;*/
			}
			for(int i=0; i<input.size(); i+=4)
				if(input.get(i).compareTo("i")==0)
					numIntersects++;
			
			g = new Graph(numIntersects);
			for(int i=0; i<numIntersects; i++){
				input.pop();
				g.insert(new Vertex(input.pop(), Double.parseDouble(input.pop()), Double.parseDouble(input.pop()) ));
			}
			while(!input.isEmpty()){
				input.pop();
				g.insert(new Edge(input.pop(), input.pop(), input.pop() ));
			}
			
			System.out.println("Num vertices in graph g: "+g.vertices());
			System.out.println("Num edges in graph g: "+g.edges());
			//g.printMatrix();
			
			if(args.length > 1){
				if(args[1].compareTo("[-show]")==0 || args[2].compareTo("[-show]")==0)
					display();
			}
			
			s.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static void display(){
		JFrame frame = new JFrame("HelloWorldSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 200);
		JLabel label = new JLabel("Hello World");
		frame.getContentPane().add(label);
		
		frame.setLocationRelativeTo(null);
		
		g.drawGraph(frame);
	}
}
