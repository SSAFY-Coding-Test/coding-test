import java.io.*;
import java.util.*;

public class BJ1647 {

	static class Edge{
		int v1, v2, w;

		Edge(int v1, int v2, int w){
			this.v1 = v1;
			this.v2 =v2;
			this.w = w;
		}

//		public int compareTo(Edge e) {
//		}
	}
	static int n,m;
	static int[] parent;
	static ArrayList<Edge> edges;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		edges = new ArrayList<>();
		for(int i=0 ; i<m ; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			edges.add(new Edge(v1, v2, w));
		}

		edges.sort((a,b)->a.w - b.w);

		parent = new int[n+1];
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}

		long total = 0;
		int maxW = 0;
		for(int i=0 ; i<edges.size(); i++) {
			Edge edge = edges.get(i);

			// 사이클이 아닌경우
			if(find_root(edge.v1) != find_root(edge.v2)){
				total += edge.w;
				union(edge.v1, edge.v2);
				maxW = edge.w;
			}
		}

		System.out.println(total - maxW);
	}

	static int find_root(int x) {
		if(parent[x] == x)	return x;
		return parent[x] = find_root(parent[x]);
	}

	static void union(int x, int y) {
		int root_x = find_root(x);
		int root_y = find_root(y);

		if(root_x != root_y) {
			parent[root_y] = root_x;	// 루트끼리의 병합
		}
	}
}
