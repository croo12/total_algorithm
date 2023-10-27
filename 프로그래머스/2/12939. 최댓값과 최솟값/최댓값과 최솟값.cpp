#include <string>
#include <vector>
#include <iostream>

using namespace std;

vector<int> to_int_array(string s);

string solution(string s)
{
    vector<int> nums = to_int_array(s);

    int max = (1 << 31);
    int min = ~(1 << 31);

    for (auto i : nums)
    {
        max = std::max(max, i);
        min = std::min(min, i);
    }

    string answer = std::to_string(min) + " " + to_string(max);
    return answer;
}

vector<int> to_int_array(string s)
{
    vector<int> result;

    bool is_negative = false;
    int sum = 0;
    for (int i = 0; i < s.size(); i++)
    {
        const char c = s[i];
        if (c == '-')
            is_negative = true;
        else if (c >= '0' && c <= '9')
        {
            sum *= 10;
            sum += (c - '0') * (is_negative ? -1 : 1);
        }
        else
        {
            result.push_back(sum);
            sum = 0;
            is_negative = false;
        }
    }

    result.push_back(sum);
    return result;
}
