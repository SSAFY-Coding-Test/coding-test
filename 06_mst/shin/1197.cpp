#include<iostream>
#include<vector>
#include<tuple>
#include<algorithm>

using namespace std;

int v, e;
vector<tuple<int, int, int>> edges; // (가중치, 정점1, 정점2)
vector<tuple<int, int, int>> tree;	// mst
int parent[10001];

int find_root(int x) {
	if (parent[x] == x)
		return x;
	return parent[x] = find_root(parent[x]);	// 경로 압축
}

void union_root(int v1, int v2) {	// 서로 다른 두 집합 연결
	v1 = find_root(v1);
	v2 = find_root(v2);

	if (v1 != v2)
		parent[v2] = v1;	// 두 집합 연결(union)
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> v >> e;

	int a, b, c;
	for (int i = 0; i < e; i++) {
		cin >> a >> b >> c;
		edges.push_back({ c,a,b });
	}
	sort(edges.begin(), edges.end());

	for (int i = 1; i <= v; i++) {
		parent[i] = i;
	}

	for (int i = 0; i < e; i++) {
		// 간선 뽑아내기
		auto [w, v1, v2] = edges[i];

		// 사이클이 존재하는지 확인
		if (find_root(v1) == find_root(v2))	continue;

		// 사이클이 발생하지 않으면, mst에 현재 간선을 추가
		tree.push_back(edges[i]);

		// Parent 관계 갱신
		union_root(v1, v2);

		// 만약 정점 개수 v에 대해 v-1개의 간선을 찾았다면 종료
		if (tree.size() == v - 1)	break;
	}

	int ans = 0;
	for (int i = 0; i < tree.size(); i++) {
		int w = get<0>(tree[i]);
		ans += w;
	}
	cout << ans;

	return 0;
}