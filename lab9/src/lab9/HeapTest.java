/*
 * Author: Thomas Jeffries
 */
package lab9;

public class HeapTest {
	
	static MyHeap<Integer> heap = new MyHeap<Integer>(2);

	public static void main(String[] args) {
		heap.insert(new Integer(10));
		heap.insert(new Integer(4));
		heap.insert(new Integer(11));
		heap.insert(new Integer(2));
		heap.insert(new Integer(9));
		heap.insert(new Integer(9));
		heap.printHeap();
		System.out.println(heap.deleteMin().toString());
		System.out.println("Heapify and delete incompletely implemented. ran out of time, some points better than none.");
	}

}
