#include <iostream>
#include <cmath>

using namespace std;

int k;
char sign[10]; //기호
int num[11]; //만든 수
bool isUsed[10];
string minNum = "999999999", maxNum = "0";

bool solution(int n, int s) {
  if(n == k + 1) {
    string t;
    for(int i = 0; i < n; i++) {
      t += (num[i] + '0');
    }
    minNum = min(minNum, t);
    maxNum = max(maxNum, t);
    return true;
  }
  if(sign[(n - 1 == -1) ? 0 : n - 1] == '<') {
    //큰 방향
    for(int i = (n == 0) ? s : s + 1; i <= 9 ; i++) {
      if(i == 10) {
        return false;
      }
      if(!isUsed[i]) {
        num[n] = i;
        isUsed[i] = true;
        if(!solution(n + 1, i)) {
          isUsed[i] = false;
          return false;
        }
        isUsed[i] = false;
      }
    }
  }else {
    //작은 방향
    for(int i = (n == 0) ? s :s - 1; i >= 0; i--) {
      if(i == -1) {
        return false;
      }
      if(!isUsed[i]) {
        num[n] = i;
        isUsed[i] = true;
        if(!solution(n + 1, i)) {
          isUsed[i] = false;
          return false;
        }
        isUsed[i] = false;
      }
    }
  }
  return true;
}


int main() {
  
  cin >> k;
  for(int i = 0; i < k; i++) {
    cin >> sign[i];
  }
  
  solution(0, (sign[0] == '<') ? 0 : 9);
  
  cout << maxNum << "\n" << minNum << "\n";
  return 0;
}
