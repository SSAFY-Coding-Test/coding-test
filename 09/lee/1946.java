import java.io.*;
import java.util.*;

public class 신입_사원_1946 {
    // 다른 모든 지원자와 비교했을 때
    // 서류심사 성적과 면접시험 성적 중
    // 적어도 하나가 다른 지원자보다 떨어지지 않는 자만 선발한다

    // 어떤 지원자 A의 성적이 다른 어떤 지원자 B의 성적에 비해
    // 서류 심사 결과와 면접 성적이 모두 떨어진다면 A는 선발되지 않는다

    // 선발할 수 있는 신입사원의 최대 인원수 구하기
    static int N;
    static int[][] applicants;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());

            applicants = new int[N][2];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                applicants[i][0] = Integer.parseInt(st.nextToken());
                applicants[i][1] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(applicants, (o1, o2) -> o1[0] - o2[0]);
            sb.append(solve()).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int solve() {
        int count = 1;
        int topInterviewRank = applicants[0][1];
        for (int i = 0; i < N; i++) {
            if (topInterviewRank > applicants[i][1]) {
                topInterviewRank = applicants[i][1];
                count++;
            }
        }
        return count;
    }
}
