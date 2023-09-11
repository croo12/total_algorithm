#include <iostream>
#include <list>

using namespace std;

constexpr int MAX = 2'000'000;
int s[100'000];

int n, k;

bool check(const int &num);

int main() {
	cin >> n >> k;

	for (int i = 0; i < n; i++)
	{
		cin >> s[i];
	}

	int st = 0;
	int ed = MAX;
	int mid;

	while (st <= ed) {
		mid = (st + ed) / 2;

		if (check(mid)) {
			st = mid + 1;
		}
		else {
			ed = mid - 1;
		}
	}

	cout << ed << endl;
}

bool check(const int& num) {
	int sum = 0;
	int cnt = 0;
	for (int i = 0; i < n; i++)
	{
		sum += s[i];
		if (sum >= num) {
			cnt++;
			sum = 0;
		}
	}

	return cnt >= k;
}