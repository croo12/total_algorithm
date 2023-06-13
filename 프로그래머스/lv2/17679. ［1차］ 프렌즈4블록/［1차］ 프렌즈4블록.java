class Solution {
    int ROW;
    int COL;
    char[][] map;    
    
    public int solution(int m, int n, String[] board) {
        ROW = m;
        COL = n;
        map = new char[ROW][COL];
        
        for (int i = 0; i < ROW; i++) {
            map[i] = board[i].toCharArray();
        }
        
        int ans = 0;
        int beforeAns = -1;
        boolean[][] vis = new boolean[m][n];
        
        while ( ans != beforeAns) {
            beforeAns = ans;
            
            for ( int i = 0; i < m; i++ ) {
                for ( int j = 0; j < n; j++) {
                    if ( check(i, j) ) {
                        vis[i][j] = true;
                        vis[i+1][j] = true;
                        vis[i][j+1] = true;
                        vis[i+1][j+1] = true;
                    }   
                }
            }
            
            for ( int i = 0; i < m; i++ ) {
                for (int j = 0; j < n; j++) {
                    if (vis[i][j]) {
                        ans++;
                        vis[i][j] = false;
                        
                        int now = i;
                        while (now > 0) {
                            map[now][j] = map[now-1][j];
                            map[now-1][j] = '\0';
                            now--;
                        }
                    }
                }
            }
        }
        
        return ans;
    }
    
    public boolean check (int row, int col) {
        if ( row + 1 >= ROW || col + 1 >= COL || map[row][col] == '\0') 
            return false;
        
        return map[row][col] == map[row+1][col] 
            && map[row][col] == map[row][col+1]
            && map[row][col] == map[row+1][col+1];
    }
}