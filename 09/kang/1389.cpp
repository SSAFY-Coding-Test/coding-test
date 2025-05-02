#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

int N, M;
vector<int> adj[103];
int dist[103];
vector<pair<int, int>> kb;

int bfs(int s) {
  queue<int> q;
  int x, sum = 0;
  
  fill(dist, dist + N + 1, 1e9);
  q.push(s);
  dist[s] = 1;
  while(!q.empty()) {
    x = q.front();
    q.pop();
    for(int nxt : adj[x]) {
      if(dist[nxt] > dist[x] + 1) {
        dist[nxt] = dist[x] + 1;
        q.push(nxt);
      }
    }
  }
  
  for(int i = 1; i <= N; i++) {
    sum += dist[i];
  }
  return sum;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    int a, b;
    
    cin >> N >> M;
    for(int i = 0; i < M; i++) {
      cin >> a >> b;
      adj[a].push_back(b);
      adj[b].push_back(a);
    }
    
    
    for(int i = 1; i <= N; i++) {
      kb.push_back({bfs(i), i});
    }
    
    sort(kb.begin(), kb.end());
    
    cout << kb[0].second << "\n";
    
    return 0;
}
