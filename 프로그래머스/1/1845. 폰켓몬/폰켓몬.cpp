#include <vector>

using namespace std;

constexpr int MAX = 200'000;

int min (int a, int b)
{
    if (a > b)
        return b;
    return a;
}

int solution(vector<int> nums)
{
    int answer = 0;
    
    bool check[MAX + 1];
    fill(check, check + MAX + 1, false);
    
    for (const int& i : nums)
    {
        if (check[i]) continue;
        check[i] = true;
        ++answer;
    }
    
    return min(answer, nums.size()/2);
}