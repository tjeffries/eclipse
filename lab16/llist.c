#include <stdio.h>

int main(int argc, char *argv[]){
	printf("this\n");
	printf("compare:bcd & bcda: %d\n", mystrcmp("bcd", "bcda"));
}
/* srtcmp: return < 0 if s < t, 0 if s==t, > 0 if s > t */
int mystrcmp(char *s, char *t){
	//TODO: analyse past null so that longer strings ordered after shorter
	int i=0;
	do{
		if(s[i]>t[i]) return 1;
		if(s[i]<t[i]) return -1;
		i++;
	} while (s[i-1]!=0x00 && t[i-1]!=0x00);
	return 0;
}
