class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        int idx = 0;
        for ( var number : numbers) {
            answer[idx++] = find(number);
        }
        
        return answer;
    }
    
    private long find(long number) {
        long checker = 1L;
        
        while ( checker <= number) {
            if ( (checker & number) == 0) {
                return number + checker - ( checker >> 1 );
            }
            
            checker <<= 1;
        }
        
        return number + checker - (checker >> 1);
    }
}