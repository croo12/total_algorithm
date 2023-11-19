#include<iostream>

using namespace std;

int main()
{
    int n;
    cin >> n;
    long long answer = 1;

    for (int i = n; i >= 1; i--)
    {
        answer *= i;
    }
    
    cout << answer << endl;
}