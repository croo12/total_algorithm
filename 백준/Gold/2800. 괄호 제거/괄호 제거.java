import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Stack;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        new Main().solution();
    }
    static class CharacterNode {
        CharacterNode next;
        CharacterNode endPoint;

        boolean isWork = true;
        char value;

        public CharacterNode ( char value, CharacterNode next )
        {
            this.value = value;
            this.next = next;
        }

        public CharacterNode ( char value, CharacterNode next, CharacterNode endPoint)
        {
            this.value = value;
            this.next = next;
            this.endPoint = endPoint;
        }
    }

    String line;
    CharacterNode first;
    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            line = br.readLine();
        } catch( Exception e) {
            e.printStackTrace();
        }
    }

    TreeSet<String> answer = new TreeSet<>();

    private void dfs ( CharacterNode now, StringBuilder sb, boolean flag ) {
        if ( now == null ) {
            if ( !flag) return;

            answer.add(sb.toString());
            return;
        }

        if (!now.isWork) {
            dfs( now.next, sb, flag);
        } else {
            sb.append(now.value);
            dfs (now.next, sb, flag);
            sb.setLength(sb.length() -1);

            if (now.value == '(') {
                now.endPoint.isWork = false;
                dfs( now.next, sb, true);
                now.endPoint.isWork = true;
            }
        }
    }

    private void solution() {
        input();
        CharacterNode last = null;
        var ph = new Stack<CharacterNode>();
        char now;
        for (int i = line.length() - 1; i >= 0; i--) {
            now = line.charAt(i);

            if ( now == ')')
            {
                last = new CharacterNode(now, last);
                ph.push(last);
            }
            else if ( now == '(' )
            {
                last = new CharacterNode(now, last, ph.pop());
            }
            else
            {
                last = new CharacterNode(now, last);
            }
        }

        this.first = last;

        dfs (first, new StringBuilder(), false);

        var iter = answer.iterator();
        var sb = new StringBuilder();
        while ( iter.hasNext()) {
            sb.append(iter.next());
            sb.append('\n');
        }

        System.out.print(sb);
    }
}
