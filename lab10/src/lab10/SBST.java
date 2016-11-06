/* Author: Thomas Jeffries
 * This is a simple Binary Search Tree implementation.
 * */
package lab10;

public class SBST<T extends Comparable<T>> implements BST<T> {
	
	SBSTNode<T> root;
	
	public SBST(){
		root = null;
	}
	
	public SBST(String data, String struct){
		
	}
	
	@Override
	public void insert(T x) {
		if(root==null){
			root = new SBSTNode<T>();
			root.data = x;
			root.parent = null;
		} else if(!root.lookup(x))
			root.insert(x);
	}

	@Override
	public void delete(T x) {
		if(root == null)
			return;
		root.delete(x);
	}

	@Override
	public boolean lookup(T x) {
		if(root == null)
			return false;
		return root.lookup(x);
	}
	
	public String strStructPreOrder(){
		return (root != null) ? root.strStructPreOrder() : null;
	}
	
	public String strStructInOrder(){
		return (root != null) ? root.strStructInOrder() : null;
	}
	
	public String strStructPostOrder(){
		return (root != null) ? root.strStructPostOrder() : null;
	}
	
	public String strDataPreOrder(){
		return (root != null) ? root.strDataPreOrder() : null;
	}
	
	public String strDataInOrder(){
		return (root != null) ? root.strDataInOrder() : null;
	}
	
	public String strDataPostOrder(){
		return (root != null) ? root.strDataPostOrder() : null;
	}

	@Override
	public void printPreOrder() {
		if(root == null){
			System.out.println("Tree is empty!");
			return;
		}
		root.printPreOrder();
		System.out.println();//newline pushes queued prints to console
		
	}

	@Override
	public void printInOrder() {
		if(root == null){
			System.out.println("Tree is empty!");
			return;
		}
		root.printInOrder();
		System.out.println();//newline pushes queued prints to console
	}

	@Override
	public void printPostOrder() {
		if(root == null){
			System.out.println("Tree is empty!");
			return;
		}
		root.printPostOrder();
		System.out.println();//newline pushes queued prints to console
	}

}

