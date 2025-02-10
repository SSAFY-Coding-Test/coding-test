import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {
	private static int N;
	private static int[] expectedRanks;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		expectedRanks = new int[N];
		for (int i = 0; i < N; i++) {
			expectedRanks[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(expectedRanks);
		
		long minValue = findMinValue();
		System.out.println(minValue);
	}

	private static long findMinValue() {
		long diff = 0;
		
		for (int i = 0; i < N; i++) {
			int rank = i + 1;
			diff += Math.abs(expectedRanks[i] - rank);
		}
		return diff;
	}
}
