#include <iostream>
#include <vector>

using namespace std;
int t;

long long calc_maxProfit(vector<int> cost, int n) {
	int max_cost = cost[n - 1];
	long long max_profit = 0;
	for (int i = n - 2; i >= 0; i--) {
		if (max_cost > cost[i]) {
			max_profit += max_cost - cost[i];
		}
		else {
			max_cost = cost[i];
		}
	}
	return max_profit;
}


int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	
	cin >> t;

	for (int i = 0; i < t; i++) {
		int n;
		cin >> n;
		//int cost[1000001]; // 스택오버플로우
		vector<int> cost(n);
		for (int j = 0; j < n; j++) {
			cin >> cost[j];
		}
		long long result = calc_maxProfit(cost, n);
		cout << result << "\n";
	}

	return 0;
}