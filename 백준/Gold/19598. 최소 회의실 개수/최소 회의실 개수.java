import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    int N;
    PriorityQueue<int[]> pq;

    private void solution() {
        input();

        var pq2 = new PriorityQueue<int[]>(Comparator.comparing(arr -> arr[1]));

        int answer = 0;
        int[] now;
        while (!pq.isEmpty()) {
            now = pq.poll();

            while(!pq2.isEmpty() && pq2.peek()[1] <= now[0]) {
                pq2.poll();
            }

            pq2.offer(now);
            answer = Math.max(pq2.size(), answer);
        }

        System.out.println(answer);
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());

            pq = new PriorityQueue<>(Comparator.comparing(arr -> arr[0]));

            for (int i = 0; i < N; i++) {
                var st = new StringTokenizer(br.readLine());
                pq.offer(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}