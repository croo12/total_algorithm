class Solution {
    int bottom;
    int[][] land;
    int[][] vis;
    
    int solution(int[][] land) {
        this.bottom = land.length;
        this.land = land;
        
        this.vis = new int[bottom][land[0].length];
        
        int ans = dfs(0, -1);

        return ans;
    }
    
    int dfs ( int row, int idx ) {
        if ( row == bottom ) {
            return 0;
        }
        
        int max = 0;
        
        for ( int i = 0; i < land[row].length; i++) {
            if ( idx == i ) continue;
            
            if ( vis [row][i] == 0 ) {
                vis[row][i] = dfs(row + 1, i) + land[row][i];
            }
            
            max = Math.max ( max, vis[row][i]);
        }
        
        return max;
    }
}