#include <iostream>
#include <string>
#include <queue>

using namespace std;

struct Admin
{
    int r, c, b;
    Admin(int r, int c, int b) : r(r), c(c), b(b){};
};

int _dir[4][2] = {
    {0, 1},
    {0, -1},
    {-1, 0},
    {1, 0},
};

int main()
{

    int map[100][100];
    int vis[100][100];

    fill(&vis[0][0], &vis[0][0] + sizeof(vis) / sizeof(int), 987'654'321);

    int r, c;

    cin >> c >> r;

    for (int i = 0; i < r; i++)
    {
        string s;
        cin >> s;
        for (int j = 0; j < c; j++)
        {
            map[i][j] = s[j] - '0';
        }
    }

    int answer = 987'654'321;

    queue<Admin> q;
    q.push(Admin{0, 0, 0});
    vis[0][0] = 0;

    while (!q.empty())
    {
        Admin now = q.front();
        q.pop();

        if (now.r == r - 1 && now.c == c - 1)
        {
            answer = std::min(now.b, answer);
            continue;
        }

        for (int i = 0; i < 4; ++i)
        {
            int rr = now.r + _dir[i][0];
            int rc = now.c + _dir[i][1];

            if (rr >= r || rc >= c || rr < 0 || rc < 0)
                continue;
            if (now.b + map[rr][rc] >= vis[rr][rc])
                continue;

            vis[rr][rc] = now.b + map[rr][rc];
            q.emplace(Admin{rr, rc, now.b + map[rr][rc]});            
        }
    }

    cout << answer << endl;
}