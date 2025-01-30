//Silver 1 <연산자 끼워넣기> - 백트래킹 -
#include <iostream>
#include <limits.h>
#include <algorithm>
using namespace std;

int n;
int num[11] = { 0 };
int op_num[4] = { 0 };
int op[11] = { 0 };
int r_max = INT_MIN, r_min = INT_MAX;

void backtracking(int k) {
	if (k == n - 1) {
		int tmp = num[0];

		for (int i = 0; i < n - 1; i++) {
			switch (op[i]) {
			case 0:
				tmp += num[i + 1];
				break;
			case 1:
				tmp -= num[i + 1];
				break;
			case 2:
				tmp *= num[i + 1];
				break;
			case 3:
				tmp /= num[i + 1];
				break;
			}
		}
		r_max = max(tmp, r_max);
		r_min = min(tmp, r_min);
		return;
	}
	else {
		for (int j = 0; j < 4; j++) {
			if (op_num[j] != 0) {
				op[k] = j;
				op_num[j]--;
				backtracking(k + 1);
				op_num[j]++;
			}
		}
	}

}

int main() {
	
	cin >> n;

	for (int i = 0; i < n; i++) {
		cin >> num[i];
	}

	for (int i = 0; i < 4; i++) {
		cin >> op_num[i];
	}

	backtracking(0);

	cout << r_max << "\n" << r_min << "\n";
}
