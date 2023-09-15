#include <iostream>
#include <algorithm>
#include <unordered_set>

using namespace std;

void dfs(int idx, int vowel, int consonant, string& s);

int l, c;
char* streak;


int main() {

  cin >> l >> c;
  streak = new char[c];

  for (int i = 0; i < c; i++)
  {
    cin >> streak[i];
  }

  sort(streak, streak + c);

  string s;
  dfs(0, 0, 0, s);

}

bool isVowel(char now) {
  return now == 'a' || now == 'e' || now == 'i' || now == 'o' || now == 'u';
}

void dfs(int idx, int vowel, int consonant, string& s) {
  
  if (s.length() == l) {
    if (vowel >= 1 && consonant >= 2)
      cout << s << '\n';
    
    return;
  }

  if (idx == c) return;

  char now = streak[idx];
  bool vowelFlag = isVowel(now);

  s.push_back(now);
  dfs(idx + 1, vowelFlag ? vowel + 1 : vowel, vowelFlag ? consonant : (consonant + 1), s);
  s.pop_back();

  dfs(idx + 1, vowel, consonant, s);

}