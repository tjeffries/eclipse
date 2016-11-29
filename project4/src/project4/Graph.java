/* Author: Thomas Jeffries
 * This class defines a graph ADT using an adjacency matrix data structure
 * This code is simplified and adapted from lab 13.
 */
package project4;

import javax.swing.*;

public class Graph {
	
    private int vertexCount, edgeCount, vIndex;
    private Vertex[] vArray;//maps vertex ID String to vertex index
    private boolean adj[][];
    
    public Graph(int numVertices) {
    	vertexCount = 0;
    	adj = new boolean[numVertices][numVertices];
    	vIndex = 0;
    	vArray = new Vertex[numVertices];
    	for(int i=0; i<numVertices; i++){
    		adj[i][i] = true;
    	}
    }
    
    public void insert(Vertex v){
    	vArray[vIndex] = v;
    	vIndex++;
    	vertexCount++;
    }
    
    private int findID(String v){
    	for(int i=0; i<vertexCount; i++)
    		if(vArray[i].equals(v))
    			return i;
    	return -1;
    }
    
    public void insert(Edge e) throws RuntimeException {
    	int vid = findID(e.vid);
    	int wid = findID(e.wid);
    	if(vid == -1 || wid == -1)
    		throw new RuntimeException("one or more required vertices not found in graph");
    	adj[findID(e.wid)][findID(e.vid)] = true;
    	adj[findID(e.vid)][findID(e.wid)] = true;
    	edgeCount++;
    }
    
    public void delete(Edge e) throws RuntimeException {
    	int vid = findID(e.vid);
    	int wid = findID(e.wid);
    	if(vid == -1 || wid == -1)
    		throw new RuntimeException("one or more required vertices not found in graph");
    	adj[findID(e.wid)][findID(e.vid)] = false;
    	adj[findID(e.vid)][findID(e.wid)] = false;
    	edgeCount--;
    	
    }
    
    public boolean connected(int node1, int node2) {
    	return adj[node1][node2];
    }
    
    public int vertices() { 
    	return vertexCount;
    }
    
    public int edges() {
    	return edgeCount;
    }
    
    public void printMatrix() {
    	for(int i=0; i<adj.length; i++){
    		for(int j=0; j<adj[0].length; j++){
    			System.out.printf("%c ", (adj[j][i])? 'X' : 'o');
    		}
    		System.out.println();
    	}
    }
    
    public void drawGraph(JFrame frame){
    	frame.setVisible(true);
    }
}
