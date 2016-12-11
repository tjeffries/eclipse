Author: Thomas Jeffries
athenahealth "hidden message" coding challenge
12/10/2016

Class Main contains the solution to the challenge. I chose a fairly simple recursive approach, using a HashSet 
object to keep track of confirmed deletion paths, since HashSet offers fast insertion and checking, and does not 
allow duplicate elements. This algorithm clocks in well under the required execution time (around 16 seconds for 
Star Wars Saga test), but could possibly be improved with a dynamic programming solution that tracks and doesn't 
duplicate already attempted deletion paths.

Output (copied from console):
java Main -_****_*___***_-_*-_*-*___*--_*-_*-*_***___***_*-_--*_*- -*--_---_-**_*- *-**_*_**_*-
>original message: -_****_*___***_-_*-_*-*___*--_*-_*-*_***___***_*-_--*_*-
>hidden message 1: -*--_---_-**_*-
>hidden message 2: *-**_*_**_*-
>runtime (milliseconds): 16232
>total unique deletion paths: 11474