#include <iostream>
#include <vector>

using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int T;
    cin >> T;

    int memo[10'001];

    fill(memo, memo + 10'001, 1);

    for (int i = 2; i < 10'001; i++)
    {
        memo[i] += memo[i - 2];
    }
    
    for (int i = 3; i < 10'001; i++)
    {
        memo[i] += memo[i - 3];
    }

    while (T-- > 0)
    {
        int n;
        cin >> n;

        cout << memo[n] << std::endl;
    }    
}