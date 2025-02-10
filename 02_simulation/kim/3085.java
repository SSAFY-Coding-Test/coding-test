import java.io.*;
import java.util.*;


public class Main {
	static int N;
	static char[][] candy;
	static boolean[][] candyVisited;

	static int[][] swapCandy = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	static int maxCandy;

	static void swap(int i, int j, int ii, int jj) {
		char buf = candy[i][j];
		candy[i][j] = candy[ii][jj];
		candy[ii][jj] = buf;
	}
	
	static void calcMaxCandy(int i, int j) {
		int colmax = 0, rowmax = 0;
		char nowCandy = candy[i][j];

		for(int n = 0; n < N; n++) {
			if (nowCandy == candy[n][j]) colmax++;
			else {maxCandy = Math.max(colmax, maxCandy); colmax = 0;}
			if (nowCandy == candy[i][n]) rowmax++;
			else {maxCandy = Math.max(rowmax, maxCandy); rowmax = 0;}
		}
		maxCandy = Math.max(colmax, maxCandy);
		maxCandy = Math.max(rowmax, maxCandy);
	}
	
	static void backtracking(int i, int j, int idx) {
		// 중단조건 : i, j 범위
		if (i < 0 || j < 0 || i >= N || j >=N) return;
		
		// i,j를 기준으로 하는 행or열의 사탕 연속 개수 계산 
		calcMaxCandy(i, j);
	
		for(int[] move : swapCandy) {
			// 범위 체크
			if  (0 <= i + move[0] && i + move[0] < N && 0 <= j+move[1] && j+move[1] < N) {
				// 다음 위치의 캔디 색이 지금의 캔디 색과 다를 때 
				if (candy[i+move[0]][j+move[1]] != candy[i][j]) {
					swap(i, j, i + move[0], j+move[1]);
					calcMaxCandy(i +  move[0], j +  move[1]);
					swap(i + move[0], j+move[1], i, j);
				}
			}
		}
		
		int next_i = (j == N-1) ? i+1 : i;
		int next_j = (j == N-1) ? 0 : j + 1;
		backtracking(next_i, next_j, idx);
	}
			
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        candy = new char[N][N];
        candyVisited = new boolean[N][N];
        
        for(int n = 0; n < N; n++) {
        	String line = br.readLine();
        	int idx = 0;
        	for(char c : line.toCharArray()) candy[n][idx++] = c;
        }
                
        backtracking(0, 0, 0);
        
        System.out.println(maxCandy);
    }
}

