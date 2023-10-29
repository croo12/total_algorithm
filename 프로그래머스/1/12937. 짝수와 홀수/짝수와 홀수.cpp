#include <string>

using namespace std;

constexpr const char* even = "Even";
constexpr const char* odd = "Odd";

string solution(int num) {
    if (num % 2 == 0) return even;
    else return odd;
}