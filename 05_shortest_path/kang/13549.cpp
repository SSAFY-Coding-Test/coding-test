#include <iostream>
#include <queue>

using namespace std;

int N, K;
int visit[200005];

int findMinDist() {
    queue<pair<int, int>> q; //시간, 위치
    int t, x, minus, plus, two;
    
    q.push({0, N});
    visit[N] = 0;
    while(!q.empty()) {
        t = q.front().first;
        x = q.front().second;
        q.pop();
        if(x == K * 2 + 1) {
          return visit[K];
        }
        if(visit[x] == 1e9 || visit[x] >= t) {
            minus = x - 1;
            plus = x + 1;
            two = x + x;
            if(minus >= 0 && (visit[minus] == 1e9 || visit[minus] > t + 1)) {
                q.push({t + 1, minus});
                visit[minus] = t + 1;
            }
            if(plus <= 200001 && (visit[plus] == 1e9 || visit[plus] > t + 1)) {
                q.push({t + 1, plus});
                visit[plus] = t + 1;
            }
            if(two <= 200001 && (visit[two] == 1e9 || visit[two] > t)) {
                q.push({t, two});
                visit[two] = t;
            }
        }
    }
    return 0;
}

int main() {
    cin >> N >> K;
    fill(visit, visit + 200003, 1e9);
    if(N >= K) {
        cout << N - K << "\n";
    }else {
        cout << findMinDist();
    }
    return 0;
}
