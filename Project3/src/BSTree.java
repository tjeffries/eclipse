/* Author: Thomas Jeffries
 * 
 * */

public class BSTree{
	
	TNode root;
	
	public BSTree(){
		root = null;
	}
	
	public void insert(Line x) {
		if(root==null){
			root = new TNode();
			root.line = x;
		} else
			root.insert(x);
	}
	
	public void printInOrder(){
		if(root == null)
			return;
		root.printInOrder();
		System.out.println();
	}
	
	public void printPreOrder(){
		if(root == null)
			return;
		root.printPreOrder();
		System.out.println();
	}
}
