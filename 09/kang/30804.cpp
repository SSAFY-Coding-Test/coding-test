#include <iostream>
#include <map>

using namespace std;

int N;
int s[200003];
int f[2];

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);
  
  cin >> N;
  for(int i = 0; i < N; i++) {
    cin >> s[i];  
  }
  
  int maxCnt = 1;
  int cnt = 0;
  for(int i = 0; i < N; i++) {
    bool isFind = false;
    for(int j = 0; j < 2; j++) { //과일이 있는지 판단
      if(s[i] == f[j]) {
        isFind = true;
        cnt++;
        break;
      }
    }
    if(!isFind) { //새로운 과일이면
      maxCnt = max(maxCnt, cnt);
      
      int j = i - 1;
      int bCnt = 0;
      int bf = i - 1 >= 0 ? s[i - 1] : 0;
      while(j >= 0 && s[j] == bf) {
        bCnt++;
        j--;
      }
      
      cnt = bCnt + 1; //이전 과일 개수 + 새로운 과일 개수(1)
      f[0] = bf;
      f[1] = s[i];
    }
  }
  maxCnt = max(maxCnt, cnt);
  cout << maxCnt << "\n";
  
  return 0;
}
