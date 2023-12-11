#include <iostream>
#include <cstring>

using namespace std;

int calc(int position, int idx);

int n, m;
int names[1001];
int dp[1002][1002];
int main() {
    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        cin >> names[i];
    }

    memset(dp, -1, sizeof(dp));

    cout << calc(0, 0) << endl;
}

int sum(int position) {
    return (m + 1 - position) * (m + 1 - position);
}

int calc(int position, int idx) {
    if (idx == n) {
        return 0;
    }

    int& res = dp[position][idx];
    if (res != -1) return dp[position][idx];

    res = sum(position) + calc(names[idx] + 1, idx + 1);
    if ( m < names[idx] + position ) {
        return res;
    }

    res = min(res, calc(position + names[idx] + 1, idx + 1));
    return res;
}