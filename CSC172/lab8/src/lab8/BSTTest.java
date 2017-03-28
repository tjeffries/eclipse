/* Author: Thomas Jeffries
 */
package lab8;

import java.util.Random;

public class BSTTest {
	
	private static BSTree<Integer> bst = new BSTree<Integer>();
	private static Random rand = new Random();
	
	public static void main(String[] args){
		
		System.out.println("add 10 random integers from 0-9 to new tree");
		for(int i=0; i<10; i++){
			int randInt = rand.nextInt(10);
			bst.insert(new Integer(randInt));
		}
		
		System.out.println("print In Order:");
		bst.printInOrder();
		
		System.out.println("print Pre Order:");
		bst.printPreOrder();
		
		System.out.println("print Post Order:");
		bst.printPostOrder();
		
		System.out.println("remove all 3s and 7s");
		
		if(bst.lookup(new Integer(3)) && bst.lookup(new Integer(7))){
			System.out.println("integer 3 and/or found in tree");
			bst.delete(new Integer(7));
			bst.delete(new Integer(3));
		}
		System.out.println("print In Order, after removals:");
		bst.printInOrder();
	}
}
