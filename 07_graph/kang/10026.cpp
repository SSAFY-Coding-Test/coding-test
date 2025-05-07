#include <iostream>
#include <queue>
#define MAX 101

char map[MAX][MAX];
int dist[MAX][MAX];
int dx[4] = {-1, 0, 1, 0};
int dy[4] = {0, 1, 0, -1};

using namespace std;

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    
    int n;
    
    cin >> n;
    
    for(int i = 0; i < n; i++) {
        string s;
        cin >> s;
        for(int j = 0; j < n; j++) {
            map[i][j] = s[j];
        }
    }
    
    queue<pair<int, int> > q;
    int cnt = 0, cnt2 = 0;
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            if(dist[i][j] == 0) { //dist가 방문 배열
                if(map[i][j] == 'B')
                    dist[i][j] = 1; //B
                else
                    dist[i][j] = 2; //R, G 는 같은 표시
                q.push({i, j});
                
                while(!q.empty()) {
                    pair<int, int> cur = q.front();
                    q.pop();
                    
                    for(int k = 0; k < 4; k++) {
                        int nx = cur.first + dx[k];
                        int ny = cur.second + dy[k];
                        
                        if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                        if(dist[nx][ny] > 0 || map[nx][ny] != map[cur.first][cur.second]) continue; //map이 값 배열
                        if(map[nx][ny] == 'B')
                            dist[nx][ny] = 1;
                        else
                            dist[nx][ny] = 2;
                        q.push({nx, ny});
                    }
                }
                cnt++;
            }
        }
    }
    
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            if(map[i][j] != 'O') {
                map[i][j] = 'O'; //방문 표시 -> map이 방문 배열
                q.push({i, j});
                while(!q.empty()) {
                    pair<int, int> cur = q.front();
                    q.pop();
                    
                    for(int k = 0; k < 4; k++) {
                        int nx = cur.first + dx[k];
                        int ny = cur.second + dy[k];
                        if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                        if(map[nx][ny] == 'O' || dist[nx][ny] != dist[cur.first][cur.second]) continue; //dist가 현재 값 배열
                        map[nx][ny] = 'O';
                        q.push({nx, ny});
                    }
                }
                cnt2++;
            }
        }
    }
    
    cout << cnt << " " << cnt2;

    return 0;
}
