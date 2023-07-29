import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    int N;
    static class Clazz {
        int start, end;

        public Clazz(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    Clazz[] study;

    private void solution() {
        input();

        Arrays.sort(study, Comparator.comparing(c -> c.start));
        var pq = new PriorityQueue<Clazz>(Comparator.comparing(c -> c.end));

        int max = 0;

        for (var i : study) {
            while (!pq.isEmpty() && pq.peek().end <= i.start) {
                pq.poll();
            }

            pq.offer(i);

            max = Math.max(pq.size(), max);
        }

        System.out.println(max);
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());

            study = new Clazz[N];
            int[] clazzTime;
            for (int i = 0; i < N; i++) {
                clazzTime = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                study[i] = new Clazz(clazzTime[0], clazzTime[1]);
            }

        } catch( Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Main().solution();
    }
}