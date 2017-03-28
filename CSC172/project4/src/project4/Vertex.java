/* Author: Thomas Jeffries
 * 
 */
package project4;

public class Vertex {
	public String id;
	public double x, y;
	
	public Vertex(String inID, double inX, double inY){
		id = inID;
		x = inX;
		y = inY;
	}
	
	public boolean equals(String v){
		return (v.compareTo(id)==0) ? true : false;
	}
}
