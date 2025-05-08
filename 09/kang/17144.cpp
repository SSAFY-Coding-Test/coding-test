#include <iostream>
#include <queue>
#include <cmath>
#include <cstring>
using namespace std;

int R, C, T;
int map[51][51];
int temp[51][51];
int d[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
int machine_up_r;
int machine_down_r;

void spreadDust() {
  memset(temp, 0, sizeof(temp));
  queue<pair<int, int>> q;
  int x, y, nx, ny, dCnt;
  
  for(int i = 0; i < R; i++) {
    for(int j = 0; j < C; j++) {
      if(map[i][j] != 0) {
        q.push({i, j});
        temp[i][j] = map[i][j];
      }
    }
  }
  
  while(!q.empty()) {
    x = q.front().first;
    y = q.front().second;
    q.pop();
    dCnt = 0;
    for(int i = 0; i < 4; i++) {
      nx = x + d[i][0];
      ny = y + d[i][1];
      if(nx < 0 || nx >= R || ny < 0 || ny >= C)
        continue;
      if(map[nx][ny] == -1)
        continue;
      temp[nx][ny] += floor(map[x][y] / 5);
      dCnt++;
    }
    temp[x][y] -= floor(map[x][y] / 5) * dCnt;
  }
  
  for(int i = 0; i < R; i++) {
    for(int j = 0; j < C; j++) {
      map[i][j] = temp[i][j];
    }
  }
}

void runMachine() {
  //위쪽 공기청정기
  for(int r = machine_up_r - 1; r > 0; r--) {
    map[r][0] = map[r - 1][0];
  }
  for(int c = 1; c < C; c++) {
    map[0][c - 1] = map[0][c];
  }
  for(int r = 1; r <= machine_up_r; r++) {
    map[r - 1][C - 1] = map[r][C - 1];
  }
  for(int c = C - 2; c >= 1; c--) {
    map[machine_up_r][c + 1] = map[machine_up_r][c];
  }
  map[machine_up_r][1] = 0;

  //아래쪽 공기청정기
  for(int r = machine_down_r + 2; r < R; r++) {
    map[r - 1][0] = map[r][0];
  }
  for(int c = 1; c < C; c++) {
    map[R - 1][c - 1] = map[R - 1][c];
  }
  for(int r = R - 2; r >= machine_down_r; r--) {
    map[r + 1][C - 1] = map[r][C - 1];
  }
  for(int c = C - 2; c >= 1; c--) {
    map[machine_down_r][c + 1] = map[machine_down_r][c];
  }
  map[machine_down_r][1] = 0;
}

int calcDust() {
  int cnt = 0;
  for(int i = 0; i < R; i++) {
    for(int j = 0; j < C; j++) {
      if(map[i][j] > 0) {
        cnt += map[i][j];
      }
    }
  }
  return cnt;
}

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);
  
  cin >> R >> C >> T;
  for(int i = 0; i < R; i++) {
    for(int j = 0; j < C; j++) {
      cin >> map[i][j];
      if(map[i][j] == -1) {
        if(machine_up_r == 0)
          machine_up_r = i;
        else
          machine_down_r = i;
      }
    }
  }
  
  while(T--) {
    spreadDust();
    runMachine();
  }
  cout << calcDust();
}
