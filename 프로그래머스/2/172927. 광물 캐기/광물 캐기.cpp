#include<vector>
#include<string>
#include<unordered_map>

using namespace std;

constexpr const char* diamond = "diamond";
constexpr const char* iron = "iron";
constexpr const char* stone = "stone";

int answer = 987'654'321;

const unordered_map<string, int> fatigue[3] = {
    {{ diamond, 1 }, { iron, 1 }, { stone, 1 },},
    {{ diamond, 5 }, { iron, 1 }, { stone, 1 },},
    {{ diamond, 25 }, { iron, 5 }, { stone, 1 },}
};

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
        auto tmp = fatigue[kind];
        sum += tmp[s];
    }

    return sum;
}