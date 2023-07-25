import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        new Main().solution();
    }

    class Segment {
        int[] ordering;
        int startIdx, endIdx;
        Segment leftSegment, rightSegment;

        public Segment(int startIdx, int endIdx) {
            this.startIdx = startIdx;
            this.endIdx = endIdx;

            int diff = endIdx - startIdx + 1;
            ordering = new int[diff];

            if (startIdx != endIdx) {
                leftSegment = new Segment(startIdx, (startIdx + endIdx)/2);
                rightSegment = new Segment((startIdx + endIdx)/2 + 1, endIdx);
            }
        }

        public void sort() {
            if (startIdx == endIdx) {
                ordering[0] = numbers[endIdx];
                return;
            }

            leftSegment.sort();
            rightSegment.sort();

            int right = rightSegment.startIdx;

            int leftR = leftSegment.startIdx;
            int rightR = right;

            int order = 0;

            while ( leftR < right && rightR <= endIdx ) {
                if (numbers[leftR] <= numbers[rightR]) {
                    ordering[order++] = numbers[leftR++];
                } else {
                    ordering[order++] = numbers[rightR++];
                }
            }

            while ( leftR < right ) {
                ordering[order++] = numbers[leftR++];
            }

            while ( rightR <= endIdx ) {
                ordering[order++] = numbers[rightR++];
            }

            int idx = 0;
            for (int i = startIdx; i <= endIdx; i++) {
                numbers[i] = ordering[idx++];
            }
        }

        public int findK(int left, int right, int k) {
            if (startIdx == endIdx) {
                return ordering[0] > k ? 1 : 0;
            }

            if (left == startIdx && right == endIdx) {
                return binarySearch(k);
            }

            int sum = 0;

            if (left <= leftSegment.endIdx) {
                sum += leftSegment.findK(left, Math.min(leftSegment.endIdx, right), k);
            }

            if (right >= rightSegment.startIdx) {
                sum += rightSegment.findK(Math.max(rightSegment.startIdx, left), right, k);
            }

            return sum;
        }

        public int binarySearch(int k) {
            int start = 0;
            int end = endIdx - startIdx;
            int mid;

            while (start <= end) {
                mid = (start + end)/2;
                if (ordering[mid] > k) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }

            return (endIdx - startIdx) - end;
        }
    }

    int N, M;
    int[] numbers;
    int[][] querys;

    private void solution() {
        input();

        Segment root = new Segment(0, N-1);
        root.sort();

        var sb = new StringBuilder();
        int lastAns = 0;
        for (int i = 0; i < M; i++) {
            lastAns = root.findK((querys[i][0]^lastAns) - 1, (querys[i][1]^lastAns) - 1, querys[i][2]^lastAns);
            sb.append(lastAns).append('\n');
        }

        System.out.print(sb);
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            numbers = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            M = Integer.parseInt(br.readLine());
            querys = new int[M][3];
            int[] tmp;
            for (int i = 0; i < M; i++) {
                tmp = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                querys[i][0] = tmp[0];
                querys[i][1] = tmp[1];
                querys[i][2] = tmp[2];
            }

        } catch( Exception e) {
            e.printStackTrace();
        }
    }
}