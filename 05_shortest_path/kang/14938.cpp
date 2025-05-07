#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int n, m, r;
int items[105];
vector<pair<int, int> > adj[105];
int d[105];

int calcMaxItems(int s) {
	queue<pair<int, int>> q; //정점, 거리
	int cur, dist, newDist;
	int maxItems = items[s];

	q.push({s, 0});
	d[s] = 0;
	while (!q.empty()) {
		cur = q.front().first;
		dist = q.front().second;
		q.pop();
		for (pair<int, int> nxt : adj[cur]) {
			newDist = nxt.second + dist;
			if (d[nxt.first] >= newDist && newDist <= m) {
				q.push({nxt.first, newDist});
				if (d[nxt.first] == 1e9) {
					maxItems += items[nxt.first];
				}
				d[nxt.first] = newDist;
			}
		}
	}
	return maxItems;
}

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);
	int a, b, l;
	
	cin >> n >> m >> r;
	for (int i = 1; i <= n; i++) {
		cin >> items[i];
	}
	for (int i = 0; i < r; i++) {
		cin >> a >> b >> l;
		adj[a].push_back({ b, l });
		adj[b].push_back({ a, l });
	}

	int maxItems = 0;
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			d[j] = 1e9;
		}
		maxItems = max(maxItems, calcMaxItems(i));
	}
	cout << maxItems << "\n";
	return 0;
}
