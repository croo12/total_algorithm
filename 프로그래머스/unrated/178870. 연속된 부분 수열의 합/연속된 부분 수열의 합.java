class Solution {
    public int[] solution(int[] sequence, int k) {
        int len = sequence.length;
        
        int sum = sequence[0];
        int startIdx = 0;
        int endIdx = 0;
        
        int min = len + 2;
        
        int[] answer = new int[2];
        if ( sum == k) {
            return answer;
        }
        for (int i = 1; i < len; i++) {
            sum += sequence[i];
            endIdx++;
            
            while ( startIdx < endIdx && sum > k ) {
                sum -= sequence[startIdx++];
            }
            
            if( sum == k && endIdx - startIdx < min) {
                answer[0] = startIdx;
                answer[1] = endIdx;
                min = endIdx - startIdx;
            }
        }
        
        return answer;
    }
}