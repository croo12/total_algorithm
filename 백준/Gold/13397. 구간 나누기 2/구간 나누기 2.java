import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {


    int N, M;
    int MAX = 0;
    int MIN = 10_000;
    int[] numbers;
    private void solution() {
        input();

        int start = 0;
        int end = MAX - MIN;
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

    boolean check (int answer) {

        int i = 0;
        int min = numbers[i];
        int max = numbers[i];
        int dimension = 1;

        for (; i < N; i++) {
            min = Math.min(min, numbers[i]);
            max = Math.max(max, numbers[i]);

            if ( max - min > answer) {
                min = numbers[i];
                max = numbers[i];
                dimension++;
            }
        }

        return dimension <= M;
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            numbers = new int[N];
            for (int i = 0; i < N; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
                MIN = Math.min(MIN, numbers[i]);
                MAX = Math.max(MAX, numbers[i]);
            }

        } catch( Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Main().solution();
    }
}