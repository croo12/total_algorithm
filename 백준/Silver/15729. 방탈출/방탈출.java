import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Main {
    //좌우가 아니라 오른쪽 두개였네
    int N;
    boolean[] target;
    boolean[] lights;
    int answer;
    private void solution() {
        input();

        dfs(0,0);

        System.out.println(answer);
    }

    void dfs (int idx, int clicks) {
        if (idx == N) {
            answer = Math.min(clicks, answer);
            return;
        }

        if (lights[idx] == target[idx]) {
            dfs(idx+1, clicks);
        } else {
            clickButton(idx);
            dfs(idx + 1, clicks + 1);
            clickButton(idx);
        }
    }

    void clickButton (int idx) {
        lights[idx] = !lights[idx];
        if (idx < N - 1)
            lights[idx+1] = !lights[idx+1];
        if (idx < N - 2)
            lights[idx+2] = !lights[idx+2];
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());

            String tmp = br.readLine();

            target = new boolean[N];
            lights = new boolean[N];
            for ( var i = 0; i < 2 * N; i += 2) {
                target[i / 2] = tmp.charAt(i) == '1';
            }

            answer = N + 1;
        } catch( Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main().solution();
    }
}
