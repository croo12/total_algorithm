#include <iostream>
#include <list>

using namespace std;

constexpr int MAX = 1'000'000'000;
int n;

void calc(const string& s);

int main() {
	int t;
	cin >> t;

	while (t-- > 0) {
		string s;
		cin >> s >> n;
		calc(s);

	}
}

void calc(const string& s) {

	list<int> dq[26];

	int mn = MAX;
	int mx = -1;

	int idx = 0;
	for (char c : s)
	{
		int now = c - 'a';
		dq[now].emplace_back(idx);

		if (dq[now].size() == n) {
			mn = min(mn, dq[now].back() - dq[now].front() + 1);
			mx = max(mx, dq[now].back() - dq[now].front() + 1);

			dq[now].pop_front();
		}

		++idx;
	}

	if (mx != -1)
		cout << mn << ' ' << mx << '\n';
	else
		cout << -1 << '\n';
}