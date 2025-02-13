#include <iostream>
#include <deque>
#include <algorithm>

// 원래 오름차순 큐(우선순위 큐)를 사용하려다, 맨앞과 맨뒤 접근이 번거로워서 deque로 변경
// 어차피 정렬을 한 번 해두면 순서가 바뀔 일이 없기 때문에, 우선순위 큐 사용할 이유가 없어짐
using namespace std;
int n;
int result;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n;
    deque<int> q;
    int tmp;
    for (int i = 0; i < n; i++) {
        cin >> tmp;
        q.push_back(tmp);
    }
    sort(q.begin(), q.end());

    if (n < 3) { // 체인이 3개 미만이면 바로 연결
        result = 1;
    }
    else {
        while (q.size() > 2) {
            q.front() -= 1;
            if (q.front() < 0) {    // 다 쓴 체인일 경우, 그 다음 체인 사용
                q.pop_front();
                q.front() -= 1;
            }
            int back1 = q.back();
            q.pop_back();
            int back2 = q.back();
            q.pop_back();
            q.push_back(back1 + back2);
            result++;
        }
        if (q.front() != 0) {   // 맨 앞 체인을 다 사용하지 못한경우, 2번째 체인과 추가연결
            result++;
        }
    }

    cout << result;

    return 0;
}