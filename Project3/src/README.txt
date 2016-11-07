Name: Thomas Jeffries
tjeffri2@u.rochester.edu
No partner
Project 3, CSC172

The purpose of this lab was to write a program that would read from a file a set of lines within a 
unit square, then read pairs of test points within that unit square and determine whether the test 
points were in the same line-subdivided region. To do this I implemented a binary tree (BSTree), 
tree node (TNode), and line (Line) class as specified in the project directions. However, I was 
unable to get the line segment tree to build properly, and reverted to an array based 
implementation that executes in O(n) time rather than O(logn) time. The PLocate class contains the 
main method, algorithms, and file io, and the Line class is used to represent line segments and 
contains the method for determining one which side of the line a point falls. In order to determine
whether two points are in the same region, the testPoints method of PLocate iterates through the 
stored array of lines and compares the results of the rel method in the Line class on each point. 
If the rel method returns the same value for both points, they are on the same side of the line 
and the algorithm moves on to check the next line. If rel returns different values, the two points 
are on opposite sides of the line and thus not in the same region, and iteration terminates. If the 
loop reaches the end of the array without any line seperating the two points, we know that the 
points share a region.