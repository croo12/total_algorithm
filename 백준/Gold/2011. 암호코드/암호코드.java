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
        memo = new int[n+1];

        memo[n] = 1;
        memo[n-1] = N[n-1] == '0' ? 0 : 1;

        for (int i = n - 2; i >= 0; i--) {
            if (N[i] == '0') continue;
            if (N[i] == '1' || (N[i] == '2' && N[i + 1] <= '6'))
                memo[i] += memo[i+2];
            memo[i] += memo[i+1];
            memo[i] %= INF;
        }

        System.out.println(memo[0]);
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