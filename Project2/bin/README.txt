Author: Thomas Jeffries
Email: tjeffri2@u.rochester.edu
CSC172 Project 2 - Infix/Postfix evaluation

Main class in file PostFix.java
Stack structure in SLinkedStack.java, stack node in MyNode.java
Queue structure in DLinkedQueue.java, queue node in DLinkNode.java
usage: $ PostFix inFile outFile

The purpose of this project was to write an infix notation to postfix notation converter using stack
and queue data structures, and a postfix evaluator using a stack structure. The Shunting Yard Algorithm
is used to reorder a series of operators and operands into postfix notation, then repeated stack pushes,
pops, operations, and repushes are used to evaluate the resulting postfix expression.

EXTRA CREDIT: implemented modulo (with % symbol) and exponent (with ^ symbol) operations.