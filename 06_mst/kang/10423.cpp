#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N, M, K;
int p[1003];
bool isPower[1003];
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
    int point, u, v, w;
    int totalCost = 0;

    cin >> N >> M >> K;
    for(int i = 0; i < K; i++) {
        cin >> point;
        isPower[point] = true;
    }
    for(int i = 0; i < M; i++) {
            cin >> u >> v >> w;
            edges.push_back({w, u, v});
    }
    sort(edges.begin(), edges.end(), compare);

    for(int i = 1; i <= N; i++) {
        if(isPower[i]) 
            p[i] = -1;
        else
            p[i] = i;
    }

    int edgeSize = edges.size();
    for(int i = 0; i < edgeSize; i++) {
        u = edges[i].a;
        v = edges[i].b;
        if(findParent(u) != findParent(v)) {
            unionParent(u, v);
            totalCost += edges[i].cost;
        }
    }

    cout << totalCost << "\n";
    return 0;
}
