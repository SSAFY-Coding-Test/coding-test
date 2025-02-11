#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
	int n, cnt = 0;
	vector<pair<int, int>> time;

	cin >> n;

	int s, e;
	for (int i = 0; i < n; i++) {
		cin >> s >> e;
		time.push_back({ e, s });
	}

	sort(time.begin(), time.end());

	for (int i = 0; i < n - 1; i++) {
		e = time[i].first;
		while (e > time[i + 1].second && i < n - 1) i++;
		if (e <= time[i + 1].second) {
			cnt++;
		}
	}

	cout << cnt + 1;

}
