/* Author: Thomas Jeffries
 * This class defines a graph ADT using an adjacency matrix data structure
 * This code is simplified and adapted from lab 13.
 */
package project4;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Hashtable;


public class Graph {
	
    private int vertexCount, edgeCount;
    private Hashtable<String, Vertex> vTable;
    private Hashtable<String, LinkedList<String>> eTable;
    private ArrayList<String> vIDs;
    
    public Graph(int numVertices) {
    	vertexCount = numVertices;
    	vTable = new Hashtable<String, Vertex>(numVertices*2);
    	eTable = new Hashtable<String, LinkedList<String>>(numVertices*2);
    	vIDs = new ArrayList<String>(numVertices);
    }
    
    public void insert(Vertex v){
    	vTable.put(v.id, v);
    	//instantiate and hash empty LinkedList for each new vertex
    	LinkedList<String> tmp = new LinkedList<String>();
    	eTable.put(v.id, tmp);
    	vIDs.add(v.id);
    }
    
    public void insert(Edge e){
    	eTable.get(e.vid).add(e.wid);
    	edgeCount++;
    }
    
    public void delete(Edge e){
    	eTable.get(e.vid).remove(e.wid);
    	eTable.get(e.wid).remove(e.vid);
    }
    
    public boolean connected(int vid, int wid) {
    	return (eTable.get(vid).contains(wid) || eTable.get(wid).contains(vid));
    }
    
    public int vertices() { 
    	return vertexCount;
    }
    
    public int edges() {
    	return edgeCount;
    }
    
    private double[] maxMin(){
    	double[] maxMin = new double[4];//minX, maxX, minY, maxY
    	maxMin[0] = maxMin[1] = vTable.get(vIDs.get(0)).x;
    	maxMin[2] = maxMin[3] = vTable.get(vIDs.get(0)).y;
    	for(int i=0; i<vertexCount; i++){
    		maxMin[0] = (maxMin[0] < vTable.get(vIDs.get(i)).x) ? maxMin[0] : vTable.get(vIDs.get(i)).x;
    		maxMin[1] = (maxMin[1] > vTable.get(vIDs.get(i)).x) ? maxMin[1] : vTable.get(vIDs.get(i)).x;
    		maxMin[2] = (maxMin[2] < vTable.get(vIDs.get(i)).y) ? maxMin[2] : vTable.get(vIDs.get(i)).y;
    		maxMin[3] = (maxMin[3] > vTable.get(vIDs.get(i)).y) ? maxMin[3] : vTable.get(vIDs.get(i)).y;
    	}
    	return maxMin;
    }
    
    @SuppressWarnings("static-access")
	public void drawGraph(JFrame frame){
    	int margin = 3;
    	int windowSize = 600;
    	
    	double[] maxMin = maxMin();
    	System.out.printf("%f %f %f %f\n", maxMin[0], maxMin[1], maxMin[2], maxMin[3]);
    	double xSF = Math.abs(maxMin[1] - maxMin[0]);
    	double ySF = Math.abs(maxMin[3] - maxMin[2]);
    	double scaleFactor = (maxMin[1]-maxMin[0] > maxMin[3]-maxMin[2]) ? 1/(maxMin[1]-maxMin[0]) : 1/(maxMin[3]-maxMin[2]);
    
    	System.out.printf("scale factor: %f, x/y scale factor: %f\n", scaleFactor, ySF/xSF);
    	
    	frame.setSize((int)Math.ceil(windowSize*(ySF/xSF)+margin*2), (int)Math.ceil(1.35*(windowSize+margin*2)));
    	
    	scaleFactor = (xSF>ySF) ? Math.abs(scaleFactor*frame.getBounds().height) : Math.abs(scaleFactor*frame.getBounds().width);
    	
    	DPanel draw = new DPanel();
    	for(int i=0; i<vertices(); i++){
    		LinkedList<String> eList = eTable.get(vIDs.get(i));
    		Vertex v = vTable.get(vIDs.get(i));
    		for(int j=0; j<eList.size(); j++){
    			draw.addLine(	Math.abs((int)((v.y-maxMin[2])*scaleFactor))+margin,
    							Math.abs((int)((v.x-maxMin[1])*scaleFactor*1.3))+margin,
    							Math.abs((int)((vTable.get(eList.get(j)).y-maxMin[2])*scaleFactor))+margin,
    							Math.abs((int)((vTable.get(eList.get(j)).x-maxMin[1])*scaleFactor*1.3))+margin );
    		}
    	}
    	
    	frame.setLocationRelativeTo(null);
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
