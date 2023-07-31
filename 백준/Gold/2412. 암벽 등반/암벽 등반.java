import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    int N, T;
    Set<Long> set;
    final long MAX_HEIGHT = 200_001L;
    final long[] d = {
            MAX_HEIGHT, 2 * MAX_HEIGHT, -MAX_HEIGHT, -2 * MAX_HEIGHT,
            MAX_HEIGHT + 1,  2 * MAX_HEIGHT + 1, -MAX_HEIGHT + 1, -2 * MAX_HEIGHT + 1,
            MAX_HEIGHT + 2,  2 * MAX_HEIGHT + 2, -MAX_HEIGHT + 2, -2 * MAX_HEIGHT + 2,
            MAX_HEIGHT - 1,  2 * MAX_HEIGHT - 1, -MAX_HEIGHT - 1, -2 * MAX_HEIGHT - 1,
            MAX_HEIGHT - 2,  2 * MAX_HEIGHT - 2, -MAX_HEIGHT - 2, -2 * MAX_HEIGHT - 2,
            1,  2, -1, -2,
    };

    private void solution() {
        input();

        var q = new LinkedList<Long>();
        q.offer(0L);

        int move = 0;
        while ( !q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                long now = q.poll();

                if ( now % MAX_HEIGHT >= T) {
                    System.out.println(move);
                    return;
                }

                for( var v : d) {
                    if (set.contains(now + v)) {
                        q.offer(now + v);
                        set.remove(now + v);
                    }
                }
            }

            move++;
        }

        System.out.println(-1);
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            T = Integer.parseInt(st.nextToken());

            set = new HashSet<>();

            int x,y;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                set.add(x * MAX_HEIGHT + y);
            }

        } catch( Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Main().solution();
    }
}