import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int minGap = Integer.MAX_VALUE;
    private static int[][] skills;
    private static boolean[] isUsed;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        skills = new int[N][N];
        isUsed = new boolean[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                skills[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        backtracking(0, 0);
        System.out.println(minGap);
    }

    private static void backtracking(int start, int count) {
        if (count == N / 2) {
            calculateSkillGap();
            return;
        }

        for (int i = start; i < N; i++) {
            if (!isUsed[i]) {
                isUsed[i] = true;
                backtracking(i + 1, count + 1);
                isUsed[i] = false;
            }
        }
    }

    private static void calculateSkillGap() {
        int startSkill = 0;
        int linkSkill = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (isUsed[i] && isUsed[j]) {
                    startSkill += skills[i][j] + skills[j][i];
                } else if (!isUsed[i] && !isUsed[j]) {
                    linkSkill += skills[i][j] + skills[j][i];
                }
            }
        }

        int gap = Math.abs(startSkill - linkSkill);
        if (gap >= minGap) return;
        minGap = gap;
    }
}

