#include <iostream>
#include <set>

using namespace std;

int N, K, answer;
string word[51]; //단어 목록
set<char> alphaType[51]; //단어의 알파벳 종류 목록
bool visit[27]; //선택한 알파벳


void readWord();

void chooseAlpha(int k, int s) {
  if(k == K - 5) {
    readWord();
    return;
  }
  for(int i = s; i < 26; i++) {
    if(!visit[i]) {
      visit[i] = true;
      chooseAlpha(k + 1, i + 1);
      visit[i] = false;
    }
  }
}

void readWord() {
  int readWordCount = 0;
  bool canRead;
  
  for(int i = 0; i < N; i++) {
    canRead = true;
    for(char c : alphaType[i]) {
      if(!visit[c - 'a']) {
        canRead = false;
        break;
      }
    }
    if(canRead) 
      readWordCount++;
  }
  
  answer = max(readWordCount, answer);
}

int main() 
{
    cin >> N >> K;
    for(int i = 0; i < N; i++) {
      cin >> word[i];
      for(char c : word[i]) {
        alphaType[i].insert(c);
      }
    }
    
    if(K - 5 < 0) {
      cout << answer << "\n";
    }else {
      visit[0] = visit[2] = visit[8] = visit[13] = visit[19] = true;
      chooseAlpha(0, 0);
      cout << answer << "\n";
    }
    return 0;
}
