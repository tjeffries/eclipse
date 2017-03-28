/*
 * Author: Thomas Jeffries
 */
import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

public class PostFix {
	
	//private static int lineNum;//for debuggin and console line numbering

	private static DLinkedQueue<String> queue;
	private static SLinkedStack<String> stack;
	private static final int ADD = 0;
	private static final int SUB = 1;
	private static final int MUL = 2;
	private static final int DIV = 3;
	private static final int GRT = 4;
	private static final int LES = 5;
	private static final int AND = 6;
	private static final int OR = 7;
	private static final int NOT = 8;
	private static final int EQL = 9;
	private static final int MOD = 10;
	private static final int EXP = 11;
	
	public static void main(String[] args) {
		try{
		if(args.length != 2){
			System.out.println("error, usage: PostFix inFile outFile");
			System.exit(1);
		}
		Scanner s = new Scanner(new FileInputStream(args[0]));
		File fout = new File(args[1]);
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		
		while(s.hasNextLine()){
			String line = s.nextLine();
			line = line.replaceAll("\\s+","");
			String regex = "(?<=[-+*/()&|<>!=%^])|(?=[-+*/()&|<>!=%^])"; //regex sourced from http://stackoverflow.com/questions/15983053/reading-mathmatical-expressions-from-user-input
			String[] tokens = line.split(regex);//split line about operators
	//System.out.println(line);
	//System.out.println(Arrays.toString(tokens));
			queue = new DLinkedQueue<String>();
			stack = new SLinkedStack<String>();
			
			for(int i=0; i<tokens.length; i++){
				if(!isOperator(tokens[i])){
					queue.enqueue(tokens[i]);//if token is an operand, enqueue it
				} else {
					if(tokens[i].contains(")")){
						while(! stack.peek().contains("("))
							queue.enqueue(stack.pop());//pop and enqueue from stack until open paren found
						stack.pop();//pop start paren, discard start and end paren
					} else if(tokens[i].contains("!") && stack.peek() != null && stack.peek().contains("!")) {
						stack.pop();
					}else if(tokens[i].contains("(")) {
						stack.push(tokens[i]);
					} else if(precedence(tokens[i]) < precedence(stack.peek()) ){
						queue.enqueue(stack.pop());
						stack.push(tokens[i]);
					} else {
						stack.push(tokens[i]);
					}
				}
				//queue.printQueue();
				//stack.printStack();
			}//end of array iteration
			
			//enqueue remaining operators from stack:
			while(stack.peek() != null)
				queue.enqueue(stack.pop());
			//queue.printQueue();
			printFile(evaluate(queue), bw);
			if(s.hasNextLine())
				bw.write("\n");
			//break;//TEMP BREAKPOINT
		}//end while (reached end of file)
		
		//close all open resources:
		s.close();
		bw.close();
		fos.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//evaluates postfix expression and writes to output file
	private static double evaluate(DLinkedQueue<String> q){
		SLinkedStack<String> s = new SLinkedStack<String>();
		while(!q.isEmpty()){
			if(isOperator(q.peek()) ){
				//pop proper number of operands from stack and pass to calculate(), push result to stack
				if(q.peek().contains("+"))
					s.push(Double.toString(calculate(Double.parseDouble(s.pop()), Double.parseDouble(s.pop()), ADD)) );
				if(q.peek().contains("-"))
					s.push(Double.toString(calculate(Double.parseDouble(s.pop()), Double.parseDouble(s.pop()), SUB)) );
				if(q.peek().contains("*"))
					s.push(Double.toString(calculate(Double.parseDouble(s.pop()), Double.parseDouble(s.pop()), MUL)) );
				if(q.peek().contains("/"))
					s.push(Double.toString(calculate(Double.parseDouble(s.pop()), Double.parseDouble(s.pop()), DIV)) );
				if(q.peek().contains(">"))
					s.push(Double.toString(calculate(Double.parseDouble(s.pop()), Double.parseDouble(s.pop()), GRT)) );
				if(q.peek().contains("<"))
					s.push(Double.toString(calculate(Double.parseDouble(s.pop()), Double.parseDouble(s.pop()), LES)) );
				if(q.peek().contains("&"))
					s.push(Double.toString(calculate(Double.parseDouble(s.pop()), Double.parseDouble(s.pop()), AND)) );
				if(q.peek().contains("|"))
					s.push(Double.toString(calculate(Double.parseDouble(s.pop()), Double.parseDouble(s.pop()), OR)) );
				if(q.peek().contains("!"))
					s.push(Double.toString(calculate(0, Double.parseDouble(s.pop()), NOT)) );
				if(q.peek().contains("="))
					s.push(Double.toString(calculate(Double.parseDouble(s.pop()), Double.parseDouble(s.pop()), EQL)) );
				if(q.peek().contains("%"))
					s.push(Double.toString(calculate(Double.parseDouble(s.pop()), Double.parseDouble(s.pop()), MOD)) );
				if(q.peek().contains("^"))
					s.push(Double.toString(calculate(Double.parseDouble(s.pop()), Double.parseDouble(s.pop()), EXP)) );
				q.dequeue();//pop operator
			} else //if next in queue is operand
				s.push(q.dequeue());
			//s.printStack();
		}
		//lineNum++;
		//System.out.println(lineNum+" "+new DecimalFormat("0.00").format(Double.parseDouble(s.peek())));
		return Double.parseDouble(s.peek());
	}
	
	private static double calculate(double b, double a, int op){
		switch(op)
		{
		case ADD: return a+b;
		case SUB: return a-b;
		case MUL: return a*b;
		case DIV: return a/b;
		case GRT: //System.out.printf("GREATER: %f, %f, %s\n", a, b, a>b); 
			if(a > b) return 1;
				return 0;
		case LES: //System.out.printf("LESS: %f, %f, %s\n", a, b, a<b); 
			if(a < b) return 1;
				return 0;
		case AND: //System.out.printf("AND: %f, %f, %s\n", a, b, a>0 && b>0);
			if(a>0 && b>0) return 1;
				return 0;
		case OR: //System.out.printf("OR: %f, %f, %s\n", a, b, a>0 || b>0);
			if(a>0 || b>0) return 1;
				return 0;
		case NOT: if(a>0) return 0;
				return 1;
		case EQL: //System.out.printf("EQUALS: %f, %f, %s\n", a, b, a==b);
			if(a == b) return 1;
				return 0;
		case MOD: //System.out.printf("MOD: %f, %f, %s\n", a, b, a%b);
				return a%b;
		case EXP: //System.out.printf("EXP: %f, %f, %f\n", a, b, Math.pow(a, b)); 
				return Math.pow(a, b);
		}
		return 0;
	}
	
	private static void printFile(double d, BufferedWriter bw){
		//System.out.println("hit printFile");
		try{
			bw.write(new DecimalFormat("0.00").format(d));
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static boolean isOperator(String s){
		return (s.contains("-") || s.contains("+") || s.contains("*") || s.contains("/") || 
				s.contains("(") || s.contains(")") || s.contains("&") || s.contains("|") || 
				s.contains("<") || s.contains(">") || s.contains("!") || s.contains("=")
				|| s.contains("%") || s.contains("^"));
	}
	
	private static int precedence(String s){
		if(s == null)
			return 0;
		if(s.contains("+") || s.contains("-") || s.contains("=") || s.contains(">") || s.contains("<"))
			return 1;
		if(s.contains("*") || s.contains("/")  || s.contains("&") || s.contains("|"))
			return 2;
		if(s.contains("%"))
			return 3;
		if(s.contains("^"))
			return 4;
		return 1;
	}
}
