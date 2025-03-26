#include <iostream>
#include <queue>

using namespace std;

int board[301][301];
int dx[8] = {-1, -2, -2, -1, 1, 2, 2, 1};
int dy[8] = {-2, -1, 1, 2, 2, 1, -1, -2};

int bfs(int l, int sx, int sy, int ex, int ey) {
    queue<pair<int, int>> q;
    q.push({sx, sy});
    board[sx][sy] = 1;
    while(!q.empty()) {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();
        if(x == ex && y == ey)
            break;
        for(int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || nx >= l || ny < 0 || ny >= l)
                continue;
            if(board[nx][ny] > 0)
                continue;
            q.push({nx, ny});
            board[nx][ny] = board[x][y] + 1;
        }
    }
    int ans = board[ex][ey];
    for(int i = 0; i < l; i++) {
        for(int j = 0; j < l; j++) {
            board[i][j] = 0;
        }
    }
    return ans - 1;
}



int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    
    int t, l;
    int sx, sy, ex, ey;
    cin >> t;
    for(int i = 0; i < t; i++) {
        cin >> l;
        cin >> sx >> sy >> ex >> ey;
        cout << bfs(l, sx, sy, ex, ey) << "\n";
    }

    return 0;
}
