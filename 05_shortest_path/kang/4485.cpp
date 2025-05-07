#include <iostream>
#include <queue>

using namespace std;

int N, T;
int map[126][126];
int dist[126][126];
int dx[4] = {-1, 1, 0, 0};
int dy[4] = {0, 0, -1, 1};

int calcMinRupee() {
    queue<pair<int, int>> q;
    int x, y, nx, ny;
    q.push({0, 0});
    dist[0][0] = map[0][0];
    
    while(!q.empty()) {
        x = q.front().first;
        y = q.front().second;
        q.pop();
        for(int i = 0; i < 4; i++) {
            nx = x + dx[i];
            ny = y + dy[i];
            if(nx < 0 || nx >= N || ny < 0 || ny >= N)
                continue;
            if(map[nx][ny] + dist[x][y] < dist[nx][ny]) {
                dist[nx][ny] = map[nx][ny] + dist[x][y];
                q.push({nx, ny});
            }
        }
    }
    return dist[N - 1][N - 1];
}

int main() {
    cin.tie(0) -> ios::sync_with_stdio(0);
    while(true) {
        cin >> N;
        if(N == 0) {
            break;
        }
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                cin >> map[i][j];
                dist[i][j] = 1e9;
            }
        }
        cout << "Problem " << ++T << ": " << calcMinRupee() << "\n";
    }

    return 0;
}
