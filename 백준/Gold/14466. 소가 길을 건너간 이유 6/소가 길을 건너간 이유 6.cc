#include <iostream>
#include <vector>

using namespace std;

int n, k, r;

//시계방향
const int DIR[4][2] = {
    {-1, 0}, {0, 1}, {1, 0}, {0, -1} 
};

int field[101][101];
int vis[101][101];

void dfs(int row, int col, const int& count);

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n >> k >> r;

    while (r-- > 0)
    {
        int r, c, r2, c2;

        cin >> r >> c >> r2 >> c2;

        for (int i = 0; i < 4; i++)
        {
            if (r + DIR[i][0] == r2 && c + DIR[i][1] == c2)
            {
                field[r][c]   |= 1 << i;
                field[r2][c2] |= 1 << ((i + 2) % 4);

                break;
            }
        }
    }

    fill(&vis[0][0], &vis[0][0] + sizeof(vis)/sizeof(int), 0);   

    int count = 0;

    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            if (vis[i][j] == 0)
            {
                count++;
                dfs(i, j, count);
            }
        }
    }
    
    vector<int> cow(count, 0);

    for (int i = 0; i < k; i++)
    {
        int r, c;
        cin >> r >> c;

        cow[vis[r][c] - 1]++;
    }
    
    int answer = 0;
    for (int i = 0; i < count; i++)
    {
        int now = cow[i];
        for (int j = i + 1; j < count; j++)
        {
            answer += now * cow[j];
        }
    }
    
    cout << answer << endl;
}

void dfs(int row, int col, const int &count)
{
    if (row > n || col > n || row <= 0 || col <= 0 || vis[row][col] != 0) return;

    vis[row][col] = count;

    for (int i = 0; i < 4; i++)
    {
        if ((field[row][col] & (1 << i)) == 0)
        {
            dfs(row + DIR[i][0], col + DIR[i][1], count);
        }
    }
}
