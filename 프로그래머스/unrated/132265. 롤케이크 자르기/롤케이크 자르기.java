class Solution {
    int[] memo;
    int[] total;
    int[] top;
    int len;
    int cnt;
    int answer;
    
    public int solution(int[] topping) {
        len = topping.length;
        top = topping;
        
        memo = new int[10_001];
        total = new int[10_001];
        
        cnt = 0;
        
        for (var i : topping) {
            if ( total[i] == 0) cnt++;
            total[i]++;
        }
        
        int number = 0;
        
        for ( int idx = 0; idx < len; idx++) {

            int now = top[idx];
            memo[now]++;
            total[now]--;
            if ( memo[now] == 1 ) {
                number += 1;
            }
            if ( total[now] == 0 ) {
                cnt--;
            }

            if ( number == cnt) answer++;
        }
        
        return answer;
    }
    
    void check(int idx, int number) {
        
        
        check(idx + 1, number);
    }
}