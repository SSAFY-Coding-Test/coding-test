#include <string>
#include <vector>
#include <queue>

using namespace std;

long long solution(int n, vector<int> works) {
    long long answer = 0;
    priority_queue<int> pq;
    int wSize = works.size();
    for(int i = 0; i < wSize; i++) {
        pq.push(works[i]);
    }
    
    while(n--) {
        if(pq.empty())
            break;
        int x = pq.top();
        pq.pop();
        if(x > 1)
            pq.push(x - 1);
    }
    
    while(!pq.empty()) {
        int x = pq.top();
        answer += x * x;
        pq.pop();
    }
    return answer;
}
