#include <iostream>

using namespace std;

int N, ans = 1e9;
int score[21][21];
bool team[11];

void solution(int s, int k) {
    if(k == N / 2) {
        int t1 = 0, t2 = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(team[i] && team[j]) {
                    t1 += score[i][j];
                }
                if(!team[i] && !team[j]) {
                    t2 += score[i][j];
                }
            }
        }
        ans = min(abs(t1 - t2), ans);
        return;
    }
    for(int i = s; i < N; i++) {
        team[i] = true;
        solution(i + 1, k + 1);
        team[i] = false;
    }
}

int main() {
    cin >> N;
    for(int i = 0; i < N; i++) {
        for(int j = 0; j < N; j++) {
            cin >> score[i][j];
        }
    }
    
    team[0] = true;
    solution(1, 1);
    
    cout << ans;

    return 0;
}
