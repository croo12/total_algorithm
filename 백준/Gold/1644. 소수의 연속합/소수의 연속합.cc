#include <iostream>
#include <vector>

int main() {
	int n;
	std::cin >> n;
	
	bool* is_prime = new bool[n + 1];
	for (size_t i = 2; i <= n; ++i)
	{
		is_prime[i] = false;
	}

	std::vector<int> primes;

	int start_point = 0;
	int end_point = -1;
	int sum = 0;

	int cnt = 0;

	for (size_t i = 2; i <= n; ++i)
	{
		if (!is_prime[i]) {
			primes.push_back(i);
			sum += i;
			++end_point;

			if (sum == n) {
				++cnt;
			}
			else if (sum > n) {
				while (sum > n) {
					sum -= primes[start_point++];
				}

				if (sum == n) ++cnt;
			}

			for (size_t j = i+i; j <= n; j+=i)
			{
				is_prime[j] = true;
			}
		}
	}

	std::cout << cnt << '\n';
}