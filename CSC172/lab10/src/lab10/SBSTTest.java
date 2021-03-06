/* Author: Thomas Jeffries
 */
package lab10;

//import java.util.Random;

public class SBSTTest {
	
	private static SBST<Integer> bst = new SBST<Integer>();
	//private static Random rand = new Random();
	
	public static void main(String[] args){
		
		//System.out.println("add 10 random integers from 0-9 to new tree");
		/*
		for(int i=0; i<10; i++){
			int randInt = rand.nextInt(10);
			bst.insert(new Integer(randInt));
		}
		*/
		bst.insert(new Integer(37));
		bst.insert(new Integer(24));
		bst.insert(new Integer(42));
		bst.insert(new Integer(7));
		bst.insert(new Integer(2));
		bst.insert(new Integer(40));
		bst.insert(new Integer(43));
		bst.insert(new Integer(32));
		bst.insert(new Integer(120));
		
		
		System.out.printf("print Pre Order: ");
		bst.printPreOrder();
		
		System.out.printf("print In Order: ");
		bst.printInOrder();
		
		System.out.printf("print Post Order: ");
		bst.printPostOrder();
		
		System.out.printf("data Pre Order: ");
		System.out.println(bst.strDataPreOrder());
		
		System.out.printf("data In Order: ");
		System.out.println(bst.strDataInOrder());
		
		System.out.printf("data Post Order: ");
		System.out.println(bst.strDataPostOrder());
		
		System.out.printf("struct Pre Order: ");
		System.out.println(bst.strStructPreOrder());
		System.out.printf("struct In Order: ");
		System.out.println(bst.strStructInOrder());
		System.out.printf("struct Post Order: ");
		System.out.println(bst.strStructPostOrder());
		
		
		//System.out.println("remove all 3s and 7s");
		if(bst.lookup(new Integer(3)) && bst.lookup(new Integer(7))){
			//System.out.println("integer 3 and/or found in tree");
			bst.delete(new Integer(7));
			bst.delete(new Integer(3));
		}
		//System.out.println("print In Order, after removals:");
		//bst.printInOrder();
	}
}
