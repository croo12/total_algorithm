import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    char[] N;
    int n;
    int[] memo;
    final int INF = 1000000;
    private void solution() {
        input();
        n = N.length;
        memo = new int[n];

        for (int i = 0; i < n; i++) {
            memo[i] = -1;
        }

        System.out.println(dfs(0));
    }

    int dfs(int idx) {
        if (idx > n) {
            return 0;
        }

        if (idx == n) return 1;

        if (idx == n - 1) {
            return N[idx] == '0' ? 0 : 1;
        }

        if ( memo[idx] != -1) return memo[idx];

        if ( N[idx] == '0') return memo[idx] = 0;

        int sum = 0;
        if (N[idx] == '1' || (N[idx] == '2' && N[idx + 1] <= '6'))
            sum += dfs(idx + 2);

        sum += dfs(idx + 1);

        return memo[idx] = sum % INF;
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            N = br.readLine().toCharArray();
        } catch( Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}