import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static HashMap<Integer, String> names1 = new HashMap<>();
    private static HashMap<String, Integer> names2 = new HashMap<>();
    private static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            String name = br.readLine();
            names1.put(i, name);
            names2.put(name, i);
        }

        for (int i = 0; i < M; i++) {
            String problem = br.readLine();
            try {
                int num = Integer.parseInt(problem);
                sb.append(names1.get(num)).append("\n");
            } catch (NumberFormatException e) {
                sb.append(names2.get(problem)).append("\n");
            }
        }
        System.out.println(sb.toString());
    }
}