/* Author: Thomas Jeffries
 * Solution for athenahealth first round coding challenge, improved with dynamic optimization.
 * This class encapsulates both io and algorithms.
 * I have attempted to include comments wherever necessary for clarity.
 * */
package athena;

import java.util.HashSet;
import java.util.Iterator;


public class MainDynamic {
	
	private static HashSet<String> attempted;//set of all attempted deletion paths
	private static long rCount;//recursion call counter
	
	public static void main(String[] args) {
		long startTime = System.nanoTime();//timing runtime
		rCount = 0;
		attempted = new HashSet<String>(2048);
		
		//System.out.println("program start: "+args.length);
		if(args.length < 3){
			System.out.println("incorrect arguments. usage: Main originalMessage hiddenMessage1 hiddenMessage2");
			System.exit(1);
		}
		
		char[] m0 = args[0].toCharArray();
		char[] m1 = args[1].toCharArray();
		char[] m2 = args[2].toCharArray();
		System.out.println("original message: ".concat(String.valueOf(m0)));
		System.out.println("hidden message 1: ".concat(String.valueOf(m1)));
		System.out.println("hidden message 2: ".concat(String.valueOf(m2)));
		
		int uniques = combinations(m0, m1, m2);
		//int uniques = combinations(m0, m1);
		
		
		long endTime = System.nanoTime();//end runtime
		long duration = (endTime - startTime);
		
		System.out.printf("recursion count: %d\n", rCount);
		System.out.printf("runtime (milliseconds): %d\n", duration/1000000);
		System.out.printf("total unique deletion paths: %d\n", uniques);
	}
	
	//for three message input
	private static int combinations(char[] m0, char[] m1, char[] m2){
		HashSet<String> hs = new HashSet<String>();
		HashSet<String> hs1 = new HashSet<String>();
		HashSet<String> hsf = new HashSet<String>();
		c1(m0, m1, 0, 0, hs);
		//iterate through deletion paths found for message minus hidden message 1
		Iterator<String> iter = hs.iterator();
		while(iter.hasNext()){
			attempted.clear();
			hs1.clear();
			c1(iter.next().toCharArray(), m2, 0, 0, hs1);
			Iterator<String> iter1 = hs1.iterator();
			while(iter1.hasNext())
				hsf.add(iter1.next());
		}
		return hsf.size();
	}
	
	//for two message input
	private static int combinations(char[] m0, char[] m1){
		HashSet<String> hs = new HashSet<String>(128);
		c1(m0, m1, 0, 0, hs);
		return hs.size();
	}
	
	//recursive function
	private static void c1(char[] m0, char[] m1, int ind0, int ind1, HashSet<String> hs){
		//for tracking recursion count
		rCount++;
		
		//reached end of m1 array (all chars in hidden message found)
		if(ind1 == m1.length){
			//replace all placeholder "0" substrings in m0 with null strings (remove them)
			String tmp = new String(m0).replace("0", "");
			hs.add(tmp);
			return;
		}
		//return if reached end of m0, or if more untested characters remaining in m1 than m0 (no path possible)
		if(ind0 == m0.length || m1.length-ind1 > m0.length-ind0)
			return;
		
		//System.out.println(new String(m0).substring(0, ind0).replace("0", "").concat("  ").concat(new String(m1)));
		if(!attempted.add(new String(m0).substring(0, ind0).replace("0", "").concat(new String(m1)))){
			//System.out.println("ALREADY ATTEMPTED");
			return;
		}
		
		//match found for current chars in m0 and m1
		if(m0[ind0] == m1[ind1]){
			char[] tmp0 = new char[m0.length];
			char[] tmp1 = new char[m1.length];
			System.arraycopy(m0, 0, tmp0, 0, m0.length);
			System.arraycopy(m1, 0, tmp1, 0, m1.length);
			tmp0[ind0] = '0';
			tmp1[ind1] = '0';
			//recurse on next char in m0 and m1
			c1(tmp0, tmp1, ind0+1, ind1+1, hs);
		}
		
		//regardless of whether match found, test next char in m0 against current char in m1
		c1(m0, m1, ind0+1, ind1, hs);
	}
}
