#include <iostream>
#include <vector>

int p[500'000];

int find(int x) {
	return p[x] == x ? x : p[x] = find(p[x]);
}

bool union_numbers(int x, int y) {
	int xp = find(x);
	int yp = find(y);
	p[xp] = yp;
	return xp != yp;
}

int main() {
	int n, m;
	std::cin >> n >> m;

	for (size_t i = 0; i < n; i++)
	{
		p[i] = i;
	}

	int cnt = 0;
	while (m-- > 0) {
		cnt++;

		int first, second;
		std::cin >> first >> second;

		if (!union_numbers(first, second)) {
			std::cout << cnt << '\n';
			return 0;
		}
	}

	std::cout << 0 << '\n';
}