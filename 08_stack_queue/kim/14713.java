import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[][] words = new String[N][];
        for(int i = 0; i < N; i++)
            words[i] = br.readLine().split(" ");

        int[] idxs = new int[N];
        String[] result = br.readLine().split(" ");
        int remainBird = 0;

        while (remainBird < result.length) {
            int i;
            for (i = 0; i < N; i++) {
                if (idxs[i] >= words[i].length) continue;
                if (result[remainBird].equals(words[i][idxs[i]])) {
                    idxs[i]++;
                    remainBird++;
                    break;
                }
            }
            if (i == N) break;
        }
        boolean check = true;
        for(int i = 0; i < N; i++) {
            if (idxs[i] != words[i].length) {
                check = false;
                break;
            }
        }

        if (remainBird == result.length && check)
            System.out.println("Possible");
        else System.out.println("Impossible");
    }
}
