import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    int N;
    int[] kids;
    private void solution() {
        input();

        int[] longest = new int[N];
        int max = 0;
        for (int i = 0; i < N; i++) {
            longest[i] = N + 1;
        }

        for (int i = 0; i < N; i++) {
            int now = kids[i];

            int start = 0;
            int end = max - 1;
            int mid;

            while (start <= end) {
                mid = (start + end)/2;

                if (longest[mid] > now) {
                    end = mid -1;
                } else {
                    start = mid + 1;
                }
            }

            longest[start] = now;
            max = Math.max(start + 1, max);
        }

        System.out.println(N - max);
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            kids = new int[N];

            for (int i = 0; i < N; i++) {
                kids[i] = Integer.parseInt(br.readLine());
            }
        } catch( Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}