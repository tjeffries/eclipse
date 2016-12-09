/* Author: Thomas Jeffries
 * Solution for athenahealth first round coding challenge.
 * This class encapsulates both io and algorithms.
 * I have attempted to include comments wherever necessary for clarity.
 * */
package athena;

import java.util.HashSet;
import java.util.Iterator;


public class Main {
	
	public static void main(String[] args) {
		System.out.println("program start: "+args.length);
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
		System.out.println(uniques);
	}
	
	//for three message input
	private static int combinations(char[] m0, char[] m1, char[] m2){
		HashSet<String> hs = new HashSet<String>();
		HashSet<String> hs1 = new HashSet<String>();
		c1(m0, m1, 0, 0, hs);
		
		int count = 0;
		Iterator<String> iter = hs.iterator();
		System.out.println(iter.hasNext());
		while(iter.hasNext()){
			hs1.clear();
			c1(iter.next().toCharArray(), m2, 0, 0, hs1);
			count += hs1.size();
		}
		return count;
	}
	
	//for two message input
	private static int combinations(char[] m0, char[] m1){
		HashSet<String> hs = new HashSet<String>(128);
		c1(m0, m1, 0, 0, hs);
		return hs.size();
	}
	
	private static void c1(char[] m0, char[] m1, int ind0, int ind1, HashSet<String> hs){
		//reached end of m1 array (all chars in hidden message found)
		if(ind1 == m1.length){
			//replace all placeholder "0" substrings in m0 with null strings (remove them)
			String tmp = new String(m0).replace("0", "");
			hs.add(tmp);
			return;
		}
		if(ind0 == m0.length)
			return;
		
		//match found for current chars in m0 and m1
		if(m0[ind0] == m1[ind1]){
			char[] tmp0 = new char[m0.length];
			char[] tmp1 = new char[m1.length];
			//java arrays by reference not value...may have briefly forgotten that...
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
