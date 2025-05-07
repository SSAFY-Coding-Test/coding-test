#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N;
int p[1003];
struct Edge{
    int cost, a, b;
};
vector<Edge> edges;
vector<int> addEdges;

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
    int addCnt = 0, totalCost = 0;

    cin >> N;

    for(int i = 1; i <= N; i++) {
        p[i] = i;
    }

    for(int i = 1; i <= N; i++) {
        for(int j = 1; j <= N; j++) {
            cin >> cost;
            if(i >= j)
                continue;
            if(cost < 0) { //이미 설치된 철도
                unionParent(i, j);
                totalCost += -cost;
                continue;
            }
            edges.push_back({cost, i, j});
        }
    }
    sort(edges.begin(), edges.end(), compare);

    int edgeSize = edges.size();
    for(int i = 0; i < edgeSize; i++) {
        a = edges[i].a;
        b = edges[i].b;
        if(findParent(a) != findParent(b)) {
            unionParent(a, b);
            totalCost += edges[i].cost;
            addEdges.push_back(i);
            addCnt++;
        }
    }

    cout << totalCost << " " << addCnt << "\n";
    for(int i = 0; i < addEdges.size(); i++) {
        cout << edges[addEdges[i]].a << " " << edges[addEdges[i]].b << "\n";
    }
    return 0;
}
