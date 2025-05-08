#include <iostream>
#include <queue>
using namespace std;

int N, M;
int ladder[103];
int snake[103];
bool visit[103];

int bfs() {
  queue<pair<int , int>> q; //좌표, 주사위 수
  int x, cnt, nx;
  
  q.push({1, 0});
  visit[1] = true;
  while(!q.empty()) {
    x = q.front().first;
    cnt = q.front().second;
    q.pop();
    if(x == 100) {
      return cnt;
    }
    for(int i = 1; i <= 6; i++) {
      nx = x + i;
      if(nx > 100)
        break;
      if(visit[nx])
        continue;
      if(ladder[nx] != 0) //사다리를 타고 이동한 좌표
        nx = ladder[nx];
      if(snake[nx] != 0) //뱀으로 이동한 좌표
        nx = snake[nx];
      q.push({nx, cnt + 1});
      visit[nx] = true;
    }
  }
  
}

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);
  int idx;
  
  cin >> N >> M;
  for(int i = 0; i < N; i++) {
    cin >> idx >> ladder[idx]; 
  }
  for(int i = 0; i < M; i++) {
    cin >> idx >> snake[idx]; 
  }

  cout << bfs() << "\n";
  
  return 0;
}
