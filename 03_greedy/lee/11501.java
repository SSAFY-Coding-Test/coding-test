import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int T, N;
	private static int[] stocks;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			stocks = new int[N];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				stocks[i] = Integer.parseInt(st.nextToken());
			}

			System.out.println(findMaxProfit());
		}
	}


	private static long findMaxProfit() {
		long profit = 0;
		int max = stocks[N - 1];  // 마지막 값을 최댓값으로 설정

        // 역방향으로 탐색
		for (int i = N - 2; i >= 0; i--) {
			if (stocks[i] > max) {
				max = stocks[i];
			} else {
				profit += max - stocks[i];
			}
		}
		return profit;
	}
}
