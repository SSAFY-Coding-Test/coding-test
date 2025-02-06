#include <iostream>
#include <algorithm>

using namespace std;

int n, m;
int map[510][510];
int max_sum = 0;

int calcTet(int x, int y) {
    int result = 0;

    if (y + 3 < m)
        result = max(result, map[x][y] + map[x][y + 1] + map[x][y + 2] + map[x][y + 3]);
    if (x + 3 < n)
        result = max(result, map[x][y] + map[x + 1][y] + map[x + 2][y] + map[x + 3][y]);

    if (x + 1 < n && y + 1 < m)
        result = max(result, map[x][y] + map[x][y + 1] + map[x + 1][y] + map[x + 1][y + 1]);

    if (x + 2 < n && y + 1 < m)
        result = max(result, map[x][y] + map[x + 1][y] + map[x + 2][y] + map[x][y + 1]);
    if (x + 2 < n && y - 1 >= 0)
        result = max(result, map[x][y] + map[x + 1][y] + map[x + 2][y] + map[x][y - 1]);
    if (x - 1 >= 0 && y + 2 < m)
        result = max(result, map[x][y] + map[x][y + 1] + map[x][y + 2] + map[x - 1][y]);
    if (x + 1 < n && y + 2 < m)
        result = max(result, map[x][y] + map[x][y + 1] + map[x][y + 2] + map[x + 1][y]);
    if (x - 2 >= 0 && y - 1 >= 0)
        result = max(result, map[x][y] + map[x - 1][y] + map[x - 2][y] + map[x][y - 1]);
    if (x - 2 >= 0 && y + 1 < m)
        result = max(result, map[x][y] + map[x - 1][y] + map[x - 2][y] + map[x][y + 1]);
    if (x + 1 < n && y - 2 >= 0)
        result = max(result, map[x][y] + map[x][y - 1] + map[x][y - 2] + map[x + 1][y]);
    if (x - 1 >= 0 && y - 2 >= 0)
        result = max(result, map[x][y] + map[x][y - 1] + map[x][y - 2] + map[x - 1][y]);

    if (x - 1 >= 0 && x + 1 < n && y - 1 >= 0)
        result = max(result, map[x][y] + map[x - 1][y] + map[x][y - 1] + map[x + 1][y - 1]);
    if (x - 1 >= 0 && x + 1 < n && y - 1 >= 0)
        result = max(result, map[x][y] + map[x + 1][y] + map[x][y - 1] + map[x - 1][y - 1]);
    if (x + 1 < n && y + 1 < m && y - 1 >= 0)
        result = max(result, map[x][y] + map[x + 1][y] + map[x][y + 1] + map[x + 1][y - 1]);
    if (x + 1 < n && y + 1 < m && y - 1 >= 0)
        result = max(result, map[x][y] + map[x + 1][y] + map[x][y - 1] + map[x + 1][y + 1]);

    if (x - 1 >= 0 && x + 1 < n && y + 1 < m)
        result = max(result, map[x][y] + map[x + 1][y] + map[x - 1][y] + map[x][y + 1]);
    if (x - 1 >= 0 && x + 1 < n && y - 1 >= 0)
        result = max(result, map[x][y] + map[x + 1][y] + map[x - 1][y] + map[x][y - 1]);
    if (y - 1 >= 0 && y + 1 < m && x - 1 >= 0)
        result = max(result, map[x][y] + map[x - 1][y] + map[x][y + 1] + map[x][y - 1]);
    if (y - 1 >= 0 && y + 1 < m && x + 1 < n)
        result = max(result, map[x][y] + map[x + 1][y] + map[x][y + 1] + map[x][y - 1]);

    return result;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> map[i][j];
        }
    }

    // 도형별로 함수를 분리작성하는 것 보다
    // 한 위치에서 가능한 모든 도형을 계산하는 것이 더 효율적
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            max_sum = max(max_sum, calcTet(i, j));
        }
    }

    cout << max_sum;

    return 0;
}