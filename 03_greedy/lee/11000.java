import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static List<int[]> meetings = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());

            meetings.add(new int[] {startTime, endTime});
        }

        // 종료 시간 기준 정렬 (종료 시간이 같은 경우 시작 시간이 빠른 순)
        meetings.sort((o1, o2) -> o1[1] == o2[1]? o1[0] - o2[0] : o1[1] - o2[1]);

        int count = 1;
        int currentEndTime = meetings.get(0)[1];
        for (int i = 1; i < meetings.size(); i++) {
            int nextStartTime = meetings.get(i)[0];
            int nextEndTime = meetings.get(i)[1];

            // 회의 끝나고 제일 먼저 시작될 회의 찾기
            if (nextStartTime >= currentEndTime) {
                currentEndTime = nextEndTime;
                count++;
            }
        }
        System.out.println(count);
    }
}