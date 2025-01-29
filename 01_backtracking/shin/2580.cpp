#include <iostream>
#include <vector>

using namespace std;

int map[9][9];
vector<pair<int, int>> blank;
bool isEnd;

void print() {
    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
            cout << map[i][j] << " ";
        }
        cout << "\n";
    }
}

// true를 반환하면, 현재 위치(x,y)에 쓸 수 있는 수임
bool canWrite(int x, int y, int num) {
    // 조건 1.가로세로 중복숫자 X
    for (int i = 0; i < 9; i++) {
        if (map[i][y] == num)
            return false;
        if (map[x][i] == num)
            return false;
    }
    // 조건 2. 3x3 칸 내에 이미 존재하는지 탐색
    int startX = x / 3;
    int startY = y / 3;
    startX *= 3;
    startY *= 3;
    for (int i = startX; i < startX + 3; i++) {
        for (int j = startY; j < startY + 3; j++) {
            if (map[i][j] == num)
                return false;
        }
    }
    return true;
}

void dfs(int cnt) {
    if (isEnd)
        return;
    if (cnt == blank.size()) {
        print();
        isEnd = true;
        return;
    }

    int x = blank[cnt].first;
    int y = blank[cnt].second;
    for (int i = 1; i <= 9; i++) {
        if (canWrite(x, y, i)) {
            map[x][y] = i;
            dfs(cnt + 1);
            map[x][y] = 0;  // 백트래킹 -> 다른 수 기입을 위해 초기화시켜두기
        }
    }
}


int main()
{
    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
            cin >> map[i][j];
            if (map[i][j] == 0) {
                blank.push_back({ i,j });
            }
        }
    }

    dfs(0);

    return 0;
}