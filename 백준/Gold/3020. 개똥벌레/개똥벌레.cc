#include <iostream>
#include <vector>

using namespace std;

const int MAX = 500'001;

int top[MAX];
int bottom[MAX];

int main() {
	int n, h;
	cin >> n >> h;

	int tmp;
	for (size_t i = 0; i < n; i++)
	{
		cin >> tmp;

		if (i % 2 == 0) {
			bottom[tmp - 1]++;
		}
		else {
			top[tmp - 1]++;
		}
	}

	for (int i = h - 1; i >= 0; i--)
	{
		top[i] += top[i + 1];
		bottom[i] += bottom[i + 1];
	}

	int ans = MAX;
	int cnt = 0;
	for (size_t i = 0; i < h; i++)
	{
		int now = bottom[i] + top[h - i - 1];

		if (now < ans) {
			ans = now;
			cnt = 1;
		}
		else if (now == ans) {
			cnt++;
		}
	}

	cout << ans << ' ' << cnt << '\n';
}