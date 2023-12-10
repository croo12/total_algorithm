import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static int N;
	static int max;
	
	static int[] operatorsAndNumbers;
	static boolean[] check;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		operatorsAndNumbers = new int[N];
		check = new boolean[N];
		for (int i = 0; i < N; i++) {
			operatorsAndNumbers[i] = ((i%2 != 0) ? br.read() : (br.read()-'0'));
		}
		//N 길이의 수식에는 N/2 + 1 개의 숫자와 N/2개의 연산이 있음
		//N의 길이가 19까지, 연산자의 최대 개수는 9개임
		//나이브하게 9! = 362880개니까 0.5초안에 가능할듯 실제로는 더 적을 것이다.
		
		// 괄호로 묶은건 정규연산 전에 처리되며 괄호는 겹칠 수 없다.
		// -> 괄호로 묶은 숫자 둘과 연산자는 check처리를 해서 다시 선택하지 않아야함
		
		// 그러면 마지막 결과처리에서 체크한 부분은 미리 계산 -> 그 후 남은 값들 계산
		// 순서로 최종 값을 만든 다음  max와 비교해서 답을 도출 할 수 있을 것 같다
		
//		System.out.println(Arrays.toString(operatorsAndNumbers));
		
		max = Integer.MIN_VALUE;
		comb(0, 0);
		System.out.println(max);
	}
	
	static void comb(int n, int cnt) {
		if( n == N/2) {
			int len = N-cnt*2;
			int[] last = new int[len];
			int idx = -1;
			for (int i = 0; i < N; i++) {
				idx++;
				if(check[i]) {
					last[idx] = calc(i, operatorsAndNumbers);
					i = i+2;
				}else {
					last[idx] = operatorsAndNumbers[i];
				}
			}
			
			for (int i = 0; i < len; i+=2) {
				if(i == len - 1)
					break;
				
				last[i+2] = calc(i,last);
			}
			
			max = Math.max(last[len-1], max);
			
			return;
		} // 탈출
		
		if(!check[n*2]) {
			for (int i = n*2; i <= n*2 + 2; i++) {
				check[i] = true;
			}
			comb(n+1,cnt+1);
			for (int i = n*2; i <= n*2 + 2; i++) {
				check[i] = false;
			}
		}
		
		comb(n+1,cnt);
		
	}
	
	static int calc(int idx, int[] arr) {
		// * : 42 // + : 43 // - : 45
		switch(arr[idx+1]) {
		case 42 :
			return arr[idx] * arr[idx+2];
		case 43 :
			return arr[idx] + arr[idx+2];
		case 45 :
			return arr[idx] - arr[idx+2];
		}
		return 0;
	}
}