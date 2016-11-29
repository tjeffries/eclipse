/* Author: Thomas Jeffries
 * This class defines a graph ADT using an adjacency matrix data structure
 * This code is simplified and adapted from lab 13.
 */
package project4;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;


public class Graph {
	
    private int vertexCount, edgeCount, vIndex;
    private Vertex vArray[];//maps vertex ID String to vertex index
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
    
    private double[] maxMin(){
    	double[] maxMin = new double[4];//minX, maxX, minY, maxY
    	maxMin[0] = maxMin[1] = vArray[0].x;
    	maxMin[2] = maxMin[3] = vArray[0].y;
    	for(int i=0; i<vertexCount; i++){
    		maxMin[0] = (maxMin[0] < vArray[i].x) ? vArray[i].x : maxMin[0];
    		maxMin[1] = (maxMin[1] > vArray[i].x) ? vArray[i].x : maxMin[1];
    		maxMin[2] = (maxMin[2] < vArray[i].y) ? vArray[i].y : maxMin[2];
    		maxMin[3] = (maxMin[3] > vArray[i].y) ? vArray[i].y : maxMin[3];
    	}
    	return maxMin;
    }
    
    public void drawGraph(JFrame frame){
    	int wWidth = 600;
    	int wHeight = 600;
    	int margin = 10;
    	
    	double[] maxMin = maxMin();
    	System.out.printf("%f %f %f %f\n", maxMin[0], maxMin[1], maxMin[2], maxMin[3]);
    	double scaleFactor = (maxMin[1]-maxMin[0] > maxMin[3]-maxMin[2]) ? 1/(maxMin[1]-maxMin[0]) : 1/(maxMin[3]-maxMin[2]);
    	scaleFactor = Math.abs(scaleFactor*wWidth*.9);
    	System.out.printf("scale factor: %f\n", scaleFactor);
    	
    	frame.setSize(wWidth, wHeight);
    	frame.setLocationRelativeTo(null);
    	
    	DPanel draw = new DPanel();
    	for(int i=0; i<vertexCount; i++){
    		for(int j=0; j<vertexCount; j++){
    			if(connected(i, j)){
    				
	    			DPanel.addLine(
    					Math.abs((int)((vArray[i].y-maxMin[3])*scaleFactor))+margin,
    					Math.abs((int)((vArray[i].x-maxMin[0])*scaleFactor))+margin,
    					Math.abs((int)((vArray[j].y-maxMin[3])*scaleFactor))+margin,
    					Math.abs((int)((vArray[j].x-maxMin[0])*scaleFactor))+margin );
    			}
    		}
    	}
    	
    	
    	frame.add(draw);
    	frame.setVisible(true);
    }
    
    @SuppressWarnings("serial")
    private static class DPanel extends JPanel{
    	
    	private static ArrayList<int[]> lines = new ArrayList<int[]>();
    	public static void addLine(int x1, int y1, int x2, int y2){
    		int[] d = {x1, y1, x2, y2};
    		lines.add(d);
    	}
    	
    	public void paintComponent(Graphics g){
    		super.paintComponent(g);
    		for(int i=0; i<lines.size(); i++){
    			int[] d = lines.get(i);
    			g.drawLine(d[0],d[1],d[2],d[3]);
    		}
    	}
    }
}
