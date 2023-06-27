import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    int N;
    int idx = 0;

    private void solution() throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        var pq = new PriorityQueue<Tower>();
        pq.offer(new Tower(idx++,1_000_000_001));

        var sb = new StringBuilder();

        Tower tower;
        Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).forEach((el) -> {

            while(!pq.isEmpty() && pq.peek().height < el)
                pq.poll();

            sb.append(pq.peek().no).append(" ");
            pq.offer(new Tower(idx++, el));
        });

        System.out.println(sb);
    }

    class Tower implements Comparable<Tower> {
        int no, height;
        Tower(int no, int height){
            this.no = no;
            this.height = height;
        }

        @Override
        public int compareTo(Tower o) {
            return Integer.compare(this.height, o.height);
        }
    }
}