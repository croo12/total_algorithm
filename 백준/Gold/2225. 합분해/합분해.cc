#include <iostream>
using namespace std;

constexpr int MOD = 1'000'000'000;

int n, k;
int memo[201];

int main() {
	cin >> n >> k;
	memo[0] = 1;

	for (int i = 1; i <= k; i++)
	{
		for (int j = 1; j <= n; j++)
		{
			memo[j] = (memo[j] + memo[j - 1]) % MOD ;
		}
	}

	cout << memo[n] << '\n';
}