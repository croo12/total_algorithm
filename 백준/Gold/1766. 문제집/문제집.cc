#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int cnt[32'000];
vector<int> adjList[32'000];

int main() {
	cin.tie(0); 
	ios_base::sync_with_stdio(0);

	int n, m;
	cin >> n >> m;

	for (int i = 0; i < m; i++)
	{
		int a, b;
		cin >> a >> b;

		adjList[a-1].emplace_back(b-1);
		cnt[b - 1]++;
	}

	priority_queue<int, vector<int>, greater<int>> pq;

	for (int i = 0; i < n; i++)
	{
		if (cnt[i] == 0)
			pq.push(i);
	}

	string s;

	while (!pq.empty()) {
		int now = pq.top();
		pq.pop();

		for (auto end : adjList[now])
			if (--cnt[end] == 0) pq.push(end);

		cout << now + 1 << ' ';
	}

	cout << '\n';
}