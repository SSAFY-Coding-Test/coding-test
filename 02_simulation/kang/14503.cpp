#include <iostream>
#include <queue>

using namespace std;

int N, M, cleanRoomCnt;
int map[51][51];
int dx[4] = {-1, 0, 1, 0}; //북 동 남 서
int dy[4] = {0, 1, 0, -1};
struct Cleaner{
  int x, y;
  int d;
};

bool isValid(int x, int y) {
  if(x < 0 || x >= N || y < 0 || y >= M)
    return false;
  return true;
}

void cleanRoom(int sx, int sy, int sd) {
  queue<Cleaner> q;
  int x, y, d, nx, ny;
  bool hasEmptyRoom;
  
  q.push({sx, sy, sd});
  map[sx][sy] = 2; //벽과 구분하기 위해 2로 초기화
  while(!q.empty()) {
    x = q.front().x;
    y = q.front().y;
    d = q.front().d;
    q.pop();
    cleanRoomCnt = map[x][y];
    hasEmptyRoom = false;
    
    for(int i = 0; i < 4; i++) {
      nx = x + dx[i];
      ny = y + dy[i];
      if(!isValid(nx, ny))
        continue;
      if(map[nx][ny] == 0) { //빈방이 있으며
        int nd = (d + 3) % 4; //반시계 90도 회전
        if(isValid(x + dx[nd], y + dy[nd])) {
          if(map[x + dx[nd]][y + dy[nd]] == 0) { //전진
            hasEmptyRoom = true;
            q.push({x + dx[nd], y + dy[nd], nd});
            map[x + dx[nd]][y + dy[nd]] = map[x][y] + 1;
            break;
          }else { //전진 못하면 제자리로
            hasEmptyRoom = true;
            q.push({x, y, nd});
            break;
          }
        }
      }
    }
    if(!hasEmptyRoom) { //빈방 없으면 
      int nd = (d < 2) ? d + 2 : d - 2; //후진
      if(isValid(x + dx[nd], y + dy[nd])) {
        if(map[x + dx[nd]][y + dy[nd]] != 1) { //벽이 아니면 후진
          q.push({x + dx[nd], y + dy[nd], d}); //방향은 유지
          map[x + dx[nd]][y + dy[nd]] = map[x][y]; //개수도 유지
          continue;
        } else {
          return; //후진 못하면 멈춤
        }
      }
      return;  //후진 못하면 멈춤
    }
  }
}


int main() {
  cin.tie(0);
  ios::sync_with_stdio(0);
  
  int r, c, d;
  
  cin >> N >> M;
  cin >> r >> c >> d;
  for(int i = 0; i < N; i++) {
    for(int j = 0; j < M; j++) {
      cin >> map[i][j];
    }
  }
  
  cleanRoom(r, c, d);
  
  cout << cleanRoomCnt - 1;
  return 0;
}
