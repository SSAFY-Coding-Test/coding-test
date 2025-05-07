#include<iostream>
#include<vector>
#include<queue>
#include<algorithm>
#include<limits.h>

using namespace std;

int n, m, k, x;
vector<int> city[300001];	// city[i] = a : i -> a 방향의 길
int minD[300001] = { INT_MAX };
bool visited[300001];

void bfs(int v, int cnt) {
	visited[v] = true;
	queue<pair<int,int>> q;
	q.push({ v,0 });

	while (!q.empty()) {
		int cur = q.front().first;
		int cnt = q.front().second;
		q.pop();
		for (int i = 0; i < city[cur].size(); i++) {
			if (visited[city[cur][i]])	continue;
			visited[city[cur][i]] = true;
			q.push({ city[cur][i], cnt + 1 });
			// 근데 무조건 cnt+1이 거리 최솟값이 되는지 -> Yes
			minD[city[cur][i]] = cnt + 1;
		}
	}
} 

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> m >> k >> x;
	
	int a, b;
	for (int i = 0; i < m; i++) {
		cin >> a >> b;
		city[a].push_back(b);
	}

	minD[x] = 0;
	bfs(x, 0);

	vector<int> ans;

	for (int i = 1; i <= n; i++) {
		if (minD[i] == k) {
			ans.push_back(i);
		}
	}

	sort(ans.begin(), ans.end());
	if (ans.size() != 0) {
		for (int i = 0; i < ans.size(); i++) {
			cout << ans[i] << "\n";
		}
	}
	else
		cout << -1;


	return 0;
}