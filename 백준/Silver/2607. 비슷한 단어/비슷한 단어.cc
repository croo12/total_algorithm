#include <iostream>
#include <string>

using namespace std;

int memo[100][26];

int main()
{
    int n;
    cin >> n;

    for (int i = 0; i < n; i++)
    {
        string s;
        cin >> s;

        for (char c : s)
        {
            memo[i][c - 'A']++;
        }
    }
    
    int answer = 0;
    int* first = memo[0];

    for (int i = 1; i < n; i++)
    {
        int* tmp = memo[i];

        int diff_count = 0;
        int diff = 0;

        for (int j = 0; j < 26; j++)
        {
            if (first[j] != tmp[j]) {
                diff_count++;

                int gap = first[j] - tmp[j];
                diff += gap;

                if (gap > 1 || gap < -1) break;
                if (diff_count > 2) break;
            }
        }
        
        if ((diff_count == 2 && diff == 0) || (diff_count == 1 && (diff == 1 || diff == -1)) || diff_count == 0 ) answer++;
    }
    
    cout << answer << '\n';
}