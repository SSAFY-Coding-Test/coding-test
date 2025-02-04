#include <iostream>
#include <set>
using namespace std;

int N, answer = 10000;
int map[11][11];
bool visit[11][11];
pair<int, int> seeds[3];
int dx[5] = {0, -1, 1, 0, 0};
int dy[5] = {0, 0, 0, -1, 1};

void plantSeed();

void selectSeedLocation(int k) {
    if(k == 3) {
      plantSeed();
      return;
    }
    for(int i = 1; i < N - 1; i++) {
      for(int j = 1; j < N - 1; j++) {
        if(!visit[i][j]) {
          seeds[k] = {i, j};
          visit[i][j] = true;
          selectSeedLocation(k + 1);
          visit[i][j] = false;
        }
      }
    }
}

void plantSeed() {
  set<pair<int, int>> s;
  int x, y, nx, ny;
  int price = 0;
  
  for(int i = 0; i < 3; i++) {
    x = seeds[i].first;
    y = seeds[i].second;
    for(int d = 0; d < 5; d++) {
      nx = x + dx[d];
      ny = y + dy[d];
      if(nx < 0 || nx >= N || ny < 0 || ny >= N)
        continue;
      if(s.find({nx, ny}) != s.end()) {  //꽃이 겹치는 경우 
        return;
      }
      s.insert({nx, ny});
      price += map[nx][ny];
    }
  }
  answer = min(price, answer);
}

int main() {
    cin.tie(0);
    ios::sync_with_stdio(0);
    
    cin >> N;
    for(int i = 0; i < N; i++) {
      for(int j = 0; j < N; j++) {
        cin >> map[i][j];
      }
    }
    
    selectSeedLocation(0);
    
    cout << answer;
    
    return 0;
}
