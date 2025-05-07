#include <iostream>

using namespace std;

int arr[100005];
int n;
int state[100005];

const int NOT_VISITED = 0;
const int CYCLE_IN = -1;

void run(int x) { //DFS
	int cur = x;
	while (true) {
		state[cur] = x; //방문 표시를 출발 정점으로 표시함
		cur = arr[cur];
		if (state[cur] == x) { //출발 정점으로 표시한 정점에 다시 도착 -> cycle
			while(state[cur] != CYCLE_IN) { 
				state[cur] = CYCLE_IN; //cycle 표시
				cur = arr[cur]; //cycle 내부를 전부 돌면서 표시
			}
			return;
		}
		else if (state[cur] != NOT_VISITED) { //cycle이 아닌데 방문한 정점이면 이미 처리된 정점이니 pass
			return;
		}
		//방문 안한 경우 -> DFS로 다음 정점으로 이동
	}
}

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	int t;
	cin >> t;
	while (t--) {
		cin >> n;
		fill(state + 1, state + n + 1, 0);
		for (int i = 1; i <= n; i++) {
			cin >> arr[i];
		}
		int ans = 0;
		for (int i = 1; i <= n; i++) {
			if (state[i] == NOT_VISITED) run(i);
		}
		int cnt = 0;
		for (int i = 1; i <= n; i++) {
			if (state[i] != CYCLE_IN) cnt++; //cycle 표시가 안된 정점의 수
		}
		cout << cnt << "\n";
	}
}
