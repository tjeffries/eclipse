/* Author: Thomas Jeffries
 * This is a simple singlely linked stack implementation
 * 
 * Rather than use a singly linked list form lab 3 inside my stack object, I've transferred
 * and modified code from the SLinkedList class to implement a new (more tidy) stack class.
 * */
package lab6;

public class SLinkedStack<AnyType> {
	
	private MyNode<AnyType> head;
	
	public SLinkedStack(){
		head = null;
	}
	
	public void push(AnyType x){
		MyNode<AnyType> tmp = new MyNode<AnyType>();
		tmp.data = x;
		tmp.next = head;
		head = tmp;
	}
	
	public AnyType peek(){
		if(isEmpty())
			return null;
		return head.data;
	}
	
	public AnyType pop(){
		if(isEmpty())
			return null;
		AnyType data = head.data;
		head = head.next;
		return data;
	}
	
	public boolean isEmpty(){
		if(head == null)
			return true;
		return false;
	}
	
	public void printStack(){
		String s = "";
		MyNode<AnyType> n = head;
		while(n != null){
			s += n.data.toString()+" ";
			n = n.next;
		}
		System.out.println(s);
	}
}
