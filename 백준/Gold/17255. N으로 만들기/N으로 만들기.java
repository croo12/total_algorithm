import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    final int D = 11;
    String s;
    int headSlicer;

    private void solution() {
        input();

        int len = s.length();
        headSlicer = 1;

        if (s.length() == 1) {
            System.out.println(1);
            return;
        }

        Map<Integer, Integer>[] map = new HashMap[len-1];

        int n1 = 0;
        for (int i = 0; i < len-1; i++) {
            headSlicer *= D;
            map[i] = new HashMap<>();
            n1 = n1 * D + s.charAt(i) - '0' + 1;
        }

        headSlicer /= D;
        int n2 = n1%headSlicer*D + s.charAt(len-1) - '0' + 1;

        map[len - 2].put(n1, 1);
        if (n1 != n2)
            map[len - 2].put(n2, 1);

        for (int i = len - 2; i > 0; i--) {
            for (var entry : map[i].entrySet()) {
                map[i-1].compute(entry.getKey()/D, (k, v) -> v != null ? v + entry.getValue() : entry.getValue());
                if (!isEquals(entry.getKey())) {
                    map[i-1].compute(entry.getKey() % headSlicer, (k, v) -> v != null ? v + entry.getValue() : entry.getValue());
                }
            }

            headSlicer /= D;
        }

        int sum = 0;
        for (var i : map[0].values()) {
            sum += i;
        }

        System.out.println(sum);
    }

    boolean isEquals(int n) {
        return n%headSlicer == n/D;
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            s = br.readLine();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}