import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    int N;
    int[][] cost;
    int[][][] cache;

    private void solution() {
        input();

        cache = new int[N][3][3];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < 3; j++)
                Arrays.fill(cache[i][j], -1);

        int answer = calc(1, 0, 0) + cost[0][0];
        answer = Math.min(calc(1, 1, 1) + cost[0][1], answer);
        answer = Math.min(calc(1, 2, 2) + cost[0][2], answer);

        System.out.println(answer);
    }

    int calc(int idx, int before, int first) {
        int max = 1_000_001;

        if (cache[idx][before][first] != -1) return cache[idx][before][first];

        if ( idx == N - 1) {
            for (int i = 0; i < 3; i++) {
                if ( i != before && i != first)
                    max = Math.min(cost[idx][i], max);
            }

            return max;
        }

        for (int i = 0; i < 3; i++) {
            if ( i != before)
                max = Math.min(cost[idx][i] + calc(idx + 1, i, first), max);
        }

        return cache[idx][before][first] = max;
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());

            cost = new int[N][3];

            StringTokenizer st;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                cost[i][0] = Integer.parseInt(st.nextToken());
                cost[i][1] = Integer.parseInt(st.nextToken());
                cost[i][2] = Integer.parseInt(st.nextToken());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}