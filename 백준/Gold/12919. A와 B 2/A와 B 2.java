import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {

    static class Now {
        int start, end;
        boolean isReversed;

        public Now(int start, int end, boolean isReversed) {
            this.start = start;
            this.end = end;
            this.isReversed = isReversed;
        }
    }
    char[] S, T;
    private void solution() {
        input();

        int sLen = S.length;
        int tLen = T.length;

        var q = new LinkedList<Now>();

        q.offer(new Now(0, tLen - 1, false));

        Now now;
        while (!q.isEmpty()) {
            now = q.poll();
            int start = now.start;
            int end = now.end;
            boolean isReversed = now.isReversed;

            if (end - start == sLen - 1) {
                if (check(start,end,isReversed)) {
                    System.out.println(1);
                    return;
                }
                continue;
            }

            if (!isReversed) {
                if (T[end] == 'A') q.offer(new Now(start, end-1, false));
                if (T[start] != 'B') continue;
                q.offer(new Now(start+1, end, true));
            } else {
                if (T[start] == 'A') q.offer(new Now(start+1, end, true));
                if (T[end] != 'B') continue;
                q.offer(new Now(start, end-1, false));
            }
        }

        System.out.println(0);
    }

    boolean check (int start, int end, boolean isReversed) {
        int cnt = 0;
        if (!isReversed) {
            for (int i = start; i <= end; i++) {
                if (T[i] != S[cnt++]) {
                    return false;
                }
            }
        } else {
            for (int i = end; i >= start; i--) {
                if (T[i] != S[cnt++]) {
                    return false;
                }
            }
        }

        return true;
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            S = br.readLine().toCharArray();
            T = br.readLine().toCharArray();

        } catch( Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Main().solution();
    }
}