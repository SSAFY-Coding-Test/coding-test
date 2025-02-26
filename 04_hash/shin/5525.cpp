#include <iostream>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;
int n, m, result;
string s;

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> m >> s;
	// Pn = n+1개의 I와 n개의 O

	for (int i = 0; i < m; i++) {
		int cnt = 0;	// 처음 I 뒤의 'OI' 개수
		if (s[i] == 'O') continue;

		while (s[i + 1] == 'O' && s[i + 2] == 'I') {
			cnt++;
			if (cnt == n) {
				result++;
				cnt--;	// 현재 Pn의 맨앞에있던 'IOI' 제외시켜줌으로써, 다음 OI 등장 시 계산을 편리하게함
			}
			i += 2;
		}

	}
	cout << result;

	return 0;
}