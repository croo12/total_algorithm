#include <iostream>
#include <vector>

using namespace std;

long long memo[31][31];

long long dp(int one, int half);

int main() {
	for (size_t i = 0; i < 31; i++)
	{
		for (size_t j = 0; j < 31; j++)
		{
			memo[i][j] = -1;
		}
	}

	memo[0][1] = 1L;

	int n;
	cin >> n;

	while(n != 0) {
		long long  answer = dp(n-1, 1);
		cout << answer << '\n';

		cin >> n;
	} while (n != 0);
}

long long  dp(int one, int half) {
	if (memo[one][half] != -1) return memo[one][half];

	
	long long value = 0;

	if (one != 0) value += dp(one - 1, half + 1);
	if (half != 0) value += dp(one, half - 1);

	return memo[one][half] = value;
}