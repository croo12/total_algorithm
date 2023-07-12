import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Main().solution();
    }
    int row, col;
    int[][] map;
    int[] endPoint;
    boolean[][] vis;

    final int[] dRow = {1,-1,0,0};
    final int[] dCol = {0,0,1,-1};

    static class Node {
        int row,col,count;

        public Node(int row, int col, int count) {
            this.row = row;
            this.col = col;
            this.count = count;
        }
    }

    private void solution() {
        input();
        var q = new LinkedList<Node>();
        q.offer(new Node(endPoint[0], endPoint[1], 0));
        vis[endPoint[0]][endPoint[1]] = true;

        Node now;
        while(!q.isEmpty()) {
            now = q.poll();
            map[now.row][now.col] = now.count;

            for (int i = 0; i < 4; i++) {
                int r = now.row + dRow[i];
                int c = now.col + dCol[i];

                if ( r >= row || r < 0 || c < 0 || c >= col || vis[r][c] ) continue;

                q.offer(new Node(r, c, now.count+1));
                vis[r][c] = true;
            }
        }

        var sb = new StringBuilder();

        for (var r : map) {
            for ( var c : r) {
                sb.append(c).append(' ');
            }
            sb.setLength(sb.length() - 1);
            sb.append('\n');
        }

        System.out.print(sb);
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            row = Integer.parseInt(st.nextToken());
            col = Integer.parseInt(st.nextToken());

            map = new int[row][col];
            vis = new boolean[row][col];
            endPoint = new int[2];

            for (int i = 0; i < row; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < col; j++) {
                    switch (Integer.parseInt(st.nextToken())) {
                        case 0 -> {
                            vis[i][j] = true;
                            map[i][j] = 0;
                        }
                        case 1 -> map[i][j] = -1;
                        case 2 -> {
                            map[i][j] = -1;
                            endPoint[0] = i;
                            endPoint[1] = j;
                        }
                    }
                }
            }

        } catch( Exception e) {
            e.printStackTrace();
        }
    }
}