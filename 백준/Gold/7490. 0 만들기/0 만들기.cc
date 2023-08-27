#include <iostream>
#include <sstream>

using namespace std;

int t, n;
int sum;

int op[9];

void dfs(int now, int cache, bool flag);

int main() {
	cin >> t;

	op[0] = 0;

	while (t-- > 0) {
		cin >> n;

		sum = 0;

		dfs(2, 1, true);
		cout << '\n';
	}
}

void dfs(int now, int cache, bool flag) {
	if (now == n + 1) {

		if ((flag ? (sum + cache) : (sum - cache)) == 0) {
			stringstream ss;
			ss << 1;

			for (int i = 1; i < n; i++)
			{
				if (op[i] == 0) {
					ss << '+';
				}
				else if (op[i] == 1) {
					ss << '-';
				}
				else {
					ss << ' ';
				}
				
				ss << i + 1;
			}

			cout << ss.str() << '\n';
		}

		return;
	}

	op[now - 1] = 2;
	dfs(now + 1, cache * 10 + now, flag);

	sum = flag ? (sum + cache) : (sum - cache);
	
	op[now - 1] = 0;
	dfs(now + 1, now, true);
	
	op[now - 1] = 1;
	dfs(now + 1, now, false);

	sum = !flag ? (sum + cache) : (sum - cache);
};