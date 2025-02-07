public class 1062 {

    private static int N;
    private static int K;
    private static int max_count = 0;
    private static String[] words;
    private static boolean[] isAlphaUsed = new boolean[26];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        words = new String[N];
        for (int test_case = 0; test_case < N; test_case++) {
            words[test_case] = br.readLine();
        }

        isAlphaUsed['a' - 'a'] = true;
        isAlphaUsed['n' - 'a'] = true;
        isAlphaUsed['t' - 'a'] = true;
        isAlphaUsed['i' - 'a'] = true;
        isAlphaUsed['c' - 'a'] = true;

        if (K < 5) {
            System.out.println(0);
        } else {
            backtracking(0, 1);
            System.out.println(max_count);
        }
    }

    private static void backtracking(int currentCharCount, int start) {
        if (currentCharCount == K - 5) {
            int readableWordCount = countReadableWords();
            max_count = Math.max(max_count, readableWordCount);
            return;
        }

        for (int i = start; i < 26; i++) {
            if (!isAlphaUsed[i]) {
                isAlphaUsed[i] = true;
                backtracking(currentCharCount + 1, i + 1);
                isAlphaUsed[i] = false;
            }
        }
    }

    private static int countReadableWords() {
        int count = 0;

        for (String word : words) {
            boolean readable = true;

            for (char c : word.toCharArray()) {
                if (!isAlphaUsed[c - 'a']) {
                    readable = false;
                    break;
                }
            }
            if (readable) count++;
        }
        return count;
    }
}
