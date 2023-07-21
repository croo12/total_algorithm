import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Main().solution();
    }

    static class Node implements Comparable<Node>{
        int value, idx;

        public Node(int value, int idx) {
            this.value = value;
            this.idx = idx;
        }

        @Override
        public int compareTo(Node o) {
            int compareValue = Integer.compare(o.value, this.value);

            if (compareValue == 0) {
                return Integer.compare(this.idx, o.idx);
            }

            return compareValue;
        }
    }
    int[] counter;
    int N, K;
    int[] numbers;

    private void solution() {
        input();

        var pq = new PriorityQueue<Node>();

        int removable = K;
        int nowIdx = 0;
        int lastIdx = 0;

        var sb = new StringBuilder();

        Node now;
        while (nowIdx < N - removable) {

            for (; lastIdx <= nowIdx + removable; lastIdx++ ) {
                pq.offer(new Node(numbers[lastIdx], lastIdx));
            }

            now = pq.poll();
            while (now.idx < nowIdx) {
                now = pq.poll();
            }

            sb.append(now.value);
            removable -= now.idx - nowIdx;
            nowIdx = now.idx + 1;
        }

        System.out.println(sb);
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            counter = new int[10];
            numbers = br.readLine().chars().map(c -> c - '0').toArray();

        } catch( Exception e) {
            e.printStackTrace();
        }
    }
}