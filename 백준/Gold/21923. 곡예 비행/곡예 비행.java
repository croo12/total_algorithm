import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    int N, M;
    int[][] map;
    int[][] upMemo;
    int[][] downMemo;
    final int MIN = -100_000_001;
    private void solution() {
        input();

        downMemo[N-1][M-1] = map[N-1][M-1];
        int answer = upper(N-1,0);

        System.out.println(answer);
    }

    int upper(int row, int col) {
        if (row < 0 || col >= M) return MIN;
        if (upMemo[row][col] != MIN) return upMemo[row][col];
        return upMemo[row][col] = Math.max( Math.max(upper(row, col+1), upper(row-1, col)), lower(row, col)) + map[row][col];
    }

    int lower(int row, int col) {
        if (row >= N || col >= M) return MIN;
        if (downMemo[row][col] != MIN) return downMemo[row][col];
        return downMemo[row][col] = Math.max(lower(row, col+1), lower(row+1, col)) + map[row][col];
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            upMemo = new int[N][M];
            downMemo = new int[N][M];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    upMemo[i][j] = downMemo[i][j] = MIN;
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