import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        new Main().solution();
    }
    final int[][] endCondition = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    LinkedList<String> ttts;

    private void solution() {
        input();

        var sb = new StringBuilder();
        while (!ttts.isEmpty()) {
            String now = ttts.poll();
            if (check(now.toCharArray())) {
                sb.append("valid").append('\n');
            } else {
                sb.append("invalid").append('\n');
            }
        }

        System.out.print(sb);
    }

    private boolean check(char[] now) {
        int xCnt = 0;
        int oCnt = 0;

        for (int i = 0; i < 9; i++) {
            char c = now[i];
            if (c == 'X') {
                xCnt++;
            } else if (c == 'O') {
                oCnt++;
            }
        }

        if (xCnt == oCnt) {
            return isEnd(now, 'O', false);
        } else if (xCnt == oCnt + 1) {
            return isEnd(now, 'X', xCnt + oCnt == 9);
        }

        return false;
    }

    boolean isEnd(char[] now, char answer, boolean isFull) {
        boolean answerIsO = answer == 'O';

        boolean mustWin = isWin(now, answerIsO);
        boolean neverWin = isWin(now, !answerIsO);
        if ( neverWin ) {
//            System.out.println("이기면 안되는 놈이 이김");
            return false;
        } else if ( mustWin ) {
            //하나씩 지워서 지워도 이기는 상황인지 체크
            for (int i = 0; i < 9; i++) {
                boolean flag = false;

                if ( now[i] == answer ) {
                    now[i] = '.';

                    if (!isWin(now, answerIsO)) {
                        flag = true;
                    }

                    now[i] = answer;
                }

                if (flag) {
                    return true;
                }
            }

            //이미 돌 놓기전에 이겨있음
            return false;
        } else {
            return isFull;
        }
    }

    boolean isWin(char[] now, boolean bool) {
        char c = bool ? 'O' : 'X';

        for (int[] is : endCondition) {
            boolean flag = true;
            for (var i : is) {
                if (now[i] != c) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                return true;
            }
        }

        return false;
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            ttts = new LinkedList<>();
            for (var now = br.readLine(); !now.equals("end"); now = br.readLine())
                ttts.addLast(now);

        } catch( Exception e) {
            e.printStackTrace();
        }
    }
}