#include <iostream>
#include <queue>

using namespace std;

int T, h, w, docCnt;
string row;
char map[105][105];
bool visit[105][105];
bool key[26];
int dx[4] = {-1, 1, 0, 0};
int dy[4] = {0, 0, -1, 1};

void resetKey() {
  for(int i = 0; i < 26; i++) {
    key[i] = false;
  }
}

void bfs() {
  queue<pair<int, int> > q;
  int x, y, nx, ny;
  int newKey = -1;
  docCnt = 0;
  
  while(newKey != 0) { //새로 얻은 열쇠가 없으면 멈춤
    newKey = 0;
    
    for(int i = 0; i < h + 2; i++) { //현재 가진 키로 열 수 있는 모든 문 열기
      for(int j = 0; j < w + 2; j++) {
        visit[i][j] = false;
        if(map[i][j] >= 'A' && map[i][j] <= 'Z' && key[map[i][j] - 'A']) {
          map[i][j] = '.';
        }
      }
    }
    
    q.push({0, 0});
    visit[0][0] = true;
    
    while(!q.empty()) {
      x = q.front().first;
      y = q.front().second;
      q.pop();
      
      for(int d = 0; d < 4; d++) {
        nx = x + dx[d];
        ny = y + dy[d];
        if(nx < 0 || nx >= h + 2 || ny < 0 || ny >= w + 2)
          continue;
        if(map[nx][ny] == '*' || visit[nx][ny])
          continue;
        if(map[nx][ny] >= 'a' && map[nx][ny] <= 'z') {
          key[map[nx][ny] - 'a'] = true;
          newKey++;
          map[nx][ny] = '.';
        }else if(map[nx][ny] == '$') {
          docCnt++;
          map[nx][ny] = '.';
        }
        if(map[nx][ny] == '.') {
          q.push({nx, ny});
          visit[nx][ny] = true;
        }
      }
    }
  }
}

int main() {
  cin.tie(0);
  ios::sync_with_stdio(0);
  
  cin >> T;
  for(int t = 0; t < T; t++) {
    resetKey();
    
    cin >> h >> w;
    for(int i = 0; i < h + 2; i++) { //바깥 테두리를 추가해 이동가능하도록
      if(i > 0 && i < h + 1)
        cin >> row;
      for(int j = 0; j < w + 2; j++) {
        if(i == 0 || i == h + 1 || j == 0 || j == w + 1) {
          map[i][j] = '.';
        }else {
          map[i][j] = row[j - 1];
        }
      }
    }
    
    cin >> row;
    if(row != "0") {
      for(char k : row) {
        key[k - 'a'] = true;
      }
    }
    
    bfs();

    cout << docCnt << "\n";
  }
  return 0;
}
