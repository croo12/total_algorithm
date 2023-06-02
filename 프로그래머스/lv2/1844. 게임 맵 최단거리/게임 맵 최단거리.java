import java.util.*;
class Solution {
    int[] dx = {0,1,0,-1};
    int[] dy = {1,0,-1,0};
    
    public int solution(int[][] maps) {
        int answer = 0;
        
        int r = maps.length;
        int c = maps[0].length;
        
        boolean[][] vis = new boolean[r--][c--];
        
        Queue<Pair> q = new LinkedList<Pair>();
        q.offer(new Pair(0,0,1));
        vis[0][0] = true;
        
        while ( !q.isEmpty()) {
            Pair now = q.poll();
            
            if (now.y == r && now.x == c) {
                return now.count;
            }
            
            for (int i = 0; i < 4; i++) {
                int rr = now.y + dy[i];
                int rc = now.x + dx[i];
                
                if ( rr > r || rc > c 
                    || rr < 0 || rc < 0 
                    || vis[rr][rc] || maps[rr][rc] == 0 ) 
                    continue;
                
                vis[rr][rc] = true;
                q.offer(new Pair(rc, rr, now.count + 1));
            }
        }
        
        return -1;
    }
    
    class Pair {
        int x,y, count;
        Pair ( int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}