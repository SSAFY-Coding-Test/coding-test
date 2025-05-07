#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int n, m, r;
int item[101];
int dist[101];	//시작 지점부터 각 지점까지의 최단 거리
bool visited[101];
int maxItem = 0;
vector<pair<int, int>> v[101];

void bfs(int x) {
	priority_queue <pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;

	dist[x] = 0;
	visited[x] = true;
	pq.push({ dist[x], x });

	while (!pq.empty()) {
		// 거리가 가장 짧은 노드를 큐에서 꺼내기
		int curD = pq.top().first;
		int cur = pq.top().second;
		pq.pop();
		visited[cur] = true;

		for (int i = 0; i < v[cur].size(); i++) {
			int nextD = v[cur][i].first;
			int next = v[cur][i].second;

			if (visited[next])
				continue;
			if (dist[next] > curD + nextD) {
				dist[next] = curD + nextD;
				pq.push({ dist[next], next });
			}
		}
	}

	int total = 0;
	for (int i = 1; i <= n; i++) {
		if (dist[i] <= m)
			total += item[i];
	}

	if (maxItem < total)
		maxItem = total;
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);	cout.tie(0);

	cin >> n >> m >> r;
	for (int i = 1; i <= n; i++) {
		cin >> item[i];
	}

	int a, b, l;
	for (int i = 0; i < r; i++) {
		cin >> a >> b >> l;
		v[a].push_back({ l,b });
		v[b].push_back({ l,a });
	}

	for (int i = 1; i <= n; i++) {
		// 0,1 값이 아닌 값 초기화 -> fill 이용
		fill(dist, dist + n + 1, 9999999);
		fill(visited, visited + n + 1, false);

		bfs(i);
	}

	cout << maxItem;

	return 0;
}