#include <iostream>
#include <queue>
#include <unordered_map>

using namespace std;

int main() {

	int k, n;
	cin >> n >> k;

	int sum = 0;
	int answer = 0;

	int* slice = new int[n];

	int div = 1;
	for (int i = n-1; i >= 0; i--)
	{
		slice[i] = div;
		div *= 10;
	}
	
	for (size_t i = 1; i <= n; i++)
	{
		int tmp;
		cin >> tmp;

		sum = sum * 10 + tmp;
		answer = answer * 10 + i;
	}

	unordered_map<int, int> vis;

	queue<int> q;

	q.push(sum);
	vis[sum] = 0;

	while (!q.empty()) {
		int num = q.front();
		q.pop();

		for (size_t i = 0; i <= n - k; i++)
		{
			int reverse = 0;
			for (int d = k - 1; d >= 0; d--)
			{
				reverse = reverse * 10 + (num / slice[i+d]) % 10;
			}

			int after = num / (slice[i]*10) * (slice[i]*10) + reverse * slice[i + k - 1] + num % slice[i + k - 1];

			if (vis.find(after) == vis.end()) {
				vis[after] = vis[num] + 1;
				q.push(after);
			}
		}

	}

	if (vis.find(answer) == vis.end()) {
		cout << -1 << '\n';
	}
	else 
	{
		cout << vis[answer] << '\n';
	}

}