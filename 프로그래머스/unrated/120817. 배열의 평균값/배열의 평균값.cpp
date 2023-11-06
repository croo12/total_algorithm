#include <string>
#include <vector>

using namespace std;

double solution(vector<int> numbers) {
    double answer = 0;
    double len = numbers.size();
    
    for (auto number: numbers)
        answer += number / len;
    
    return answer;
}