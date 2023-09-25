#include <string>
#include <vector>

using namespace std;

int _dir[4][2] = {
    {0,1}, {1,0}, {0,-1}, {-1,0}
};

vector<vector<int>> solution(int n) {
    vector<vector<int>> answer(n, vector<int>(n, -1));
    
    int cnt = 1;
    int r = 0;
    int c = -1;
    int dir = 0;
    while (cnt <= n*n)
    {
        int dr = r + _dir[dir][0];
        int dc = c + _dir[dir][1];
        
        if (dr >= n || dc >= n || dr < 0 || dc < 0 || answer[dr][dc] != -1) 
        {
            ++dir %= 4;
            continue;
        }
        
        answer[dr][dc] = cnt++;
        r = dr;
        c = dc;
    }
    
    return answer;
}