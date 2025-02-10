#include <iostream>
#include <queue>

using namespace std;

int n, w, l;
int truck[1001];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n >> w >> l;
    for (int i = 0; i < n; i++) {
        cin >> truck[i];
    }

    int time = 0;          // 경과 시간
    int bridge_weight = 0; // 현재 다리 위의 총 무게
    queue<int> onBridge;   // 다리 위의 트럭 상태 (현재 트럭 무게 저장)

    for (int i = 0; i < w; i++) {
        onBridge.push(0);  // 트럭이 없음을 0으로 표현
    }

    int idx = 0;  // 다음에 다리에 올라갈 트럭 인덱스
    while (idx < n || bridge_weight > 0) {
        time++;

        bridge_weight -= onBridge.front();
        onBridge.pop();

        // 새로운 트럭 추가 가능한 경우
        if (idx < n && bridge_weight + truck[idx] <= l) {
            onBridge.push(truck[idx]);
            bridge_weight += truck[idx];
            idx++;
        }
        else {
            onBridge.push(0); // 트럭이 못 올라가면, 빈 공간 유지
        }
    }

    cout << time;

    return 0;
}