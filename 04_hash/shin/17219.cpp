#include <iostream>
#include <unordered_map>
using namespace std;

int main() {
    cin.tie(NULL);
    ios::sync_with_stdio(false);

    int N, M;
    unordered_map<string, string> m;        // map(파이썬의 딕셔너리와 비슷) 자료구조 활용
    string name, pw;

    cin >> N >> M;

    for (int i = 0; i < N; i++) {
        cin >> name >> pw;
        m[name] = pw;
    }

    for (int i = 0; i < M; i++) {
        cin >> name;
        cout << m[name] << '\n';
    }

    return 0;
}