import java.util.*;
class Solution {
    int length = -1;
    String msg;

    Word root = new Word
            (0,
                    'A', 'B', 'C', 'D', 'E', 'F', 'G'
                    ,'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O'
                    ,'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W'
                    ,'X', 'Y', 'Z'
            );
    
    public int[] solution(String msg) {
        this.msg = msg;
        var ans = new ArrayList<Integer>();

        int strLength = msg.length();
        int[] idx = {0};
        while (idx[0] < strLength) {
            ans.add(search(idx, root));
        }
        
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
    
    int search(int[] idx, Word node) {
        if (idx[0] == msg.length()) return node.me;

        int now = msg.charAt(idx[0]) - 'A';

        if ( node.next[now] == null ) {
            node.addChild(now);
            return node.me;
        } else {
            idx[0]++;
            return search(idx, node.next[now]);
        }
    }
    
    class Word {
        int depth;
        int me;
        Word[] next = new Word[27];

        public Word(int depth, char ...child) {
            me = ++length;
            this.depth = depth;

            for (int word : child) {
                next[word - 'A'] = new Word(depth + 1);
            }
        }

        public void addChild(int child) {
            next[child] = new Word(depth + 1);
        }
    }
}