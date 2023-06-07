import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        Queue<String> q = new LinkedList<String>();
        boolean[] vis = new boolean[words.length];
        q.add(begin);
        int cnt = 0;
        while ( !q.isEmpty()) {
            int length = q.size();
            cnt++;
            
            while (--length >= 0) {
                String now = q.poll();
                
                if (now.equals(target))
                    return --cnt;
                
                for (int i = 0; i < words.length; i++) {
                    if ( !vis[i] && movable(now, words[i]) ) {
                        vis[i] = true;
                        q.offer(words[i]);
                    }
                }
            }
        }
        
        return 0;
    }
    
    boolean movable(String s1, String s2) {
        boolean flag = false;
        for ( int i = 0; i < s1.length() && i < s2.length(); i++) {
            
            if ( s1.charAt(i) != s2.charAt(i)) {
                if ( flag == true ) 
                    return false;
                else {
                    flag = true;
                }
            }
        }
        
        return flag;
    }
}