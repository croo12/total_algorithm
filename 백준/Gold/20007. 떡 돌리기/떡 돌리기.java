import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    int N, M, X, Y;
    ArrayList<Adj>[] adjList;
    final int INF = 2_000_654_321;

    class Adj {
        int endPoint, weight;

        public Adj(int endPoint, int weight) {
            this.endPoint = endPoint;
            this.weight = weight;
        }
    }

    private void solution() {
        input();
        int[] dist = new int[N];
        for (int i = 0; i < N; i++) {
            dist[i] = INF;
        }

        dijkstra(dist);

        Arrays.sort(dist);

        int cnt = 0;
        long sum = 0L;

//        System.out.println(Arrays.toString(dist));

        for (int i = 1; i < N; i++) {
            if (dist[i] * 2 > X) {
                System.out.println(-1);
                return;
            }

            sum += dist[i] * 2;

            if (sum > X) {
                sum = dist[i] * 2;
                cnt++;
            }
        }

        System.out.println(cnt + 1);
    }

    void dijkstra(int[] dist) {

        var pq = new PriorityQueue<Adj>(Comparator.comparing(o -> o.weight));
        var vis = new boolean[N];

        pq.offer(new Adj(Y, 0));
        dist[Y] = 0;

        Adj now;
        while(!pq.isEmpty()) {
            now = pq.poll();

            if (vis[now.endPoint]) continue;

            for(var adj : adjList[now.endPoint]) {
                if (vis[adj.endPoint] || dist[now.endPoint] + adj.weight >= dist[adj.endPoint]) continue;

                dist[adj.endPoint] = dist[now.endPoint] + adj.weight;
                pq.offer(new Adj(adj.endPoint, dist[adj.endPoint]));
            }

            vis[now.endPoint] = true;
        }
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            Y = Integer.parseInt(st.nextToken());

            adjList = new ArrayList[N];

            for (int i = 0; i < N; i++) {
                adjList[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                adjList[a].add(new Adj(b,c));
                adjList[b].add(new Adj(a,c));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}