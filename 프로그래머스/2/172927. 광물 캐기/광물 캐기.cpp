#include<vector>
#include<string>

using namespace std;

int answer = 987'654'321;

void dfs(int cnt, int sum, vector<int>& _picks, const vector<string>& _minerals);
int calc(int start, int kind, const vector<string>& _minerals);

int solution(vector<int> picks, vector<string> minerals)
{
    dfs(0, 0, picks, minerals);

    return answer;
}

void dfs(int cnt, int sum, vector<int>& _picks, const vector<string>& _minerals)
{
    if (cnt >= _minerals.size() || (_picks[0] == 0 && _picks[1] == 0 && _picks[2] == 0))
    {
        answer = min(answer, sum);
        return;
    }

    for (int i = 0; i < 3; i++)
    {
        if (_picks[i] == 0) continue;

        _picks[i]--;
        int val = calc(cnt, i, _minerals);

        dfs(cnt + 5, sum + val, _picks, _minerals);

        _picks[i]++;
    }
}

int calc(int start, int kind, const vector<string>& _minerals)
{
    int sum = 0;
    for (int i = start; i < start + 5 && i < _minerals.size(); i++)
    {
        const string s = _minerals[i];
        switch (kind)
        {
        case 0:
            sum += 1;
            break;
        case 1:
            if (s.compare("diamond") == 0)
            {
                sum += 5;
            }
            else
            {
                sum += 1;
            }
            break;
        case 2:
            if (s.compare("diamond") == 0)
            {
                sum += 25;
            }
            else if (s.compare("iron") == 0)
            {
                sum += 5;
            }
            else
            {
                sum += 1;
            }
            break;
        }
    }

    return sum;
}