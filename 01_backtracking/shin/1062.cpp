#include<iostream>
#include<string>

using namespace std;

int n, k;
string input[51];   // 문자열 input
char arr[51];   // 학습하는 문자 기록
bool learned[26]; // 알파벳 배웠는지 여부
int max_r = 0;

void dfs(int idx, int cnt) {
    if (cnt == k) {
        // 읽을 수 있는 단어 세기
        int result = 0;
        for (int i = 0; i < n; i++) {
            bool isRead = true; // 읽을 수 있는 단어인지 여부
            //  anta, tica를 빼고 가운데 글자만 체크
            for (int j = 4; j < input[i].size() - 4; j++) {
                char ch = learned[input[i][j] - 'a'];   // i번째로 입력받은 단어의 j번째 글짜가 학습되었는지 여부
                if (!ch) {
                    isRead = false;
                    break;
                }
            }
            if (isRead) {
                result++;
            }
        }
        max_r = max(result, max_r);

        return;
    }
    // 알파벳순으로 최대 26번 탐색
    for (int i = idx; i < 26; i++) {
        if (learned[i]) {
            continue;
        }
        learned[i] = true;
        dfs(i + 1, cnt + 1);
        learned[i] = false;
    }

}

int main()
{
    cin >> n >> k;
    for (int i = 0; i < n; i++) {
        cin >> input[i];
    }

    // a c i n t는 디폴트로 학습
    learned['a' - 'a'] = learned['n' - 'a'] = learned['t' - 'a'] = learned['i' - 'a'] = learned['c' - 'a'] = true;
    arr[0] = 'a';   arr[1] = 'n';   arr[2] = 't';   arr[3] = 'i';   arr[4] = 'c';

    if (k < 5) {
        cout << 0;
        return 0;
    }

    dfs(0,5);

    cout << max_r;

    return 0;
}