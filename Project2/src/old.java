/*
 * Author: Thomas Jeffries
 */
import java.io.*;
import java.util.*;

public class PostFix {
	
	private static int line;

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
	
	public static void main(String[] args) {
		try{
		Scanner s = new Scanner(new FileInputStream("C:/Users/thomas/workspace/Project2/bin/infix_expr_short.txt"));
		
		while(s.hasNext()){
			String line = s.nextLine();
			line = line.replaceAll("\\s+","");
			String regex = "(?<=[-+*/()&|<>!=])|(?=[-+*/()&|<>!=])"; //regex sourced from http://stackoverflow.com/questions/15983053/reading-mathmatical-expressions-from-user-input
			String[] tokens = line.split(regex);//split line about operators
			
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
					} else if(precedence(tokens[i]) < precedence(stack.peek()) ){
						queue.enqueue(stack.pop());
						stack.push(tokens[i]);
					} else {
						stack.push(tokens[i]);
					}
				}
			}//end of array iteration
			
			//enqueue remaining operators from stack:
			while(stack.peek() != null)
				queue.enqueue(stack.pop());
			//queue.printQueue();
			evaluate(queue);
			//break;//TEMP BREAKPOINT
		}//end while (reached end of file)
		
		s.close();
		
		} catch(Exception e) {
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
				q.dequeue();//pop operator
			} else //if next in queue is operand
				s.push(q.dequeue());
			//s.printStack();
		}
		line++;
		System.out.println(line+" "+s.peek());
		return 0;
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
		}
		return 0;
	}
	
	private static boolean isOperator(String s){
		return (s.contains("-") || s.contains("+") || s.contains("*") || s.contains("/") || 
				s.contains("(") || s.contains(")") || s.contains("&") || s.contains("|") || 
				s.contains("<") || s.contains(">") || s.contains("!") || s.contains("="));
	}
	
	private static int precedence(String s){
		if(s==null)
			return 0;//protection against empty stack
		if(s.contains("+") || s.contains("-"))
			return 1;
		if(s.contains("*") || s.contains("/"))
			return 2;
		return 0;
	}
}
