import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    int R, C;
    boolean[][] map;
    boolean[][] vis;
    int[][] vector = {
        {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    int[] end;

    Queue<int[]> q = new LinkedList<>();
    Queue<int[]> qNext = new LinkedList<>();
    Queue<int[]> w = new LinkedList<>();
    Queue<int[]> wNext = new LinkedList<>();


    private void solution() {
        input();
        int cnt = 0;
        while (!bfs()) {
            updateMap();
            cnt++;
        }
        System.out.println(cnt);
    }

    boolean bfs() {

        int[] now;
        while (!q.isEmpty()) {
            now = q.poll();

            if (now[0] == end[0] && now[1] == end[1]) return true;

            for (int i = 0; i < 4; i++) {
                int rr = now[0] + vector[i][0];
                int rc = now[1] + vector[i][1];

                if (rr >= R || rr < 0 || rc >= C || rc < 0 || vis[rr][rc]) continue;

                vis[rr][rc] = true;
                if (map[rr][rc]) {
                    qNext.offer(new int[]{rr, rc});
                } else {
                    q.offer(new int[]{rr, rc});
                }
            }
        }

        while (!qNext.isEmpty()) q.offer(qNext.poll());
        return false;
    }

    void updateMap() {
        int[] now;
        while(!w.isEmpty()) {
            now = w.poll();

            for (int i = 0; i < 4; i++) {
                int rr = now[0] + vector[i][0];
                int rc = now[1] + vector[i][1];

                if (rr >= R || rr < 0 || rc >= C || rc < 0 || !map[rr][rc]) continue;

                map[rr][rc] = false;
                wNext.offer(new int[]{rr, rc});
            }
        }

        while(!wNext.isEmpty()) w.offer(wNext.poll());
    }

    void input() {
        try {
            var br = new BufferedReader(new InputStreamReader(System.in));
            var st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new boolean[R][C];
            vis = new boolean[R][C];

            String s;
            for (int i = 0; i < R; i++) {
                s = br.readLine();
                for (int j = 0; j < C; j++) {
                    char c = s.charAt(j);

                    if (c == 'X')
                        map[i][j] = true;
                    else if (c == 'L') {
                        if (end == null)
                            end = new int[]{i, j};
                        else{
                            q.offer(new int[]{i, j});
                            vis[i][j] = true;
                        }
                        w.offer(new int[]{i, j});
                    } else {
                        w.offer(new int[]{i, j});
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