#include <iostream>

int main() {

  while (true) {
    int now;
    std::cin >> now;

    if (now == 0) break;

    int sum = 1;

    while (now != 0) {
      int tmp = now % 10;
      if (tmp == 0) sum += 5;
      else if (tmp == 1) sum += 3;
      else sum += 4;

      now /= 10;
    }

    std::cout << sum << '\n';
  }
}