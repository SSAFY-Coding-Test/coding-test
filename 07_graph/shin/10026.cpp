#include <iostream>
#include <queue>
#include <vector>

using namespace std;
int n;

char map[101][101];
bool isVisited[101][101];
bool isVisited2[101][101];
int cnt, cnt2;	// cnt : 적록색약이 아닌 사람이 봤을때의 구역의 개수 ,cnt2 : // 적록색약인 사람이 봤을때의 구역의 개수
int dx[4] = { -1,1,0,0 };
int dy[4] = { 0,0,-1,1 };

// 적록색약이 아닌 사람이 봤을때의 구역 파악하기
void bfs(int x, int y) {
	isVisited[x][y] = true;
	queue<pair<int, int>> q;
	q.push({ x,y });
	char color = map[x][y];
	while (!q.empty()) {
		int topx = q.front().first;
		int topy = q.front().second;
		q.pop();
		for (int i = 0; i < 4; i++) {
			int nx = topx + dx[i];
			int ny = topy + dy[i];
			if (nx < 0 || nx >= n || ny < 0 || ny >= n)
				continue;
			if (!isVisited[nx][ny] && map[nx][ny] == color) {
				q.push({ nx,ny });
				isVisited[nx][ny] = true;
			}
		}
	}
}
// 적록색약인 사람이 봤을때의 구역 파악하기
void bfs2(int x, int y) {
	isVisited2[x][y] = true;
	queue<pair<int, int>> q;
	q.push({ x,y });
	char color = map[x][y];
	while (!q.empty()) {
		int topx = q.front().first;
		int topy = q.front().second;
		q.pop();
		for (int i = 0; i < 4; i++) {
			int nx = topx + dx[i];
			int ny = topy + dy[i];
			if (nx < 0 || nx >= n || ny < 0 || ny >= n)
				continue;
			if (!isVisited2[nx][ny] && (map[nx][ny] == color ||
				map[nx][ny] == 'R' && color == 'G' || map[nx][ny] == 'G' && color == 'R')) {
				q.push({ nx,ny });
				isVisited2[nx][ny] = true;
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
			if (!isVisited[i][j]) {
				bfs(i, j);
				cnt++;
			}
			if (!isVisited2[i][j]) {
				bfs2(i, j);
				cnt2++;
			}
		}
	}

	cout <<cnt << " " << cnt2;

	return 0;
}
