import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    int N, K, D;
    int[] check;
    ArrayList<Integer>[] adjList;
    int[][] capacity;
    int[][] flow;
    int answer = 0;

    private void solution() {
        input();
        maxFlow();
        System.out.println(answer);
    }

    void maxFlow() {
        int startPoint = 0;
        int endPoint = N + D + 1;

        while(true) {
            Arrays.fill(check, -1);
            var q = new LinkedList<Integer>();
            q.push(startPoint);
            while (!q.isEmpty()) {
                int now = q.poll();
                for (var target : adjList[now]) {
                    if (capacity[now][target] - flow[now][target] > 0 && check[target] == -1) {
                        q.push(target);
                        check[target] = now;
                        if (target == endPoint) break;
                    }
                }
            }

            if (check[endPoint] == -1) return;

            int f = 987_654_321;
            for (int i = endPoint; i != startPoint; i = check[i]) {
                f = Math.min(f, capacity[check[i]][i] - flow[check[i]][i]);
            }

            for (int i = endPoint; i != startPoint; i = check[i]) {
                flow[check[i]][i] += f;
                flow[i][check[i]] -= f;
            }

            answer += f;
        }
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            int nodes = N + D + 2;

            check = new int[nodes];
            adjList = new ArrayList[nodes];

            capacity = new int[nodes][nodes];
            flow = new int[nodes][nodes];

            for (int i = 0; i < nodes; i++) {
                adjList[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            for (int i = N + 1; i <= N + D; i++) {
                adjList[i].add(N+D+1);
                adjList[N+D+1].add(i);
                capacity[i][N+D+1] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= N; i++) {
                adjList[0].add(i);
                adjList[i].add(0);
                capacity[0][i] = K;

                st = new StringTokenizer(br.readLine());
                int z = Integer.parseInt(st.nextToken());

                for (int j = 0; j < z; j++) {
                    int foodId = N + Integer.parseInt(st.nextToken());
                    adjList[i].add(foodId);
                    adjList[foodId].add(i);
                    capacity[i][foodId] = 1;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}