#include <iostream>
#include <vector>

using namespace std;

int scores[1000];
int memo[1000];

int main() {
	int n = 0;
	cin >> n;

	for (int i = 0; i < n; i++)
	{
		cin >> scores[i];
		memo[i] = -1;
	}

	//2차원 배열 만들기
	vector<vector<int>> cache(n, vector<int>(n, 0));

	int min, max;
	for (int i = 0; i < n; i++)
	{
		min = max = scores[i];

		for (int j = i+1; j < n; j++)
		{
			if (scores[j] > max) max = scores[j];
			else if (scores[j] < min) min = scores[j];

			cache[i][j] = max - min;
		}
	}

	memo[n - 1] = 0;
	for (int i = n-2; i >= 0; i--)
	{
		memo[i] = cache[i][n-1];
		for (int j = i+1; j < n; j++)
		{
			memo[i] = std::max(memo[i], cache[i][j-1] + memo[j]);
		}
	}

	cout << memo[0] << '\n';
}