package lab13;

public class GTest {
	
	private static Graph g;
	
	public static void main(String[] args) {
		
		g = new Graph(5, false);
		g.insert(new Edge(1,0));
		//System.out.println(g.connected(1, 0));
		g.insert(new Edge(0,0));
		g.insert(new Edge(0,2));
		g.insert(new Edge(0,3));
		g.insert(new Edge(1,2));
		g.insert(new Edge(3,4));
		g.printEdges();
    	System.out.println();
		g.show();
		System.out.println();
    	
    	
		g = new Graph(5, true);
		g.insert(new Edge(1,0));
		//System.out.println(g.connected(1, 0));
		g.insert(new Edge(0,0));
		g.insert(new Edge(0,2));
		g.insert(new Edge(0,3));
		g.insert(new Edge(1,2));
		g.insert(new Edge(3,4));
		g.printEdges();
    	System.out.println();
		g.show();
		System.out.println();
	}
}
