Name: Thomas Jeffries
tjeffri2@u.rochester.edu
No partner
Lab 12, CSC172

In this lab I implemented a simple hash table and used it to count unique appearances of words in 
text files. The hashing algorithm is from page 319 of "Data Structures and Algorithm Analysis in 
Java" Third Edition. Linear Probing was used to for collision avoidance, and a maximum load factor 
of <.50 (<50%) is permitted before the storage array is resized (doubled) and the table rehashed into 
the new array. There are only two classes in this implementation: HTable, which is the hash table 
class, and HTest, which contains the main method and implements file io and console output. Input text 
is stripped of simple punctuation (commas and periods) before being split into words (split around 
spaces) and inserted into the hash table. The file 'in.txt' contains 10 paragraphs of Lorem Ipsum 
input text for testing.