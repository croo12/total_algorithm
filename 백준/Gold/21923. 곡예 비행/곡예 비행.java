import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    int N, M;
    int[][] map;
    int[][][] memo;
    final int MIN = -100_000_001;
    private void solution() {
        input();

        int answer = upper(N-1,0);

        System.out.println(answer);
    }

    int upper(int row, int col) {

        if (memo[row][col][0] != MIN) return memo[row][col][0];

        int max = MIN;
        int point = map[row][col];

        if (col != M - 1)
            max = Math.max(upper(row, col+1), max);

        if (row != 0)
            max = Math.max(upper(row-1, col), max);

        max = Math.max(lower(row, col), max);

        return memo[row][col][0] = max + point;
    }

    int lower(int row, int col) {

        if (memo[row][col][1] != MIN) return memo[row][col][1];

        int max = MIN;
        int point = map[row][col];

        if (row == N-1 && col == M -1) {
            return map[N-1][M-1];
        }

        if (col != M - 1)
            max = Math.max(lower(row, col+1), max);

        if (row != N-1)
            max = Math.max(lower(row+1, col), max);

        return memo[row][col][1] = max + point;
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            memo = new int[N][M][2];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    memo[i][j][0] = MIN;
                    memo[i][j][1] = MIN;
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