import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N, M;
	private static int maxSum = Integer.MIN_VALUE;
	private static int[][] data;
	private static boolean[][] visited;
	private static int[][] directions = {
			{-1, 0}, {1, 0},
			{0, 1}, {0, -1}
	};
	private static int[][][] shapes = {
		{{0, 0}, {0, 1}, {0, 2}, {1, 1}},  // ㅜ
		{{1, 0}, {0, 1}, {1, 1}, {2, 1}},
		{{0, 1}, {1, 0}, {1, 1}, {1, 2}},
		{{0, 0}, {1, 0}, {2, 0}, {1, 1}}
	};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		data = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				data[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				findMaxSum(i, j, data[i][j], 1);
				visited[i][j] = false;
				findMaxSum2(i, j, 0);
			}
		}
		System.out.println(maxSum);
	}
	
	private static void findMaxSum(int currentX, int currentY, int sum, int depth) {
		if (depth == 4) {
			maxSum = Math.max(sum, maxSum);
			return;
		}
		
		for (int k = 0; k < 4; k++) {
			int nx = currentX + directions[k][0];
			int ny = currentY + directions[k][1];
			
			if (isValid(nx, ny) && !visited[nx][ny]) {
				visited[nx][ny] = true;
				findMaxSum(nx, ny, sum + data[nx][ny], depth + 1);
				visited[nx][ny] = false;
			}
		}
	}
	
	private static void findMaxSum2(int currentX, int currentY, int sum) {
		for (int[][] shape : shapes) {
			int currentSum = sum;
			boolean isValidShape = true;
			for (int k = 0; k < 4; k++) {
				int nx = currentX + shape[k][0];
				int ny = currentY + shape[k][1];
				
				if (isValid(nx, ny)) {
					currentSum += data[nx][ny];
				} else {
					isValidShape = false;
					break;
				}
			}
			if (isValidShape) {
				maxSum = Math.max(currentSum, maxSum);
            }	
		}
	}
	
	private static boolean isValid(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
}
