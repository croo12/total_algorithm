import java.util.*;
class Solution {
    
    public String[] solution(String[] record) {
        
        var map = new HashMap<String, String>();
        var list = new ArrayList<String[]>();
        
        for (String line : record) {
            String[] s = line.split(" ");
            
            System.out.println(s.length);
            
            
            switch (s[0]) {
                case "Enter" :
                    map.put(s[1], s[2]);
                    list.add(s);
                    break;
                case "Leave" :
                    list.add(s);
                    break;
                default:
                    map.put(s[1], s[2]);
            }
        }
        
        String[] answer = new String[list.size()];
        String[] val;       
        for (int i = 0; i < answer.length; i++) {
            val = list.get(i);
            if ( val[0].equals("Enter") ){
                answer[i] = map.get(val[1]) + "님이 들어왔습니다.";             
            } else {
                answer[i] = map.get(val[1]) + "님이 나갔습니다.";
            }
        }
        
        return answer;
    }
}