import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    int N, M, L;
    int[] dividePoint;
    int[] q;

    private void solution() {
        input();

        var sb = new StringBuilder();
        for (var i : q) {
            int start = 1;
            int end = 4_000_000;
            int mid;

            while (start <= end) {
                mid = (start + end)/2;

                if (check(mid, i)) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }

            sb.append(end);
            sb.append('\n');
        }

        System.out.print(sb);
    }

    boolean check(int value, int select) {
        int i = 0;
        int count = 0;
        for (var l : dividePoint) {
            if (l - i >= value) {
                count++;
                i = l;
            }
        }

        return count > select;
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            dividePoint = new int[M+1];
            for (int i = 0; i < M; i++) {
                dividePoint[i] = Integer.parseInt(br.readLine());
            }
            dividePoint[M] = L;

            q = new int[N];
            for (int i = 0; i < N; i++) {
                q[i] = Integer.parseInt(br.readLine());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}