import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    int W, H;
    int[][] map;
    int rowC = -1, colC = -1;
    int endR, endC;
    int[] dRow = {1, -1, 0, 0};
    int[] dCol = {0, 0, -1, 1};
    static class Laser {
        int row, col, mirror, dir;

        public Laser(int row, int col, int mirror, int dir) {
            this.row = row;
            this.col = col;
            this.mirror = mirror;
            this.dir = dir;
        }
    }

    private void solution() {
        input();

        int[][][] vis = new int[H][W][4];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                Arrays.fill(vis[i][j], 987_654_321);
            }
        }

        var q = new LinkedList<Laser>();
        Arrays.fill(vis[rowC][colC], 0);

        for (int i = 0; i < 4; i++) {
            int row = rowC + dRow[i];
            int col = colC + dCol[i];

            if ( row >= H || row < 0 || col >= W || col < 0 || map[row][col] == '*') continue;

            vis[row][col][i] = 0;
            q.offer(new Laser(row, col, 0, i));
        }

        Laser now;
        while (!q.isEmpty()) {
            now = q.poll();

            for (int i = 0; i < 4; i++) {
                int row = now.row + dRow[i];
                int col = now.col + dCol[i];

                int mirror = now.dir != i ? now.mirror + 1 : now.mirror;

                if ( row >= H || row < 0 || col >= W || col < 0 || map[row][col] == '*' || vis[row][col][i] <= mirror) continue;

                vis[row][col][i] = mirror;
                q.offer(new Laser(row, col, mirror, i));
            }
        }

        int min = 987_654_321;
        for (var i : vis[endR][endC]) {
            min = Math.min(min, i);
        }

        System.out.println(min);
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            map = new int[H][W];

            String s;
            for (int i = 0; i < H; i++) {
                s = br.readLine();
                for (int j = 0; j < W; j++) {
                    map[i][j] = s.charAt(j);
                    if (map[i][j] == 'C') {
                        if (rowC == -1) {
                            rowC = i;
                            colC = j;
                        } else {
                            endR = i;
                            endC = j;
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}