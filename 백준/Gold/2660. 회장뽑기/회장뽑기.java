import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    int N;
    ArrayList<Integer>[] adjList;
    int[] dist;
    private void solution() {
        input();
        dist = new int[N];
        for (int i = 0; i < N; i++) {
            dist[i] = bfs(i);
        }

        var list = new ArrayList<Integer>(N);
        var sb = new StringBuilder();

        int min = N+1;
        for (int i = 0; i < N; i++) {
            if (dist[i] < min) {
                min = dist[i];
                list.clear();
                list.add(i);
            } else if (dist[i] == min) {
                list.add(i);
            }
        }

        sb.append(min).append(' ').append(list.size()).append('\n');
        for (var i : list) {
            sb.append(i+1).append(' ');
        }

        System.out.println(sb);
    }

    int bfs (int startPoint) {
        var vis = new boolean[N];
        var q = new LinkedList<Integer>();
        q.offer(startPoint);
        vis[startPoint] = true;
        int cnt = 1;
        int level = 0;

        while(!q.isEmpty()) {
            int size = q.size();

            if (cnt == N) return level;

            for (int i = 0; i < size; i++) {
                int now = q.pollFirst();

                for(var next: adjList[now]) {
                    if (vis[next]) continue;

                    q.offer(next);
                    vis[next] = true;
                    cnt++;
                }
            }

            level++;
        }

        return -1;
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            adjList = new ArrayList[N];

            for (int i = 0; i < N; i++) {
                adjList[i] = new ArrayList<>();
            }

            StringTokenizer st;
            while (true) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;

                if (a < 0 && b < 0) break;

                adjList[a].add(b);
                adjList[b].add(a);
            }

        } catch( Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}