import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    int N;
    int[] numbers;
    final int MAX = 1_000_001;
    int[] f = new int[MAX];
    private void solution() {
        input();

        var stk = new Stack<int[]>();

        int now, fValue;
        for (int i = 0; i < N; i++) {
            now = numbers[i];
            fValue = f[now];

            //스택에서 얘보다 낮은애들 다뽑아
            while(!stk.isEmpty() && stk.peek()[0] < fValue) {
                numbers[stk.pop()[1]] = now;
            }

            stk.push(new int[]{fValue, i});
            numbers[i] = -1;
        }

        var sb = new StringBuilder();
        for (var i : numbers) {
            sb.append(i).append(' ');
        }
        sb.setLength(sb.length()-1);

        System.out.println(sb);
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            numbers = new int[N];
            var st = new StringTokenizer(br.readLine());
            int now;
            for (int i = 0; i < N; i++) {
                now = Integer.parseInt(st.nextToken());
                f[now]++;
                numbers[i] = now;
            }

        } catch( Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Main().solution();
    }
}