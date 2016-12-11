/* Author: Thomas Jeffries
 * Solution for athenahealth first round coding challenge.
 * This class encapsulates both io and algorithms.
 * I have attempted to include comments wherever necessary for clarity.
 * */
package athena;

import java.util.HashSet;
import java.util.Iterator;


public class Mainold {
	
	public static void main(String[] args) {
		long startTime = System.nanoTime();//timing runtime
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
		
		
		long endTime = System.nanoTime();//end runtime
		long duration = (endTime - startTime);
		
		System.out.printf("runtime (milliseconds): %d\n", duration/1000000);
		System.out.printf("total unique deletion paths: %d\n", uniques);
	}
	
	//for three message input
	private static int combinations(char[] m0, char[] m1, char[] m2){
		HashSet<String> hs = new HashSet<String>();
		HashSet<String> hs1 = new HashSet<String>();
		HashSet<String> hsf = new HashSet<String>();
		c0(m0, m1, 0, 0, hs);
		
		Iterator<String> iter = hs.iterator();
		while(iter.hasNext()){
			hs1.clear();
			c0(iter.next().toCharArray(), m2, 0, 0, hs1);
			Iterator<String> iter1 = hs1.iterator();
			while(iter1.hasNext())
				hsf.add(iter1.next());
		}
		return hsf.size();
	}
	
	//for two message input
	private static int combinations(char[] m0, char[] m1){
		HashSet<String> hs = new HashSet<String>(128);
		c0(m0, m1, 0, 0, hs);
		return hs.size();
	}
	
	//counts and supplies lookahead info for recursive method c1
	private static void c0(char[] m0, char[] m1, int ind0, int ind1, HashSet<String> hs){
		int[] counts = new int[6];//character counts, format: {m0dot,m0dash,m0space, m1dot,m1dash,m1space}
		for(int i=0; i<m0.length; i++){
			if(m0[i]=='*') counts[0]++;
			else if(m0[i]=='-') counts[1]++;
			else counts[2]++;
		}
		for(int i=0; i<m1.length; i++){
			if(m1[i]=='*') counts[3]++;
			else if(m1[i]=='-') counts[4]++;
			else counts[5]++;
		}
		
		c1(m0, m1, ind0, ind1, counts, hs);
	}
	
	//recursive function
	private static void c1(char[] m0, char[] m1, int ind0, int ind1, int[] counts, HashSet<String> hs){
		//reached end of m1 array (all chars in hidden message found)
		if(ind1 == m1.length){
			//replace all placeholder "0" substrings in m0 with null strings (remove them)
			String tmp = new String(m0).replace("0", "");
			hs.add(tmp);
			return;
		}
		//return if reached end of m0
		if(ind0 == m0.length)
			return;
		
		//check if enough of each type of char in untested remainder of m0. if not, return (no path possible)
		for(int i=0; i<counts.length/2; i++)
			if(counts[i]<counts[i+(counts.length/2)])
				return;
		
		int[] tmpcnt = new int[counts.length];
		System.arraycopy(counts, 0, tmpcnt, 0, counts.length);
		
		if(m0[ind0]=='*') counts[0]--;
		else if(m0[ind0]=='-') counts[1]--;
		else counts[2]--;
		
		//match found for current chars in m0 and m1
		if(m0[ind0] == m1[ind1]){
			char[] tmp0 = new char[m0.length];
			char[] tmp1 = new char[m1.length];
			System.arraycopy(m0, 0, tmp0, 0, m0.length);
			System.arraycopy(m1, 0, tmp1, 0, m1.length);
			tmp0[ind0] = '0';
			tmp1[ind1] = '0';
			
			if(m1[ind1]=='*') counts[3]--;
			else if(m1[ind1]=='-') counts[4]--;
			else counts[5]--;
			//recurse on next char in m0 and m1
			c1(tmp0, tmp1, ind0+1, ind1+1, tmpcnt, hs);
		}
		
		//regardless of whether match found, test next char in m0 against current char in m1
		c1(m0, m1, ind0+1, ind1, tmpcnt, hs);
	}
}

//obsolete code from c1 (did not reduce runtime)
/*
//count numbers of characters ahead
int dot0, dot1, dash0, dash1, space0, space1;
dot0 = dot1 = dash0 = dash1 = space0 = space1 = 0;
int i0 = ind0;
int i1 = ind1;
while(i0 < m0.length){
	if(m0[i0]=='*')
		dot0++;
	else if(m0[i0]=='-')
		dash0++;
	else
		space0++;
	i0++;
}
while(i1 < m1.length){
	if(m1[i1]=='*')
		dot1++;
	else if(m1[i1]=='-')
		dash1++;
	else
		space1++;
	i1++;
}
//look ahead and see if path is possible given number of characters
if(dot0 < dot1 || dash0 < dash1 || space0 < space1)
	return;
*/
