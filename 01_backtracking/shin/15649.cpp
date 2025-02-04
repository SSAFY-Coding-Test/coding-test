#include <iostream>

using namespace std;

int n, m;                 // n: 숫자의 최대값, m: 출력할 자리수
int arr[9];               // 숫자를 저장할 배열 (최대 8자리까지 가능)
bool visited[9];          // visited[i]: 숫자 i가 이미 사용되었는지 여부

void dfs(int cnt) {
    // 현재 cnt는 자리수를 의미함 (0부터 시작)

    // m개의 숫자를 다 채웠을 경우 (기저 조건)
    if (cnt == m) {
        // 배열에 저장된 숫자를 출력
        for (int i = 0; i < m; i++) {
            cout << arr[i] << " ";
        }
        cout << "\n";
        return;
    }

    // 숫자 1부터 n까지 반복
    for (int i = 1; i <= n; i++) {
        if (visited[i]) continue;  // 이미 사용한 숫자는 건너뜀

        visited[i] = true;         // 숫자 i를 사용 표시
        arr[cnt] = i;              // cnt번째 자리에 숫자 i를 저장
        dfs(cnt + 1);              // 다음 자리수를 채우기 위해 재귀 호출
        visited[i] = false;        // 재귀 호출이 끝나면 숫자 i를 사용 해제
    }
}

int main()
{
    cin >> n >> m;
    dfs(0);             // DFS 시작 (0번째 자리부터 채우기)
    return 0;
}
