#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

vector<int> solution(vector<string> name, vector<int> yearning, vector<vector<string>> photo) 
{
    unordered_map<string, int> map;
    
    int len = name.size();
    for (int i = 0; i < len; ++i)
    {
        const string& n = name[i];
        const int& point = yearning[i];
        
        map[n] = point;
    }
    
    vector<int> answer;
    
    for (auto now : photo)
    {
        int sum = 0;
        for (const string& n : now)
        {
            sum += map[n];
        }
        answer.emplace_back(sum);
    }
    
    return answer;
}