#include <iostream>
#include <queue>

using namespace std;

constexpr int INF = 987'654'321;
int n, m, x;

int dist[1000][1000];

struct Road {
	int endPoint, time;
	Road(int _endPoint, int time) : endPoint(_endPoint), time(time) {};
	
	bool operator< (const Road& r) const
	{
		if (time <= r.time) return false;
		return true;
	}
};

void dijkstra(const int& k, const vector<Road> (&adjList)[1000]);

int main() {
	cin >> n >> m >> x;

	fill(&dist[0][0], &dist[0][0] + sizeof(dist)/sizeof(int), INF); 

	vector<Road> adjList[1000];

	for (int i = 0; i < m; i++)
	{
		int s, e, t;
		cin >> s >> e >> t;

		adjList[s - 1].emplace_back(Road{ e - 1, t });
	}
	
	for (int i = 0; i < n; i++)
	{
		dijkstra(i, adjList);
	}

	int max = 0;
	for (int i = 0; i < n; i++)
	{
		int now = dist[i][x - 1] + dist[x - 1][i];
		max = max < now ? now : max;
	}

	cout << max << endl;
}

void dijkstra(const int& k, const vector<Road> (&adjList)[1000]) {

	dist[k][k] = 0;
	priority_queue<Road> pq;
	pq.push(Road{ k, 0 });

	bool vis[1000];
	fill(vis, vis + 1000, false);

	while (!pq.empty()) {
		auto now = pq.top();
		pq.pop();

		vis[now.endPoint] = true;

		for (const auto& r : adjList[now.endPoint])
		{
			if (!vis[r.endPoint] && dist[k][r.endPoint] > dist[k][now.endPoint] + r.time) {
				dist[k][r.endPoint] = dist[k][now.endPoint] + r.time;
				pq.push(Road{ r.endPoint, dist[k][r.endPoint] });
			}
		}
	}
}