import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    int N, M;
    int[][] adjList;
    boolean[] vis;
    int[] direction;
    private void solution() {
        input();

        vis = new boolean[M];
        direction = new int[M];

        Arrays.fill(direction, -1);

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            Arrays.fill(vis, false);
            if (match(i)) cnt++;
        }

        System.out.println(cnt);
    }

    boolean match(int idx) {

        for (var i : adjList[idx]) {

            if (!vis[i]) {
                vis[i] = true;
                if ( direction[i] == -1 || match(direction[i]) ) {
                    direction[i] = idx;
                    return true;
                }
            }
        }

        return false;
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            adjList = new int[N][];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());

                adjList[i] = new int[n];
                for (int j = 0; j < n; j++) {
                    adjList[i][j] = Integer.parseInt(st.nextToken()) - 1;
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