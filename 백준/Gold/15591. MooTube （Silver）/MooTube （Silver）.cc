#include <iostream>
#include <vector>

using namespace std;

struct USADO
{
    int endPoint;
    int usado;

    USADO(int endPoint, int usado) : endPoint(endPoint), usado(usado){};
    ~USADO(){};
};

vector<USADO> adjList[5'000];
int dfs(int now, int length, const int& k, bool*& vis);

int main()
{
    int n, q;
    cin >> n >> q;

    for (int i = 1; i < n; i++)
    {
        int p, q, r;
        cin >> p >> q >> r;

        adjList[p - 1].emplace_back(USADO{q - 1, r});
        adjList[q - 1].emplace_back(USADO{p - 1, r});
    }

    while (q-- > 0)
    {
        int k, v;
        cin >> k >> v;
        --v;

        bool *vis = new bool[n];
        fill(vis, vis + n, false);

        cout << dfs(v, 1'000'000'001, k, vis) - 1 << '\n';
    }
}

int dfs(int now, int length, const int &k, bool *&vis)
{
    if (length < k)
        return 0;

    vis[now] = true;

    int result = 0;

    for (const auto adj: adjList[now])
    {
        if (vis[adj.endPoint]) continue;

        result += dfs(adj.endPoint, std::min(length, adj.usado), k, vis);
    }

    return result + 1;
}