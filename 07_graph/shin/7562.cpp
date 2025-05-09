#include <iostream>
#include <algorithm>
#include <queue>
#include <climits>


using namespace std;

struct pos {
	int x, y;
	int cnt;
};

int t, l, cX, cY, gX, gY;
bool visited[301][301];
int dx[8] = { -1,-2,-2,-1,1,2,2,1 };
int dy[8] = { -2,-1,1,2,-2,-1,1,2 };

int bfs(int cX, int cY) {
	int min_move = INT_MAX;
	queue<pos> q;
	q.push({ cX,cY,0 });
	visited[cX][cY] = true;

	while (!q.empty()) {
		pos p = q.front();
		q.pop();
		int cx = p.x;
		int cy = p.y;
		int cnt = p.cnt;

		for (int i = 0; i < 8; i++) {
			int nx = cx + dx[i];
			int ny = cy + dy[i];

			if (nx < 0 || ny < 0 || nx >= l || ny >= l || visited[nx][ny])
				continue;
			if (nx == gX && ny == gY) {
				min_move = min(min_move, cnt + 1);
			}
			else {
				q.push({ nx,ny,cnt + 1 });
				visited[nx][ny] = true;
			}
		}
	}


	return min_move;
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);	cout.tie(0);

	cin >> t;
	while (t--) {
		cin >> l >> cX >> cY >> gX >> gY;

		// bool 배열 초기화
		fill(visited[0], visited[301], false);

		int result = bfs(cX, cY);
		cout << (result == INT_MAX ? 0 : result) << "\n";
	}

	return 0;
}