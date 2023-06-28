class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int idx = 0;
        
        int start = 0;
        
        int value = 2 * w + 1;
        
        int end = 0;
        for ( var station : stations ) {
            end = station - w - 1;
            int need = (end - start + value - 1) / value;
            
            answer += need;
            start = station + w;
        }
        
        answer += (n - start + value - 1) / value;

        return answer;
    }
}