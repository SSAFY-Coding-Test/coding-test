#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int n, m;
vector<int> adj[100003];
int score[100003];

void bfs() {
    queue<int> q;
    int x;

    q.push(1);
    while(!q.empty()) {
        x = q.front();
        q.pop();
        for(int nx : adj[x]) {
            score[nx] += score[x];
            q.push(nx);
        }
    }
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    int idx, w;
    
    cin >> n >> m;
    cin >> idx;
    for(int i = 2; i <= n; i++) {
      cin >> idx;
      adj[idx].push_back(i);
    }
    for(int i = 0; i < m; i++) {
      cin >> idx >> w;
      score[idx] += w;
    }
    
    bfs();
    for(int i = 1; i <= n; i++) {
      cout << score[i] << " ";
    }
    cout << "\n";
    return 0;
}
