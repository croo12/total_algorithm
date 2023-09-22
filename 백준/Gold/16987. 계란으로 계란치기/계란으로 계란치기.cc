#include <iostream>
#include <string>

using namespace std;

int answer, n;
int eggs[8][2];

void dfs(int idx);

int main()
{
    answer = 0;
    cin >> n;

    for (int i = 0; i < n; i++)
    {
        cin >> eggs[i][0] >> eggs[i][1];
    }

    dfs(0);

    cout << answer << '\n';
}

void dfs(int idx)
{
    if (idx == n)
    {
        int cnt = 0;
        for (int i = 0; i < n; i++)
        {
            if (eggs[i][0] <= 0)
                cnt++;
        }

        answer = std::max(cnt, answer);

        return;
    }

    int *now = eggs[idx];
    if (now[0] <= 0)
    {
        dfs(idx + 1);
        return;
    }

    bool flag = false;
    for (int i = 0; i < n; i++)
    {
        int *egg = eggs[i];
        if (i == idx || egg[0] <= 0)
            continue;

        flag = true;

        now[0] -= egg[1];
        egg[0] -= now[1];

        dfs(idx + 1);

        now[0] += egg[1];
        egg[0] += now[1];
    }

    if (!flag)
        dfs(n);
}