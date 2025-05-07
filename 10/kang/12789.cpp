#include <iostream>
#include <stack>

using namespace std;

int N;
int order[1003];
stack<int> st;
int idx = 1;

int main() {
    cin >> N;
    
    for(int i = 0; i < N; i++) {
      cin >> order[i];
    }
    
    for(int i = 0; i < N; i++) {
      if(idx < order[i]) {
        while(!st.empty()) {
          if(idx != st.top()) {
            break;
          }else {
            st.pop();
            idx++;
          }
        }
        st.push(order[i]);
      }else if(idx == order[i]) {
        idx++;
      }
    }
    
    while(!st.empty()) {
      if(idx != st.top()) {
        break;
      }else {
        st.pop();
        idx++;
      }
    }
    if(idx != N + 1)
      cout << "Sad\n";
    else
      cout << "Nice\n";
    return 0;
}
