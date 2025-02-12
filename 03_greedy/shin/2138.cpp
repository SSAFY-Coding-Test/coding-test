#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int n;
string s1, s2;
int result[2] = { 1,0 };
bool canMake[2];

void changeBulb(vector<int>& b, vector<int> goal, int ver) {
    for (int i = 1; i < n; i++) {
        if (b[i] != goal[i]) {    // 현재 전구가 목표와 다른경우, 바로 다음 전구를 바꿔준다.
            result[ver]++;
            b[i] = !b[i];
            b[i + 1] = !b[i + 1];
            if (i + 2 <= n) b[i + 2] = !b[i + 2];
        }
    }
}

bool checkBulb(vector<int> b, vector<int> goal) {
    for (int i = 1; i <= n; i++) {
        if (b[i] != goal[i]) return false;
    }
    return true;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n;

    cin >> s1 >> s2;

    // 초기화
    vector<int> b1(n + 1), b2(n + 1), goal(n + 1);    // 1 based index
    for (int i = 0; i < n; i++) {
        b1[i + 1] = s1[i] - '0';
        b2[i + 1] = s1[i] - '0';
        goal[i + 1] = s2[i] - '0';
    }

    // b1에서 goal로 만드는 것이 목표
    // 1. 1번 스위치를 눌렀다고 가정
    b1[1] = !b1[1];
    b1[2] = !b1[2];
    changeBulb(b1, goal, 0);

    // 2. 1번 스위치를 안눌렀다고 가정
    changeBulb(b2, goal, 1);


    canMake[0] = checkBulb(b1, goal);
    canMake[1] = checkBulb(b2, goal);

    if (canMake[0] && canMake[1]) {
        cout << min(result[0], result[1]);
    }
    else if (canMake[0]) {
        cout << result[0];
    }
    else if (canMake[1]) {
        cout << result[1];
    }
    else
        cout << -1;


    return 0;
}