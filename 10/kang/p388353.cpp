#include <string>
#include <vector>
#include <queue>
#include <cstring>

using namespace std;

int n, m;
char st[55][55];
bool visit[55][55];
int d[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

void bfs(char a) {
    queue<pair<int, int>> q;
    queue<pair<int, int>> delQ;
    memset(visit, 0, sizeof(visit));
    int x, y, nx, ny;
    
    q.push({0, 0});
    visit[0][0] = true;
    while(!q.empty()) {
        x = q.front().first;
        y = q.front().second;
        q.pop();
        for(int i = 0; i < 4; i++) {
            nx = x + d[i][0];
            ny = y + d[i][1];
            if(nx < 0 || nx >= n + 2 || ny < 0 || ny >= m + 2)
                continue;
            if(visit[nx][ny])
                continue;
            if(st[nx][ny] == a) { // ' ' 외부와 연결된 a이면 delQ에 넣기
                visit[nx][ny] = true;
                delQ.push({nx, ny});
                continue;
            }
            if(st[nx][ny] == ' ') { //' ' 외부로부터 갈 수 있는 경로면 탐색
                q.push({nx, ny});
                visit[nx][ny] = true;
            }
        }
    }
    
    while(!delQ.empty()) { //꺼낼 a들을 꺼내줌
        x = delQ.front().first;
        y = delQ.front().second;
        delQ.pop();
        st[x][y] = ' ';
    }
}

int solution(vector<string> storage, vector<string> requests) {
    int answer = 0;
    int rSize = requests.size();
    n = storage.size();
    m = storage[0].size();

    //테두리를 ' '로 채운 char 배열
    for(int i = 0; i < n + 2; i++) {
        fill(st[i], st[i] + m + 2, ' ');
    }
    for(int r = 1; r <= n; r++) {
        for(int c = 1; c <= m; c++) {
            st[r][c] = storage[r - 1][c - 1];
        }
    }
    
    for(int i = 0; i < rSize; i++) {
        if(requests[i].size() == 1) {
            bfs(requests[i][0]);
        }else {
            for(int r = 0; r < n + 2; r++) {
                for(int c = 0; c < m + 2; c++) {
                    if(st[r][c] == requests[i][0]) {
                        st[r][c] = ' ';
                    }
                }
            }
        }
    }
    
    for(int r = 0; r < n + 2; r++) {
        for(int c = 0; c < m + 2; c++) {
            if(st[r][c] != ' ') {
                answer++;
            }
        }
    }
    return answer;
}
