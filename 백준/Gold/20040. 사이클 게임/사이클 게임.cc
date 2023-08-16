#include <iostream>
#include <vector>

int find(int x, std::vector<int>& p) {
	if (p[x] != x)
		p[x] = find(p[x], p);

	return p[x];
}

void union_numbers(int x, int y, std::vector<int>& p) {
	p[x] = y;
}

int main() {
	int n, m;
	std::cin >> n >> m;

	std::vector<int> p(n, 0);
	for (size_t i = 0; i < n; i++)
	{
		p[i] = i;
	}

	int cnt = 0;
	while (m-- > 0) {
		cnt++;

		int first, second;
		std::cin >> first >> second;

		first = find(first, p);
		second = find(second, p);

		if (first == second) {
			std::cout << cnt << '\n';
			return 0;
		}

		union_numbers(first, second, p);
	}

	std::cout << 0 << '\n';
}