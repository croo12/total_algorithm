#include <string>
#include <vector>
#include <queue>

using namespace std;

constexpr int MAX = 100;

int map[MAX][MAX];
bool vis[MAX][MAX];

int _vector[4][2] = {
    {0, 1}, {1, 0}, {-1, 0}, {0, -1}
};

int solution(vector<string> board) {  
    fill(&vis[0][0], &vis[0][0] + sizeof(vis), false);
    
    pair<int, int> robot;
    pair<int, int> goal;
    
    int row = 0;
    int col = 0;
    for (; row < board.size(); ++row)
    {
        const string& line = board[row];
        for (col = 0; col < line.length(); ++col)
        {
            char c = line[col];
            
            if (c == 'R')
            {
                robot = {row, col};
                map[row][col] = '.';
            }
            else if (c == 'G')
            {
                goal = {row, col};
                map[row][col] = '.';
            }
            else
            {
                map[row][col] = c;
            }
        }
    }
    
    queue<pair<int, int>> q;
    q.push(robot);
    vis[robot.first][robot.second] = true;
    
    int answer = 0;
    while (!q.empty())
    {
        int s = q.size();
        
        for (int i = 0; i < s; ++i)
        {
            auto now = q.front();
            q.pop();
            
            if (now.first == goal.first && now.second == goal.second)
                return answer;
        
            for (int k = 0; k < 4; ++k)
            {
                int rr = now.first;
                int rc = now.second;

                while (
                    rr >= 0 && rr < row && rc >= 0 && rc < col &&
                    map[rr][rc] == '.'
                )
                {
                    rr += _vector[k][0];
                    rc += _vector[k][1];
                }

                rr -= _vector[k][0];
                rc -= _vector[k][1];
            
                if (!vis[rr][rc])
                {
                    vis[rr][rc] = true;
                    q.push({rr, rc});
                }
            }
        }
        
        answer++;
    }
    
    return -1;
}