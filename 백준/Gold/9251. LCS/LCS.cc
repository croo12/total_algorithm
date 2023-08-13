#include <iostream>
#include <vector>

using namespace std;

const int MAX = 1001;

int main()
{
    std::ios::sync_with_stdio(false);
    std::cin.tie(NULL);

    string s1;
    string s2;
    cin >> s1;
    cin >> s2;

    int s1_len = s1.length();
    int s2_len = s2.length();

    vector<vector<int>> count(s1_len + 1, vector<int>(s2_len + 1, 0));

    for(int i = 1; i <= s1_len; ++i) {
        for (int j = 1; j <= s2_len; ++j) {
            if (s1[i-1] == s2[j-1]) {
                count[i][j] = count[i-1][j-1] + 1;
            } else {
                count[i][j] = max(count[i-1][j], count[i][j-1]);
            }
        }
    }

    cout << count[s1_len][s2_len] << endl;
}
