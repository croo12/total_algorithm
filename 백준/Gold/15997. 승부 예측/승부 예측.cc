#include <iostream>
#include <unordered_map>

using namespace std;

unordered_map<string, int> m;
struct Expect {
	string f, s;
	double w, d, l;

	Expect() : Expect(Expect("", "", 0.0, 0.0, 0.0)) {};
	Expect(string _f, string _s, double _w, double _d, double _l)
		: f(_f), s(_s), w(_w), d(_d), l(_l) {};
};

Expect p[6];
int score[4];
double answer[4];

void dfs(int idx, double percentage);

int main() {
	cin.tie(0); 
	ios_base::sync_with_stdio(0);

	for (int i = 0; i < 4; i++) {
		string s;
		cin >> s;

		m[s] = i;
	}

	for (int i = 0; i < 6; i++)
	{
		string f, s;
		double w, d, l;

		cin >> f >> s >> w >> d >> l;

		p[i] = Expect(f, s, w, d, l);
	}

	fill(score, score + 4, 0);
	fill(answer, answer + 4, 0.0);

	dfs(0, 1.0);

	for (int i = 0; i < 4; i++)
	{
		cout << answer[i] << '\n';
	}
}

void dfs(int idx, double percentage) {
	if (percentage == 0.0) return;

	if (idx == 6) {
		//점수가 높은 사람
		//2번으로 높은 사람
		//같으면 n빵하기
		int max = 0;
		int count = 0;

		for (int i = 0; i < 4; ++i) {
			if (score[i] > max) {
				max = score[i];
				count = 1;
			}
			else if ( score[i] == max ) {
				++count;
			}
		}

		if (count == 1) {
			for (int i = 0; i < 4; ++i)
				if (score[i] == max) answer[i] += percentage;

			int n_max = 0;
			count = 0;

			for (int i = 0; i < 4; ++i) {
				if (score[i] != max && score[i] > n_max) {
					n_max = score[i];
					count = 1;
				}
				else if (score[i] == n_max) {
					++count;
				}
			}

			for (int i = 0; i < 4; i++)
				if (score[i] == n_max) answer[i] += percentage / count;
		}
		else if (count == 2) {
			for (int i = 0; i < 4; ++i)
				if (score[i] == max) answer[i] += percentage;
		}
		else {
			for (int i = 0; i < 4; ++i)
				if (score[i] == max) answer[i] += percentage * 2 / count ;
		}

		return;
	}

	Expect e = p[idx];
	int f = m[e.f];
	int s = m[e.s];

	double w = e.w;
	double d = e.d;
	double l = e.l;

	score[f] += 3;
	dfs(idx + 1, percentage * w);
	score[f] -= 3;

	score[f] += 1;
	score[s] += 1;
	dfs(idx + 1, percentage * d);
	score[f] -= 1;
	score[s] -= 1;

	score[s] += 3;
	dfs(idx + 1, percentage * l);
	score[s] -= 3;
}