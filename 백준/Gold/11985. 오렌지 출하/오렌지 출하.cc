#include <iostream>
#include <vector>
using namespace std;
typedef long long ll;

constexpr int MAX = 20'000;

int n, m, k;
int A[MAX];
ll cache[MAX];

ll divide(int idx);

int main() {
	cin >> n >> m >> k;

	for (int i = 0; i < n; i++)
	{
		cin >> A[i];
	}

	fill(cache, cache + n, -1);
	cache[n] = 0;

	ll answer = divide(0);
	cout << answer << '\n';
}

ll divide(int idx) {
	if (cache[idx] != -1) return cache[idx];

	ll v = 9223372036854775807LL;
	ll biggest = -1;
	ll smallest = 1'000'000'001;

	for (int i = idx; i < n && i < idx + m; ++i)
	{
		int now = A[i];
		if (biggest < now) biggest = now;
		if (smallest > now) smallest = now;

		ll calc = divide(i + 1) + k + (i - idx + 1) * (biggest - smallest);
		if (calc < v) v = calc;
	}

	cache[idx] = v;
	return cache[idx];
}