/* Author: Thomas Jeffries
 * */
package lab6;

import lab6.SLinkedStack;

public class SLinkTest{
	
	static SLinkedStack<Integer> lstack;
	
	public static void main(String[] args){
		lstack = new SLinkedStack<Integer>();
		
		System.out.println("pushed ints 0-9 to the stack");
		for(int i=0; i<10; i++){
			lstack.push(new Integer(i));
		}
		System.out.printf("stack print: ");
		lstack.printStack();
		
		System.out.println("popping 3 elements from stack");
		for(int i=0; i<3; i++){
			lstack.pop();
		}
		
		System.out.println("stack peek: "+lstack.peek().toString());
		
		System.out.printf("stack print after pops: ");
		lstack.printStack();
	}
}

