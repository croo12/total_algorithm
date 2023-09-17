#include <iostream>
#include <string>
#include <queue>

using namespace std;

int map[1000][1000];
bool vis[1000][1000];

struct Point {
	int row, col;
	Point(int row, int col) : row(row), col(col) {};
	bool operator < (const Point& p) const {
		if (map[row][col] < map[p.row][p.col]) return true;
		return false;
	}
};

int _vector[4][2] = {
	{1, 0}, {-1, 0}, {0, 1}, {0, -1}
};

int main() {
	int n, m, k;
	cin >> n >> m;

	priority_queue<Point> pq;

	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < m; j++) {
			cin >> map[i][j];
			
			if (i == 0 || j == 0 || i == n - 1 || j == m - 1) {
				vis[i][j] = true;
				pq.emplace(Point{ i, j });
			}
		}
	}

	cin >> k;

	string answer;

	while (k-- > 0) {
		Point now = pq.top(); 
		pq.pop();

		answer += to_string( now.row + 1 );
		answer.push_back(' ');
		answer += to_string( now.col + 1 );
		answer.push_back('\n');

		for (int i = 0; i < 4; i++) {
			int rr = now.row + _vector[i][0];
			int rc = now.col + _vector[i][1];

			if (rr >= n || rc >= m || rr < 0 || rc < 0 || vis[rr][rc]) continue;

			vis[rr][rc] = true;
			pq.emplace(Point{ rr, rc });
		}
	}

	cout << answer;
}