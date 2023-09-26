#include <iostream>
#include <vector>

using namespace std;

int Find(int x);
void Union(int x, int y);

int p[201];

int main()
{
    ios_base::sync_with_stdio(false);
	cin.tie(NULL);

    int n, m;
    cin >> n >> m;
    for (int i = 1; i <= n; i++)
    {
        p[i] = i;
    }

    int tmp;
    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            cin >> tmp;

            if (tmp == 1) {
                Union(Find(i), Find(j));
            }
        }
    }
    
    int before = -1;
    for (int i = 0; i < m; i++)
    {
        cin >> tmp;

        if (before == -1) {
            before = Find(tmp);
        } else if (Find(tmp) != before) {
            cout << "NO\n";
            return 0;
        }
    }
    
    cout << "YES\n";
}

int Find(int x)
{
    if (p[x] == x)
        return x;
    return p[x] = Find(p[x]);
}

void Union(int x, int y)
{
    p[x] = y;
}