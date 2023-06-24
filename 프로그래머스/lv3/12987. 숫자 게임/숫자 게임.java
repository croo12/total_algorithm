import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        int len = A.length;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        int cnt = 0;
        for (int idx = 0; idx < len && cnt < len; idx++) {
            
            while ( idx < len && B[idx] <= A[cnt]) {
                idx++;
            }
            
            if ( idx < len && B[idx] > A[cnt] ) {
                answer++;
                cnt++;
            }
        }
        
        return answer;
    }
}