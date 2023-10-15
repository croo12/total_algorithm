#include <iostream>
#include <unordered_set>

using namespace std;

int r, c;
string s[1000];

bool check(int number);

int main()
{
    cin >> r >> c;

    for (int i = 0; i < r; i++)
    {
        cin >> s[i];
    }

    int start = 0;
    int end = r;
    int mid;

    while (start <= end)
    {
        mid = (start + end) / 2;

        if (check(mid))
        {
            start = mid + 1;
        }
        else
        {
            end = mid - 1;
        }
    }

    cout << start << std::endl;
}

bool check(int number)
{
    std::unordered_set<string> set;

    for (int i = 0; i < c; i++)
    {
        string tmp;
        for (int j = number + 1; j < r; j++)
        {
            tmp.push_back(s[j][i]);
        }

        if (set.find(tmp) == set.end())
            set.insert(tmp);
        else
        {
            return false;
        }
    }

    return true;
}