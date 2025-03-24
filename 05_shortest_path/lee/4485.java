import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
	public int x;
	public int y;
	public int loss;

	public Node (int x, int y, int loss) {
		this.x = x;
		this.y = y;
		this.loss = loss;
	}

	public int compareTo(Node n) {
		return this.loss - n.loss;
	}
}

public class Main {
    private static final int INF = Integer.MAX_VALUE;
    private static int N;
    private static int[][] cave;
    private static int[][] minLoss;
    private static int[][] directions = {
        {-1, 0}, {1, 0}, {0, 1}, {0, -1}
    };

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int count = 1;
        N = Integer.parseInt(br.readLine());
        while (N != 0) {
            cave = new int[N][N];
            minLoss = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    cave[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N; i++) {
                Arrays.fill(minLoss[i], INF);
            }
            dijkstra(0, 0);
            sb.append("Problem ").append(count).append(": ").append(minLoss[N - 1][N - 1]).append("\n");
            count++;
            N = Integer.parseInt(br.readLine());
        }
        System.out.println(sb.toString());
    }

    private static void dijkstra(int startX, int startY) {
        PriorityQueue<Node> queue = new PriorityQueue<>();

        queue.add(new Node(startX, startY, cave[startX][startY]));
        minLoss[startX][startY] = cave[startX][startY];

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            // 현재 노드가 이미 더 작은 손실을 가지고 있다면 건너뛰기
            if (minLoss[current.x][current.y] < current.loss) continue;

            for (int i = 0; i < 4; i++) {
                int nx = current.x + directions[i][0];
                int ny = current.y + directions[i][1];

                if (!isValid(nx, ny)) continue;

                int newLoss = current.loss + cave[nx][ny];
                if (minLoss[nx][ny] > newLoss) {
                    minLoss[nx][ny] = newLoss;
                    queue.add(new Node(nx, ny, minLoss[nx][ny]));
                }
            }
        }
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
