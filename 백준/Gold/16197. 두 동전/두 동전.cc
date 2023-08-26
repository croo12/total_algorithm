#include <iostream>
#include <queue>

using namespace std;

struct Coins {
	int r1, c1, r2, c2;
	int count;
	Coins() : r1(-1), c1(-1), r2(-1), c2(-1), count(0) {};
	Coins(int _r1, int _r2, int _c1, int _c2, int _count)
		: r1(_r1), c1(_c1), r2(_r2), c2(_c2), count(_count) {};
};

char map[20][20];
bool vis[400][400];

int max_row;
int max_col;

int calcPoint(int row, int col) {
	return row * 20 + col;
}

bool isOut(int &row, int &col) {
	return row >= max_row || row < 0 || col >= max_col || col < 0;
}

int d_row[] = { 1,-1,0,0 };
int d_col[] = { 0,0,1,-1 };

int main() {
	cin >> max_row >> max_col;

	Coins coin;

	for (int i = 0; i < max_row; i++)
	{
		for (int j = 0; j < max_col; j++)
		{
			cin >> map[i][j];

			if (map[i][j] == 'o') {
				if (coin.r1 == -1) {
					coin.r1 = i;
					coin.c1 = j;
				}
				else {
					coin.r2 = i;
					coin.c2 = j;
				}
			}
		}
	}

	queue<Coins> q;
	vis[calcPoint(coin.r1, coin.c1)][calcPoint(coin.r2, coin.c2)] = true;
	vis[calcPoint(coin.r2, coin.c2)][calcPoint(coin.r1, coin.c1)] = true;
	q.push(coin);

	Coins now;
	while (!q.empty()) {
		now = q.front(); q.pop();

		if (now.count == 10) break;

		for (int i = 0; i < 4; i++)
		{
			int rr1 = now.r1 + d_row[i];
			int rc1 = now.c1 + d_col[i];

			if (map[rr1][rc1] == '#') {
				rr1 = now.r1;
				rc1 = now.c1;
			}

			int rr2 = now.r2 + d_row[i];
			int rc2 = now.c2 + d_col[i];

			if (map[rr2][rc2] == '#') {
				rr2 = now.r2;
				rc2 = now.c2;
			}

			bool flag1 = !isOut(rr1, rc1);
			bool flag2 = !isOut(rr2, rc2);

			if (flag1 && flag2) {
				if (!vis[calcPoint(rr1, rc1)][calcPoint(rr2, rc2)]) {
					vis[calcPoint(rr1, rc1)][calcPoint(rr2, rc2)] = true;
					vis[calcPoint(rr2, rc2)][calcPoint(rr1, rc1)] = true;

					q.push(Coins(rr1, rr2, rc1, rc2, now.count + 1));
				}
			}
			else if (flag1 || flag2) {
				cout << now.count + 1 << '\n';
				return 0;
			}
		}

	}

	cout << -1 << '\n';
}