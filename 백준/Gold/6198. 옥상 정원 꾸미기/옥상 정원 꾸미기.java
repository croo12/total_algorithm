import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    int N;
    int[] buildings;
    private void solution() {
        input();

        var stk = new Stack<Integer>();
        long sum = 0L;
        for (int i = 0; i < N; i++) {
            while (!stk.isEmpty() && buildings[stk.peek()] <= buildings[i] ) {
                int idx = stk.pop();
                sum += i - idx - 1;
            }

            stk.push(i);
        }

        while (!stk.isEmpty()) {
            sum += N - stk.pop() - 1;
        }

        System.out.println(sum);
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            buildings = new int[N];
            for (int i = 0; i < N; i++) {
                buildings[i] = Integer.parseInt(br.readLine());
            }


        } catch( Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Main().solution();
    }
}