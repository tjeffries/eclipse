/* Author: Thomas Jeffries
 * Tree node containing Line object
 * */
public class TNode {
	
	public Line line;
	public TNode leftChild;
	public TNode rightChild;
	private static final int CCW = 0;
	private static final int CL = 1;
	private static final int CW = 2;
	
	public void insert(Line x){
		if(line.rel(x.x1, x.y1) != line.rel(x.x2, x.y2)){//intersecting
			goLeft(x);
			goRight(x);
			return;
		}
		if(line.slope() < 0){
			if(line.rel(x.x1, x.y1) == CCW && line.rel(x.x2, x.y2) == CCW)
				goLeft(x);
			else if(line.rel(x.x1, x.y1) == CW && line.rel(x.x2, x.y2) == CW)
				goRight(x);
		} else {
			if(line.rel(x.x1, x.y1) == CCW && line.rel(x.x2, x.y2) == CCW)
				goRight(x);
			else if(line.rel(x.x1, x.y1) == CW && line.rel(x.x2, x.y2) == CW)
				goLeft(x);
		}
	}
	
	//goLeft/goRight calls insert on that child or adds if nonexistant
	public void goLeft(Line l){
		//no left child, add new node holding L
		if(leftChild == null){
			leftChild = new TNode();
			leftChild.line = l;
			return;
		}
		//left child exists, call insert on it
		leftChild.insert(l);
	}
	
	public void goRight(Line l){
		//no right child, add new node holding L
		if(rightChild == null){
			rightChild = new TNode();
			rightChild.line = l;
			return;
		}
		//right child exists, call insert on it
		rightChild.insert(l);
	}
	
	public void printInOrder(){
		if(leftChild != null)
			leftChild.printInOrder();
		else
			System.out.printf("X ");
		System.out.printf("%d ", line.num);
		if(rightChild != null)
			rightChild.printInOrder();
		else
			System.out.printf("X ");
	}
	
	public void printPreOrder(){
		System.out.printf("%d ", line.num);
		if(leftChild != null)
			leftChild.printPreOrder();
		else
			System.out.printf("X ");
		if(rightChild != null)
			rightChild.printPreOrder();
		else
			System.out.printf("X ");
	}
}
