import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Main().solution();
    }

    int N;
    int[] skyLines;

    private void solution() {
        input();
        var stk = new Stack<Integer>();

        var answer = 0;
        for(var height : skyLines) {
            while ((!stk.isEmpty()) && stk.peek() > height) {
                stk.pop();
                answer++;
            }

            if (stk.isEmpty() || stk.peek() != height)
                stk.push(height);
        }

        while ((!stk.isEmpty())) {
            if (stk.pop() != 0)
                answer++;
        }

        System.out.println(answer);
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            StringTokenizer st;
            skyLines = new int[N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                st.nextToken();
                skyLines[i] = Integer.parseInt(st.nextToken());
            }

        } catch( Exception e) {
            e.printStackTrace();
        }
    }
}
