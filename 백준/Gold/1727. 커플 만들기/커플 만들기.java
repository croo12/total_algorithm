import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    int N, M;
    int[] men;
    int[] women;
    int[] less, much;
    final int INF = 987_654_321;
    private void solution() {
        input();

        int N, M;

        if (this.N < this.M) {
            N = this.N;
            M = this.M;
            less = men;
            much = women;
        } else {
            N = this.M;
            M = this.N;
            less = women;
            much = men;
        }

        int[][] memo = new int[N+1][M+1];

        Arrays.sort(less);
        Arrays.sort(much);

        for (int i = 1; i <= N; i++) {
            memo[i][i] = memo[i - 1][i - 1] + diff(i, i);
            for (int j = i + 1; j <= M - (N - i); j++) {
                memo[i][j] = Math.min(memo[i-1][j-1] + diff(i, j), memo[i][j-1]);
            }
        }

        System.out.println(memo[N][M]);
    }

    int diff (int lessIdx, int muchIdx) {
        return Math.abs(less[lessIdx] - much[muchIdx]);
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            men = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                men[i] = Integer.parseInt(st.nextToken());
            }

            women = new int[M + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= M; i++) {
                women[i] = Integer.parseInt(st.nextToken());
            }

        } catch( Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Main().solution();
    }
}