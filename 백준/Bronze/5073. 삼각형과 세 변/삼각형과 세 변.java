import java.util.*;
import java.io.*;
public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int[] arr;
        while(true){
            arr= Arrays.stream(bf.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .sorted()
                    .toArray();

            if(arr[0] + arr[1] + arr[2] == 0) break;
            if(arr[0] + arr[1] <= arr[2]){
                sb.append("Invalid").append('\n');
                continue;
            }
            if(arr[0]==arr[1]&&arr[1]==arr[2]){
                sb.append("Equilateral").append('\n');
                continue;
            }
            if(arr[0]<arr[1]&&arr[1]<arr[2]){
                sb.append("Scalene").append('\n');
                continue;
            }
            sb.append("Isosceles").append('\n');
        }
        System.out.println(sb);
    }
}