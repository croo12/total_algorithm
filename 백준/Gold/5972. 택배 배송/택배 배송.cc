#include <iostream>
#include <vector>
#include <queue>

using namespace std;

struct Road
{
    int endPoint, w;
    explicit Road(int _endPoint, int _w) : endPoint(_endPoint), w(_w){};

    bool operator<(const Road &other) const
    {
        if (w > other.w) return true;

        return false;
    }
};

int n, m;
vector<Road> *adjList;

int main()
{
    cin >> n >> m;
    adjList = new vector<Road>[n + 1];

    for (int i = 0; i < m; i++)
    {
        int s, e, w;
        cin >> s >> e >> w;

        adjList[s].emplace_back(Road{e, w});
        adjList[e].emplace_back(Road{s, w});
    }

    priority_queue<Road> pq;

    int* dist = new int[n + 1];
    bool* vis = new bool[n + 1];

    fill(dist, dist + n + 1, 987'654'321);
    fill(vis, vis + n+ 1, false);

    dist[1] = 0;
    pq.emplace(Road{1, 0});

    while (!pq.empty()) 
    {
        Road now = pq.top();
        pq.pop();

        vis[now.endPoint] = true;

        for (auto adj : adjList[now.endPoint]) {

            if (!vis[adj.endPoint] && dist[adj.endPoint] > dist[now.endPoint] + adj.w) {
                
                dist[adj.endPoint] = dist[now.endPoint] + adj.w;
                pq.emplace(Road {adj.endPoint, dist[adj.endPoint]});
            }
        }
    }

    cout << dist[n] << '\n';
}