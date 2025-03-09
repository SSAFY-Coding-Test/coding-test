#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int V, E;
int p[10003];
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
    int A, B, C;
    int sum = 0;

    cin >> V >> E;

    for(int i = 0; i < E; i++) {
        cin >> A >> B >> C;
        edges.push_back({C, A, B});
    }
    sort(edges.begin(), edges.end(), compare);

    for(int i = 1; i <= V; i++) {
        p[i] = i;
    }

    for(int i = 0; i < E; i++) {
        A = edges[i].a;
        B = edges[i].b;
        if(findParent(A) != findParent(B)) {
            unionParent(A, B);
            sum += edges[i].cost;
        }
    }

    cout << sum << "\n";
    return 0;
}
