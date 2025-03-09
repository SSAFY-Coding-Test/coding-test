#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int V, E;
int p[1003];
struct Edge{
    int cost, a, b;
};
vector<Edge> edges;

bool compare(const Edge& a, const Edge& b) {
    if(a.cost == b.cost)
        return a.a < b.a;
    return a.cost < b.cost;
}

int findParent(int x) {
    if(p[x] == x)
        return x;
    return p[x] = findParent(p[x]);
}

void unionParent(int a, int b) {
    a = findParent(a);
    b = findParent(b);
    if(a < b) 
        p[b] = a;
    else
        p[a] = b;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    int N, cost, a, b;
    long long sum = 0;

    cin >> N;

    for(int i = 0; i < N; i++) {
        for(int j = 0; j < N; j++) {
            cin >> cost;
            if(i >= j)
                continue;
            edges.push_back({cost, i, j});
        }
    }
    sort(edges.begin(), edges.end(), compare);

    for(int i = 0; i < N; i++) {
        p[i] = i;
    }

    int edgeSize = edges.size();
    int a, b;
    for(int i = 0; i < edgeSize; i++) {
        a = edges[i].a;
        b = edges[i].b;
        if(findParent(a) != findParent(b)) {
            unionParent(a, b);
            sum += edges[i].cost;
        }
    }

    cout << sum << "\n";
    return 0;
}
