/* Author: Thomas Jeffries
 * This is a simple doubly-linked queue implementation.
 * */


public class DLinkedQueue<AnyType> {
	
	private DLinkNode<AnyType> head, tail;
	
	public DLinkedQueue(){
		head = tail = null;
	}
	
	//appends node to end of queue
	public void enqueue(AnyType x){
		DLinkNode<AnyType> tmp = new DLinkNode<AnyType>();
		tmp.data = x;
		if(isEmpty()){
			head = tmp;
			tail = tmp;
			return;
		}
		tmp.last = tail;
		tmp.next = null;
		tail.next = tmp;
		tail = tmp;
	}
	
	public AnyType dequeue(){
		if(isEmpty())
			return null;
		AnyType tmp = head.data;
		head = head.next;
		if(head != null)
			head.last = null;
		return tmp;
	}
	
	public AnyType peek(){
		if(isEmpty())
			return null;
		return head.data;
	}
	
	public boolean isEmpty(){
		if(head == null)
			return true;
		return false;
	}
	
	public void printQueue(){
		String s = "";
		DLinkNode<AnyType> n = head;
		while(n != null){
			s += n.data.toString()+" ";
			n = n.next;
		}
		System.out.println(s);
	}
}
