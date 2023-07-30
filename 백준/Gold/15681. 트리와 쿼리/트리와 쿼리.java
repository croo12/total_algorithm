import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    ArrayList<Integer>[] adjList;
    int[] nodes;
    int[] query;
    int N, R, Q;

    private void solution() {
        input();

        makeTree(R, -1);

        var sb = new StringBuilder();
        for (var q : query) {
            sb.append(nodes[q]).append('\n');
        }

        System.out.print(sb);
    }

    int makeTree(int num, int parent) {

        int value = 1;

        for (var i : adjList[num]) {
            if (i != parent)
                value += makeTree(i, num);
        }

        return nodes[num] = value;
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken()) - 1;
            Q = Integer.parseInt(st.nextToken());

            nodes = new int[N];
            adjList = new ArrayList[N];

            for(var i = 0; i < N; i++) {
                adjList[i] = new ArrayList<Integer>();
            }

            int a, b;
            for(var i = 1; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken()) - 1;
                b = Integer.parseInt(st.nextToken()) - 1;
                adjList[a].add(b);
                adjList[b].add(a);
            }

            query = new int[Q];
            for (int i = 0; i < Q; i++) {
                query[i] = Integer.parseInt(br.readLine()) - 1;
            }

        } catch( Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Main().solution();
    }
}