#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int n, m;
vector<pair<int, int>> adj[50003]; //소, 도착 헛간
int dist[50003];

void findMinCow() {
  priority_queue<pair<int, int>> pq;
  int x, cow, newCow;
  fill(dist, dist + n + 1, 1e9);
  
  pq.push({0, 1});
  dist[1] = 0;
  while(!pq.empty()) {
    cow = -pq.top().first; //최소힙으로 사용하기 위함함
    x = pq.top().second;
    pq.pop();
    if(dist[x] < cow) //더 이상 가도 작아질 수 없는 경우 제외
      continue;
    for(pair<int, int> nxt : adj[x]) {
      newCow = cow + nxt.first;
      if(dist[nxt.second] > newCow) { //새로운 cost가 더 적으면 이동
        dist[nxt.second] = newCow;
        pq.push({-newCow, nxt.second});
      }
    }
  }
  
}

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);
  int a, b, c;
  
  cin >> n >> m;
  for(int i = 0; i < m; i++) {
    cin >> a >> b >> c;
    adj[a].push_back({c, b});
    adj[b].push_back({c, a});
  }
  
  findMinCow();
  
  cout << dist[n] << "\n";
  
}
