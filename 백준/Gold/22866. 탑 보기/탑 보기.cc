#include<iostream>

constexpr int MAX = 100'000;

struct Building {
    int idx, h, near, count;
    Building() : Building(0, 0) {};
    Building(int idx, int h) : idx(idx), h(h), near(-987'654'321), count(0) {};
};

Building arr[MAX];
Building stack[MAX];

int main()
{
	int n;
    std::cin >> n;

    for (int i = 0; i < n; i++)
    {
        int tmp; 
        std::cin >> tmp;
        arr[i] = Building{i + 1, tmp};
    }
    
    int rear = 0;

    for (int i = 0; i < n; i++)
    {
        Building& now = arr[i];

        while (rear > 0 && stack[rear - 1].h <= now.h)
        {
            rear--;
        }

        now.count += rear;
        if (
            rear != 0 
            && (std::abs(now.idx - stack[rear - 1].idx) < std::abs(now.idx - now.near) 
                || (stack[rear - 1].idx < now.near 
                    && std::abs(now.idx - stack[rear - 1].idx) == std::abs(now.idx - now.near)
                    )
                )
            ) 
        {
            now.near = stack[rear - 1].idx;
        }

        stack[rear++] = now;
    }

    rear = 0;

    for (int i = n - 1; i >= 0; i--)
    {
        Building& now = arr[i];

        while (rear > 0 && stack[rear - 1].h <= now.h)
        {
            rear--;
        }

        now.count += rear;

        if (
            rear != 0 
            && (std::abs(now.idx - stack[rear - 1].idx) < std::abs(now.idx - now.near) 
                || (stack[rear - 1].idx < now.near 
                    && std::abs(now.idx - stack[rear - 1].idx) == std::abs(now.idx - now.near)
                    )
                )
            ) 
        {
            now.near = stack[rear - 1].idx;
        }

        stack[rear++] = now;
    }

    for (int i = 0; i < n; i++)
    {
        std::cout << arr[i].count;
        if (arr[i].count != 0)
        {
            std::cout << " " << arr[i].near;
        }
        std::cout << '\n';
    }
}