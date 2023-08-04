import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    int S, P;
    char[] chars;
    int[] condition = new int[4];
    private void solution() {
        input();

        int[] now = new int[4];

        for (int i = 0; i < P; i++) {
            now[findIdx(chars[i])]++;
        }

        int answer = checkCondition(now) ? 1 : 0;
        int start = 1;
        int end = P;

        while (end < S) {
            now[findIdx(chars[start - 1])]--;
            now[findIdx(chars[end])]++;

            if (checkCondition(now)) answer++;

            start++;
            end++;
        }

        System.out.println(answer);
    }

    boolean checkCondition(int[] now) {
        for (int i = 0; i < 4; i++) {
            if (now[i] < condition[i]) return false;
        }
        return true;
    }

    int findIdx(char c) {
        switch (c) {
            case 'A' -> {return 0;}
            case 'C' -> {return 1;}
            case 'G' -> {return 2;}
            case 'T' -> {return 3;}
            default  -> {return -1;}
        }
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());

            chars = br.readLine().toCharArray();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) {
                condition[i] = Integer.parseInt(st.nextToken());
            }

        } catch( Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Main().solution();
    }
}