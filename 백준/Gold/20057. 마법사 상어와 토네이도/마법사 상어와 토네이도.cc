#include <iostream>

using namespace std;

int map[499][499];
bool vis[499][499];

//보는 방향에 따른 전후좌우 기록
int vec[4][4][2] = { 
	//f b l r
	{{0, -1}, {0, 1}, {1, 0}, {-1, 0}},
	{{1, 0}, {-1, 0}, {0, 1}, {0, -1}},
	{{0, 1}, {0, -1}, {-1, 0}, {1, 0}},
	{{-1, 0}, {1, 0}, {0, -1}, {0, 1}},
};

int n;

bool checkOutOfBound(const int &r, const int &c);

int main() {
	cin.tie(0); 
	ios_base::sync_with_stdio(0);

	cin >> n;

	//지도 입력
	for (int i = 0; i < n; ++i)
	{
		for (int j = 0; j < n; ++j)
		{
			cin >> map[i][j];
		}
	}

	//vis 배열 초기화
	fill(&vis[0][0], &vis[0][0] + sizeof(vis), false);
	
	//정답
	int answer = 0;

	//현재 좌표
	int r, c;
	r = c = n / 2;
	int dir = 0;

	vis[r][c] = true;

	while (r != 0 || c != 0) {

		//보는 방향대로 이동
		r += vec[dir][0][0];
		c += vec[dir][0][1];

		//vis 체크
		vis[r][c] = true;

		//y에 있는 모래 값
		int now = map[r][c];

		//모래 날리기
		int amount, rr, rc;
		int record = 0;

		for (int k = 0; k < 10; k++)
		{
			switch (k)
			{
			case 0:
				//좌
				amount = now * 7 / 100;
				rr = r + vec[dir][2][0];
				rc = c + vec[dir][2][1];
				break;

			case 1:
				//우
				amount = now * 7 / 100;
				rr = r + vec[dir][3][0];
				rc = c + vec[dir][3][1];
				break;

			case 2:
				//좌좌
				amount = now * 2 / 100;
				rr = r + vec[dir][2][0] * 2;
				rc = c + vec[dir][2][1] * 2;
				break;

			case 3:
				//우우
				amount = now * 2 / 100;
				rr = r + vec[dir][3][0] * 2;
				rc = c + vec[dir][3][1] * 2;
				break;

			case 4:
				//좌전
				amount = now * 10 / 100;
				rr = r + vec[dir][2][0] + vec[dir][0][0];
				rc = c + vec[dir][2][1] + vec[dir][0][1];
				break;

			case 5:
				//우전
				amount = now * 10 / 100;
				rr = r + vec[dir][3][0] + vec[dir][0][0];
				rc = c + vec[dir][3][1] + vec[dir][0][1];
				break;

			case 6:
				//좌후
				amount = now / 100;
				rr = r + vec[dir][2][0] + vec[dir][1][0];
				rc = c + vec[dir][2][1] + vec[dir][1][1];
				break;

			case 7:
				//우후
				amount = now / 100;
				rr = r + vec[dir][3][0] + vec[dir][1][0];
				rc = c + vec[dir][3][1] + vec[dir][1][1];
				break;

			case 8:
				//전전
				amount = now * 5 / 100;
				rr = r + vec[dir][0][0] * 2;
				rc = c + vec[dir][0][1] * 2;
				break;

			case 9:
				//전
				amount = now - record;
				rr = r + vec[dir][0][0];
				rc = c + vec[dir][0][1];
				break;

			default:
				break;
			}

			if (checkOutOfBound(rr, rc)) {
				answer += amount;
			}
			else {
				map[rr][rc] += amount;
			}

			record += amount;
		}

		//다음 방향 정하기
		//좌회전 가능하면 무조건 좌회전
		rr = r + vec[dir][2][0];
		rc = c + vec[dir][2][1];

		if (!checkOutOfBound(rr, rc) && !vis[rr][rc]) {
			dir = ++dir % 4;
		}
	}

	cout << answer << endl;
}

bool checkOutOfBound(const int& r, const int& c) {
	return r >= n || c >= n || r < 0 || c < 0;
}