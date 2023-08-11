import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    int N, total;
    int[] marbles;
    long[][][][][][] memo;

    private void solution() {
        input();

        memo = new long[11][11][11][11][11][25];

        for (int j = 0; j < 11; j++) {
            for (int k = 0; k < 11; k++) {
                for (int l = 0; l < 11; l++) {
                    for (int m = 0; m < 11; m++) {
                        for (int n = 0; n < 11; n++) {
                            for (int o = 0; o < 25; o++) {
                                memo[j][k][l][m][n][o] = -1L;
                            }
                        }
                    }
                }
            }
        }

        long answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ( i == j) continue;
                marbles[i]--;
                marbles[j]--;
                answer += dfs( i, j);
                marbles[i]++;
                marbles[j]++;
            }
        }

        System.out.println(answer);
    }

    long dfs(int before, int last) {
        if (marbles[0] + marbles[1] + marbles[2] + marbles[3] + marbles[4] == 0) {
            return 1;
        }

        if (memo[marbles[0]][marbles[1]][marbles[2]][marbles[3]][marbles[4]][before * 5 + last] != -1) {
            return memo[marbles[0]][marbles[1]][marbles[2]][marbles[3]][marbles[4]][before * 5 + last];
        }

        long sum = 0;
        for (int i = 0; i < N; i++) {
            if (i == before || i == last || marbles[i] == 0) continue;

            marbles[i]--;
            sum += dfs(last, i);
            marbles[i]++;
        }

        return memo[marbles[0]][marbles[1]][marbles[2]][marbles[3]][marbles[4]][before * 5 + last] = sum;
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            total = 0;
            marbles = new int[5];
            for (int i = 0; i < N; i++) {
                marbles[i] = Integer.parseInt(br.readLine());
                total += marbles[i];
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}