#include <iostream>
#include <vector>

using namespace std;

const int MAX = 1000000;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int m, n;
    cin >> m >> n;

    vector<bool> prime = vector(MAX + 1, false);
    prime[1] = true;
    for (int i = 2; i * i <= n ; ++i) {
        if (!prime[i])
            for (int j = i + i; j <= MAX; j += i) {
                prime[j] = true;
            }

    }

    int start = m % 2 == 1 ? m : (m+1);
    if (m <= 2) cout << 2 << '\n';
    for (int i = start; i <= n; i+=2) {
        if (!prime[i]) {
            cout << i << '\n';
        }
    }
}