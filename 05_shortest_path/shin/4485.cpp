#include <iostream>
#include <queue>
#include <limits.h>

using namespace std;

int n, ans;
int map[125][125];
int cost[125][125];
int dx[4] = { -1,1,0,0 };
int dy[4] = { 0,0,-1,1 };

void init() {
	// 0 , 1 이 아닌값으로 초기화 시 memset() 사용 불가
	for (int i = 0; i < 125; i++) {
		for (int j = 0; j < 125; j++) {
			cost[i][j] = INT_MAX;
		}
	}
}

void bfs(int x, int y) {
	queue<pair<int,int>> q;
	q.push({ x,y });
	cost[x][y] = map[x][y];

	while (!q.empty()) {
		int curX = q.front().first;
		int curY = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = curX + dx[i];
			int ny = curY + dy[i];
			if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

			if (cost[nx][ny] > cost[curX][curY] + map[nx][ny]) {
				cost[nx][ny] = cost[curX][curY] + map[nx][ny];
				q.push({ nx,ny });
			}
		}
	}
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);	cout.tie(0);

	int tc = 1;
	while (true) {
		cin >> n;
		if (n == 0) {
			break;
		}
		init();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cin >> map[i][j];
			}
		}

		bfs(0, 0);

		int result = cost[n - 1][n - 1];
		cout << "Problem " << tc << ": " << result << "\n";
		tc++;
	}


	return 0;
}