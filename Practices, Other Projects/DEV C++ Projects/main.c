#include <stdio.h>
#include <conio.h>

main() 
{
	int m, n, ans;
	
	scanf("%d", &m);
	scanf("%d", &n);
	ans = factorial(m, n);
	
	printf("\n %d", ans);
}

int factorial(int m, int n)
{
	if(n == 1)
		return m;
	else
		return m * factorial(m, n-1);
}
