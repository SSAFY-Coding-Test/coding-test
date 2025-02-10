#include <iostream>

using namespace std;

int N, maxCnt;
char candy[51][51];

void swap(int i, int j, int ni, int nj);
void countContinueRowCandy(int j);
void countContinueColCandy(int i);

void playGame() {
  for(int i = 0; i < N; i++) {
    for(int j = 0; j < N; j++) {
      if(j < N - 1) {
        swap(i, j, i, j + 1); // 좌 우 바꾸기
        countContinueColCandy(j);
        countContinueColCandy(j + 1);
        countContinueRowCandy(i);
        swap(i, j + 1, i, j);
      }
      if(i < N - 1) {
        swap(i, j, i + 1, j); //위 아래 바꾸기
        countContinueRowCandy(i);
        countContinueRowCandy(i + 1);
        countContinueColCandy(j);
        swap(i + 1, j, i, j);
      }
    }
  }
}

void swap(int i, int j, int ni, int nj) {
  int tmp = candy[i][j];
  candy[i][j] = candy[ni][nj];
  candy[ni][nj] = tmp;
}

void countContinueColCandy(int j) {
  int k = -1, cnt, maxRowCnt = 0;
  while(k < N - 2) {
    cnt = 1;
    k++;
    while(candy[k][j] == candy[k + 1][j]) {
      cnt++;
      k++;
      if(k == N - 1) {
        break;
      }
    }
    maxRowCnt = max(cnt, maxRowCnt);
  }
  maxCnt = max(maxCnt, maxRowCnt);
}

void countContinueRowCandy(int i) {
  int k = -1, cnt, maxColCnt = 0;
  while(k < N - 2) {
    cnt = 1;
    k++;
    while(candy[i][k] == candy[i][k + 1]) {
      cnt++;
      k++;
      if(k == N - 1) {
        break;
      }
    }
    maxColCnt = max(cnt, maxColCnt);
  }
  maxCnt = max(maxCnt, maxColCnt);
}

int main() {
    cin >> N;
    for(int i = 0; i < N; i++) {
      for(int j = 0; j < N; j++) {
        cin >> candy[i][j];
      }
    }
    
    for(int i = 0; i < N; i++) {
      countContinueRowCandy(i);
      countContinueColCandy(i);
    }
    
    playGame();
    
    cout << maxCnt << "\n";
    return 0;
}
