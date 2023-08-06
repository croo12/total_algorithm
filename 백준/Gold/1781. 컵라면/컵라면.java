import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) {
		try {
			new Main().solution();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void solution() throws IOException {
		var br = new BufferedReader(new InputStreamReader(System.in));
		//컵라면 개수별로 정렬
		var pq = new PriorityQueue<Pair>( new Comparator<Pair>() {
			@Override
			public int compare(Pair o1, Pair o2) {
				return Integer.compare(o2.value, o1.value);
			}
		});
		
		int N = Integer.parseInt(br.readLine());
		int[] schedule = new int[N+1];
		
		String[] tmp;
		int d, v;
		for (int i = 1; i <= N; i++) {
			tmp = br.readLine().split(" ");
			schedule[i] = i;
			d = Integer.parseInt(tmp[0]);
			v = Integer.parseInt(tmp[1]);
			
			pq.offer(new Pair(d, v));
		}
		
		//답
		int ans = 0;
		Pair now;
		int ex;
		while(!pq.isEmpty()) {
			now = pq.poll();
			ex = now.deadline;
			
			while( schedule[schedule[ex]] != schedule[ex] )
				schedule[ex]--;
				
			if(schedule[schedule[ex]] != 0) {
				schedule[schedule[ex]]--;
				ans += now.value;
			}
		}
		
		System.out.println(ans);
	}
	
	class Pair{
		int deadline, value;

		public Pair(int deadline, int value) {
			super();
			this.deadline = deadline;
			this.value = value;
		}
	}
}