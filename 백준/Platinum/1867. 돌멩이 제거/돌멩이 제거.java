import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    int N, K;
    boolean[] vis;
    int[] d;
    ArrayList<Integer>[] matrixList;

    private void solution() {
        input();

        vis = new boolean[N];
        d = new int[N];
        Arrays.fill(d, -1);

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            Arrays.fill(vis, false);
            if (match(i)) cnt++;
        }

        System.out.println(cnt);
    }

    boolean match(int idx) {
        for (var i : matrixList[idx])
            if ( !vis[i] ) {
                vis[i] = true;
                if (d[i] == -1 || match(d[i])) {
                    d[i] = idx;
                    return true;
                }
            }

        return false;
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            matrixList = new ArrayList[N];

            for (int i = 0; i < N; i++) {
                matrixList[i] = new ArrayList<>();
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int row = Integer.parseInt(st.nextToken()) - 1;
                int col = Integer.parseInt(st.nextToken()) - 1;

                matrixList[row].add(col);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}