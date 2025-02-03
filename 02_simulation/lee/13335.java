import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int n, w, L;
	private static int time = 0;
	private static int bridgeWeight = 0;
	private static Queue<Integer> trucks = new LinkedList<>();
	private static Queue<int[]> bridge = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			trucks.add(Integer.parseInt(st.nextToken()));
		}
		moveBridge();
		System.out.println(time);
	}
    
	
	private static void moveBridge() {
		while (!trucks.isEmpty() || !bridge.isEmpty()) {
			time++;
			if (!bridge.isEmpty() && bridge.peek()[1] == time) {
				bridgeWeight -= bridge.poll()[0];
			}

			if (!trucks.isEmpty()) {
				int truck = trucks.peek();
				if (bridgeWeight + truck <= L) {
					bridge.add(new int[] {trucks.poll(), time + w});
					bridgeWeight += truck;
				}
			}
		}
	}
}
