import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    int N;
    int[][][] cache;
    int SUCCESS = 1 << 10;
    int MOD = 1_000_000_000;

    private void solution() {
        input();

        //idx, check, position;
        cache = new int[N][SUCCESS][10];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < SUCCESS; j++) {
                Arrays.fill(cache[i][j], MOD);
            }
        }

        int answer = 0;
        for (int i = 1; i < 10; i++) {
            answer += dfs(1, 1 << i, i);
            answer %= MOD;
        }

        System.out.println(answer);
    }

    private int dfs(int idx, int check, int position) {
        if (position == 10 || position == -1) return 0;

        if (idx == N) {
            return check == SUCCESS - 1 ? 1 : 0;
        }

        if (cache[idx][check][position] != MOD) return cache[idx][check][position];

        cache[idx][check][position] = 0;

        cache[idx][check][position] += dfs(idx + 1, check | (1 << (position + 1)), position + 1);
        cache[idx][check][position] += dfs(idx + 1, check | (1 << (position - 1)), position - 1);

        return cache[idx][check][position] %= MOD;
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