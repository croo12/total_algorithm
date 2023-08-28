#include <iostream>

using namespace std;

constexpr int MAX = 1'000;
int students[MAX + 1][2];
bool vis[MAX + 1];
int d[MAX + 1];

void solution();
bool match(int idx);

int main() {
	int t;
	cin >> t;

	while (t-- > 0) {
		solution();
	}
}

void solution() {
	int n, m;
	cin >> n >> m;

	int count = 0;
	fill(d, d + MAX, 0);

	for (int i = 1; i <= m; i++)
	{
		cin >> students[i][0] >> students[i][1];
	}

	for (int i = 1; i <= m; i++)
	{
		fill(vis, vis + MAX, false);
		if (match(i)) ++count;
	}

	cout << count << '\n';
}

bool match(int idx) {
	int start = students[idx][0];
	int end = students[idx][1];

	for (int i = start; i <= end; i++)
	{
		if (!vis[i]) {
			vis[i] = true;

			if (d[i] == 0 || match(d[i])) {
				d[i] = idx;
				return true;
			}
		}
	}

	return false;
}