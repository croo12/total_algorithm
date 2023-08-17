#include <iostream>
#include <vector>

using namespace std;

#define MAX 1'000'000'000'000'000LL;
int stones[5000];

int main() {
	int n = 0;
	cin >> n;

	for (int i = 0; i < n; i++)
	{
		cin >> stones[i];
	}

	long long memo[5000];
	memo[n-1] = 0;

	for (int i = n-2; i >= 0; i--)
	{
		memo[i] = MAX;
		
		for (int j = i + 1; j < n; j++)
		{
			long long energy = (j - i) * (1LL + abs(stones[i] - stones[j]));
			memo[i] = min(memo[i], max(energy, memo[j]));
		}
	}

	cout << memo[0] << '\n';
}