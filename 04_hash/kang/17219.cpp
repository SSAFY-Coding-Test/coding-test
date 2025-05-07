#include <iostream>
#include <unordered_map>

using namespace std;


int N, M;
string word, pw;
unordered_map<string, string> password;


int main() {
  cin.tie(0) -> ios::sync_with_stdio(0);
  
  cin >> N >> M;
  for(int i = 0; i < N; i++) {
    cin >> word >> pw;
    password[word] = pw;
  }
  
  for(int i = 0; i < M; i++) {
    cin >> word;
    cout << password[word] << "\n";
  }
  return 0;
}
