Author:Thomas Jeffries
athenahealth "hidden message" coding challenge - revised submission
12/16/2016


Class MainDynamic contains the updated solution to the challenge. My original attempt used simple recursive approach; 
this revision uses dynamic optimization to improve runtimes by about 60%. The non-dynamic solution ran in about 16 
seconds on my machine (for the Star Wars Saga test), this revised version runs under 6 seconds. To improve runtime 
I kept track of previously attempted deletion paths with another String HashSet to avoid checking already completed 
subpaths.
Recursive calls in non-dynamic solution: 163821866.
Recursive calls in dynamic solution:       6017153.


Output (copied from console):
java Main -_****_*___***_-_*-_*-*___*--_*-_*-*_***___***_*-_--*_*- -*--_---_-**_*- *-**_*_**_*-
>runtime (milliseconds): 5203
>total unique deletion paths: 11474