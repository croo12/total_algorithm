import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    int N, M;
    int[] p;
    long total;
    PriorityQueue<Vector> kruskal;
    static class Vector {
        int left, right, cost;

        public Vector(int left, int right, int cost) {
            this.left = left;
            this.right = right;
            this.cost = cost;
        }
    }

    private void solution() {
        input();

        int select = 0;
        long sum = 0L;
        Vector now;
        while (!kruskal.isEmpty() && select < N - 1) {
            now = kruskal.poll();

            int left = find(now.left);
            int right = find(now.right);

            if (left != right) {
                union(left, right);
                sum += now.cost;
                select++;
            }
        }

        if (select < N - 1) {
            System.out.println(-1);
        } else {
            System.out.println(total - sum);
        }
    }

    int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    void union (int x, int z) {
        p[x] = find(z);
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            total = 0L;

            p = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                p[i] = i;
            }

            kruskal = new PriorityQueue<>(Comparator.comparing(o -> o.cost));

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                kruskal.offer(new Vector(left, right, cost));
                total += cost;
            }

        } catch( Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main().solution();
    }
}