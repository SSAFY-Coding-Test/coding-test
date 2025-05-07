#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int V, E;
int p[100003];
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
    int cost, a, b;
    long long totalCost = 0;

    cin >> V >> E;

    for(int i = 0; i < E; i++) {
            cin >> a >> b >> cost;
            edges.push_back({cost, a, b});
    }
    sort(edges.begin(), edges.end(), compare);

    for(int i = 1; i <= V; i++) {
        p[i] = i;
    }

    int edgeSize = edges.size();
    int maxCost = 0;
    for(int i = 0; i < edgeSize; i++) {
        a = edges[i].a;
        b = edges[i].b;
        if(findParent(a) != findParent(b)) {
            unionParent(a, b);
            totalCost += edges[i].cost;
            maxCost = edges[i].cost;
        }
    }

    cout << totalCost - maxCost << "\n";
    return 0;
}
