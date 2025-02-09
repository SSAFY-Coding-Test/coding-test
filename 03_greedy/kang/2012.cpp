#include <iostream>
#include <algorithm>

using namespace std;

int N;
int expectScore[500005];
long long answer; 

int main() 
{
  cin.tie(0);
  ios::sync_with_stdio(0);
  
  cin >> N;
  for(int i = 0; i < N; i++) {
    cin >> expectScore[i];
  }
  
  sort(expectScore, expectScore + N);
  
  for(int i = 0; i < N; i++) {
    answer += abs(expectScore[i] - (i + 1));
  }
  
  cout << answer;
  return 0;
}
