import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken());

		int[][] arr = new int[R][C];
		boolean[][][] vis = new boolean[R][C][64];
		int startR = -1;
		int startC = -1;
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				arr[i][j] = s.charAt(j);
				if (arr[i][j] == '0') {
					arr[i][j] = '.';
					startR = i;
					startC = j;
				}
			}
		}

		int[] dr = { 0, 0, 1, -1 };
		int[] dc = { 1, -1, 0, 0 };

		Queue<Minsik> q = new ArrayDeque<>();
		q.offer(new Minsik(startR, startC, 0 , 0));
		vis[startR][startC][0] = true;

		while (!q.isEmpty()) {
			Minsik now = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int key;
				int rr = now.r + dr[i];
				int rc = now.c + dc[i];

				if (rr < 0 || rc < 0 || rr >= R || rc >= C)
					continue;
				
				if (arr[rr][rc] == '1') {
					System.out.println(now.num+1);
					return;
				}
				else if (arr[rr][rc] == '#')
					continue;
				else if (arr[rr][rc] == '.' && !vis[rr][rc][now.hasKey]) {
					vis[rr][rc][now.hasKey] = true;
					q.offer(new Minsik(rr, rc, now.hasKey ,now.num + 1));
				}
				else if(arr[rr][rc] >= 'A' && arr[rr][rc] <= 'F' && !vis[rr][rc][now.hasKey]) {
					if((now.hasKey & (1 << (arr[rr][rc] - 'A'))) != 0) {
						vis[rr][rc][now.hasKey] = true;
						q.offer(new Minsik(rr,rc,now.hasKey,now.num + 1));
					}
				}
				else if (arr[rr][rc] >= 'a' && !vis[rr][rc][(key = ((now.hasKey & (1 << (arr[rr][rc] - 'a'))) != 0) ? now.hasKey : (now.hasKey + (1 << arr[rr][rc] - 'a')))]) {
					Minsik clone = new Minsik(rr, rc, key, now.num + 1);
					vis[rr][rc][key] = true;
					q.offer(clone);
				}
			}
			
		}

		System.out.println(-1);
	}

	static class Minsik {
		int r, c;
		int num;
		int hasKey; // 6개까지

		Minsik(int r, int c, int hasKey, int num) {
			this.r = r;
			this.c = c;
			this.hasKey = hasKey;
			this.num = num;
		}
	}
}
