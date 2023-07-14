import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashMap<String, Integer> map = new HashMap<>();
		PriorityQueue<String> pq = new PriorityQueue<>();
		
		int cnt = 0;
		String now;
		while((now = br.readLine()) != null) {
			cnt++;
			
			if(map.containsKey(now)) {
				map.put(now, map.get(now) + 1);
			}else{
				map.put(now, 1);
				pq.offer(now);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		while(!pq.isEmpty()) {
			now = pq.poll();
			
			sb.append(now).append(' ')
			  .append(String.format("%.4f", (map.get(now)/(double)cnt) * 100))
			  .append('\n');
		}
		
		System.out.print(sb);
	}
}