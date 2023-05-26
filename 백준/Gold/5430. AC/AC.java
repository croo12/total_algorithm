import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			String p = br.readLine();
			int n = Integer.parseInt(br.readLine());
			String arr = br.readLine();
			ArrayDeque<Integer> dq = new ArrayDeque<>();
			boolean dir = true; // true : 정방향, false : 역방향
			boolean flag = true;
			// 문자열 분리 후 덱에 넣어준다
			StringTokenizer st = new StringTokenizer(arr, "[],");
			for (int i = 0; i < n; i++) {
				dq.offer(Integer.parseInt(st.nextToken()));
			}
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < p.length(); i++) {
				char ch = p.charAt(i);
				if (ch == 'R') {
					dir = !dir;
				} else {
					if (dq.isEmpty()) {
						sb.append("error");
						flag = false;
						break;
					}
					if (dir) {
						dq.pollFirst();
					} else {
						dq.pollLast();
					}
				}
			}
			if (flag) {
				sb.append("[");
				if (dq.size() > 0) {
					if (dir) {
						while (!dq.isEmpty()) {
							sb.append(dq.getFirst()).append(",");
							dq.pollFirst();
						}
					} else {
						while (!dq.isEmpty()) {
							sb.append(dq.getLast()).append(",");
							dq.pollLast();
						}
					}
					sb.delete(sb.length() - 1, sb.length());
				}
				sb.append("]");
				System.out.println(sb);
			}else {
				System.out.println(sb);
			}
		}
	}
}