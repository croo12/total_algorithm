#include <iostream>

using namespace std;

const int MAX = 100000;

int main()
{
    std::ios::sync_with_stdio(false);
    std::cin.tie(NULL);

    int* stack = new int[MAX];
    int rear = -1;

    int n;
    cin>>n;

    string answer;

    int cnt = 0;
    for(int i = 0; i < n; i++) {
        int tmp;
        cin>> tmp;

        while (tmp > cnt) {
            stack[++rear] = ++cnt;
            answer.push_back('+');
            answer.push_back('\n');
        }

        if (stack[rear] == tmp) {
            rear--;
            answer.push_back('-');
            answer.push_back('\n');
        } else {
            cout << "NO" << endl;
            return 0;
        }
    }

    cout << answer;
}
