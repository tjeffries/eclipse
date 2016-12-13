#include <stdlib.h>
#include <stdio.h>

int mystrcmp(char *s, char *t);
void mystrcat(char *dest, char *source);
void mystrcpy(char *s, char *dest);

int main(int argc, char *argv[]){
	int i;
	char *s2, *s3, *s4;
	char s1[128] =  "this";
	char s5[30];
	s2 = " that";
	s3 = " and the other thing";
	s4 = "the end";
	printf("Arguments: ");
	for(i=1; i<argc; i++){
		if(mystrcmp(argv[i], "thomas")==0){
			mystrcpy("hello", s5);
			mystrcat(s5, " ");//add space between hello and name
			mystrcat(s5, argv[i]);
			printf("\n%s\n", s5);
		} else
			printf("%s ", argv[i]);
	} printf("\n");
	
	//test various string functions
	printf("Functions:\n");
	printf("compare:bcd & bcda: %d\n", mystrcmp("bcd", "bcda"));
	mystrcat(s1, s2);
	mystrcat(s1, s3);
	printf("concat: %s\n", s1);
	mystrcpy(s4, s1);
	printf("copy: %s\n", s1);
	
	//fill and print out array of squares
	int sqrs[16];
	printf("Squares: ");
	for(i=0; i<sizeof(sqrs)/sizeof(sqrs[0]); i++){
		sqrs[i] = i*i;
	}
	for(i=0; i<sizeof(sqrs)/sizeof(sqrs[0]); i++){
		printf("%d ", *(sqrs+i));
	} printf("\n");
}
/* srtcmp: return < 0 if s < t, 0 if s==t, > 0 if s > t */
int mystrcmp(char *s, char *t){
	int i=0;
	do{
		if(s[i]>t[i]) return 1;
		if(s[i]<t[i]) return -1;
		i++;
	} while (s[i-1] != 0x00 && t[i-1] != 0x00);
	return 0;
}
//warning, this is not safe! May end up stack bashing if dest isn't large enough.
void mystrcat(char *dest, char *source){
	while(*dest != 0x00)
		*dest++;
	while(*source != 0x00)
		*dest++ = *source++;
	*dest = 0x00;
}
//warning, this is not safe! May end up stack bashing if dest isn't large enough.
void mystrcpy(char *s, char *dest){
	while(*s != 0x00)
		*dest++ = *s++;
	*dest = 0x00;
}