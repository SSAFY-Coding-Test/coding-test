#define TWELVE 0
#define LEFT 1
#define RIGHT 2
#include <iostream>
#include <cmath>

using namespace std;

bool gear[4][8]; //기어번호, 기어톱니
int gearIdx[4][3]; //기어번호, 톱니 인덱스(12시, 왼쪽, 오른쪽)
int k;

void rotateGear(int gearNum, int rotate, bool isLeft, bool isRight) {
  int twelve = gearIdx[gearNum][TWELVE];
  int left = gearIdx[gearNum][LEFT];
  int right = gearIdx[gearNum][RIGHT];
  //기어 회전
  if(rotate == 1) { //시계
    gearIdx[gearNum][TWELVE] = (twelve + 7) % 8;
    gearIdx[gearNum][LEFT] = (left + 7) % 8;
    gearIdx[gearNum][RIGHT] = (right + 7) % 8;
  }else { //반시계
    gearIdx[gearNum][TWELVE] = (twelve + 1) % 8;
    gearIdx[gearNum][LEFT] = (left + 1) % 8;
    gearIdx[gearNum][RIGHT] = (right + 1) % 8;
  }
  //왼쪽 기어 회전이 가능하고, 왼쪽 기어가 존재하고, 맞닿는 부분 극이 다르면
  if(isLeft && gearNum - 1 >= 0 && gear[gearNum][left] != gear[gearNum - 1][gearIdx[gearNum - 1][RIGHT]])
    rotateGear(gearNum - 1, -1 * rotate, true, false);
  //오른쪽 기어 회전
  if(isRight && gearNum + 1 < 4 && gear[gearNum][right] != gear[gearNum + 1][gearIdx[gearNum + 1][LEFT]])
    rotateGear(gearNum + 1, -1 * rotate, false, true);
  
  
}

int main() {
  cin.tie(0);
  ios::sync_with_stdio(0);
  
  string s;
  int gearNum, rotate;
  for(int i = 0; i < 4; i++) {
    cin >> s;
    for(int j = 0; j < 8; j++) {
      gear[i][j] = (s[j] == '1') ? 1 : 0;
    }
    gearIdx[i][TWELVE] = 0;
    gearIdx[i][LEFT] = 6;
    gearIdx[i][RIGHT] = 2;
  }
  
  cin >> k;
  for(int i = 0; i < k; i++) {
    cin >> gearNum >> rotate;
    rotateGear(gearNum - 1, rotate, true, true);
  }
  
  int score = 0;
  for(int i = 0; i < 4; i++) {
    if(gear[i][gearIdx[i][TWELVE]] == 1)
      score += pow(2, i);
  }
  
  cout << score;
  return 0;
}
