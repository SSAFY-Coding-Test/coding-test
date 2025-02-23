#include <iostream>
#include <string>
#include <map>
using namespace std;

string str[100001];

int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);

	int n, m, id;
	map <string, int> dict;
	string question;

	cin >> n >> m;

	for (int i = 0; i < n; i++) {
		cin >> str[i + 1];
		dict[str[i+1]] = i + 1;
	}
	
	for (int i = 0; i < m; i++) {
		cin >> question;
		if (question[0] >= 'A' && question[0] <= 'Z') {
			cout << dict[question] << '\n';
		}
		else {
			id = 0;
			for (int j = 0; j < question.size(); j++) {
				id = id * 10 + (question[j] - '0');
			}
			cout << str[id] << '\n';
		}
	}

}
