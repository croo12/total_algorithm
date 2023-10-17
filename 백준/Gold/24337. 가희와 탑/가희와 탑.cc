#include <iostream>
#include <vector>

using namespace std;

int main()
{
    int n, a, b;
    cin >> n >> a >> b;

    vector<int> v;

    if (a > b)
    {
        for (int i = 1; i <= a; i++)
        {
            v.push_back(i);
        }

        for (int i = b - 1; i >= 1; i--)
        {
            v.push_back(i);
        }
    }
    else 
    {
        for (int i = 1; i < a; i++)
        {
            v.push_back(i);
        }

        for (int i = b; i >= 1; i--)
        {
            v.push_back(i);
        }
    }

    int len = v.size();

    if (len > n)
    {
        cout << -1 << endl;
    }
    else 
    {
        cout << v[0] << ' ';

        for (int i = 0; i < n - len; i++)
        {
            cout << 1 << ' ';
        }

        for (int i = 1; i < v.size(); i++)
        {
            cout << v[i] << ' ';
        }

        cout << '\n';
    }
}