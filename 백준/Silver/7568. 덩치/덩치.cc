#include <iostream>
#include <queue>

using namespace std;

struct Man {
  int t, w;
};

int n;

int main() {

  cin >> n;

  Man* m = new Man[50];

  for (int i = 0; i < n; i++)
  {
    cin >> m[i].w >> m[i].t;
  }

  for (int i = 0; i < n; i++)
  {
    int rank = 1;
    for (int j = 0; j < n; j++)
    {
      if (m[j].t > m[i].t && m[j].w > m[i].w) ++rank;
    }

    cout << rank << ' ';
  }
  cout << endl;
}