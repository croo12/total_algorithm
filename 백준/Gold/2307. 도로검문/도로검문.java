import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Vector {
        int startPoint,endPoint, weight;

        public Vector(int startPoint, int endPoint, int weight) {
            this.startPoint = startPoint;
            this.endPoint = endPoint;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Vector)) return false;
            Vector other = (Vector) obj;
            return (startPoint == other.startPoint && endPoint == other.endPoint) || (startPoint == other.endPoint && endPoint == other.startPoint);
        }
    }

    static class Node {
        int totalWeight;
        int number;
        Node before;

        public Node(int totalWeight, int number, Node before) {
            this.totalWeight = totalWeight;
            this.number = number;
            this.before = before;
        }
    }

    int N,M;
    final Node INF = new Node(987_654_321, -1,null);
    ArrayList<Vector>[] adjList;
    Comparator<Vector> cmp = Comparator.comparing(v -> v.weight);
    Vector banned;

    private void solution() {
        input();

        Node first = dijkstraWithRoot();

        int firstValue = first.totalWeight;
        int max = 0;

        for (Node now = first; now.before != null; now = now.before) {
            banned = new Vector(now.before.number, now.number, 0);

            int tmp = dijkstra();

            if (tmp == INF.totalWeight) {
                System.out.println(-1);
                return;
            } else {
                max = Math.max(max, tmp - firstValue);
            }
        }

        System.out.println(max);
    }

    Node dijkstraWithRoot() {
        boolean[] vis = new boolean[N];
        Node[] dist = new Node[N];

        for (int i = 1; i < N; i++) {
            dist[i] = INF;
        }
        dist[0] = new Node(0, 0, null);

        var distPq = new PriorityQueue<>(cmp);
        distPq.offer(new Vector(0,0, 0));

        Vector now;
        while (!distPq.isEmpty()) {
            now = distPq.poll();
            vis[now.endPoint] = true;

            if (now.endPoint == N - 1) return dist[now.endPoint];

            for (var nowV : adjList[now.endPoint]) {
                if (vis[nowV.endPoint]
                        || nowV.equals(banned)
                        || nowV.weight + dist[nowV.startPoint].totalWeight >= dist[nowV.endPoint].totalWeight ) continue;

                dist[nowV.endPoint] = new Node(nowV.weight + dist[nowV.startPoint].totalWeight, nowV.endPoint, dist[nowV.startPoint]);
                distPq.offer(new Vector(0, nowV.endPoint, dist[nowV.endPoint].totalWeight));
            }

        }

        return INF;
    }

    int dijkstra() {
        boolean[] vis = new boolean[N];
        int[] dist = new int[N];

        for (int i = 1; i < N; i++) {
            dist[i] = INF.totalWeight;
        }

        dist[0] = 0;

        var distPq = new PriorityQueue<>(cmp);
        distPq.offer(new Vector(0,0, 0));

        Vector now;
        while (!distPq.isEmpty()) {
            now = distPq.poll();
            vis[now.endPoint] = true;

            if (now.endPoint == N - 1) return dist[now.endPoint];

            for (var nowV : adjList[now.endPoint]) {
                if (vis[nowV.endPoint]
                        || nowV.equals(banned)
                        || nowV.weight + dist[nowV.startPoint] >= dist[nowV.endPoint] ) continue;

                dist[nowV.endPoint] = nowV.weight + dist[nowV.startPoint];
                distPq.offer(new Vector(0, nowV.endPoint, dist[nowV.endPoint]));
            }

        }

        return INF.totalWeight;
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            adjList = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                //M / N 넣고 테스트해보기
                adjList[i] = new ArrayList<>();
            }

            int x,y,z;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken()) - 1;
                y = Integer.parseInt(st.nextToken()) - 1;
                z = Integer.parseInt(st.nextToken());

                adjList[x].add(new Vector(x,y,z));
                adjList[y].add(new Vector(y,x,z));
            }

        } catch( Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Main().solution();
    }
}