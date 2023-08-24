#include <iostream>
#include <vector>

using namespace std;

int main() {
	int n, k;
	cin >> n >> k;

	vector<vector<int>> memo(2, vector<int>(n+1, 0));

	for (int i = 0; i < k; i++)
	{
		int weight, time;
		cin >> weight >> time;

		for (int j = 0; j <= n; j++)
		{
			if (memo[1][j] < memo[0][j]) memo[1][j] = memo[0][j];

			if (j + time <= n && memo[0][j] + weight > memo[0][j + time]) {
				memo[1][j + time] = memo[0][j] + weight;
			}
		}

		memo[0] = memo[1];
		memo[1] = vector<int>(n + 1, 0);
	}
	
	cout << memo[0][n];
}