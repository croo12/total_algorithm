#include <iostream>
#include <vector>
using namespace std;
typedef long long ll;

constexpr int MAX = 20'000;

int n, m, k;
int A[MAX];
ll cache[MAX];

int main() {
	cin >> n >> m >> k;

	for (int i = 0; i < n; i++)
	{
		cin >> A[i];
	}

	fill(cache, cache + n, -1);
	cache[n] = 0;

	for (int i = n-1; i >= 0; --i)
	{
		ll v = 9223372036854775807LL;
		ll biggest = -1;
		ll smallest = 1'000'000'001;

		for (int j = i; j < n && j < i + m; j++) {
			int now = A[j];
			if (biggest < now) biggest = now;
			if (smallest > now) smallest = now;

			ll calc = cache[j + 1] + k + (j - i + 1) * (biggest - smallest);
			if (calc < v) v = calc;
		}

		cache[i] = v;
	}

	cout << cache[0] << '\n';
}