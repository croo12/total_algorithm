#include <iostream>
#include <string>

using namespace std;

constexpr int MAX = 250;
int map[MAX][MAX];
bool vis[MAX][MAX];

int _dir[8][2] = {
    {1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

void dfs(int row, int col);

int m, n;
int main()
{
    cin >> m >> n;

    for (int i = 0; i < m; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cin >> map[i][j];
            vis[i][j] = false;
        }
    }

    int answer = 0;

    for (int i = 0; i < m; i++)
    {
        for (int j = 0; j < n; j++)
        {
            if (vis[i][j] || map[i][j] == 0)
                continue;

            dfs(i, j);
            ++answer;
        }
    }

    cout << answer << endl;
}

void dfs(int row, int col)
{
    if (row >= m || col >= n || row < 0 || col < 0 || vis[row][col]) return;

    vis[row][col] = true;

    for (int i = 0; i < 8; i++)
    {
        int rr = row + _dir[i][0];
        int rc = col + _dir[i][1];

        if (map[rr][rc] == 0) continue;

        dfs(rr, rc);
    }
}