import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        new Main().solution();
    }


    static class Building implements Comparable<Building>{
        int L,H,R;
        boolean flag;

        public Building(int l, int h, int r) {
            L = l;
            H = h;
            R = r;
            flag = false;
        }

        @Override
        public int compareTo(Building o) {
            int compareValue = L;
            int otherGuy = o.L;
            if ( flag ) compareValue = R;
            if ( o.flag ) otherGuy = o.R;

            int result = Integer.compare(compareValue, otherGuy);

            if ( result == 0 ) {
                return Integer.compare(o.H, H);
            }

            return result;
        }
    }

    PriorityQueue<Building> notStart;
    PriorityQueue<Building> notEnd;

    private void solution() {
        input();

        var nowHeights =  0;
        var heights = new TreeMap<Integer, Integer>(Comparator.reverseOrder());
        var sb = new StringBuilder();
        heights.put(0, 9999);

        while(!(notStart.isEmpty() && notEnd.isEmpty())) {
            var start = notStart.peek();
            var end = notEnd.peek();

            if (end == null || (start != null && end.R > start.L)) {

                //어떤 빌딩의 왼쪽 시작부분
                while ( !notStart.isEmpty() && notStart.peek().L == start.L) {

                    var tmp = notStart.poll();

                    //높이 저장
                    heights.merge(tmp.H, 1, Integer::sum);
                    tmp.flag = true;
                    notEnd.offer(tmp);
                }

                int newGuy;
                if ( (newGuy = heights.firstKey()) != nowHeights) {
                    nowHeights = newGuy;
                    sb.append(start.L).append(' ').append(newGuy).append(' ');
                }
            } else if ( start == null || end.R < start.L) {
                //어떤 빌딩의 오른쪽 끝
                while ( !notEnd.isEmpty() && notEnd.peek().R == end.R ) {
                    var tmp = notEnd.poll();

                    //저장되어있는 높이에서 넌 추방이다
                    heights.merge(tmp.H, 1, (oldV, newV) -> {
                        if ( oldV == 1 ) return null;
                        else return oldV - newV;
                    });
                }

                int newGuy;
                if ( (newGuy = heights.firstKey()) != nowHeights) {
                    nowHeights = newGuy;
                    sb.append(end.R).append(' ').append(newGuy).append(' ');
                }
            } else {
                //둘이 같음
                while ( !notEnd.isEmpty() && notEnd.peek().R == end.R ) {
                    var tmp = notEnd.poll();

                    //저장되어있는 높이에서 넌 추방이다
                    heights.merge(tmp.H, 1, (oldV, newV) -> {
                        if ( oldV == 1 ) return null;
                        else return oldV - newV;
                    });
                }

                while ( !notStart.isEmpty() && notStart.peek().L == start.L) {
                    var tmp = notStart.poll();

                    //높이 저장
                    heights.merge(tmp.H, 1, Integer::sum);
                    tmp.flag = true;
                    notEnd.offer(tmp);
                }

                int newGuy;
                if ( (newGuy = heights.firstKey()) != nowHeights) {
                    nowHeights = newGuy;
                    sb.append(start.L).append(' ').append(newGuy).append(' ');
                }
            }
        }

        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            int N = Integer.parseInt(br.readLine());

            notStart = new PriorityQueue<>();
            notEnd = new PriorityQueue<>();

            StringTokenizer st;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int l = Integer.parseInt(st.nextToken());
                int h = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());

                notStart.offer(new Building(l, h, r));
            }
        } catch( Exception e) {
            e.printStackTrace();
        }
    }
}