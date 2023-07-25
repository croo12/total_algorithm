import java.io.BufferedReader;
import java.io.InputStreamReader;;
import java.util.StringTokenizer;

public class Main {
    int N, M;
    int[] counter;
    final long MAX = 1_000_000_000L;
    private void solution() {
        input();

        long start = 0L;
        long end = M * MAX;
        long mid;

        while (start <= end) {
            mid = (start + end) / 2;
            if (check(mid)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        System.out.println(start);
    }

    boolean check(long time) {
        long sum = 0;
        for (var i : counter) {
            sum += time/i;
            if (sum >= M) return true;
        }

        return false;
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            counter = new int[N];

            for ( int i = 0; i < N; i++){
                counter[i] = Integer.parseInt(br.readLine());
            }
        } catch( Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main().solution();
    }
}