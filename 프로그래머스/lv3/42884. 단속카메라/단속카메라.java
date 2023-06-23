import java.util.*;
class Solution {
    class Car implements Comparable<Car> {
        int start, end;
        public Car (int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        @Override
        public int compareTo(Car o) {
            int now = Integer.compare(this.start, o.start);
            
            if ( now == 0) {
                return Integer.compare(this.end, o.end);
            }
            
            return now;
        }
    }
    
    public int solution(int[][] routes) {
        var pq = new PriorityQueue<Car>();
        
        for (int[] me : routes) {
            pq.offer(new Car(me[0], me[1]));
        }
        
        int answer = 0;
        Car now;
        int limit = -30_001;
        while (!pq.isEmpty()) {
            now = pq.poll();
            
            if ( now.start > limit) {
                answer++;
                limit = now.end;
            } else if ( now.end < limit ) {
                limit = now.end;
            }
        }
        
        return answer;
    }
}