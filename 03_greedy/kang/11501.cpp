#include <iostream>

using namespace std;

int T, N;
long long answer;
int price[1000005];

int main() {
    cin.tie(0);
    ios::sync_with_stdio(0);
    cin >> T;
    for(int i = 0; i < T; i++) {
      answer = 0;
      cin >> N;
      for(int j = 0; j < N; j++) {
        cin >> price[j];
      }
      
      int pivot, j = N;
      bool isEnd = false;
      while(true) {
        if(j <= 1)
          break;
        pivot = price[--j];
        while(price[j - 1] <= pivot) {
          j--;
          answer += pivot - price[j];
          if(j == 0) {
            isEnd = true;
            break;
          }
        }
        if(isEnd)
          break;
      }
      
      cout << answer << "\n";
    }
    
    return 0;
}
