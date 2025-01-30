import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static int M;
    static StringBuilder sb = new StringBuilder();

    static void backtracking(int n, List<Integer> nums) {
        // 중단조건
        if (nums.size() == M) {
            for(int nu : nums)
                sb.append(nu).append(" ");
            sb.append("\n");
            return;
        }
        for (int i = 1; i <= N; i++) {
            if(!nums.contains(i)) {
                nums.add(i);
                backtracking(i,	nums);
                nums.remove(nums.size()-1);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        List<Integer> nums = new ArrayList<>();
        backtracking(1, nums);

        System.out.println(sb);
    }
}
