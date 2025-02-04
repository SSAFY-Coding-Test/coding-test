#define ROW 0
#define COL 1
#define GRID 2
#include <iostream>

using namespace std;

int board[10][10];
bool visit[3][10][10]; //가로 세로 격자 / 번호 / 1~9 여부

int calculGridNum(int x, int y) {
  return 3 * (x / 3) + y / 3;
}

bool solveSudoku(int i, int j) {
  if(i == 9) { //끝칸까지 모두 탐색한 경우
    return true;
  }
  
  int gridNum;
  int ni, nj;
  
  if(j == 8) { //새로운 좌표 계산
    ni = i + 1;
    nj = 0;
  }else {
    ni = i;
    nj = j + 1;
  }
  
  if(board[i][j] != 0) {
    return solveSudoku(ni, nj);
  }
  
  gridNum = calculGridNum(i, j);
  for(int k = 1; k <= 9; k++) {
    if(!visit[ROW][i][k] && !visit[COL][j][k] && !visit[GRID][gridNum][k]) {
      board[i][j] = k;
      visit[ROW][i][k] = true;
      visit[COL][j][k] = true;
      visit[GRID][gridNum][k] = true;

      if(solveSudoku(ni, nj)) { //모든 칸 채운 경우
        return true;
      }
      
      board[i][j] = 0;
      visit[ROW][i][k] = false;
      visit[COL][j][k] = false;
      visit[GRID][gridNum][k] = false;
    }
  }
  return false; //해당 칸에 모든 숫자를 넣어보고 실패한 경우
}

int main() {
  int gridNum;
  
  for(int i = 0; i < 9; i++) {
    for(int j = 0; j < 9; j++) {
      cin >> board[i][j];
      if(board[i][j] != 0) {
        gridNum = calculGridNum(i, j);
        visit[ROW][i][board[i][j]] = true;
        visit[COL][j][board[i][j]] = true;
        visit[GRID][gridNum][board[i][j]] = true;
      }
    }
  }
  
  solveSudoku(0, 0);
    
  for(int i = 0; i < 9; i++) {
    for(int j = 0; j < 9; j++) {
      cout << board[i][j] << " ";
    }
    cout << "\n";
  }
    
  return 0;
}
