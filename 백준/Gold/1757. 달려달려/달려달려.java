import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    int N, M;
    int[] dist;
    int[][] cache;

    private void solution() {
        input();

        cache = new int[N][M+1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= M; j++) {
                cache[i][j] = -1;
            }
        }

        int max = calc(0, 0);

        System.out.println(max);
    }

    int calc(int idx, int energy) {

        if (energy > M || idx > N || (idx == N && energy != 0)) {
            return Integer.MIN_VALUE;
        }

        if (idx == N) {
            return 0;
        }

        if (cache[idx][energy] != -1) return cache[idx][energy];

        int max = calc(idx + 1, energy + 1) + dist[idx];

        if (energy == 0) max = Math.max(max, calc(idx + 1, 0));
        if (energy != 0) max = Math.max(max, calc(idx + energy, 0));

        return cache[idx][energy] = max;
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            dist = new int[N];
            for (int i = 0; i < N; i++) {
                dist[i] = Integer.parseInt(br.readLine());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}