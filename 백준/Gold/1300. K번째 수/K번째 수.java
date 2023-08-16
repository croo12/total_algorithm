import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    int N, K;
    private void solution() {
        input();

        long start = 1;
        long end = 1_000_000_000;
        long mid;
        while (start <= end) {
            mid = (start + end)/2;
            if (check(mid)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        System.out.println(start);
    }

    boolean check(long point) {
        long sum = 0;
        for (int i = 1; i <= N; i++) {
            long now = Math.min(point / i, N);
            if (now == 0) break;
            sum += now;
        }

        return sum >= K;
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            K = Integer.parseInt(br.readLine());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}