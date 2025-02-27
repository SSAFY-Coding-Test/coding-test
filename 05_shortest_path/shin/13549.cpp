#include <iostream>
#include <vector>
#include <cmath>
#include <string>
#include <cstring>
#include <algorithm>
#include <queue>

using namespace std;
int N, K;
bool visited[100001];

// 최소 시간 찾기 -> bfs 이용
void bfs(int x) {
	queue<pair<int, int>> q;	// 위치 x, 시간 cnt 순
	q.push(make_pair(x, 0));
	visited[x] = true;
	while (!q.empty()) {
		int v = q.front().first;
		int cnt = q.front().second;
		visited[v] = true;
		q.pop();
		if (v == K) {	// 동생을 찾은경우를 먼저 점검.
			cout << cnt;	// 찾은경우 시간을 출력하고 bfs 및 프로그램 종료
			break;
		}
		else {
			// 다음 위치를 방문하지 않은 상태일 경우,
			// 다음 위치를 큐에 시간+1 하여 push
			if (0 <= 2 * v && 2 * v <= 100000) {
				if (!visited[2 * v]) {
					q.push(make_pair(2 * v, cnt));
				}
			}
			if (0 <= v - 1 && v - 1 <= 100000) {	// 다음 위치가 유효한지 점검
				if (!visited[v - 1]) {
					q.push(make_pair(v - 1, cnt + 1));
				}
			}
			if (0 <= v + 1 && v + 1 <= 100000) {
				if (!visited[v + 1]) {
					q.push(make_pair(v + 1, cnt + 1));
				}
			}
		}
	}
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);	cout.tie(0);

	cin >> N >> K;

	bfs(N);

	return 0;
}