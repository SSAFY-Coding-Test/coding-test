#include <iostream>
#include <vector>
#include <sstream>

using namespace std;

int N;
vector<string> sentences[101];
int p[101];
string L, t;
string sentence;
vector<string> Lsentence;

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);
  
  cin >> N;
  cin.ignore();
  for(int i = 0; i < N; i++) {
    getline(cin, sentence);
    stringstream T(sentence);
    while(getline(T, t, ' ')) {
      sentences[i].push_back(t);
    }
  }
  
  getline(cin, L);
  stringstream T(L);
  while(getline(T, t, ' ')) {
    Lsentence.push_back(t);
  }
  
  bool isPos = false;
  for(int i = 0; i < Lsentence.size(); i++) {
    isPos = false;
    for(int j = 0; j < N; j++) {
      if(p[j] < sentences[j].size() && sentences[j][p[j]] == Lsentence[i]) {
        isPos = true;
        p[j]++;
        break;
      }
    }
    if(!isPos) {
      cout << "Impossible\n";
      return 0;
    }
  }
  
  for(int j = 0; j < N; j++) { //말을 다 못한 앵무새가 있는 경우
    if(p[j] != sentences[j].size()) {
      cout << "Impossible\n";
      return 0;
    }
  }
  
  cout << "Possible\n";
  return 0;
}
