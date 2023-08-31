import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    int N;
    boolean[][] map;

    private void solution() {
        input();

        map = new boolean[N][N];

        recc(0, 0, N);

        var sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j] ? '*' : ' ');
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }

    void recc(int row, int col, int width) {
        if (width == 1) {
            map[row][col] = true;
            return;
        }

        int len = width/3;
        recc(row, col, len);
        recc(row + len, col, len);
        recc(row + 2 * len, col, len);
        recc(row, col + len, len);

        recc(row + 2 * len, col + len, len);
        recc(row, col + 2 * len, len);
        recc(row + len, col + 2 * len, len);
        recc(row + 2 * len, col + 2 * len, len);
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}