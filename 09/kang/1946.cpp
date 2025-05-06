#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
	int t, n;
	int ans = 0, temp_min;

	cin >> t;
	while (t > 0) {
		cin >> n;

		ans = 0;
		temp_min = n;
		vector <pair<int, int>> rank(n);

		for (int i = 0; i < n; i++) {
			cin >> rank[i].first >> rank[i].second;
		}

		sort(rank.begin(), rank.end());

		for (int i = 0; i < n; i++) {
			if (rank[i].second <= temp_min) {
				ans++;
				temp_min = rank[i].second;
			}
		}

		cout << ans << "\n";

		t--;
	}

}
