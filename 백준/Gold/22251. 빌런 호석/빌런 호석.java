import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    final int[][] REVERSE =
            {
                {0, 4, 3, 3, 4, 3, 2, 3, 1, 2},
                {4, 0, 5, 3, 2, 5, 6, 1, 5, 4},
                {3, 5, 0, 2, 5, 4, 3, 4, 2, 3},
                {3, 3, 2, 0, 3, 2, 3, 2, 2, 1},
                {4, 2, 5, 3, 0, 3, 4, 3, 3, 2},
                {3, 5, 4, 2, 3, 0, 1, 4, 2, 1},
                {2, 6, 3, 3, 4, 1, 0, 5, 1, 2},
                {3, 1, 4, 2, 3, 4, 5, 0, 4, 3},
                {1, 5, 2, 2, 3, 2, 1, 4, 0, 1},
                {2, 4, 3, 1, 2, 1, 2, 3, 1, 0}
            };
    int N, K, P, X;
    private void solution() {
        input();

        //N 호석이가 바꾸는 숫자 최대
        //K 층 자리수
        //P 바꿀수있는 숫자
        //X 지금 층
        int answer = 0;

        for (int i = 1; i <= N ; i++) {
            if ( i == X) continue;
            if (check(i, X)) answer++;
        }

        System.out.println(answer);
    }

    boolean check (int n, int m) {
        int cnt = 0;

        while ( n != 0 || m != 0) {
            int nowN = n % 10;
            int nowM = m % 10;

            cnt += REVERSE[nowN][nowM];

            n /= 10;
            m /= 10;
        }

        return cnt <= P;
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());

        } catch( Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Main().solution();
    }
}