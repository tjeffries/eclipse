package lab3;

public class SLinkTest {
	static SLinkedList<Integer> llist;
	
	public static void main(String[] args){
		llist = new SLinkedList<Integer>();
		
		for(int i=0; i<10; i++){
			llist.insert(new Integer(i));
		}
		
		llist.printList();
		try{
			System.out.println("lookup 6: returns "+llist.lookup(new Integer(6)).toString());
			System.out.println("lookup 10: returns "+llist.lookup(new Integer(10)).toString());
		} catch(Exception e){
			System.out.println("returned an error: "+e.toString());
		}
		llist.delete(new Integer(6));
		llist.delete(new Integer(0));
		llist.delete(new Integer(9));
		llist.delete(new Integer(10));
		llist.printList();
	}
}
