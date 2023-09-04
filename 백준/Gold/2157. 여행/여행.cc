#include <iostream>
#include <vector>

#define INF -987654321;

using namespace std;

struct Node {
	int endPoint;
	int value;
	Node* next;

	Node(int _endPoint, int _value, Node* _next) : endPoint( _endPoint ), value( _value ) , next( _next ) {};
	~Node(){
		if (next != nullptr)
			delete next;
	}
};

int n, m, k;
Node* road[301];
int memo[301][301];

int calc(int idx, int visit);

int main() {
	cin >> n >> m >> k;

	fill(road, road + 301, nullptr);
	fill(&memo[0][0], &memo[0][0] + 301 * 301, -1);

	for (int i = 0; i < k; i++)
	{
		int a, b, c;
		cin >> a >> b >> c;

		if (a < b)
			road[a] = new Node(b, c, road[a]);
	}

	int answer = calc(1, 1);

	cout << answer << endl;
}

int calc(int idx, int visit) {
	if (visit > m) {
		return INF;
	}

	if (idx == n) return 0;

	if (memo[idx][visit] != -1) return memo[idx][visit];

	int max = INF;
	for (Node* i = road[idx]; i != nullptr; i = i->next)
	{
		int now = calc(i->endPoint, visit + 1) + i->value;

		if (now > max) {
			max = now;
		}
	}

	return memo[idx][visit] = max;
}