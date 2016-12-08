/* Author: Thomas Jeffries
 * Solution for athenahealth first round coding challenge.
 * This class encapsulates both io and algorithms.
 * */
package athena;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Arrays;


public class Main {
	
	public static void main(String[] args) {
		System.out.println("program start");
		if(args.length != 2){
			System.out.println("incorrect arguments. usage: Main originalMessage hiddenMessage1");
			System.exit(1);
		}
		
		char[] m0 = args[0].toCharArray();
		char[] m1 = args[1].toCharArray();
		System.out.println("original message: ".concat(String.valueOf(m0)));
		System.out.println("hidden message 1: ".concat(String.valueOf(m1)));
		
		int uniques = combinations(m0, m1);
		System.out.println(uniques);
	}
	
	private static int combinations(char[] m0, char[] m1){
		HashSet<String> hs = new HashSet<String>(128);
		c1(m0, m1, 0, 0, hs);
		return hs.size();
	}
	
	private static void c1(char[] m0, char[] m1, int ind0, int ind1, HashSet<String> hs){
		//reached end of m1 array (all chars in hidden message found)
		if(ind1 == m1.length){
			String tmp = new String(m0);
			//replace all placeholder "0" substrings in m0 with null strings (remove them)
			tmp = tmp.replace("0", "");
			System.out.println(tmp);
			hs.add(tmp);
			return;
		}
		if(ind0 == m0.length)
			return;
		
		System.out.println(ind0);
		System.out.println(ind1);
		
		//match found for current chars in m0 and m1
		if(m0[ind0] == m1[ind1]){
			char[] tmp0 = m0;
			char[] tmp1 = m1;
			tmp0[ind0] = '0';
			tmp1[ind1] = '0';
			//System.out.println(tmp0);
			//System.out.println(tmp1);
			c1(tmp0, tmp1, ind0+1, ind1+1, hs);
		}
		
		//regardless of whether match found, test next char in m0
		c1(m0, m1, ind0+1, ind1, hs);
	}
	
	/*private static void c2(char[] m0, char[] m1, int index, HashSet<String> hs){
		
		char[] tmp1 = m0;
		char[] tmp2 = m1;
		
		if(m0[0]==m1[0]){
			//if no more chars in hidden message (all chars removed), add remainder to set hs
			tmp2 = Arrays.copyOfRange(m1, 1, m1.length);
			if(index == tmp2.length){
				hs.add(new String(tmp1));
				return;
			}
		}
		
		tmp1 = Arrays.copyOfRange(m0, 1, m0.length);
		//reached end of original message with hidden message chars still remaining
		if(tmp1.length == 0)
			return;
		
		//else call c2 again with input of 1 shorter original message
		c2(tmp1, tmp2, index, hs);
	}*/
}
