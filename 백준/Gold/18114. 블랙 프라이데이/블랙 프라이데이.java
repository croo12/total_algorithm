import java.io.BufferedReader;
import java.io.InputStreamReader;;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    int N, C;
    int[] weight;

    private void solution() {
        input();

        Arrays.sort(weight);

        if (check()) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }

    }

    boolean check() {
        for (int firstIdx = 0; firstIdx < N; firstIdx++) {
            if (weight[firstIdx] == C) return true;

            for (int secondIdx = firstIdx + 1; secondIdx < N; secondIdx++) {
                if (weight[firstIdx] + weight[secondIdx] == C) return true;

                if (binarySearch(weight[firstIdx] + weight[secondIdx], secondIdx + 1)) {
                    return true;
                }
            }
        }

        return false;
    }

    boolean binarySearch(int value, int start) {
        int end = N - 1;
        int mid;

        int target = C - value;

        while ( start <= end ) {
            mid = (start + end)/2;

            if (weight[mid] >= target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return start != N && weight[start] == target;
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            weight = new int[N];
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                weight[i] = Integer.parseInt(st.nextToken());
            }

        } catch( Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main().solution();
    }
}