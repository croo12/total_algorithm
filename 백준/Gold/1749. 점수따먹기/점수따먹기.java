import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    int N, M;
    int[][] map;
    final int INF = 400_000_001;

    private void solution() {
        input();

        int max = -INF;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                for (int k = i; k <= N; k++) {
                    for (int l = j; l <= M; l++) {
                        max = Math.max(max, map[i-1][j-1] + map[k][l] - map[k][j-1] - map[i-1][l]);
                    }
                }
            }
        }

        System.out.println(max);
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new int[N+1][M+1];

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= M; j++) {
                    map[i][j] = map[i-1][j] + map[i][j-1] - map[i-1][j-1] + Integer.parseInt(st.nextToken());
                }
            }

        } catch( Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Main().solution();
    }
}