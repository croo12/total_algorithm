import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		try {
			new Main().solution();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		int[] count = new int[N+1];
		
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			
			count[left]++;
			count[right]++;
		}
		
		int cnt = 0;
		if(count[1] == 0) cnt++;
		for (int i = 2; i <= N; i++) {
			if(--count[i] == 0) cnt++;
		}
		
		//영묵이형의 말을 들어보니 물을 나눠줄 필요조차 없음
		
		System.out.println(W/(double)cnt);
	}
}