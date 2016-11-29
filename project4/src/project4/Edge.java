/* Author: Thomas Jeffries
 * This class is a wrapper object for the edge between two graph vertices.
 */
package project4;

public class Edge {
	public final int v, w; // an edge from v to w
	
	public Edge(int inV, int inW) {
		v = inV;
		w = inW;
	}
}