#include <iostream>
#include <set>
#include <stack>

using namespace std;

int T, n, cnt;
int pick[100003];
bool visit[100003];

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);
  
  cin >> T;
  for(int t = 0; t < T; t++) {
    cin >> n;
    for(int i = 1; i <= n; i++) {
      visit[i] = false;
      cin >> pick[i];
    }
    
    cnt = 0;
    for(int i = 1; i <= n; i++) { //DFS로 방문 시작
      if(!visit[i]) {
        set<int> s; 
        stack<int> st;
        s.insert(i);
        st.push(i);
        visit[i] = true;
        
        int nxt = pick[i];
        while(true) {
          if(s.find(nxt) != s.end()) { //사이클을 만나면
            while(st.top() != nxt) { //사이클에 포함하는 정점 전부 삭제 -> stack에는 사이클에 포함되지 않았지만, 방문한 정점이 남음
              s.erase(st.top());
              st.pop();
            }
            s.erase(st.top());
            st.pop();
            break;
          }else if(visit[nxt]) { //사이클이 아닌데 방문한 정점이라면? -> 이미 전에 방문한 정점이니 pass
            break;
          }else{ //DFS
            visit[nxt] = true;
            s.insert(nxt);
            st.push(nxt);
            nxt = pick[nxt];
          }
        }
        cnt += st.size(); //사이클에 포함되지 않은 정점의 수
      }
    }
    cout << cnt << "\n";
  }
  

}
