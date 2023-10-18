#include <iostream>
#include <queue>

using namespace std;

int arr[500'000];

int main()
{
    int n, k;
    cin >> n >> k;

    int count = 0;
    priority_queue<pair<int, int>> pq;

    for (int i = 0; i < n; i++)
    {
        cin >> arr[i];
        pq.push({arr[i], -i});
    }

    while (n-- > 1)
    {
        auto now = pq.top();

        while (arr[-now.second] != now.first){
            pq.pop();
            now = pq.top();
        }

        pq.pop();

        if (now.first > arr[n])
        {
            ++count;

            if (count == k)
            {
                cout << arr[n] << ' ' << now.first << endl;
                return 0;
            }

            arr[-now.second] = arr[n];
            arr[n] = now.first;

            pq.push({arr[-now.second], now.second});
        }
    }

    cout << -1 << endl;
}