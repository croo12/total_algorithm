#include <bitset>

using namespace std;

int solution(int n, int a, int b)
{
    int diff_pattern = --a^--b;
    
    for(int i = 0; i < 22; ++i)
    {
        if (diff_pattern == 0 ) return i;
        diff_pattern >>= 1;
    }
    
    return -1;
}