#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>

using namespace std;

typedef long long ll;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int n;
	cin >> n;

	ll start, end;
	vector<pair<ll, ll>> lecture;
	for (int i = 0; i < n; i++) {
		cin >> start >> end;
		lecture.push_back({ start,end });
	}

	// 시작 시간 기준으로 강의 정렬
	sort(lecture.begin(), lecture.end());

	priority_queue<ll, vector<ll>, greater<ll>> q;	// 가장 빨리 끝나는 시간을 오름차순으로 관리
	q.push(lecture[0].second);

	for (int i = 1; i < n; i++) {
		if (!q.empty() && lecture[i].first >= q.top()) {	// 기존 강의실 재사용 가능한 경우
			q.pop();	// 기존 강의실 제거
		}

		q.push(lecture[i].second);	// 새 강의를 강의실에 배정(직전에 pop이 일어난 경우는, 해당 강의실에 이어서 배정된다고 보면 됨)
	}

	cout << q.size();
	
	return 0;
}