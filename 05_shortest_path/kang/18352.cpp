#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#define MAX 300000
using namespace std;

vector<int> graph[MAX + 1];
vector<int> minDist;
int visited[MAX + 1];
int k;

void bfs(int start) {
	queue<int> q;
	int distance = 0;
	q.push(start);
	visited[start] = true;

	while (!q.empty()) {
		int q_size = q.size();
		for (int i = 0; i < q_size; i++) {
			int x = q.front();
			q.pop();

			if (distance == k) {
				minDist.push_back(x);
			}

			for (int j = 0; j < graph[x].size(); j++) {
				if (!visited[graph[x][j]]) {
					q.push(graph[x][j]);
					visited[graph[x][j]] = true;
				}
			}
		}
		distance++;
		if (distance > k) {
			return;
		}
	}
}

int main() {
	int n, m, x;
	int u, v;

	cin >> n >> m >> k >> x;

	for (int i = 0; i < m; i++) {
		cin >> u >> v;
		graph[u].push_back(v);
	}

	bfs(x);

	if (minDist.empty()) {
		cout << -1 << "\n";
	}
	else {
		sort(minDist.begin(), minDist.end());
		for (int x : minDist) {
			cout << x << "\n";
		}
	}
}
