import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    class Kids {
        int candy;
        int count;
        public Kids(int candy, int count) {
            this.candy = candy;
            this.count = count;
        }
    }
    int N, M, K;
    int[] candies;
    int[] friends;
    int[] p;

    private void solution() {
        input();

        var items = new ArrayList<Kids>();

        for (int i = 1; i <= N; i++) {
            if (p[i] == i) {
                items.add(new Kids(candies[i], friends[i]));
            }
        }

        int[][] memo = new int[items.size() + 1][K];
        Arrays.fill(memo[0], 0);
        for ( int i = 1; i <= items.size(); i++) {
            var kid = items.get(i - 1);
            for (int j = 0; j < K; j++) {
                memo[i][j] = memo[i-1][j];

                if (j - kid.count >= 0 && memo[i-1][j- kid.count] + kid.candy > memo[i][j]) {
                    memo[i][j] = memo[i-1][j- kid.count] + kid.candy;
                }
            }
        }

        System.out.println(memo[items.size()][K - 1]);
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            candies = new int[N+1];
            friends = new int[N+1];
            p = new int[N+1];
            for (int i = 1; i <= N; i++) {
                candies[i] = Integer.parseInt(st.nextToken());
                friends[i] = 1;
            }

            for (int i = 1; i <= N; i++) {
                p[i] = i;
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = find(Integer.parseInt(st.nextToken()));
                int b = find(Integer.parseInt(st.nextToken()));

                if (a != b) {
                    union(a, b);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void union(int a, int b) {
        p[b] = a;
        candies[a] += candies[b];
        friends[a] += friends[b];
        candies[b] = 0;
        friends[b] = 0;
    }

    int find(int a) {
        if (p[a] != a) p[a] = find(p[a]);
        return p[a];
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}