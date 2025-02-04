#include <iostream>
#include <algorithm>

using namespace std;

int N, M;
int arr[10];
int answer[10];
bool visit[10];

void printArr(int k) {
  if(k == M) {
    for(int i = 0; i < M; i++) {
      cout << answer[i] << " ";
    }
    cout << "\n";
    return;
  }
  for(int i = 0; i < N; i++) {
    if(!visit[i]) {
      answer[k] = arr[i];
      visit[i] = true;
      printArr(k + 1);
      visit[i] = false;
    }
  }
}

int main() 
{
    cin.tie(0);
    ios::sync_with_stdio(0);
    
    cin >> N >> M;
    for(int i = 0; i < N; i++) {
      cin >> arr[i];
    }
    
    sort(arr, arr + N);
    
    printArr(0);
    
    return 0;
}
