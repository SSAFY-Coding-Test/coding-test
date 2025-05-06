#include <iostream>
#include <vector>
#include <queue>


using namespace std;

int n, m;
vector<int> adj[1001];
int dist[1001];

void bfs() {
  for(int i = 1; i <= n; i++) {
    dist[i] = 0;
  }
  queue<int> q;
  int x;
  
  q.push(1);
  while(!q.empty()) {
    x = q.front();
    q.pop();
    for(int nxt : adj[x]) {
      if(dist[nxt] == 0) {
        dist[nxt] = dist[x] + 1;
        q.push(nxt);
      }
    }
  }
  
  cout << 0 << " ";
  for(int i = 2; i <= n; i++) {
    cout << ((dist[i] == 0)? -1 : dist[i])  << " ";
  }
  cout << "\n";
}

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);
  int q, a, b;
  
  cin >> n >> m;
  for(int i = 0; i < m; i++) {
    cin >> a >> b;
    adj[a].push_back(b);
    adj[b].push_back(a);
  }
  
  cin >> q;
  for(int i = 0; i < q; i++) {
    cin >> a >> b;
    adj[a].push_back(b);
    adj[b].push_back(a);
    //각 도시별 1번 도시와의 최단 거리 (1번도시 기준 모든 도시의 최단거리)
    bfs();
  }
}
