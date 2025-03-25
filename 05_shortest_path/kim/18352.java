import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 도시 개수
        int M = Integer.parseInt(st.nextToken()); // 도로 개수
        int K = Integer.parseInt(st.nextToken()); // 거리 정보
        int X = Integer.parseInt(st.nextToken()); // 출발 도시

        Map<Integer, List<Integer>> roads = new HashMap<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            List<Integer> buf = roads.getOrDefault(s, new ArrayList<>());
            buf.add(e);
            roads.put(s, buf);
        }

        int[] minDist = new int[N+1];
        boolean[] visited = new boolean[N+1];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(X);
        visited[X] = true;
        while(!queue.isEmpty()) {
            int now = queue.poll();
            for(int next : roads.getOrDefault(now, new ArrayList<>())) {
                if (!visited[next]) {
                    minDist[next] = minDist[now] + 1;
                    queue.add(next);
                    visited[next] = true;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        boolean isAns = false;
        for(int i = 1; i <= N; i++) {
            if(minDist[i] == K) {
                sb.append(i).append("\n");
                isAns = true;
            }
        }

        System.out.println(isAns ? sb : -1);
    }
}