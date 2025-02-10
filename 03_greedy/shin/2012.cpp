#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n;
vector<int> input;
long long min_unhappy;	// int로 선언하면 overflow 발생함
// why? 차이의 최대는 500000이므로, 최악의 경우 전부 최대 차이라면 약 2500억이 됨

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n; 
	int tmp;
	for (int i = 1; i <= n; i++) {
		cin >> tmp;
		input.push_back(tmp);
	}

	sort(input.begin(), input.end());
	
	//	백트래킹으로 풀면, 최악의 경우 O(N!) 이므로 시간 초과 발생
	// 정렬한 뒤 정직한 오름차순 등수와의 차이를 계산하면, 불만도의 합 최소가 됨.
	for (int i = 0; i < n; i++) {
		min_unhappy += abs(input[i] - (i+1));
	}
	cout << min_unhappy;

	return 0;
}