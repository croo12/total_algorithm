#include <iostream>

using namespace std;
int map[100][100];
int r[100];
int c[100];

int main() {
	cin.tie(0); 
	ios_base::sync_with_stdio(0);

	int n, m;
	cin >> n >> m;

	fill(r, r + 100, 0);
	fill(c, c + 100, 0);

	for (int row = 0; row < n; row++)
	{
		for (int col = 0; col < m; col++)
		{
			int tmp;
			cin >> tmp;
			map[row][col] = tmp;
			r[row] += tmp;
			c[col] += tmp;
		}
	}

	int answer = -10'000'001;

	for (int rs = 0; rs < n; rs++)
		for (int cs = 0; cs < m; cs++)
			for (int re = rs + 1; re < n; re++)
				for (int ce = cs + 1; ce < m; ce++) {
					int sum = r[rs] + r[re] + c[cs] + c[ce] - map[rs][cs] - map[rs][ce] - map[re][cs] - map[re][ce];
					answer = max(answer, sum + (re - rs - 1) * (ce - cs - 1));
				}

	cout << answer << endl;
}