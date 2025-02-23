import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] original = new String[N];
        Map<String, Integer> words = new HashMap<>();
        int word1 = N;
        int word2 = 0;
        int sameLength = 0;

        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            original[i] = line;

            for(int w = 0; w < line.length(); w++) {
                String key = line.substring(0, line.length()-w);
                if(!words.containsKey(key)) {
                    words.put(key, i);
                }
                else {

                    if (sameLength < key.length()) {
                        word1 = words.get(key);
                        word2 = i;
                        sameLength = key.length();
                    }
                    else if(sameLength == key.length() && word1 > words.get(key) ) {
                        word1 = words.get(key);
                        word2 = i;
                    }
                    break;
                }
            }
        }
        if (original[word1].equals(original[word2])) return;

        StringBuilder sb = new StringBuilder();
        sb.append(original[word1]).append("\n").append(original[word2]);
        System.out.println(sb);
    }
}
