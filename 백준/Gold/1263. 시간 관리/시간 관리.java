import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    int N;
    PriorityQueue<Pair> pq;
    private void solution() {
        input();

        Pair now;
        int t = 1_000_001;
        while(!pq.isEmpty()) {
            now = pq.poll();

            if (t > now.deadline) {
                t = now.deadline;
            }

            t -= now.time;
        }

        if (t < 0) {
            System.out.println(-1);
        } else {
            System.out.println(t);
        }
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            pq = new PriorityQueue<>(Comparator.comparing(o -> o.deadline, Comparator.reverseOrder()));
            for (int i = 0; i < N; i++) {
                var st = new StringTokenizer(br.readLine());
                pq.offer(new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class Pair {
        int time, deadline;
        Pair(int time, int deadline) {
            this.time = time;
            this.deadline = deadline;
        }
    }
    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}