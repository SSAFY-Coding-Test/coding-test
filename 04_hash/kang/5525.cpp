#include <iostream>
#include <string>

using namespace std;

int N, M, OICnt, ans;
string s;

int main() {
    cin >> N >> M >> s;

    for(int i = 0; i < M; i++) {
      if(s[i] == 'O') { //시작 문자가 O이면 의미 없음!
        continue;
      }
      OICnt = 0;
      //시작 문자가 I이면 
      while(s[i + 1] == 'O' && s[i + 2] == 'I') {
        OICnt++; //OI 묶음 개수
        if(OICnt == N) { //패턴 발견하면
          ans++;
          OICnt--; //이전까지 센 OI 묶음 개수 감소
        }
        i += 2;
      }
    }
    cout << ans << "\n";
    return 0;
}
