import java.util.*;
class Solution {
    public int solution(int x, int y, int n) {
        int answer = 0;
        
        var q = new LinkedList<Integer>();
        q.offer(x);
        
        boolean[] vis = new boolean[y + 1];
        vis[x] = true;
        
        while( !q.isEmpty()) {
            int qSize = q.size();

            for ( int i = 0; i < qSize; i++) {
                int now = q.poll();
            
                if ( now == y) {
                    return answer;
                }
                
                for ( int k = 0; k < 3; k++) {
                    int tmp = now;
                    
                    switch (k) {
                        case 0:
                            tmp = now * 2;
                            break;
                        case 1:
                            tmp = now * 3;
                            break;
                        case 2:
                            tmp = now + n;
                            break;
                    }        
                    
                    if ( tmp > y || vis[tmp]) continue;

                    vis[tmp] = true;
                    q.offer(tmp);
                }    
            }
            
            answer++;
        }
        
        return -1;
    }
}