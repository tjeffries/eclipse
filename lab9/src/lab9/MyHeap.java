/*
 * Author: Thomas Jeffries
 */
package lab9;

public class MyHeap<T extends Comparable<T>> implements Heap<T> {

	private int size;
	private T[] heap;
	
	public MyHeap(){
		size = 0;
		heap = (T[]) new Comparable[10];
	}
	
	public MyHeap(int startSize){
		size = 0;
		heap = (T[]) new Comparable[startSize];
	}
	
	@Override
	public void insert(T item) {
		// TODO Auto-generated method stub
		if(size == heap.length -1)
			doubleSize();
		
		int pos = ++size;
		bubbleUp(pos, item);
	}

	@Override
	public boolean isEmpty() {
		if(size == 0) return true;
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public T deleteMin() {
		// TODO Auto-generated method stub
		if(isEmpty()) return null;
		
		T tmp = heap[1];
		heap[1] = heap[size+1]; //head of heap become last item in heap
		size--;
		bubbleDown(1);
		
		return tmp;
	}
	
	private void bubbleUp(int pos, T item){
		for(; pos > 1 && item.compareTo(heap[pos/2]) > 0; pos = pos/2)
			heap[pos] = heap[pos/2];
		heap[pos] = item;
	}
	
	private void bubbleDown(int k){
		T item = heap[k];
		/*
		if(k*2 <= size && heap[k].compareTo(heap[k*2]) < 0){
			heap[k] = heap[k*2];
			heap[k*2] = item;
		} else if(k*2+1 <= size && heap[k].compareTo(heap[k*2+1]) < 0){
		}
		*/
		System.out.println("BubbleDown doesn't work yet. I ran out of time :(");
	}
	
	private void doubleSize(){
		T[] old = heap;
		heap = (T[]) new Comparable[old.length*2];
		System.arraycopy(old, 1, heap, 1, size);
		System.out.println("size doubled");
	}
	
	public void printHeap(){
		for(int i=1; i<(size+1); i++)
			System.out.printf("%s ", heap[i].toString());
		System.out.println();
	}

}
