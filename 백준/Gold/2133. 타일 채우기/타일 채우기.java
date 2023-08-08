import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    int N;
    int[][] memo;
    private void solution() {
        input();

        if (N%2 == 1) {
            System.out.println(0);
            return;
        }

        memo = new int[N][8];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 8; j++) {
                memo[i][j] = -1;
            }
        }

        int answer = dfs(0, 0);

        System.out.println(answer);
    }

    int dfs(int idx, int check) {
        if ( idx == N-1) {
            if ( check == 7 || check == 4 || check == 1) {
                return 1;
            } else {
                return 0;
            }
        }

        if (memo[idx][check] != -1) {
            return memo[idx][check];
        }

        int sum = 0;
        switch (check) {
            case 0:
                sum += dfs(idx+1, 1);
                sum += dfs(idx+1, 4);
                sum += dfs(idx+1, 7);
                break;

            case 1:
                sum += dfs(idx+1, 0);
                sum += dfs(idx+1, 6);
                break;

            case 2:
                sum += dfs(idx+1, 5);
                break;

            case 3:
                sum += dfs(idx+1, 4);
                break;

            case 4:
                sum += dfs(idx+1, 0);
                sum += dfs(idx+1, 3);
                break;

            case 5:
                sum = dfs(idx+1, 2);
                break;

            case 6:
                sum += dfs(idx+1, 1);
                break;

            case 7:
                sum += dfs(idx+1, 0);
                break;
        }

        return memo[idx][check] = sum;
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
        } catch( Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main().solution();
    }
}