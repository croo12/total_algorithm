import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
    int N;
    int[] A;
    private void solution() {
        input();

        long answer = 0L;

        var list = new LinkedList<Integer>();
        for (int i = 0; i < N-1; i++) {
            int now = A[i];
            int next = A[i+1];

            if (now < next) {
                //일단 이전거랑 비교해봄
                while (!list.isEmpty() && list.peekLast() < next) {
                    list.pollLast();
                }

                answer += next - now;

            } else if (now > next) {
                list.addLast(now);
            }

        }

        int now = A[N-1];
        if (!list.isEmpty())
            answer += Math.max(now, list.peekFirst()) - Math.min(list.peekLast(), now);

        System.out.println(answer);
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            A = new int[N];

            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(br.readLine());
            }

        } catch( Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}