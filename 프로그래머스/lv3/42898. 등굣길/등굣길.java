class Solution {
    final int MOD = 1_000_000_007;
    
    boolean[][] map;
    int[][] memo;
    
    int ROW,COL;
    
    public int solution(int m, int n, int[][] puddles) {
        ROW = n;
        COL = m;
        
        map = new boolean[n+1][m+1];
        memo = new int[n+1][m+1];
        
        for (var line : memo) {
            for (int i = 1; i <= m; i++) {
                line[i] = -1;
            }
        }
        
        for (var pair : puddles) {
            map[pair[1]][pair[0]] = true;
        }
        
        return findWay(1,1);
    }
    
    int findWay(int row, int col) {
        if ( row > ROW || col > COL || map[row][col]) return 0;
        
        if ( row == ROW && col == COL ) return 1;
        
        if (memo[row][col] != -1 ) return memo[row][col];
        
        int now = findWay(row+1, col);
        now += findWay(row, col+1);
        
        return memo[row][col] = now % MOD;
    }
}