import java.io.*;
import java.util.*;


public class Main {
	static int N;
	static int[][] map;
	static int result = Integer.MAX_VALUE;
	
	static void calcStamina(boolean[] start) {
		int startR = 0;
		int linkR = 0;
		
		for(int i = 0; i < N-1; i++) {
			for (int j = i+1; j < N; j++) {
				if (start[i] && start[j]) 
					startR += (map[i][j] + map[j][i]);
				else if (!(start[i] || start[j]))
					linkR += (map[i][j] + map[j][i]);
			}
		}
		result = Math.min(Math.abs(startR - linkR), result);
	}
	
	static void backTracking(boolean[] nums, int number, int boolN) {
		// 모든 수의 조합이 모이면 점수 계산 
		if (boolN == N/2) {
			calcStamina(nums);
			return;
		}
		
		for(int i = number; i< N; i++) {
			nums[i] = true;
			boolN += 1;
			backTracking(nums, i+1, boolN);
			boolN -= 1;
			nums[i] = false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		backTracking(new boolean[N], 0, 0);
		System.out.println(result);
	}
}

