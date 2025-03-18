#include <iostream>
#include <vector>
#include <set>
#include <algorithm>

using namespace std;

int N, D;
int d[10005]; //i까지의 최단 거리
set<int> x; //s, e 위치
int n[30]; //지름길이 있는 위치
vector<pair<int, int> > adj[10005]; 

void calcMinDist() {
	int idx = 0, dIdx;
	for (int cur : x) {
		n[idx++] = cur;
		d[cur] = cur;
		if (cur == D) {
			dIdx = idx - 1;
		}
	}
	for (int i = 0; i <= dIdx; i++) {
		int cur = n[i];
		for (int j = i; j <= dIdx; j++) {
			d[n[j]] = min(d[n[j]], d[cur] + n[j] - cur);
		}
		for (pair<int, int> nxt : adj[cur]) {
			d[nxt.first] = min(d[nxt.first], d[cur] + nxt.second);
		}
	}
}


int main() {
	cin >> N >> D;

	int s, e, dist;

	for (int i = 0; i < N; i++) {
		cin >> s >> e >> dist;
		adj[s].push_back({ e, dist });
		x.insert(s);
		x.insert(e);
	}

	x.insert(D);
	calcMinDist();

	cout << d[D] << "\n";
	return 0;
}
