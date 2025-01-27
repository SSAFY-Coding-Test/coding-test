#include<iostream>
#include<queue>
#include<algorithm>

using namespace std;

int n;
int A[101];
int oper[4];    // 각 연산자의 개수 (+, -, *, /) 카운팅
int arr[12];    // 연산자 순서 저장

long long max_t = -1000000001;  // 연산값은 -10억~10억 범위
long long min_t = 1000000001;

void dfs(int cnt) {
    if (cnt == n - 1) {
        long long total = A[0];
        // 선택된 연산자 순서에 따라 계산 수행
        for (int i = 0; i < n - 1; i++) {
            switch (arr[i]) {
                case 0:
                    total += A[i + 1];
                    break;
                case 1:
                    total -= A[i + 1];
                    break;
                case 2:
                    total *= A[i + 1];
                    break;
                case 3:
                    total /= A[i + 1];
                    break;
            }
        }
        if (total > max_t) {
            max_t = total;
        }
        if (total < min_t) {
            min_t = total;
        }

        return;
    }
    for (int i = 0; i < 4; i++) {
        if (oper[i] <= 0) { // 연산자를 다썼으면 건너뜀
            continue;
        }
        arr[cnt] = i;   // 연산자 기록
        oper[i]--;  // 연산자 사용횟수 감소
        dfs(cnt + 1);
        oper[i]++;  // 연산자 횟수 복원
        arr[cnt] = 0;   // 기록된 연산자 초기화
    }
}

int main()
{
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> A[i];
    }
    for (int i = 0; i < 4; i++) {
        cin >> oper[i];
    }

    dfs(0);

    cout << max_t << "\n" << min_t;

    return 0;
}