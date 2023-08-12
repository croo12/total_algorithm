import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    int N, total;
    int[] numbers;

    private void solution() {
        input();

        boolean[] check = new boolean[100_001];

        int length = 0;
        int start = 0;

        long answer = 0L;

        for (int end = 0; end < N; end++) {

            while (check[numbers[end]]) {
                answer += length;

                check[numbers[start]] = false;
                length--;
                start++;
            }

            check[numbers[end]] = true;
            length++;
        }

        while(start < N) {
            answer += length;

            check[numbers[start]] = false;
            length--;
            start++;
        }

        System.out.println(answer);
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            numbers = new int[N];

            var st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
                total += numbers[i];
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}