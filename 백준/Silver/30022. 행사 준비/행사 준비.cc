#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int main() {
	int n, a, b;
	cin >> n >> a >> b;

	priority_queue<int, vector<int>, greater<int>> pqA;
	priority_queue<int, vector<int>, greater<int>> pqB;

	long long total = 0LL;
	while (n-- > 0) {
		int p1, p2;
		cin >> p1 >> p2;

		if (p1 < p2) {
			total += p1;
			pqA.push(p2 - p1);
		}
		else {
			total += p2;
			pqB.push(p1 - p2);
		}
	}

	while (pqA.size() > a) {
		total += pqA.top();
		pqA.pop();
	}

	while (pqB.size() > b) {
		total += pqB.top();
		pqB.pop();
	}

	cout << total << endl;
}