/*Author: Thomas Jeffries
 * self-expanding hash table implementation
 * hashing code sourced from "Data Structures and Algorithm Analysis in Java" Third Edition, p.319
 */
package lab12;

public class HTable {
	private String[] table;
	private int eCount;
	private int total;
	
	public HTable(){
		table = new String[13];
		eCount = 0;
		total = 0;
	}
	
	public void insert(String s){
		total++;
		if(s.compareTo("") == 0)//removes empty strings
			return;
		if(loadFactor() >= .50)
			rehash(2*table.length);
		int index = (int)sfold(s, table.length);
		while(table[index] != null){
			if(table[index].compareTo(s) == 0){
				//System.out.printf("multiple cases of String '%s'\n", s);
				return;
			}
			//System.out.printf("linearly probing! '%s' collided with '%s'\n", s, table[index]);
			index++;
			if(index == table.length)//if reach end of array, wrap to beginning
				index = 0;
		}
		table[index] = s;
		eCount++;
	}
	
	private void rehash(int size){
		//System.out.println("rehashing; new table size: "+size);
		total -= eCount;
		eCount = 0;
		//rehashes contents into new table with length of 'size'
		String[] tmp = table;
		table = new String[2*table.length];
		for(int i=0; i<tmp.length; i++)
			if(tmp[i] != null)
				insert(tmp[i]);
	}
	
	public int total(){
		return total;
	}
	
	public int uniques(){
		return eCount;
	}
	
	public int capacity(){
		return table.length;
	}
	
	public double loadFactor(){
		//System.out.println(eCount);
		//System.out.println(table.length);
		return (double)eCount/(double)table.length;
	}
	
	public void print(){
		System.out.printf("table contents: ");
		for(int i=0; i<table.length; i++)
			if(table[i] != null)
				System.out.printf("'%s' ", table[i]);
		System.out.println();
	}
	
	private long sfold(String s, int M) {
		int intLength = s.length() / 4;
		long sum = 0;
		for (int j = 0; j < intLength; j++) {
			char c[] = s.substring(j*4,(j*4)+4).toCharArray();
			long mult = 1;
			for (int k = 0; k < c.length; k++) {
				sum += c[k]*mult;
				mult *= 256;
			}
		}
		
		char c[] = s.substring(intLength*4).toCharArray();
		long mult = 1;
		for (int k = 0; k < c.length; k++) {
			sum += c[k]*mult;
			mult *= 256;
		}
		
		return(Math.abs(sum) % M);
	}
}
