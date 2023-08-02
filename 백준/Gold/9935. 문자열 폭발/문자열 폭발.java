	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.util.ArrayDeque;
	import java.util.HashMap;
	
	public class Main {
		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String origin = br.readLine();
			String word = br.readLine();
			HashMap<Character, Integer> map = new HashMap<>();
	
			int wordLength = word.length();
			for (int i = 0; i < wordLength; i++) {
				map.put(word.charAt(i), i);
			}
			
			//어느 순서로 폭발시키던 상관 없으니 앞부터 하면 순서 정리 가능
			
			//내가 폭발 문자열에 없는 경우. -> 그 앞의 요소들은 영원히 폭발 불가능임
			//내가 0번 순서인 경우 넣음
			//내가 0번이 아닌데 앞에 있는 녀석이 내 번호 - 1이 아닌경우
			//-> 내 앞은 스스로 폭발할 수 없고 나도 스스로 폭발할 수 없으니 영원한 교착상태가 됨
			
			int originLength = origin.length();
			char[] ans = new char[originLength];
			
			ArrayDeque<Character> deque = new ArrayDeque<>();
			
			char now;
			int index;
			int idx = 0;
			for (int i = 0; i < originLength; i++) {
				now = origin.charAt(i);
				
				if( map.containsKey(now) ) {
					index = map.get(now);
					if(index == wordLength - 1 && (wordLength == 1 || (!deque.isEmpty() && map.get(deque.peekLast()) == index - 1))) {
						for (int j = 0; j < wordLength - 1; j++) {
							deque.removeLast();
						}
					}else if(index == 0 || (!deque.isEmpty() && (index - 1 == map.get(deque.peekLast())))) {
						deque.addLast(now);
					}else{
						while(!deque.isEmpty())
							ans[idx++] = deque.removeFirst();
						
						ans[idx++] = now;
					}
					
				}else{
					while(!deque.isEmpty())
						ans[idx++] = deque.removeFirst();
					
					ans[idx++] = now;
				}
			}
			
			while(!deque.isEmpty())
				ans[idx++] = deque.removeFirst();
			
			if(idx == 0)
				System.out.println("FRULA");
			else {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < idx; i++) {
					sb.append(ans[i]);
				}
				
				System.out.println(sb);
			}
		}
	}