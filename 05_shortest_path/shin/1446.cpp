#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

int n, d;

struct ShortCut {
    int start;
    int end;
    int dist;
};

ShortCut s[12];

int main() {
    cin.tie(NULL);
    ios::sync_with_stdio(false);

    cin >> n >> d;
    int a, b, c;
    for (int i = 0; i < n; i++) {
        cin >> a >> b >> c;
        s[i] = { a,b,c };
    }

    vector<int> dist(d + 1, 10001);
    dist[0] = 0;    // 시작점 거리는 0

    for (int i = 0; i <= d; i++) {
        if (dist[i] == 10001)
            continue;

        // 다음 1km 지점으로 가는 경우
        if (i + 1 <= d) {
            dist[i + 1] = min(dist[i + 1], dist[i] + 1);
        }

        // 지름길 사용하는 경우
        for (int j = 0; j < n; j++) {
            if (s[j].start == i && s[j].end <= d) {
                dist[s[j].end] = min(dist[s[j].end], dist[i] + s[j].dist);
            }
        }
    }

    cout << dist[d] << endl;

    return 0;
}