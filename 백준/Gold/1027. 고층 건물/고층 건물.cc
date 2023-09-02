#include <iostream>

using namespace std;

int building[50];
int memo[50];

int main() {
	int n;
	cin >> n;

	for (int i = 0; i < n; i++)
	{
		cin >> building[i];
	}

	int max = 0;
	for (int i = 0; i < n; i++)
	{
		int cnt = 0;
		double slight = -1'000'000'000.0;
		double d;
		for (int j = i + 1; j < n; j++) {
			d = (building[j] - building[i]) / (1.0 * (j - i));

			if (slight < d) {
				slight = d;
				++cnt;
			}
		}
		
		slight = 1'000'000'000.0;
		for (int j = i-1; j >= 0; j--)
		{
			d = (building[i] - building[j]) / (1.0 * (i - j));
			if (slight > d) {
				slight = d;
				++cnt;
			}
		}

		if (cnt > max) max = cnt;
	}
	
	cout << max << '\n';
}