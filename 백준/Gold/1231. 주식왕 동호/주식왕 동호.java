import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    int C, D, M;
    int[][] stock;

    private void solution() {
        input();

        int[] cache = new int[500_001];

        //돌려돌려
        for (int i = 0; i < D - 1; i++) {
            Arrays.fill(cache, 0, M+1, 0);

            for (int j = 0; j < C; j++) {
                int value = stock[j][i+1] - stock[j][i];
                int need = stock[j][i];

                if ( value <= 0 ) continue;

                for (int k = need; k <= M ; k++) {
                    if (cache[k - need] + value > cache[k])
                        cache[k] = cache[k - need] + value;

                }
            }

            M += cache[M];
        }

        System.out.println(M);
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());

            C = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            stock = new int[C][D];

            for (int i = 0; i < C; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < D; j++) {
                    stock[i][j] = Integer.parseInt(st.nextToken());
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