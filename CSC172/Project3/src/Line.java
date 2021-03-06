/* Author: Thomas Jeffries
 * Line object
 * */
public class Line {
	
	protected double x1, y1, x2, y2;
	int num;
	private static final int COUNTERCLOCKWISE = 0;
	private static final int COLINEAR = 1;
	private static final int CLOCKWISE = 2;
	
	public Line(double i1, double i2, double i3, double i4){
		x1 = i1;
		y1 = i2;
		x2 = i3;
		y2 = i4;
	}
	
	public int rel(double x0, double y0) {
	    double dx1 = x1 - x0;
	    double dy1 = y1 - y0;
	    double dx2 = x2 - x0;
	    double dy2 = y2 - y0;
	    if (dx1*dy2 > dy1*dx2) return COUNTERCLOCKWISE;
	    else if (dx1*dy2 < dy1*dx2) return CLOCKWISE;
	    else if ((dx1*dx2 < 0) || (dy1*dy2 < 0)) return CLOCKWISE;
	    else if ((dx1*dx1+dy1*dy1) < (dx2*dx2+dy2*dy2)) return COUNTERCLOCKWISE;
	    else return COLINEAR;
	}
	
	public void print(){
		System.out.printf("%.2f ", x1);
		System.out.printf("%.2f ", y1);
		System.out.printf("%.2f ", x2);
		System.out.printf("%.2f ", y2);
		System.out.println();
	}
	
	public double slope(){
		return (y2-y1)/(x2-x1);
	}
}
