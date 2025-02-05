import java.io.*;
import java.util.*;


public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] wheel = {br.readLine(), br.readLine(), br.readLine(), br.readLine()};
		int[] wheelIdx = {0, 0, 0, 0};
		
		boolean[] Move1 = {true, false, true, false}; // 시계 방향 : true. 반시계 방향 : false
		boolean[] Move2 = {false, true, false, true};
		
		int K = Integer.parseInt(br.readLine());
		for(int k = 0; k < K; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()) -1;
			
			boolean way = st.nextToken().equals("-1") ? false : true;
			boolean[] moveWay = Move1[N] == way ? Move1 : Move2;
			boolean[] spin = {false, false, false, false};
			
			spin[N] = true;
			// 현재 톱니의 돌림 여부를 판단. 
			// 돌아간 톱니로부터 왼쪽 부분
			for(int n = N; n > 0; n--) {
				//왼쪽 톱니 
				int checkIdx1 = (wheelIdx[n-1] + 2) % 8;
				// 오른쪽 톱니
				int checkIdx2 = (8 + wheelIdx[n] - 2) % 8; 
				// 마주보는 극이 다르다면, 
				if (wheel[n-1].charAt(checkIdx1) != wheel[n].charAt(checkIdx2)) 
					spin[n-1] = true;
				else break;
			}
			
			for(int n = N; n < 3; n++) {
				//왼쪽 톱니 
				int checkIdx1 = (wheelIdx[n] + 2) % 8;
				// 오른쪽 톱니
				int checkIdx2 = (8 + wheelIdx[n+1] - 2) % 8; 
				// 마주보는 극이 다르다면, 
				if (wheel[n].charAt(checkIdx1) != wheel[n+1].charAt(checkIdx2)) 
					spin[n+1] = true;
				else break;
			}
			
			for (int i = 0; i < 4; i++) {
				// 돌아가는 지 확인 후, 어느쪽으로 돌아가는지 확인 -> 기준 인덱스 수정
				if (spin[i]) {
					if (moveWay[i]) // 시계 방향으로 돌아간다면, 
						wheelIdx[i] = ((8 + wheelIdx[i] - 1) % 8);
					else // 반시계 방향으로 돌아간다면, 
						wheelIdx[i] = ((wheelIdx[i] + 1) % 8);
				}
			}
		}
		
		int result = 0;
		int idx = 1;
		for(int i = 0; i < 4; i++) {
			result += (wheel[i].charAt(wheelIdx[i]) - '0') * (idx);
			idx *= 2;
		}
		System.out.println(result);
	}
}
