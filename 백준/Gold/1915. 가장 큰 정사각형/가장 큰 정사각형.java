import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    int N, M;
    int[][] memo;
    private void solution() {
        input();

        int answer = 0;

        for (int i = N-1; i >= 0; i--) {
            for (int j = M-1; j >= 0; j--) {
                if (memo[i][j] == 1 && j != M-1 && i != N-1) {
                    memo[i][j] = Math.min(Math.min(memo[i+1][j], memo[i+1][j+1]), memo[i][j+1]) + 1;
                }

                answer = Math.max(memo[i][j], answer);
            }
        }

        System.out.println(answer * answer);
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            String tmp;
            memo = new int[N][M];
            for (int i = 0; i < N; i++) {
                tmp = br.readLine();
                for (int j = 0; j < M; j++) {
                    memo[i][j] = tmp.charAt(j) - '0';
                }
            }
        } catch( Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}