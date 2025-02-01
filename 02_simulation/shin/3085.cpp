#include <iostream>
#include <algorithm>

using namespace std;

int n;
char map[51][51];
int dx[4] = { -1,1,0,0 };
int dy[4] = { 0,0,-1,1 };
int maxCandy = 0;

void countRowCandy() {
	for (int i = 0; i < n; i++) {
		int cnt = 1;
		for (int j = 0; j < n; j++) {  // n-1까지 가야 최대사탕 수 갱신 가능
			if (map[i][j] == map[i][j + 1]) {
				cnt++;
			}
			else {
				if (cnt > maxCandy) {
					maxCandy = cnt;
				}
				cnt = 1;
			}
		}
	}
}

void countColCandy() {
	for (int i = 0; i < n; i++) {
		int cnt = 1;
		for (int j = 0; j < n; j++) {
			if (map[j][i] == map[j+1][i]) {
				cnt++;
			}
			else {
				if (cnt > maxCandy) {
					maxCandy = cnt;
				}
				cnt = 1;
			}
		}
	}
}


int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> map[i][j];
		}
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			// 상,하,좌,우 순으로 캔디 swap
			for (int k = 0; k < 4; k++) {
				int nx = i + dx[k];
				int ny = j + dy[k];
				if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
					continue;
				}
				swap(map[i][j], map[nx][ny]);
				countRowCandy();	// 가로로 가장 긴 부분 구하기
				countColCandy();	// 세로로 가장 긴 부분 구하기
				swap(map[nx][ny], map[i][j]);	// 사탕 원위치
			}
		}
	}

	cout << maxCandy;

	return 0;
}