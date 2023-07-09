import java.util.*;
public class Main {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        int n = sc.nextInt() / 5 * 5;
        int answer = 0;
        while(n > 0) {
            int now = n;
            while(now % 5 == 0) {
                now /= 5;
                answer++;
            }
            
            n -= 5;
        }
        
        System.out.println(answer);
    }
}