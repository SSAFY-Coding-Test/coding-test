#include <iostream>
#include <stack>
using namespace std;

stack<char> st;
string s;
bool isTag;
string answer;

int main() {
    getline(cin, s);
    for(int i = 0; i < s.size(); i++) {
      if(s[i] == '<') {
         while(!st.empty()) {
            answer += st.top();
            st.pop();
          }
        isTag = true;
        answer += s[i];
      }else if(s[i] == '>') {
        isTag = false;
        answer += s[i];
      }else if(isTag && s[i] != ' ') {
        answer += s[i];
      }else if(s[i] != ' ') {
        st.push(s[i]);
      }else {
        while(!st.empty()) {
          answer += st.top();
          st.pop();
        }
        answer += ' ';
      }
    }
    while(!st.empty()) {
      answer += st.top();
      st.pop();
    }
    cout << answer;
    return 0;
}
