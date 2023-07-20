import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        new Main().solution();
    }
    final int MAX_BOUND = 1_010_001;
    int N;
    Chunk[] circles;
    Chunk root;
    static class Chunk {
        int leftBound, rightBound;
        Chunk leftChunk, midChunk, rightChunk;

        public Chunk(int leftBound, int rightBound) {
            this.leftBound = leftBound;
            this.rightBound = rightBound;
        }

        public Chunk(int leftBound, int rightBound, Chunk midChunk) {
            this.leftBound = leftBound;
            this.rightBound = rightBound;
            this.midChunk = midChunk;

            this.leftChunk = new Chunk(leftBound, midChunk.leftBound);
            this.rightChunk = new Chunk(midChunk.rightBound, rightBound);
        }

        public boolean insert(int circleLeft, int circleRight) {
            //범위 밖
            if ( circleLeft < leftBound || circleRight > rightBound) return false;

            //허허벌판에 도착함
            if ( leftChunk == null ) {
                leftChunk = new Chunk(leftBound, circleLeft);
                midChunk = new Chunk(circleLeft, circleRight);
                rightChunk = new Chunk(circleRight, rightBound);

                return true;
            }

            if (circleLeft > leftBound && circleRight < leftChunk.rightBound) {
                return leftChunk.insert(circleLeft, circleRight);
            }

            //지금 원 안에 쏙 들어감
            if (circleLeft > midChunk.leftBound && circleRight < midChunk.rightBound) {
                return midChunk.insert(circleLeft, circleRight);
            }

            //내가 지금 원 보다 더 큼
            if (circleLeft < midChunk.leftBound && circleRight > midChunk.rightBound) {
                this.midChunk = new Chunk(circleLeft, circleRight, this.midChunk);
                this.leftChunk = new Chunk(leftBound, circleLeft);
                this.rightChunk = new Chunk(circleRight, rightBound);

                return true;
            }

            if (circleLeft > rightChunk.leftBound && circleRight < rightBound) {
                return rightChunk.insert(circleLeft, circleRight);
            }
            
            //걸친대요
            return false;
        }
    }

    private void solution() {
        input();

        boolean flag = true;
        for (var chunk : circles) {
            if (!root.insert(chunk.leftBound, chunk.rightBound)) {
                flag = false;
                break;
            }
        }

        System.out.println(flag ? "YES" : "NO");
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));

            N = Integer.parseInt(br.readLine());
            circles = new Chunk[N];

            StringTokenizer st;
            for (int i = 0; i < N; i++ ) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());

                circles[i] = new Chunk(x - r, x + r);
            }

            root = new Chunk(-MAX_BOUND, MAX_BOUND);

        } catch( Exception e) {
            e.printStackTrace();
        }
    }
}
