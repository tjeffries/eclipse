#include<stdio.h>

int main()
{
	FILE *read_file;
	int x;	
	read_file = fopen("input.txt", "r");

	if (!read_file)
		return 1;

	for (x=1; x<=10; x++)
		printf("int %d: %d\n", 5, (int)fscanf(read_file, "%d\n"), 5);

	fclose(read_file);

	return  0;
}
