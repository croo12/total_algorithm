#include <iostream>
#include <algorithm>

using namespace std;

int main() {

  int n;
  cin >> n;

  int* streak = new int[n];

  for (int i = 0; i < n; i++)
  {
    cin >> streak[i];
  }

  if (next_permutation(streak, streak + n)) {
    for (auto i = 0; i < n; i++)
    {
      cout << streak[i] << ' ';
    }
    cout << '\n';
  }
  else {
    cout << -1 << '\n';
  }
}