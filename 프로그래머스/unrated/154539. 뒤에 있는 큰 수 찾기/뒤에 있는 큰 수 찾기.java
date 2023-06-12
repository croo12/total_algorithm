import java.util.*;

class Solution {
    class No {
        int value, idx;
        public No ( int value, int idx) {
            this.value = value;
            this.idx = idx;
        }
    }
    
    public int[] solution(int[] numbers) {
        var stk = new Stack<No>();
        
        var len = numbers.length;
        var answer = new int[len];
        
        for ( int i = 0; i < len; i++) {
            answer[i] = -1;
        }
        
        int idx = 0;
        for ( var number : numbers) {
            while ( !stk.isEmpty() && stk.peek().value < number ) {
                var now = stk.pop();
                answer[now.idx] = number;
            }
            
            stk.push(new No(number, idx++));
        }
        
        return answer;
    }
}