#include <iostream>
#include <queue>

constexpr auto MAX = 1'987'654'321;

using namespace std;

int map[100][100];
bool dist[100][100][3];

int d_row[] {1,-1,0,0};
int d_col[] {0,0,-1,1};

struct Pair {
	int row, col, weight, point;
	Pair(int _row, int _col, int _weight, int _point) : 
		row(_row), col(_col), weight(_weight), point(_point) {};
};

struct cmp {
	bool operator()(Pair a, Pair b) {
		return a.weight > b.weight;
	}
};

int main() {
	int n, k;
	cin >> n >> k;

	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
		{
			cin >> map[i][j];
		}
	}

	priority_queue<Pair, vector<Pair>, cmp> q;

	dist[0][0][0] = true;
	dist[0][0][1] = true;
	
	q.push(Pair(0, 0, 0, 2));

	while (!q.empty()) {
		Pair p = q.top();
		q.pop();

		if (p.row == n - 1 && p.col == n - 1) {
			cout << p.weight << '\n';
			break;
		}

		if (dist[p.row][p.col][p.point] == true) {
			continue;
		}

		dist[p.row][p.col][p.point] = true;

		for (int i = 0; i < 4; i++)
		{
			int rr = p.row + d_row[i];
			int rc = p.col + d_col[i];

			if (rr >= n || rc >= n || rr < 0 || rc < 0) continue;

			if (p.point == 0)
				q.push(Pair(rr, rc, p.weight + k + map[rr][rc], 2));
			else {
				q.push(Pair(rr, rc, p.weight + k, p.point - 1));
			}
		}
	}
}