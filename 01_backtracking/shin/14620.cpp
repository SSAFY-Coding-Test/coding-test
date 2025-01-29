#include<iostream>

using namespace std;

int n;
int map[11][11]; // 땅의 가격 정보
int price[3]; // 선택한 세 개의 씨앗 가격 저장
bool isPlanted[11][11]; // 씨앗이 심어졌는지 여부
int min_p = 99999;

// (x, y)에 꽃을 심을 때의 비용 계산
int calc_price(int x, int y) {
    int total = 0;
    total += map[x][y] + map[x - 1][y] + map[x + 1][y] + map[x][y - 1] + map[x][y + 1];
    return total;
}

// (x, y) 위치에 꽃을 심음(해당 위치 포함하여 십자 형태의 영역)
void do_plant(int x, int y) {
    isPlanted[x][y] = isPlanted[x - 1][y] = isPlanted[x + 1][y] = isPlanted[x][y - 1] = isPlanted[x][y + 1] = true;
}

// (x, y) 위치의 꽃을 제거
void un_plant(int x, int y) {
    isPlanted[x][y] = isPlanted[x - 1][y] = isPlanted[x + 1][y] = isPlanted[x][y - 1] = isPlanted[x][y + 1] = false;
}

// (x, y) 위치에 꽃을 심을 수 있는지 확인
bool can_plant(int x, int y) {
    // 지금까지 심은 씨앗의 위치로 겹치는 범위를 판별하면, 더 효율적일듯
    return !(isPlanted[x][y] || isPlanted[x - 1][y] || isPlanted[x + 1][y] || isPlanted[x][y - 1] || isPlanted[x][y + 1]);
}

void dfs(int cnt) {
    if (cnt == 3) { // 꽃 3개를 모두 심었을 경우
        int total = 0;
        for (int i = 0; i < 3; i++) {
            total += price[i];
        }
        if (min_p > total) {    // 최소 비용 갱신
            min_p = total;
        }
        return;
    }
    // 가장자리는 꽃을 심을 수 없으므로 1부터 n-2까지 탐색
    for (int i = 1; i < n - 1; i++) {
        for (int j = 1; j < n - 1; j++) {
            bool land = can_plant(i, j);
            if (!land) {    // 심을 수 없는 위치라면 건너뜀
                continue;
            }
            int total = calc_price(i, j);
            price[cnt] = total;
            do_plant(i, j);
            dfs(cnt + 1);   // 다음 꽃 심기
            un_plant(i, j); // 백트래킹-> 꽃 제거

        }
    }
}

int main()
{
    cin >> n;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> map[i][j];
        }
    }

    dfs(0);

    cout << min_p;

    return 0;
}