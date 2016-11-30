/* Author: Thomas Jeffries
 * This class is a wrapper object for the edge between two graph vertices.
 * This code is adapted from lab 13.
 */
package project4;

public class Edge {
	public final String id, vid, wid; // an edge from vertex ID v to vertex ID w
	
	public Edge(String inID, String inV, String inW) {
		id = inID;
		vid = inV;
		wid = inW;
	}
}
