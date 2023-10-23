#include <iostream>
#include <vector>
#include <queue>

using namespace std;

constexpr int MAX = 200;
constexpr int INF = 987'654'321;

int dist[MAX][MAX];

vector<pair<int, int>> adjList[200];

void dijkstra(const int &startPoint);

int main()
{
    fill(&dist[0][0], &dist[0][0] + sizeof(dist) / sizeof(int), INF);

    int n, m;
    cin >> n >> m;

    for (int i = 0; i < m; ++i)
    {
        int sp, ep, t;
        cin >> sp >> ep >> t;

        adjList[sp - 1].emplace_back(pair{ep - 1, t});
    }

    for (int i = 0; i < n; i++)
    {
        dijkstra(i);
    }

    int k;
    cin >> k;

    int friends[MAX];

    for (int i = 0; i < k; i++)
    {
        cin >> friends[i];
        --friends[i];
    }

    vector<int> answer;

    int min = INF;

    for (int i = 0; i < n; i++)
    {
        int mt = 0;

        for (int j = 0; j < k; j++)
        {
            mt = std::max(mt, dist[i][friends[j]] + dist[friends[j]][i]);

            if (mt > min)
                break;
        }

        if (mt < min)
        {
            answer.clear();
            min = mt;
            answer.emplace_back(i);
        }
        else if (mt == min)
        {
            answer.emplace_back(i);
        }
    }

    for (const int &ans : answer)
    {
        cout << ans + 1 << ' ';
    }
    cout << '\n';
}

void dijkstra(const int &startPoint)
{
    priority_queue<pair<int, int>> pq;

    dist[startPoint][startPoint] = 0;
    pq.push({0, startPoint});

    bool vis[MAX];
    fill(vis, vis + MAX, false);

    while (!pq.empty())
    {
        auto now = pq.top();
        pq.pop();

        if (vis[now.second])
            continue;

        vis[now.second] = true;

        for (auto road : adjList[now.second])
        {
            if (vis[road.first])
                continue;

            int nd = dist[startPoint][now.second] + road.second;

            if (nd < dist[startPoint][road.first])
            {
                dist[startPoint][road.first] = nd;
                pq.push({-nd, road.first});
            }
        }
    }
}