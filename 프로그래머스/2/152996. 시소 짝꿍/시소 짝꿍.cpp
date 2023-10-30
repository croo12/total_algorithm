#include <string>
#include <vector>

using namespace std;
constexpr int MAX = 1'000;

int count[MAX + 1];

int way[7][2] = {
    {1, 1}, {1, 2}, 
    {2, 3}, {2, 1},
    {3, 2}, {3, 4},
    {4, 3}
};

long long solution(vector<int> weights) 
{
    fill(count, count + MAX + 1, 0);
    for(auto i : weights) 
    {
        count[i]++;
    }
    
    long long answer = 0;
    
    for(int i = 2; i <= MAX; ++i)
    {
        for (int j = 0; j < 7; ++j)
        {
            const int a = way[j][0];
            const int b = way[j][1];
            
            int v = i / b * a;
            
            if (i % b == 0 && v <= MAX)
            {
                int me = v == i ? count[i] - 1 : count[i];
                answer += me * count[v];
            }
        }
    }
    
    return answer/2;
}