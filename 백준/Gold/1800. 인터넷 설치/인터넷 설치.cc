#include <iostream>
#include <vector>
#include <queue>

using namespace std;

constexpr int MAX = 1000;

struct Road
{
    int endPoint, price;

    Road() : endPoint(-1), price(-1) {};
    Road(int _endPoint, int _price) : endPoint(_endPoint), price(_price) {};
    ~Road(){};
};

struct Dist 
{
    int endPoint, count;

    Dist(int _endPoint, int _count)
     : endPoint(_endPoint), count(_count) {};

    bool operator< (const Dist& rhs) const
    {
        return rhs.count < count;
    }
};

int n, p, k;
vector<Road> adjList[MAX];
bool vis[MAX];
int d[MAX];

bool calc(const int& target);

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n >> p >> k;

    int maxPrice = 0;
    for (int i = 0; i < p; i++)
    {
        int a, b, price;
        cin >> a >> b >> price;

        maxPrice = std::max(maxPrice, price);

        adjList[a-1].emplace_back(Road{b-1, price});
        adjList[b-1].emplace_back(Road{a-1, price});
    }

    int start = 0;
    int end = maxPrice;
    int mid;

    vis[0] = true;

    while (start <= end)
    {
        mid = (start + end) / 2;

        if (calc(mid))
        {
            end = mid - 1;
        }
        else
        {
            start = mid + 1;
        }
    }

    cout << (start < maxPrice ? start : -1) << endl;
}

bool calc(const int& target)
{
    
    priority_queue<Dist> pq;
    
    fill(vis, vis + n, false);
    fill(d, d + n, MAX);

    pq.emplace(Dist{0, 0});
    d[0] = 0;

    while (!pq.empty())
    {
        Dist now = pq.top();
        pq.pop();

        vis[now.endPoint] = true;

        if (vis[n-1]) break;

        for (const Road& road : adjList[now.endPoint])
        {
            if (vis[road.endPoint]) continue;

            int nxt = road.price > target ? now.count + 1 : now.count;

            if (d[road.endPoint] > nxt)
            {
                d[road.endPoint] = nxt;
                pq.push(Dist{road.endPoint, nxt});
            }
        }
    }

    return d[n - 1] <= k;
}