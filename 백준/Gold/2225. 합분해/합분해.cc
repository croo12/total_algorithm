#include <iostream>
using namespace std;

constexpr int MOD = 1'000'000'000;

int n, k;
int memo[200][201];

int main() {
	cin >> n >> k;

	fill(memo[0], memo[0] + n + 1, 1);

	for (int i = 1; i < k; i++)
	{
		for (int j = 0; j <= n; j++)
		{
			memo[i][j] = memo[i - 1][j];
			for (int q = 0; q < j; q++)
			{
				memo[i][j] = (memo[i][j] + memo[i - 1][q]) % MOD;
			}
		}
	}

	cout << memo[k-1][n] << '\n';
}