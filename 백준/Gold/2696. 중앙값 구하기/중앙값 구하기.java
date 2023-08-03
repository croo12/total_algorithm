import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

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
		int T = Integer.parseInt(br.readLine());
		
		//max
		var pqMax = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o2, o1);
			}
		});
		
		//min
		var pqMin = new PriorityQueue<Integer>();
		
		StringBuilder ans = new StringBuilder();
		StringTokenizer st;
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			
			ans.append((N+1)/2);
			
			int time = (N+9)/10;
			boolean isTen = true;
			
			while(time-- > 0) {
				if(isTen)
					ans.append('\n');
				
				st = new StringTokenizer(br.readLine(), " ");
				
				boolean isOdd = true;
				
				while(st.hasMoreTokens()) {
					int now = Integer.parseInt(st.nextToken());
					
					//넣기
					if(pqMax.isEmpty() || now <= pqMax.peek())
						pqMax.offer(now);
					else
						pqMin.offer(now);
					
					while(!pqMax.isEmpty() && pqMax.size() - 1 > pqMin.size())
						pqMin.offer(pqMax.poll());
					
					while(!pqMin.isEmpty() && pqMax.size() <= pqMin.size())
						pqMax.offer(pqMin.poll());
					
					if(isOdd)
						ans.append(pqMax.peek() + " ");
					
					isOdd = !isOdd;
				}
				
				isTen = !isTen;
			}
			
			pqMax.clear();
			pqMin.clear();
			
			ans.append('\n');
		}
		
		System.out.print(ans);
	}
}