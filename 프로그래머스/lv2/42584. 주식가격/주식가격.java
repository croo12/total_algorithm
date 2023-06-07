class Solution {
    class Node {
        int idx;
        int value;
        Node prev;
        Node (int idx, int value, Node prev) {
            this.idx = idx;
            this.value = value;
            this.prev = prev;
        }
    }
    
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            answer[i] = -1;
        }
        
        Node last = null;
        
        for (int i = 0; i < prices.length; i++ ) {
            int now = prices[i];
            
            if ( last == null || now >= last.value) {
                last = new Node (i, now, last);
            
            }else {
                while ( last != null && now < last.value ) {
                    answer[last.idx] = i - last.idx;
                    last = last.prev;
                }
                
                last = new Node(i, now, last);
            }
        }
        
        for (int i = 0; i < answer.length; i++) {
            if ( answer[i] == -1) {
                answer[i] = answer.length - 1 - i;
            }
        }
        
        return answer;
    }
}