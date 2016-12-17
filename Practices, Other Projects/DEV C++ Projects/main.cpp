#include <iostream>
using namespace std;

int main()
{
	int a, i = 0, j = 0;
	cout << "How many numbers of A: ";
	cin >> a;
	while(i < a)
	{
		j = a;
		while(j > i)
		{
			cout << "A";
			j--;
		}
		cout << "\n";
		i++;
	}
}
