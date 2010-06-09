#include <cstdio>
#include <cstdlib>

int main(int argc, char **argv) {
	FILE *f = fopen("binary.dat","wb");
	int* printable;
	printable = (int*)malloc(sizeof(int));
	*printable = atoi(argv[1]);
	fwrite(printable,1,sizeof(int),f);
	fclose(f);
}
