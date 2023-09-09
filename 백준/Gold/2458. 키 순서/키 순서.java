import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    int N, M;
    ArrayList<Integer>[] tallerList;
    ArrayList<Integer>[] smallerList;
    boolean[] vis;

    private void solution() {
        input();

        vis = new boolean[N];

        int answer = 0;
        for (int i = 0; i < N; i++) {
            Arrays.fill(vis, false);
            if (test(i)) {
                ++answer;
            }
        }

        System.out.println(answer);
    }

    boolean test(int i) {

        int count  = 1;

        for (int idx : tallerList[i])
            count += dfs(idx, true);

        for (int idx : smallerList[i])
            count += dfs(idx, false);

        return count == N;
    }

    int dfs(int idx, boolean isTaller) {
        if (vis[idx]) return 0;

        vis[idx] = true;
        int math = 1;

        for (var i : isTaller ? tallerList[idx] : smallerList[idx]) {
            math += dfs(i, isTaller);
        }

        return math;
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            tallerList = new ArrayList[N];
            smallerList = new ArrayList[N];

            for (int i = 0; i < N; i++) {
                tallerList[i] = new ArrayList<>();
                smallerList[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int smaller = Integer.parseInt(st.nextToken()) - 1;
                int taller  = Integer.parseInt(st.nextToken()) - 1;

                tallerList[smaller].add(taller);
                smallerList[taller].add(smaller);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}