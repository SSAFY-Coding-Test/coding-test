#include <iostream>

using namespace std;

int N, M, answer;
int score[501][501];
int dx[4] = {0, -1, 1, 0}; //우상하좌
int dy[4] = {1, 0, 0, -1};
int tShape[4][3] = {
  {0, 2, 3}, //ㅜ
  {1, 2, 3}, //ㅓ
  {0, 1, 3}, //ㅗ
  {0, 1, 2} //ㅏ
};

void calculfourShapeScore(int x, int y, int k, int sum, int dir) {
  int nx, ny;
  if(k == 3) {
    answer = max(answer, sum);
    return;
  }
  for(int i = 0; i < 3; i++) {
    nx = x + dx[i];
    ny = y + dy[i];
    if(dir == 1 && i == 2)
      continue;
    if(dir == 2 && i == 1)
      continue;
    if(nx < 0 || nx >= N || ny < 0 || ny >= M)
      continue;
    calculfourShapeScore(nx, ny, k + 1, sum + score[nx][ny], i);
  }
}

void calculTShapeScore(int i, int j) {
  int sum, nx, ny;
  for(int s = 0; s < 4; s++) {
    sum = score[i][j];
    for(int d = 0; d < 3; d++) {
      nx = i + dx[tShape[s][d]];
      ny = j + dy[tShape[s][d]];
      if(nx < 0 || nx >= N || ny < 0 || ny >= M)
        continue;
      sum += score[nx][ny];
    }
    answer = max(answer, sum);
  }
}

int main() 
{
    cin.tie(0);
    ios::sync_with_stdio(0);
    
    cin >> N >> M;
    for(int i = 0; i < N; i++) {
      for(int j = 0; j < M; j++) {
        cin >> score[i][j];
      }
    }
    
    for(int i = 0; i < N; i++) {
      for(int j = 0; j < M; j++) {
        calculfourShapeScore(i, j, 0, score[i][j], 0);
        calculTShapeScore(i, j);
      }
    }
    
    cout << answer;
    
    return 0;
}
