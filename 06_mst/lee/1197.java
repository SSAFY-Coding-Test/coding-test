import java.io.*;
import java.util.*;

public class Main {
	static class Edge {
		int to;
		int weight;
		
		Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
	
	private static int V, E;
	private static List<Edge>[] edges;
	private static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		edges = new List[V + 1];
		for (int i = 1; i <= V; i++) {
			edges[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edges[a].add(new Edge(b, c));
			edges[b].add(new Edge(a, c)); 
		}
		
		System.out.println(prim(1));
	}

	
	private static int prim(int start) {
		int totalWeight = 0;
		visited = new boolean[V + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight)); 
		
        pq.add(new Edge(1, 0));
		
		while (!pq.isEmpty()) {
			Edge current = pq.poll();
            int node = current.to;
            int weight = current.weight;
            
            if (visited[node]) continue;
            
            visited[node] = true;
            totalWeight += weight;
			
			for (Edge neighbor : edges[node]) {
				if (!visited[neighbor.to]) {
					pq.add(neighbor);
				}
			}
		}
        return totalWeight;
	}
}
