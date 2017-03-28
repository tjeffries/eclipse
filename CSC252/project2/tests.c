/*
 * Assignment: Bit Twiddling
 * Class: CSC 252 Spring 2017
 *
 * TAs: Sayak Chakraborti (schakr11@cs.rochester.edu)
 *      Kevin Gerami(kgerami@u.rochester.edu)
 *
 * You may use this program to write tests for your solutions in bits.c.
 */

#include <stdio.h>
#include <stdlib.h>
#include "bits.h"

int main(int argc, char **argv) {
    /* Write your tests here. */
	printf("test start\n");
	
	printf("evenBits: %x\n", evenBits(0x55555555));
	printf("bitAnd: %d\n", bitAnd(3,5));
	printf("swapBytes: %02x\n", swapBytes(0x33221100));
	
	
    return 0;
}
