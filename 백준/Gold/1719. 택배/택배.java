import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    int N, M;
    final int INF = 987_654_321;
    int[][] dist;
    int[][] answer;
    private void solution() {
        input();

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int calc = dist[i][k] + dist[k][j];
                    if (dist[i][j] > calc){
                        dist[i][j] = calc;
                        answer[i][j] = answer[i][k];
                    }
                }
            }
        }

        var sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (answer[i][j] != -1 ) {
                    sb.append(answer[i][j] + 1).append(' ');
                } else {
                    sb.append('-').append(' ');
                }
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            dist = new int[N][N];
            answer = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dist[i][j] = INF;
                    answer[i][j] = -1;
                }
                dist[i][i] = 0;
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s1 = Integer.parseInt(st.nextToken()) - 1;
                int s2 = Integer.parseInt(st.nextToken()) - 1;
                int weight = Integer.parseInt(st.nextToken());

                dist[s1][s2] = weight;
                dist[s2][s1] = weight;
                answer[s1][s2] = s2;
                answer[s2][s1] = s1;
            }
        } catch( Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}