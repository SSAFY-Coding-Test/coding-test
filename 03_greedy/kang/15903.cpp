#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int main() {
	int n, m;
	int tmp;
	long long sum = 0;
	priority_queue <long long, vector<long long>, greater<long long> > num;

	cin >> n >> m;

	for (int i = 0; i < n; i++) {
		cin >> tmp;
		num.push(tmp);
	}

	for (int i = 0; i < m; i++) {
		sum = num.top();
		num.pop();
		sum += num.top();
		num.pop();
		num.push(sum);
		num.push(sum);
		sum = 0;
	}

	sum = 0;
	for (int i = 0; i < n; i++) {
		sum += num.top();
		num.pop();
	}

	cout << sum << endl;
}
