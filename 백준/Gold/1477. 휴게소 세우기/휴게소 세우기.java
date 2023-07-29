import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {

    int N, M, L;
    int[] restArea;
    private void solution() {
        input();

        int start = 1;
        int end = L;
        int mid;

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

    boolean check(int i) {
        int before = 0;
        int sum = 0;
        for (var now : restArea) {
            sum += (now - before - 1)/i;
            before = now;
        }
        sum += (L - before - 1)/i;

        return sum <= M;
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            if (N != 0)
                restArea = Stream.of(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .sorted().toArray();
            else
                restArea = new int[0];

        } catch( Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Main().solution();
    }
}