/* Author: Thomas Jeffries
 * This class defines a graph ADT using an adjacency matrix data structure
 */
package lab13;

public class Graph {
	
    private int vertexCount, edgeCount;
    boolean directed; // false for undirected graphs, true for directed
    private boolean adj[][];
    
    public Graph(int numVertices, boolean isDirected) {
    	vertexCount = numVertices;
    	directed = isDirected;
    	adj = new boolean[numVertices][numVertices];
    	
    	for(int i=0; i<numVertices; i++){
    		adj[i][i] = true;
    	}
    }
    
    public void insert(Edge e) {
    	if(!directed)//if undirected insert opposite direction as well
	    	adj[e.w][e.v] = true;
    	adj[e.v][e.w] = true;
    }
    
    public void delete(Edge e) {
    	if(!directed)//if undirected delete opposite direction as well
    		adj[e.w][e.v] = false;
    	adj[e.v][e.w] = false;
    	
    }
    
    public boolean connected(int node1, int node2) {
    	return adj[node1][node2];
    }
    
    public boolean isDirected() {
    	return directed;
    }
    
    public int vertices() { 
    	return vertexCount;
    }
    
    public int edges() {
    	return edgeCount;
    }
    
    public AdjList getAdjList(int vertex) {
    	return new AdjArray(vertex);
    }
    
    public void printEdges() {
    	for(int i=0; i<adj.length; i++){
    		for(int j=0; j<adj[0].length; j++){
    			System.out.printf("%c ", (adj[j][i])? 'x' : 'o');
    		}
    		System.out.println();
    	}
    }
    
    public void show () {
        for (int s = 0; s < vertices(); s++) {
            System.out.print(s + ": ");
            AdjList A = getAdjList(s);
            for (int t = A.begin(); !A.end(); t = A.next()) // use of iterator
                System.out.print(t + " ");
            System.out.println();
        }
    }
    
    private class AdjArray implements AdjList {
    	
    	private int v, i;//vertex and location in array
    	
    	public AdjArray(int vIn){
    		v = vIn;
    		i = -1;
    	}
    	
    	@Override
    	public int begin() {
    		i = -1;
    		return next();
    	}

    	@Override
    	public int next() {
    		for(++i; i<vertices(); i++){
    			if(connected(i, v))
    				return i;
    		}
    		return -1;
    	}

    	@Override
    	public boolean end() {
    		return (i<v) ? false : true;
    	}

    }
    
}
