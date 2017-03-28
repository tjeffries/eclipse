/* Author: Thomas Jeffries
 * This is a simple Binary Search Tree implementation.
 * */
package lab8;

public class BSTree<T extends Comparable<T>> implements BST<T> {
	
	MyTreeNode<T> root;
	
	public BSTree(){
		root = null;
	}
	
	@Override
	public void insert(T x) {
		if(root==null){
			root = new MyTreeNode<T>();
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
