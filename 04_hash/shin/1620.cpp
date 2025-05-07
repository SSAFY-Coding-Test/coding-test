#include <iostream>
#include <vector>
#include <string>
#include <unordered_map>

using namespace std;

int N, M;
unordered_map<string, int> p;
vector<string> name;
vector<string> result;

int main(void)
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> N >> M;
	string tmp;
	for (int i = 0; i < N; i++) {
		cin >> tmp;
		name.push_back(tmp);
		p[tmp] = i + 1;
	}

	for (int i = 0; i < M; i++) {
		cin >> tmp;
		char check = tmp[0];

		if ('1' <= check && check <= '9') {
			int num = stoi(tmp);
			result.push_back(name[num - 1]);
		}
		else {
			result.push_back(to_string(p[tmp]));
		}
	}
	for (int i = 0; i < M; i++) {
		cout << result[i] << "\n";
	}

	return 0;
}