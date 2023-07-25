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

        if (binarySearch(0)) {
            return true;
        }

        for (int firstIdx = 0; firstIdx < N; firstIdx++) {
            if (binarySearch(weight[firstIdx], firstIdx)) {
                return true;
            }

            for (int secondIdx = firstIdx + 1; secondIdx < N; secondIdx++) {
                if (binarySearch(weight[firstIdx] + weight[secondIdx], firstIdx, secondIdx)) {
                    return true;
                }
            }
        }

        return false;
    }

    boolean binarySearch(int value, int ...idx) {

        int start = 0;
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

        if ( start != N && weight[start] == target) {
            for (var i : idx) {
                if (start == i) return false;
            }

            return true;
        } else {
            return false;
        }
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