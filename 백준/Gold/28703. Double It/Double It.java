import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    int N;
    int max;
    PriorityQueue<Integer> pq;

    private void solution() {
        input();

        int min = max - pq.peek();

        while (true) {

            int newGuy = pq.poll() * 2;

            if (newGuy < 0) break;

            max = Math.max(newGuy, max);
            pq.offer(newGuy);
            min = Math.min(max - pq.peek(), min);
        }

        System.out.println(min);
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            var st = new StringTokenizer(br.readLine());
            pq = new PriorityQueue<>();
            for (int i = 0; i < N; i++) {
                int now = Integer.parseInt(st.nextToken());
                max = Math.max(now, max);
                pq.offer(now);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}