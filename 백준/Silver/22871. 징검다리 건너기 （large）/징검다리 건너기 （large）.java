import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    int N;
    long K;
    int[] stones;
    boolean[] vis;
    private void solution() {
        input();

        long start = 1;
        long end = (N - 1) * (1L + Math.abs(stones[N-1] - stones[1]));
        while (start <= end) {
            K = (start + end)/2;
            vis = new boolean[N];
            dfs(0);

            if (vis[N-1]) {
                end = K - 1;
            } else {
                start = K + 1;
            }
        }

        System.out.println(start);
    }

    void dfs(int idx) {
        vis[idx] = true;

        for (int i = idx + 1; i < N; i++) {
            long energy = (i - idx) * (1L + Math.abs(stones[i] - stones[idx]));

            if (energy <= K && !vis[i]) {
                dfs(i);
            }
        }
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            stones = new int[N];
            var st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                stones[i] = Integer.parseInt(st.nextToken());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}