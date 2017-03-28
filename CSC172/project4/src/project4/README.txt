Name: Thomas Jeffries
tjeffri2@u.rochester.edu
No partner
Project 4, CSC172

The purpose of this lab was to write a program that builds a graph from a given text file, renders the graph, 
and can find the shortest path between any two vertices on that graph and produce a minimum weighted 
spanning tree. Due to the large datasets I made extensive use of hash tables to reduce runtime of 
insertion of vertices and edges into the graph. Since the dataset was from road maps I also assumed that 
the graphs would be sparse and I implemented an adjacency list rather than the much more memory intensive 
adjacency matrix. 