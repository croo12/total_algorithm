import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static boolean[][] map;
	static boolean[] check;
	static int[] dr = {0,0,-1,1};
	static int[] dc = {1,-1,0,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new boolean[5][5];
		String s;
		for (int i = 0; i < 5; i++) {
			s = br.readLine();
			for (int j = 0; j < 5; j++) {
				map[i][j] = (s.charAt(j) == 'S');
			}
		}
		
		//7칸이동할거임 -> 근데 S가 4칸 이상이어야함
		//그 모든 경우의 수
		//완전탐색하면 나이브하게 25 * 4^6 < 2^17 = 262144 가짓수?
		
		//문제는 중복제거임 
		//25칸이니 0~24번까지로 해서 비트마스킹으로 체크 가능
		
		//하지만 dfs로는 탐색을 돌 수 없다는 사실이 증명되었다...
		//dfs의 이동 방향이 인접배열 전체여야 함
		//대신 한번 지나간 칸은 다시 들를 필요가 없다
		
		check = new boolean[1<<25];
		cnt = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				unite(i, j, 1, (map[i][j] ? 1 : 0) , 1<<(5*i + j), 0);
			}
		}
		
		System.out.println(cnt);
	}
	
	static int cnt;
	
	static void unite(int r, int c, int depth, int sel, int vis, int adj) {
		if(7 - depth < 4 - sel || check[vis])
			return;

		check[vis] = true;
		
		if(depth == 7) {
			cnt++;
			return;
		}
		
		int rr, rc, chk;
		for (int k = 0; k < 4; k++) {
			rr = r + dr[k];
			rc = c + dc[k];
			
			chk = 1 << (rr * 5 + rc);
			if(rr >= 5 || rr < 0 || rc >= 5 || rc < 0 || (vis & chk) != 0 || (adj & chk) != 0)
				continue;

			adj += chk;
		}
		
		for (int i = 0; i < 25; i++) {
			chk = 1 << i;
			if((vis & chk) == 0 && (adj & chk) != 0) {
				unite(i/5, i%5, depth + 1, map[i/5][i%5] ? (sel+1) : sel , vis + chk, adj - chk);
			}
		}
	}
}
