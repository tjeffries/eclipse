/*
 * Assignment: Bit Twiddling
 * Class: CSC 252 Spring 2017
 *
 * Name: Thomas Jeffries
 * Partner's Name: <Insert Partner's Name Here, or leave as is if you have no partner>
 *
 * TAs: Sayak Chakraborti (schakr11@cs.rochester.edu)
 *      Kevin Gerami(kgerami@u.rochester.edu)
 *
 * Instructions:
 * Fill in the following functions and turn this file in.
 *
 * Restrictions:
 * You MAY NOT change any of the function defintions.
 * You MAY NOT define any global variables, macros, or other functions.
 * You MAY NOT use any operations not listed in the function's description.
 * You MAY NOT use any control statements (if, loops, switch, function calls).
 * You MAY NOT use any constants outside of an unsigned byte (0 - 255 (0xFF) inclusive).
 * You MAY NOT cast any variables.
 * You MAY NOT use data types other than ints.
 *
 * You MAY define local variables.
 * You MAY use the assignment versions of allowed operators (+=, |=, etc).
 * You MAY use more than one operator per line.
 *
 * Your results will be tested on a machine that:
 * Uses 2s complement, 32-bit representations of integers.
 * Performs right shifts arithmetically.
 * Has unpredictable behavior when shifting an integer by more than the word size.
 * Bits are 0-indexed and numbered in order of increasing significance (right-to-left) so the 0th bit is the rightmost bit.
 *
 * If any of the instructions or function descriptions are unclear please contact the TA (see
 * above).
 */

/*
 * evenBits: Return the word with all even-numbered bits set to 1.
 *   Legal ops: ! ~ & ^ | + << >>
 */
int evenBits(void) {
	int a = 0x55;//binary 0101 0101
	int b = a | a<<8 | a<<16 | a<<24;
    return b;
}

/*
 * bitAnd: Compute the bitwise-and of x and y.
 *   Example: bitAnd(3, 5) = 1
 *   Legal ops: ~ |
 */
int bitAnd(int x, int y) {
    return ~((~x) | (~y));
}

/*
 * swapBytes: Swap the 0th (rightmost) and 2nd bytes of x.
 *   Example: swapBytes(0x33221100) = 0x33001122
 *   Legal ops: ! ~ & ^ | + << >>
 */
int swapBytes(int x) {
	int a = x&(0xFF << 16);
	a = a >> 16;
	int b = x&0xFF;
	b = b << 16;
	int c = x&(((0xFF<<16) | 0xFF) << 8);
	
    return a|b|c;
}

/*
 * rotateLeft: Rotate x to the left by n bits. (For 0 <= n <= 31)
 *   Example: rotateLeft(0x76543210, 8) = 0x54321076
 *   Legal ops: ~ & ^ | + << >>
 */
int rotateLeft(int x, int n) {
	int a = 0xFF<<24 | 0xFF<<16 | 0xFF<<8 | 0xFF;//a = 0xFFFFFFFF
	int b = a<<(32-n);
	
	int spillover = x & b;
	spillover = spillover>>(32-n);
	
	x = x<<n;
	
    return x|spillover;
}

/*
 * addOver: Determine if x + y overflows.
 *   Example: addOver(0x7FFFFFFE, 1) = 0
 *            addOver(0x7FFFFFFE, 2) = 1
 *   Legal ops: ! ~ & ^ | + << >>
 */
int addOver(int x, int y) {
	/*
	//mask & with 0x01 to capture 0th bit of each
	//right shift one
	//add x+y, add right shifted sum of captures 0th bits
	int x0 = x&0x01;
	int y0 = y&0x01;
	
	int a = (x0+y0)>>1;//0x01 if 0th bits both 1
	int b = (x>>1)+(y>>1);
	
	int c = b+a;
	*/
	
	//printf("x0 %x, y0 %x, a %x, b %x, c %x\n", x0, y0, a, b, c);
    return (((x+y)&(0x01<<31))>>31)&0x01;
}

/*
 * boundedMult: Compute 2x, but return the largest positive or negative signed integer if overflow
 *              would occur.
 *   Example: boundedMult(0x00000001) = 0x00000002
 *            boundedMult(0x50000000) = 0x7FFFFFFF (bound to largest positive int)
 *            boundedMult(0xA0000000) = 0x80000000 (bound to largest negative int)
 *   Legal ops: ! ~ & ^ | + << >>
 */
int boundedMult(int x) {
	//capture sign bit
	int sign = (x&(0x01<<31)>>31)&0x01;
	
    return 0;
}

/*
 * absHalf: Compute |x / 2| rounding towards 0.
 *   Example: absHalf(3)  = 1
 *            absHalf(-6) = 3
 *   Legal ops: ! ~ & ^ | + << >>
 */
int absHalf(int x) {
    return 0;
}

/*
 * lessThan: Check if x < y.
 *   Example: lessThan(4, 5) = 1
 *            lessThan(5, 4) = 0
 *   Legal ops: ! ~ & ^ | + << >>
 */
int lessThan(int x, int y) {
    return 0;
}

/*
 * multFiveEighths: Compute 5/8ths of x rounding towards 0.
 *   Example: multFiveEighths(8)  = 5
 *            multFiveEighths(-9) = -5
 *   Legal ops: ! ~ & ^ | + << >>
 */
int multFiveEighths(int x) {
    return 0;
}

/*
 * isPwr2: Check if x is a power of 2. Assume x > 0.
 *   Example: isPwr2(2) = 1
 *            isPwr2(3) = 0
 *   Legal ops: ! ~ & ^ | + << >>
 */
int isPwr2(unsigned int x) {
    return 0;
}
