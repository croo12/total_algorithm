import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    int R, C;
    char[][] map;
    int N;
    int[] order;
    Node[] memo;

    class Node {
        int row, col;
        Node prev;

        public Node(int row, int col, Node prev) {
            this.row = row;
            this.col = col;
            this.prev = prev;
        }
    }

    private void solution() {
        input();

        memo = new Node[C];
        for (int i = 0; i < C; i++) {
            memo[i] = new Node(0, i, null);
        }

        for (var i : order) {
            throwStone(i);
        }

        var sb = new StringBuilder();

        for(var line : map) {
            for(var point : line) {
                sb.append(point);
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }

    void throwStone(int i) {
        Node now = memo[i];
        while (map[now.row][now.col] != '.') {
            now = now.prev;
        }

        memo[i] = drop(now);
    }

    Node drop (Node now) {
        if (now.row == R - 1 || map[now.row + 1][now.col] == 'X') {
            map[now.row][now.col] = 'O';
            return now;
        }

        if (map[now.row + 1][now.col] == '.') {
            return drop(new Node(now.row + 1, now.col, now));
        }

        if (map[now.row + 1][now.col] == 'O') {
            if (now.col != 0 && map[now.row + 1][now.col - 1] == '.' && map[now.row][now.col - 1] == '.') {
                return drop(new Node(now.row + 1, now.col - 1, now));
            }
            else if (now.col != C - 1 && map[now.row + 1][now.col + 1] == '.' && map[now.row][now.col + 1] == '.') {
                return drop(new Node(now.row + 1, now.col + 1, now));
            }
        }

        map[now.row][now.col] = 'O';
        return now;
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new char[R][C];

            String s;
            for (int i = 0; i < R; i++) {
                s = br.readLine();
                for (int j = 0; j < C; j++) {
                    map[i][j] = s.charAt(j);
                }
            }

            N = Integer.parseInt(br.readLine());
            order = new int[N];

            for (int i = 0; i < N; i++) {
                order[i] = Integer.parseInt(br.readLine()) - 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}