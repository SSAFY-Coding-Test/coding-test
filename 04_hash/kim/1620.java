import java.util.*;
import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map dic = new HashMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int total = Integer.parseInt(st.nextToken());
        int test = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= total; i++) {
            String word = br.readLine();
            dic.put(i, word);
            dic.put(word, i);
        }

        for(int t = 0; t < test; t++) {
            String now = br.readLine();
            if (Character.isLetter(now.charAt(0))) // 알파벳
                System.out.println(dic.get(now));
            else
                System.out.println(dic.get(Integer.parseInt(now)));
        }
    }
}
