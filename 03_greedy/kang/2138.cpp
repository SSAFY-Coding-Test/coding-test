#include <iostream>

using namespace std;

int N, answer;
string str;
bool in[100005];
bool tmp[100005];
bool out[100005];

void pushSwitch(int idx);

int calcPushCnt(bool firstSwitchOn) {
  int pushCnt = 0;
  
  for(int i = 0; i < N; i++) {
    tmp[i] = in[i];
  }
  
  if(firstSwitchOn) { //첫 전구 켜기
    pushSwitch(0);
    pushCnt++;
  }
  
  int i = 0;
  while(i < N - 1) { 
    if(tmp[i] != out[i]) { //현재 전구 값이 다르면
      pushSwitch(i + 1); //이전 전구와 현재 전구는 이미 눌렀다는 가정하에 다음 전구를 누름
      pushCnt++;
    }
    i++;
  }
  
  if(tmp[i] != out[i]) { //마지막 전구가 다르면 불가능
    return 100005;
  }else {
    return pushCnt;
  }
}

void pushSwitch(int idx) {
  tmp[idx] = !tmp[idx];
  if(idx - 1 > 0) {
    tmp[idx- 1] = !tmp[idx - 1];
  }
  if(idx + 1 < N) {
    tmp[idx + 1] = !tmp[idx + 1];
  }
}

int main() 
{
    cin >> N;
    cin >> str;
    for(int i = 0; i < N; i++) {
        in[i] = str[i] - '0';
    }
    cin >> str;
    for(int i = 0; i < N; i++) {
        out[i] = str[i] - '0';
    }
    
    answer = min(calcPushCnt(true), calcPushCnt(false));
    
    if(answer == 100005)
      answer = -1;
    
    cout << answer << "\n";
    return 0;
}
