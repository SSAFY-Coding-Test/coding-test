import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int N, M;
	private static long[] cards;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cards = new long[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		
		playCards();
		printMinimumSum();
	}

	private static void playCards() {
		for (int i = 0; i < M; i++) {
			Arrays.sort(cards);
			
			long first = cards[0];
			long second = cards[1];
			long sum = first + second;
			
			cards[0] = sum;
			cards[1] = sum;
		}
	}
	
	private static void printMinimumSum() {
		long sum = 0;
		
		for (int i = 0; i < N; i++) {
			sum += cards[i];
		}
		System.out.println(sum);
	}
}
