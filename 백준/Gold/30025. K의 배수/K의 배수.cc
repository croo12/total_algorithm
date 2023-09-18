#include <iostream>

using namespace std;

constexpr int MOD = 1'000'000'007;

int memo[100][1000];

int main() {

	int n, m, k;

	cin >> n >> m >> k;

	bool check[10];

	fill(check, check + 10, false);
	fill(&memo[0][0], &memo[0][0] + sizeof(memo) / sizeof(int), 0);

	for (int i = 0; i < n; i++)
	{
		int tmp;
		cin >> tmp;
		check[tmp] = true;

		if (tmp != 0)
			memo[0][tmp % k] += 1;
	}

	//메모에 자릿수별로 어떤 숫자를 넣었을때 MOD k 값 기록하기

	for (int i = 1; i < m; ++i) {
		
		for (int j = 0; j < 10; ++j) {
			if (!check[j]) continue;

			//이거 사용가능한 숫자임
			for (int now = 0; now < k; ++now) {

				//이번 선택에서 나온 나머지
				int v = now * 10 + j;

				memo[i][v % k] += memo[i - 1][now];
				memo[i][v % k] %= MOD;
			}
		}
	}

	cout << memo[m-1][0] << endl;
}