import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    int N, M, L;
    int[] dividePoint;
    int[] q;
    int[][] cache;

    private void solution() {
        input();

        cache = new int[M+1][M+1];
        for (int i = 0; i <= M; i++) {
            Arrays.fill(cache[i], -1);
        }

        var sb = new StringBuilder();
        for (var i : q) {
            sb.append(dfs(i, 0));
            sb.append('\n');
        }

        System.out.println(sb);
    }

    int dfs(int select, int idx) {
        if (cache[select][idx] != -1) return cache[select][idx];

        int now = dividePoint[idx];

        if (select == 0) {
            //길이 - 지금위치
            return cache[select][idx] = L - now;
        }

        int max = 0;
        for (int i = idx + 1; i <= (M - select + 1); i++) {
            max = Math.max(max, Math.min(dividePoint[i] - now,  dfs(select - 1, i)));
        }

        return cache[select][idx] = max;
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            dividePoint = new int[M+1];
            for (int i = 1; i <= M; i++) {
                dividePoint[i] = Integer.parseInt(br.readLine());
            }

            q = new int[N];
            for (int i = 0; i < N; i++) {
                q[i] = Integer.parseInt(br.readLine());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}