import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    int N, M;
    int[] p;
    int[][] orders;
    private void solution() {
        input();

        var sb = new StringBuilder();
        for (int[] order : orders) {
            if ( order[0] == 0 ) {
                if ( find(order[1]) != find(order[2]))
                    union(order[1], order[2]);
            } else {
                if ( find(order[1]) == find(order[2])) {
                    sb.append("yes\n");
                } else {
                    sb.append("no\n");
                }
            }
        }

        System.out.print(sb);
    }

    void union (int a, int b) {
        p[find(a)] = find(b);
    }

    int find (int a) {
        if (p[a] != a) return p[a] = find(p[a]);
        return p[a];
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            p = new int[N+1];
            for (int i = 0; i <= N; i++) {
                p[i] = i;
            }

            orders = new int[M][3];
            for ( var i = 0; i < M; i ++) {
                st = new StringTokenizer(br.readLine());
                orders[i] = new int[3];
                orders[i][0] = Integer.parseInt(st.nextToken());
                orders[i][1] = Integer.parseInt(st.nextToken());
                orders[i][2] = Integer.parseInt(st.nextToken());
            }
        } catch( Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main().solution();
    }
}