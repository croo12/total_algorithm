import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    char[] literal;
    char[] stk = new char[100];
    int rear = -1;

    private void solution() {
        input();

        System.out.println(parse(0, literal.length, rear));
    }

    String parse(int start, int end, int startRear) {
        var sb = new StringBuilder();

        var stocked = false;
        var flag = false;

        char c;
        for (int i = start; i < end; i++) {
            c = literal[i];

            switch (c) {
                case '/', '*' -> {
                    flag = true;
                    stk[++rear] = c;
                }
                case '+', '-' -> {
                    if (stocked) {
                        sb.append(stk[rear--]);
                    }
                    stocked = true;
                    stk[++rear] = c;
                }
                case '(' -> {
                    int startPoint = i;
                    int cnt = 1;
                    while (cnt != 0) {
                        i++;

                        if (literal[i] == '(') cnt++;
                        else if (literal[i] == ')') cnt--;
                    }
                    sb.append(parse(startPoint + 1, i, rear));
                    if (flag) {
                        flag = false;
                        sb.append(stk[rear--]);
                    }
                }
                default -> {
                    sb.append(c);
                    if (flag) {
                        flag = false;
                        sb.append(stk[rear--]);
                    }
                }
            }
        }

        while (rear != startRear) sb.append(stk[rear--]);
        return sb.toString();
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            literal = br.readLine().toCharArray();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}