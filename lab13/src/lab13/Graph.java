/* Author: Thomas Jeffries
 * This class defines a graph ADT using an adjacency matrix data structure
 */
package lab13;

public class Graph {
	
    private int vertexCount, edgeCount;
    boolean directed; // false for undirected graphs, true for directed
    private boolean adj[][];
    
    public Graph(int numVerticies, boolean isDirected) { // your code here 
    	
    }
    
    public boolean isDirected() { // your code here }
    	return directed;
    }
    
    // return the number of vertices
    public int vertices() { 
    	return vertexCount;
    }
    
    // return number of edges
    public int edges() {
    	return edgeCount;
    }
    
    public void insert(Edge e) {
    	
    }
    
    public void delete(Edge e) {
    	
    }
    
    public boolean connected(int node1, int node2) {
    	return false;
    }
    
    //public AdjList getAdjList(int vertex) {
}
