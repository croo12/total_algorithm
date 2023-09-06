#include <iostream>
#include <queue>

using namespace std;

const int move_dir[4][2] = {
	{0, 1}, {0, -1}, {1, 0}, {-1, 0}
};

int r, c, ml, mv;

int map[50][50];
bool vis[50][50];

struct Pair {
	int r, c, len;
	Pair(int r, int c, int len) : r(r), c(c), len(len) {};
};

bool outOfBound(int row, int col);
void bfs(int row, int col);

int main() {
	cin >> r >> c;

	for (int i = 0; i < r; i++)
	{
		for (int j = 0; j < c; j++) {
			cin >> map[i][j];
		}
	}

	ml = 0;
	mv = 0;

	for (int i = 0; i < r; i++)
	{
		for (int j = 0; j < c; j++) {
			if (map[i][j] == 0) continue;
			bfs(i, j);
		}
	}

	cout << mv << '\n';
}

bool outOfBound(int row, int col) {
	return row < 0 || col < 0 || row >= r || col >= c;
}

void bfs(int row, int col) {

	fill(&vis[0][0], &vis[0][0] + 50 * 50, false);
	vis[row][col] = true;

	int start_v = map[row][col];

	queue<Pair> q;
	q.push(Pair{ row, col, 1 });

	while (!q.empty()) {
		Pair p = q.front();
		q.pop();

		int n = start_v + map[p.r][p.c];

		if (p.len == ml && n > mv) {
			mv = n;
		}

		if (p.len > ml) {
			ml = p.len;
			mv = n;
		}

		for (int i = 0; i < 4; i++)
		{
			int rr = p.r + move_dir[i][0];
			int rc = p.c + move_dir[i][1];

			if (outOfBound(rr, rc)) continue;
			if (vis[rr][rc] || map[rr][rc] == 0) continue;

			vis[rr][rc] = true;
			q.push(Pair(rr, rc, p.len + 1));
		}
	}
}