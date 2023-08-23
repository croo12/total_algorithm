#include <iostream>
#include <queue>

using namespace std;

struct Pair {
	int time, deadline;
	Pair(int _time, int _deadline) : time(_time), deadline(_deadline) {}
	bool operator < (const Pair& p) const {
		return deadline < p.deadline;
	}
};

int main() {
	int n;
	cin >> n;

	priority_queue<Pair> pq;

	for (int i = 0; i < n; i++)
	{
		int time, deadline;
		cin >> time >> deadline;

		pq.push(Pair(time, deadline));
	}

	int sceduled = 1'000'001;
	const Pair* now;
	while (!pq.empty()) {
		now = &pq.top();

		if (sceduled > now->deadline) {
			sceduled = now->deadline;
		}

		sceduled -= now->time;
		pq.pop();
	}

	if (sceduled < 0) {
		cout << -1 << '\n';
	}
	else {
		cout << sceduled << '\n';
	}
}