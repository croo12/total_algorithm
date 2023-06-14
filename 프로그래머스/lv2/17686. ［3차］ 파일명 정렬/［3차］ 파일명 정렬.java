import java.util.*;
class Solution {
    int cntFile = 0;
    
    class FileName implements Comparable<FileName> {
        int id;
        String name;
        int number;
        String other;
        String entireName;
        
        public FileName(String _name, int _number, String _other,
                       String _entireName) {
            id = cntFile++;
            name = _name;
            number = _number;
            other = _other;
            entireName = _entireName;
        }
        
        @Override
        public int compareTo(FileName other) { 
            int nameResult = biggerThanOther(name, other.name);
            
            if ( nameResult == 0 ) {
                int numberResult = Integer.compare(number, other.number);
                
                if ( numberResult == 0 ) {
                    return Integer.compare(id, other.id);
                }
                
                return numberResult;
            }
            
            return nameResult;
        }
    }
    
    public String[] solution(String[] files) {
        
        var pq = new PriorityQueue<FileName>();
        
        for (var name : files) { 
            pq.offer(fileNameFactory(name));
        }
        
        String[] answer = new String[pq.size()];
        int cnt = 0;
        
        FileName now;
        while ( !pq.isEmpty()) {
            now = pq.poll();
            answer[cnt++] = now.entireName;
        }
        
        return answer;
    }
    
    FileName fileNameFactory(String filename) { 
        int idx = 0;
        int len = filename.length();
        
        var header = new StringBuilder();
        char c = filename.charAt(idx);
        while (c < '0' || c > '9') 
        {
            header.append(c);
            if ( idx >= len - 1) 
                return new FileName(header.toString(), -1, "", filename);
            
            c = filename.charAt(++idx);
        }
        
        int number = 0;
        while (c >= '0' && c <= '9') 
        {
            number += c - '0' + number * 10;
            if ( idx >= len - 1) 
                return new FileName(header.toString(), number, "", filename);
            
            c = filename.charAt(++idx);
        }
        
        var tail = new StringBuilder();
        
        while (true) {
            tail.append(c);
            
            if ( idx >= len - 1) {
                return new FileName(header.toString(), number, 
                                 tail.toString(), filename);   
            }
            
            c = filename.charAt(++idx);
        }
    }
    
    // -1 nope, 1 yes, 0 equal
    int biggerThanOther(String me, String other) {
        int myLength = me.length();
        int otherLength = other.length();
        
        for (int i = 0; i < myLength && i < otherLength; i++) {
            int a = charToInt(me.charAt(i));
            int b = charToInt(other.charAt(i));
            
            if ( a > b ) return 1;
            if ( b > a) return -1;   
        }
        
        if ( myLength > otherLength ) {
            return 1;
        }
        if ( myLength < otherLength) {
            return -1;
        }
        
        return 0;
    }
    
    int charToInt(char a) {
        if ( a >= 'A' && a <= 'Z') {
            a += 32;
        }
        return a;
    }
}