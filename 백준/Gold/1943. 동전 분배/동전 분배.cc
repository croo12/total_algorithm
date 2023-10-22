#include <iostream>
#include <queue>

using namespace std;

constexpr int HALF = 500'000;

bool vis[HALF + 1];

int main()
{
    int t = 3;

    while (t-- > 0)
    {
        int n;
        cin >> n;

        fill(vis, vis + HALF + 1, false);
        queue<int> q;

        q.emplace(0);
        vis[0] = true;

        int sum = 0;

        for (int i = 0; i < n; i++)
        {
            int d, count;
            cin >> d >> count;

            sum += d * count;

            int size = q.size();

            for (int i = 0; i < size; i++)
            {
                int now = q.front();
                q.pop();

                for (int i = 1; i <= count; i++)
                {
                    if (now + i * d > HALF || vis[now + i * d])
                        continue;
                    vis[now + i * d] = true;
                    q.push(now + i * d);
                }

                q.push(now);
            }
        }

        int answer = (sum % 2 == 0 && vis[sum / 2]) ? 1 : 0;

        cout << answer << endl;
    }
}