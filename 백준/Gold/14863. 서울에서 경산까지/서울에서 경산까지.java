import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    int N, K;
    int[][] mt;
    private void solution() {
        input();

        int ans = dfs(0, 0);
        System.out.println(ans);
    }

    int dfs( int idx, int time) {
        if ( time > K ) return -1987654321;
        if ( idx == N ) return 0;

        int max = Math.max( dfs(idx+1, time + mt[idx][0]) + mt[idx][1], dfs(idx+1, time + mt[idx][2]) + mt[idx][3]);

        return max;
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            mt =  new int[N][4];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < 4; j++) {
                    mt[i][j] = Integer.parseInt(st.nextToken());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}