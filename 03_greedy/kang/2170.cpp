#include <iostream>
#include <algorithm>

using namespace std;

int N, x, y;
long long answer;
long long s, e;
pair<int, int> point[1000005];

int main() 
{
    cin.tie(0);
    ios::sync_with_stdio(0);
    
    cin >> N;
    for(int i = 0; i < N; i++) {
      cin >> x >> y;
      point[i] = {x, y};
    }
    
    sort(point, point + N);
    
    for(int i = 0; i < N; i++) { //범위 체크
      s = point[i].first;
      e = point[i].second;
      while(i < N - 1 && point[i + 1].first <= e) {
        if(point[i + 1].second >= e)
          e = point[i + 1].second;
        i++;
      }
      answer += e - s;
    }
    
    cout << answer << "\n";
    return 0;
}
