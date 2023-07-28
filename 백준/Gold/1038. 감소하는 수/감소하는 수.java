import java.io.BufferedReader;
import java.io.InputStreamReader;;

public class Main {
    int N, n;
    int answer;

    private void solution() {
        input();

        n = -1;
        answer = -1;

        int length = 0;
        while ( n < N && length < 9 ) {
            dfs(length++, 10, 0);
        }

        System.out.println(n + 1 == N ? "9876543210" : answer);
    }

    void dfs(int idx, int before, int sum) {
        if (before < idx || answer != -1) return;

        if (idx == 0) {
            n += before;

            if (n >= N) {
                answer = sum*10 + before - (n - N + 1);
            }

            return;
        }

        for (int i = 0; i < before; i++) {
            dfs(idx - 1, i, sum*10 + i);
        }
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