import java.io.*;
import java.util.regex.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = br.readLine();

        Pattern pattern = Pattern.compile("<[^>]+>|[^<]+");
        Matcher matcher = pattern.matcher(text);

        StringBuilder result = new StringBuilder();

        while (matcher.find()) {
            String segment = matcher.group();
            if (segment.charAt(0) == '<') {
                result.append(segment);
            } else {
                String[] words = segment.split(" ");
                for (int i = 0; i < words.length; i++) {
                    result.append(new StringBuilder(words[i]).reverse());
                    if (i < words.length - 1) {
                        result.append(" ");
                    }
                }
            }
        }

        System.out.println(result.toString());
    }
}
