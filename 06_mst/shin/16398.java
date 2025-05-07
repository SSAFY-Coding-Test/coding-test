import java.io.*;
import java.util.*;

public class BJ16398 {
	static int n;
	static int[][] map;
	static StringTokenizer st;
	static int[] parent;

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

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		map = new int[n+1][n+1];
		for(int i=0 ; i<n ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0 ; j<n ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		parent = new int[n+1];
		for(int i=1 ; i<=n ; i++) {
			parent[i] = i;
		}

		// 간선리스트 추출
		List<Edge> edges = new ArrayList<>();
		for(int i=0 ; i<n ; i++) {
			for(int j=i+1 ; j<n ; j++) {
				if(map[i][j] != 0)
					edges.add(new Edge(i,j,map[i][j]));
			}
		}

		// 오름차순 정렬
		edges.sort((a,b)-> a.w - b.w);

		List<Edge> tree = new ArrayList<>();

		for(int i=0 ; i<edges.size(); i++) {
			int v1 = edges.get(i).v1;
			int v2 = edges.get(i).v2;
			int w = edges.get(i).w;

			if(find_root(v1) == find_root(v2))
				continue;

			tree.add(new Edge(v1,v2,w));
			union(v1,v2);

			if(tree.size() == n-1)
				break;
		}

		long total = 0;
		for(Edge e : tree) {
			total += e.w;
		}
		System.out.println(total);

	}

	static int find_root(int v) {
		if(parent[v] == v)
			return v;
		return parent[v] = find_root(parent[v]);
	}

	static void union(int v1, int v2) {
	    int root1 = find_root(v1);
	    int root2 = find_root(v2);
	    // 루트끼리 병합
	    if (root1 != root2)
	        parent[root2] = root1;
	}
}