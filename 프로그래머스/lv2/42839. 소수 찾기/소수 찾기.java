import java.util.*;

class Solution {
    int[] numbers;
    int answer;
    
    public int solution(String numbers) {
        this.numbers = new int[10];
        
        for (var number : numbers.toCharArray()) {
            this.numbers[number - '0']++;
        }
        
        answer = 0;
        
        findNumber(0);
        
        return answer;
    }
    
    void findNumber (int sum) {
        
        if (isPrime(sum)) {
            answer++;
        }
        
        int idx = sum == 0 ? 1 : 0;
        for (; idx < 10; idx++) {
            if ( numbers[idx] != 0) {
                numbers[idx]--;
                findNumber(sum * 10 + idx);
                numbers[idx]++;
            }
        }
    }
    
    boolean isPrime (int n) {
        if ( n == 2 ) return true;
        if ( n < 2 || n % 2 == 0 ) return false;
        
        double max = Math.sqrt(n);
        
        for ( int i = 3; i <= max; i+=2 ) {
            if ( n % i == 0) return false;
        }
        
        return true;
    }
}