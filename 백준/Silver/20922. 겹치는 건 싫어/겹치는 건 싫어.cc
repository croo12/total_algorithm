#include<iostream>

int main(int argc, char** argv)
{
	int n, k;

    int count[100'001];

    std::fill(count, count + 100'001, 0);

    int arr[200'000];

    std::cin >> n >> k;

    int start = 0;
    int end = -1;

    int answer = 0;
    for (int i = 0; i < n; i++)
    {
        std::cin >> arr[i];

        end++;

        ++count[arr[i]];
            
        while (count[arr[i]] > k) 
        {                
            count[arr[start]] -= 1;
            start++;
        }

        answer = std::max(answer, end - start + 1);
    }
    
    std::cout << answer << '\n';
}