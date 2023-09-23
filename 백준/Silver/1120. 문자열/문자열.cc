#include <iostream>
#include <string>

using namespace std;

int main()
{
    string a, b;

    cin >> a >> b;
    
    int a_len = a.length();
    int last = b.length() - a_len;

    int answer = 51;

    for (int i = 0; i <= last; i++)
    {
        char* start = &b[i];
        int count = 0;

        for (int j = 0; j < a_len; j++)
        {
            char c = *(start + j);
            if (c != a[j]) ++count;
        }
        
        answer = std::min(count, answer);
    }

    cout << answer << '\n';
    
}
