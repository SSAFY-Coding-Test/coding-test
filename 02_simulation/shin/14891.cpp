#include <iostream>
#include <string>
#include <deque>

using namespace std;

deque<int> gear[4];

void rotateGear(int num, int dir) {
	if (dir == 1) { // 시계 방향 회전
		int tmp = gear[num].back();
		gear[num].pop_back();
		gear[num].push_front(tmp);
	}
	else if (dir == -1) { // 반시계 방향 회전
		int tmp = gear[num].front();
		gear[num].pop_front();
		gear[num].push_back(tmp);
	}
}

// 회전할 톱니바퀴들을 미리 결정하는 함수
void decideRotation(int num, int dir, int dirs[4]) {
	dirs[num] = dir; // 현재 톱니바퀴의 회전 방향

	// 왼쪽 방향 탐색
	for (int i = num; i > 0; i--) {
		if (gear[i][6] != gear[i - 1][2]) { // 극성이 다르면 반대 방향 회전
			dirs[i - 1] = -dirs[i];
		}
		else {
			break;	// 연쇄 회전 방지
		}
	}

	// 오른쪽 방향 탐색
	for (int i = num; i < 3; i++) {
		if (gear[i][2] != gear[i + 1][6]) {
			dirs[i + 1] = -dirs[i];
		}
		else {
			break;
		}
	}
}

int main() {
	string s;
	for (int i = 0; i < 4; i++) {
		cin >> s;
		for (int j = 0; j < 8; j++) {
			gear[i].push_back(s[j] - '0');
		}
	}

	int k;
	cin >> k;

	while (k--) {
		int num, dir;
		cin >> num >> dir;
		num--; // 0 based index로 변환

		int dirs[4] = { 0, 0, 0, 0 }; // 각 톱니바퀴의 회전 방향 저장
		decideRotation(num, dir, dirs); // 회전 방향 결정

		for (int i = 0; i < 4; i++) {
			if (dirs[i] != 0) {
				rotateGear(i, dirs[i]);
			}
		}
	}

	int result = 0;
	if (gear[0][0] == 1) result += 1;
	if (gear[1][0] == 1) result += 2;
	if (gear[2][0] == 1) result += 4;
	if (gear[3][0] == 1) result += 8;

	cout << result;

	return 0;
}