import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N, M;
	private static int maxSum = Integer.MIN_VALUE;
	private static int[][] arr;
	private static int[][][] tetrominoes = {
			{{0, 0}, {0, 1}, {0, 2}, {0, 3}}, // ㅣ
			{{0, 0}, {1, 0}, {2, 0}, {3, 0}},
			{{0, 0}, {0, 1}, {1, 0}, {1, 1}}, // ㅁ
			{{0, 0}, {1, 0}, {2, 0}, {2, 1}}, // ㄴ
			{{2, 0}, {0, 1}, {1, 1}, {2, 1}},
			{{0, 0}, {0, 1}, {0, 2}, {1, 0}},
			{{0, 0}, {1, 0}, {1, 1}, {1, 2}},
			{{0, 0}, {0, 1}, {1, 1}, {2, 1}},
			{{0, 0}, {1, 0}, {2, 0}, {0, 1}},
			{{1, 0}, {1, 1}, {1, 2}, {0, 2}},
			{{0, 0}, {0, 1}, {0, 2}, {1, 2}},
			{{0, 0}, {1, 0}, {1, 1}, {2, 1}}, //ㄴㄱ
			{{0, 1}, {1, 1}, {1, 0}, {2, 0}},
			{{1, 0}, {1, 1}, {0, 1}, {0, 2}},
			{{0, 0}, {0, 1}, {1, 1}, {1, 2}},
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
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		findMaxSum();
		System.out.println(maxSum);
	}
	
	private static void findMaxSum() {
		for (int[][] tetromino : tetrominoes) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					int sum = 0;
					for (int k = 0; k < 4; k++) {
						int x = tetromino[k][0] + i;
						int y = tetromino[k][1] + j;
						
						if (isValid(x, y)) {
							sum += arr[x][y];
						}
						else {
							sum = 0;
							break;
						}
					}
					maxSum = Math.max(maxSum, sum);
				}
			}
		}
	}
	
	private static boolean isValid(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
}
