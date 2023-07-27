import java.io.BufferedReader;
import java.io.InputStreamReader;;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    int N, M;
    double[][] connections;
    final int INF = Integer.MAX_VALUE;
    PriorityQueue<Road> pq = new PriorityQueue<>(Comparator.comparing(r -> r.cost));

    static class Pair {
        int row, col;

        public Pair(int r, int c) {
            this.row = r;
            this.col = c;
        }
    }

    static class Road {
        int start,end;
        double cost;

        public Road(int start, int end, double cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    private void solution() {
        input();

        boolean[] vis = new boolean[N];
        vis[0] = true;
        addAllRoad(0);

        int cnt = 0;
        double answer = 0.0;
        Road now;
        while (cnt < N - 1) {
            while (!pq.isEmpty() && vis[pq.peek().end]) {
                pq.poll();
            }

            now = pq.poll();

            vis[now.end] = true;
            addAllRoad(now.end);
            answer += now.cost;
            cnt++;
        }

        var df = new DecimalFormat("0.00");

        System.out.printf("%s\n", df.format(answer));
    }

    void addAllRoad(int idx) {
        for (int i = 0; i < N; i++) {
            pq.offer(new Road(idx, i, connections[idx][i]));
        }
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            connections = new double[N][N];

            var nodes = new Pair[N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                nodes[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == j) {
                        connections[i][i] = INF;
                        continue;
                    }

                    double rowDist = Math.abs(nodes[i].row - nodes[j].row);
                    double colDist = Math.abs(nodes[i].col - nodes[j].col);
                    double dist = Math.sqrt((rowDist * rowDist) + (colDist * colDist));
                    connections[i][j] = dist;
                }
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int left = Integer.parseInt(st.nextToken()) - 1;
                int right = Integer.parseInt(st.nextToken()) - 1;

                connections[left][right] = 0;
                connections[right][left] = 0;
            }

        } catch( Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Main().solution();
    }
}