

public class DQueueTest {
	
	private static DLinkedQueue<Integer> dqueue = new DLinkedQueue<Integer>();
	
	public static void main(String[] args){
		for(int i=0; i<10; i++){
			dqueue.enqueue(new Integer(i));
		}
		
		dqueue.printQueue();
		for(int i=0; i<5; i++){
			dqueue.dequeue();
			dqueue.enqueue(new Integer(10-i));
		}
		dqueue.printQueue();
	}
}
