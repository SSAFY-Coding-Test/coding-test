#include <iostream>
#include <queue>

using namespace std;

int n, w, L;
int t[1005];
queue<int> bridge;

int main()
{
    cin >> n >> w >> L;
    for(int i = 0; i < n; i++) {
        cin >> t[i];
    }
 
    int total = 0, ans = 0;
    for(int i = 0; i < n; i++) {
        while(true) {
            if(bridge.size() == w) { //다리가 꽉차면 앞 차 빼기
                total -= bridge.front();
                bridge.pop();
            }
            if(total + t[i] <= L) //차를 올릴 수 있다면
                break;
            bridge.push(0);
            ans++;
        }
        bridge.push(t[i]); //차를 올림
        total += t[i];
        ans++;
    }
    cout << ans + w; //맨 마지막 차 나가는 시간(다리 길이) 더함
    return 0;
}
