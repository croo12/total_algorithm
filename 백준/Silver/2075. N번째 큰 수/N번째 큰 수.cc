#include <iostream>
#include <queue>

using namespace std;

constexpr int MAX = 1'500;

int map[MAX][MAX];

struct NumberPointer
{
    int value, row, col;
    int *next;
    NumberPointer() : NumberPointer(0, 0, 0){};
    NumberPointer(int value, int row, int col) : value(value), row(row), col(col)
    {
        next = &map[row][col];
    };

    bool operator<(const NumberPointer &rhs) const
    {
        if (value < rhs.value)
            return true;
        else
            return false;
    };

    ~NumberPointer()
    {
    }
};

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;

    priority_queue<NumberPointer> pq;

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cin >> map[i][j];
        }
    }

    for (int i = 0; i < n; i++)
    {
        pq.push(NumberPointer{map[n - 1][i], n - 2, i});
    }

    while (n-- > 1)
    {
        NumberPointer now = pq.top();
        pq.pop();

        pq.push(NumberPointer{*now.next, now.row - 1, now.col});
    }

    cout << pq.top().value << endl;
}