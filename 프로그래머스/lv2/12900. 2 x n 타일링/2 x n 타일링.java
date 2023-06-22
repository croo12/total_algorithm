class Solution {
    int[] memo;
    final int INF = 1_000_000_007;
    
    public int solution(int n) {
        memo = new int[n+1];
        
        return find(n);
    }
    
    private int find(int n) {
        if (n == 0) return 1;
        if (n < 0) return 0;
        
        if (memo[n] != 0) return memo[n];
        
        int sum = find(n-1);
        sum += find(n-2);
        
        return memo[n] = sum % INF;
    }
}