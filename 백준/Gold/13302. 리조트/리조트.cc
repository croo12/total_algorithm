#include <iostream>
#include <vector>

using namespace std;

int memo[100][21];
bool vis[100];

int n, m;
int dfs(const int& day, const int& coupon);

int main() {
	cin.tie(0); 
	ios_base::sync_with_stdio(0);

	cin >> n >> m;

	fill(&memo[0][0], &memo[0][0] + sizeof(memo) / sizeof(int), -1);
	fill(vis, vis + 100, false);
	
	while (m-- > 0) {
		int i;
		cin >> i;
		vis[i - 1] = true;
	}

	cout << dfs(0, 0) << endl;
}

int dfs(const int& day, const int& coupon) {
	if (day >= n) return 0;
	if (memo[day][coupon] != -1) return memo[day][coupon];

	int min = dfs(day + 1, coupon) + (vis[day] ? 0 : 10'000);
	
	if (coupon >= 3) {
		min = std::min(min, dfs(day + 1, coupon - 3));
	}

	min = std::min(min, dfs(day + 3, coupon + 1) + 25'000);
	min = std::min(min, dfs(day + 5, coupon + 2) + 37'000);

	return memo[day][coupon] = min;
}