/* Author: Thomas Jeffries
 * This simple singlely linked list class only allows unique
 * elements, and does not allow duplicate items.
 * */
package lab3;

public class SLinkedList<AnyType> implements SimpleLinkedList<AnyType> {
	
	private MyNode<AnyType> head;
	
	public SLinkedList(){
		head = null;
	}
	
	/* insert method appends element to end of list.
	 * insert is an O(n) operation. 
	 * 
	 * Using contains() method to verify uniqueness, insert is still O(n).
	 */
	public void insert(AnyType x){
		MyNode<AnyType> tmp = new MyNode<AnyType>();
		if(!contains(x)){
			tmp.data = x;
			if(head==null){
				head = tmp;
				return;
			}
			MyNode<AnyType> n = head;
			while(n.next != null)
				n = n.next;
			n.next = tmp;
		}
	}
	public void delete(AnyType x){
		if(head == null) return;
		if(head.data.equals(x)){
			head = head.next;
			return;
		}
		MyNode<AnyType> n, prev;
		prev = head; n = head.next;
		while(n != null){
			if(n.data.equals(x)){
				prev.next = n.next;
				return;
			}
			prev = n;
			n = n.next;
		}
	}
	public boolean contains(AnyType x){
		MyNode<AnyType> n = head;
		while(n != null){
			if(n.data.equals(x))
				return true;
			n = n.next;
		}
		return false;
	}
	public AnyType lookup(AnyType x){
		if(contains(x))
			return x;
		return null;
	}
	
	public boolean isEmpty(){
		if(head == null)
			return true;
		return false;
	}
	public void printList(){
		String s = "";
		MyNode<AnyType> n = head;
		while(n != null){
			s += n.data.toString()+" ";
			n = n.next;
		}
		System.out.println(s);
	}
}
