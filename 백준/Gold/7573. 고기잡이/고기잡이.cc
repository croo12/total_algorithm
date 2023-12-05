#include <iostream>

#include <vector>
#include <string>

using namespace std;

int main() 
{
	int n, i, m;
	cin >> n >> i >> m;
	i /= 2;

	vector<std::pair<int, int>> fish(m);

	for (int k = 0; k < m; k++)
	{
		
		cin >> fish[k].first >> fish[k].second;
	}

	int answer = 0;

	for (int k = 0; k < m; k++) 
	{
		for (int row = 1; row < i; row++) 
		{
			int col = i - row;
			for (int r = fish[k].first - row; r <= fish[k].first; r++)
			{
				for (int c = fish[k].second - col; c <= fish[k].second; c++)
				{
					int count = 0;
					for (int j = 0; j < m; j++)
					{
						if ( fish[j].first >= r && fish[j].first <= r + row &&
								fish[j].second >= c && fish[j].second <= c + col ) 
						{
							count++;
						}
					}
					answer = max(count, answer);
				}
			}
		}
	}

	cout << answer << endl;
}
