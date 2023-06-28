class Solution {
    int[][] arr;
    int[] answer;
    int len;
    
    int cnt = 0;
    public int[] solution(int[][] arr) {
        answer = new int[2];
        len = arr.length;
        this.arr = arr;
        
        zip(0, 0, len, len);
        
        return answer;
    }
    
    void zip (int rowStart, int colStart, int rowEnd, int colEnd) {
        int rowMid = (rowStart + rowEnd) / 2;
        int colMid = (colStart + colEnd) / 2;
        
        int now = arr[rowStart][colStart];
        for ( int i = rowStart; i < rowEnd; i++) {
            for ( int j = colStart; j < colEnd; j++) {
                if ( now != arr[i][j] ) {
                    zip(rowStart, colStart, rowMid, colMid);
                    zip(rowStart, colMid, rowMid, colEnd);
                    zip(rowMid, colStart, rowEnd, colMid);
                    zip(rowMid, colMid, rowEnd, colEnd);
                    
                    return;
                }
            }
        }
        
        answer[now]++;
    }
}