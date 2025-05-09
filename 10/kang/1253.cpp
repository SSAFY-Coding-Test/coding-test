#include <iostream>
#include <map>
#include <vector>

using namespace std;

int N;
int a[2003];
map<int, int> tSum;
int zeroCnt;
int answer;

int main() {
    
    cin >> N;
    for(int i = 0; i < N; i++) {
      cin >> a[i];
      if(a[i] == 0)
        zeroCnt++;
    }
    
    for(int i = 0; i < N - 1; i++) {
      for(int j = i + 1; j < N; j++) {
        tSum[a[i] + a[j]]++;
      }
    }
    
    for(int i = 0; i < N; i++) {
      //합이 존재하는데, a[i] != 0 -> a[i] + 0 = a[i] 가 아닌 조합이 존재해야 하므로 zeroCnt != tSum[a[i]]이면 다른 조합이 존재 하는 것
      //다만 a[i] == 0 이면 zeroCnt에서 나 자신 1개를 빼주고 비교
      if(tSum.find(a[i]) != tSum.end() && ((a[i] != 0 && tSum[a[i]] != zeroCnt) || (a[i] == 0 && tSum[a[i]] != zeroCnt - 1)) ) {
        answer++;
      }
    }
    
    cout << answer;
    
    return 0;
}
