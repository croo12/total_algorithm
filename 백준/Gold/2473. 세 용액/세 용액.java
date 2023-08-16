import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    int N;
    int[] numbers;
    private void solution() {
        input();

        Arrays.sort(numbers);

        long min = Long.MAX_VALUE;
        long[] answer = new long[3];
        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                long res = binarySearch(j+1, -(numbers[i] + numbers[j]));
                long result = Math.abs(res + numbers[i] + numbers[j]);
                if (result < min) {
                    min = result;
                    answer[0] = numbers[i];
                    answer[1] = numbers[j];
                    answer[2] = res;
                }
            }
        }

        System.out.printf("%d %d %d\n", answer[0], answer[1], answer[2]);
    }

    int binarySearch(int start, int target) {
        int startPoint = start;
        int endPoint = N;
        int mid;

        while (startPoint < endPoint) {
            mid = (startPoint + endPoint)/2;

            if (numbers[mid] > target) {
                endPoint = mid;
            } else {
                startPoint = mid + 1;
            }
        }

        if (startPoint == start) return numbers[startPoint];
        if (startPoint == N) return numbers[startPoint - 1];

        if (numbers[startPoint] - target < target - numbers[startPoint-1]) {
            return numbers[startPoint];
        } else {
            return numbers[startPoint-1];
        }
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            var st = new StringTokenizer(br.readLine());
            numbers = new int[N];
            for (int i = 0; i < N; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}