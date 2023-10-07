#include <iostream>
#include <vector>

using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int T;
    cin >> T;

    while (T-- > 0)
    {
        string s;
        cin >> s;

        int len = s.length();

        if (s[len/2 - 1] == s[len/2])
        {
            cout << "Do-it\n";
        } 
        else 
        {
            cout << "Do-it-Not\n";
        }
    }
}