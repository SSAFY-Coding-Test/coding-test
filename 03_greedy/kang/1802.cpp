#include <iostream>

using namespace std;

int T, n;
string paper;
bool isPos;

int main() 
{
    cin.tie(0);
    ios::sync_with_stdio(0);
    
    cin >> T;
    for(int t = 0; t < T; t++) {
      cin >> paper;
      n = paper.size();
      isPos = true;
      if(n % 2 == 0) {
        isPos = false;
      }else {
        while(n > 1) {
          int mid = n / 2;
          for(int i = 1; i <= mid; i++) {
            if(!(paper[mid + i] - '0') != paper[mid - i] - '0') {
              isPos = false;
              break;
            } 
          }
          if(!isPos)
            break;
          n = mid;
        }
      }
      cout << (isPos ? "YES" : "NO") << "\n"; 
    }
    return 0;
}
