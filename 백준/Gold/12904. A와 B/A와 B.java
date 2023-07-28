import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;;

public class Main {
    String S,T;

    private void solution() {
        input();

        char[] T = this.T.toCharArray();
        int start = 0;
        int end = T.length - 1;
        boolean isReversed = false;

        int select = T.length - S.length();

        while (select-- > 0) {
            if (!isReversed) {
                if (T[end] == 'A') {
                    end--;
                } else {
                    end--;
                    isReversed = true;
                }
            } else {
                if (T[start] == 'A') {
                    start++;
                } else {
                    start++;
                    isReversed = false;
                }
            }
        }

        int cnt = 0;
        int result = 1;
        if (isReversed) {
            for (int i = end; i >= start; i--) {
                if (S.charAt(cnt++) != T[i]) {
                    result = 0;
                    break;
                }
            }
        } else {
            for (int i = start; i <= end; i++) {
                if (S.charAt(cnt++) != T[i]) {
                    result = 0;
                    break;
                }
            }
        }

        System.out.println(result);
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            S = br.readLine();
            T = br.readLine();

        } catch( Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Main().solution();
    }
}