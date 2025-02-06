#include <iostream>
#include <cmath>
#include <algorithm>

using namespace std;
int n;	// 1~20
int map[21][21];
int min_dif = 100;
bool isStartTeam[21];	// true일경우 start팀, false일 경우 link 팀

void dfs(int cnt, int idx) {	// cnt: 자릿수, idx: 뽑는최소숫자
	if (cnt == n / 2) {	// 팀이 결성되었다면
		int start_score = 0;
		int link_score = 0;

		// 1~n 순회, 같은 팀에서 2명씩 조합하여 점수 계산(중복X)
		for (int i = 1; i <= n; i++) {
			for (int j = i + 1; j <= n; j++) {
				if (i == j)	continue;

				if (isStartTeam[i] && isStartTeam[j]) {
					start_score += map[i][j] + map[j][i];
				}
				else if(!isStartTeam[i] && !isStartTeam[j]) {
					link_score += map[i][j] + map[j][i];
				}
			}
		}

		// 최솟값 갱신
		min_dif = min(min_dif, abs(start_score - link_score));

		return;
	}

	for (int i = idx; i <= n; i++) {
		if (isStartTeam[i]) continue;
		isStartTeam[i] = true;
		dfs(cnt + 1, i + 1);
		isStartTeam[i] = false;
	}
}

int main()
{
	cin >> n;
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			cin >> map[i][j];
		}
	}

	// 팀을 어떻게 나눌것인가?
	// 조합 -> 백트래킹
	dfs(0, 1);

	cout << min_dif;

	return 0;
}