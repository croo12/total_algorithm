#include <iostream>
#include <string>

using namespace std;

string s;
int len, n;
string* a;

int memo[100];

bool match(int idx, const string& now);
bool dfs(int idx);

int main() {
		cin >> s >> n;
		a = new string[n];
		len = s.length();

		fill(memo, memo + 100, -1);

		for (int i = 0; i < n; ++i) {
				cin >> a[i];
		}

		cout << (dfs(0) ? 1 : 0) << '\n';
}

bool dfs(int idx) {
		if (idx == len) return true;

		if (memo[idx] != -1) return memo[idx];

		for (int i = 0; i < n; ++i) {
				if (match(idx, a[i]) && dfs(idx + a[i].length())) return memo[idx] = 1;
		}

		return memo[idx] = 0;
}

bool match(int idx, const string& now) {
		int l = now.length();
		if (idx + l > len) return false;

		for (int i = 0; i < l; ++i)
		{
				if (len && now[i] != s[i + idx]) {
						return false;
				}
		}
		
		return true;
}