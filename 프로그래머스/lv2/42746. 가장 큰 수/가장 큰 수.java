import java.util.*;
class Solution {
    
    public String solution(int[] numbers) {
        
        var pq = new PriorityQueue<String>((o1, o2) -> bigger(o2, o1));
        
        for (var i : numbers) {
            pq.offer(Integer.toString(i));
        }
        
        var sb = new StringBuilder();
        
        while ( !pq.isEmpty()) {
            sb.append(pq.poll());
        }
        
        String answer = sb.toString();
        if (answer.charAt(0) == '0') return "0";
        return answer;
    }
    
    public int bigger(String now, String other) {
        int i = 0;
        for (; i < now.length() && i < other.length(); i++) { 
            char me = now.charAt(i);
            char origin = other.charAt(i);
            
            if ( origin > me ) {
                return -1;
            } else if ( me > origin ) {
                return 1;
            }
        }
        
        if ( now.length() > other.length()) {
            return bigger(now.substring(i), other);
        } else if ( now.length() < other.length()) {
            return bigger(now, other.substring(i));
        } else {
            return 0;
        }
    }
}