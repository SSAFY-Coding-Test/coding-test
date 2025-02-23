import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static HashMap<String, String> webPassword = new HashMap<>();
    private static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            String[] pair = br.readLine().split(" ");
            String address = pair[0];
            String password = pair[1];
            webPassword.put(address, password);
        }

        for (int i = 0; i < M; i++) {
            findPassword(br.readLine());
        }
        System.out.println(sb.toString());
    }

    private static void findPassword(String webSite) {
        sb.append(webPassword.get(webSite)).append("\n");
    }
}