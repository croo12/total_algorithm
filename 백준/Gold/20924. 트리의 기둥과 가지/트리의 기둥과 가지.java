import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Main().solution();
    }

    static class Node {
        int start,end,d;

        public Node(int start, int end, int d) {
            this.start = start;
            this.end = end;
            this.d = d;
        }
    }

    int N, root;
    ArrayList<Node>[] adjList;
    int pillar, longestBranch;

    private void solution() {
        input();

        int now = root;

        pillar = 0;
        longestBranch = 0;

        //기둥 구하기
        int parent = 0;
        while (true) {

            int cnt = 0;
            int sum = 0;
            int next = 0;

            for( var adj : adjList[now]){
                if (adj.end != parent) {
                    cnt++;
                    sum += adj.d;
                    next = adj.end;

                    if (cnt > 1) break;
                }
            }

            if ( cnt != 1 ) break;

            parent = now;
            now = next;
            pillar += sum;

        }

        //젤 긴 가지 구하기
        longestBranch = calc(now, parent);

        System.out.printf("%d %d\n", pillar, longestBranch);
    }

    int calc(int now, int parent) {

        int max = 0;

        for (var adj : adjList[now]) {
            if (adj.end != parent) {
                max = Math.max(max, calc(adj.end, now) + adj.d);
            }
        }

        return max;
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            root = Integer.parseInt(st.nextToken());
            adjList = new ArrayList[N+1];

            for (int i = 0; i <= N; i++) {
                adjList[i] = new ArrayList<>();
            }

            for (int i = 1; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                adjList[a].add(new Node(a, b, d));
                adjList[b].add(new Node(b, a, d));
            }

        } catch( Exception e) {
            e.printStackTrace();
        }
    }
}